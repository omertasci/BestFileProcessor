CREATE TABLE `payroll` (
  `RECORD_NBR` varchar(15) NOT NULL,
  `PAY_YEAR` varchar(4) DEFAULT NULL,
  `DEPARTMENT_NO` varchar(3) DEFAULT NULL,
  `DEPARTMENT_TITLE` varchar(100) DEFAULT NULL,
  `JOB_CLASS_PGRADE` varchar(100) DEFAULT NULL,
  `JOB_TITLE` varchar(100) DEFAULT NULL,
  `EMPLOYMENT_TYPE` varchar(50) DEFAULT NULL,
  `JOB_STATUS` varchar(20) DEFAULT NULL,
  `MOU` varchar(10) DEFAULT NULL,
  `MOU_TITLE` varchar(100) DEFAULT NULL,
  `REGULAR_PAY` varchar(50) DEFAULT NULL,
  `OVERTIME_PAY` varchar(50) DEFAULT NULL,
  `ALL_OTHER_PAY` varchar(50) DEFAULT NULL,
  `TOTAL_PAY` varchar(50) DEFAULT NULL,
  `CITY_RETIREMENT_CONTRIBUTIONS` varchar(50) DEFAULT NULL,
  `BENEFIT_PAY` varchar(50) DEFAULT NULL,
  `GENDER` varchar(20) DEFAULT NULL,
  `ETHNICITY` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci