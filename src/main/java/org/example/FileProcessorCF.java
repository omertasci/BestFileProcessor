package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class FileProcessorCF {

    public static void processFile(String pathName) throws Exception {
        // Dosyayı aç
        File file = new File(pathName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Dosyayı satır satır oku
        // Dosyayı satır satır oku
        String line;
        List<Payroll> payrollList = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            payrollList.add(preparePayrollFromLine(line));
        }

        CompletableFuture<Void> allOf = CompletableFuture.runAsync(() -> {
            try {
                writePayrollsToMysql(payrollList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        try {
            assert allOf != null;
            allOf.get(); // Bekleniyor, tüm işlemler tamamlanana kadar bloklanır
            System.out.println("CF finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dosyayı kapat
        bufferedReader.close();
    }

    private static Payroll preparePayrollFromLine(String line) {
        String[] tokens = line.split(";");

        return Payroll.builder()
                .recordNumber(isValidIndex(tokens, 0) ? tokens[0] : "")
                .payYear(isValidIndex(tokens, 1) ? tokens[1] : "")
                .departmentNo(isValidIndex(tokens, 2) ? tokens[2] : "")
                .departmentTitle(isValidIndex(tokens, 3) ? tokens[3] : "")
                .jobClassPGrade(isValidIndex(tokens, 4) ? tokens[4] : "")
                .jobTitle(isValidIndex(tokens, 5) ? tokens[5] : "")
                .employmentType(isValidIndex(tokens, 6) ? tokens[6] : "")
                .jobStatus(isValidIndex(tokens, 7) ? tokens[7] : "")
                .mou(isValidIndex(tokens, 8) ? tokens[8] : "")
                .mouTitle(isValidIndex(tokens, 9) ? tokens[9] : "")
                .regularPay(isValidIndex(tokens, 10) ? tokens[10] : "")
                .overtimePay(isValidIndex(tokens, 11) ? tokens[11] : "")
                .allOtherPay(isValidIndex(tokens, 12) ? tokens[12] : "")
                .totalPay(isValidIndex(tokens, 13) ? tokens[13] : "")
                .cityRetirementContributions(isValidIndex(tokens, 14) ? tokens[14] : "")
                .benefitPay(isValidIndex(tokens, 15) ? tokens[15] : "")
                .gender(isValidIndex(tokens, 16) ? tokens[16] : "")
                .ethnicity(isValidIndex(tokens, 17) ? tokens[17] : "")
                .build();
    }

    public static boolean isValidIndex(String[] arr, int index) {
        try {
            Objects.checkIndex(index, arr.length);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private static void writePayrollsToMysql(List<Payroll> payrollList) throws Exception {
        // 100'lü gruplar halinde verileri veritabanına yaz
        String sql = "INSERT INTO fileprocessordb.payroll (RECORD_NBR, PAY_YEAR, DEPARTMENT_NO, DEPARTMENT_TITLE, JOB_CLASS_PGRADE, JOB_TITLE, EMPLOYMENT_TYPE, JOB_STATUS, MOU, MOU_TITLE, REGULAR_PAY, OVERTIME_PAY, ALL_OTHER_PAY, TOTAL_PAY, CITY_RETIREMENT_CONTRIBUTIONS, BENEFIT_PAY, GENDER, ETHNICITY) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Statement nesnesi oluştur
        PreparedStatement statement = ConnectionFactory.getConection().prepareStatement(sql);

        for (Payroll payroll : payrollList) {
            statement.setString(1, payroll.recordNumber);
            statement.setString(2, payroll.payYear);
            statement.setString(3, payroll.departmentNo);
            statement.setString(4, payroll.departmentTitle);
            statement.setString(5, payroll.jobClassPGrade);
            statement.setString(6, payroll.jobTitle);
            statement.setString(7, payroll.employmentType);
            statement.setString(8, payroll.jobStatus);
            statement.setString(9, payroll.mou);
            statement.setString(10, payroll.mouTitle);
            statement.setString(11, payroll.regularPay);
            statement.setString(12, payroll.overtimePay);
            statement.setString(13, payroll.allOtherPay);
            statement.setString(14, payroll.totalPay);
            statement.setString(15, payroll.cityRetirementContributions);
            statement.setString(16, payroll.benefitPay);
            statement.setString(17, payroll.gender);
            statement.setString(18, payroll.ethnicity);
            statement.addBatch();
        }
        statement.executeBatch();
        statement.clearBatch();
    }
}
