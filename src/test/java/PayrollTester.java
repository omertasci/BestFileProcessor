import org.example.FileProcessor;
import org.example.FileProcessorCF;
import org.example.FileProcessorCF_Batched;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class PayrollTester {

    String pathName;
    @Before
    public  void init(){
        pathName = "C:\\excel_workspace\\payroll50k.txt"; // PT1M54.2538315S
        System.out.println("Reading the file " + pathName);
    }
    @Test
    public void test_For_ReadAndBulkInsert100() throws Exception {
        Instant start = Instant.now();
        FileProcessor.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));
        //The process time is PT1M54.5781329S
    }
    @Test
    public void test_For_ReadAllAndInsertWithCF() throws Exception {
        Instant start = Instant.now();
        FileProcessorCF.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));
        //The process time is PT1M55.536474S
    }
    @Test
    public void test_For_ReadAllAndInsertWithBatchedCF100() throws Exception {
        Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 100;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));
        //The process time is PT1M52.9153947S
    }
    @Test
    public void test_For_ReadAllAndInsertWithBatchedCF1000() throws Exception {
        Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 1000;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));
        //The process time is PT1M36.7966793S
    }
    @Test
    public void test_For_ReadAllAndInsertWithBatchedCF10000() throws Exception {
        Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 10000;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));
        //The process time is PT57.9630004S
    }

}
