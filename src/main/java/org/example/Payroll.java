package org.example;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
    String recordNumber;
    String payYear;
    String departmentNo;
    String departmentTitle;
    String jobClassPGrade;
    String jobTitle;
    String employmentType;
    String jobStatus;
    String mou;
    String mouTitle;
    String regularPay;
    String overtimePay;
    String allOtherPay;
    String totalPay;
    String cityRetirementContributions;
    String benefitPay;
    String gender;
    String ethnicity;
}
