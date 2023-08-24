package org.example;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws Exception {


//        String pathName = "C:\\excel_workspace\\37000_reviews_of_thread_app.txt"; // PT0.3861865S
        String pathName = "C:\\excel_workspace\\payroll50k.txt"; // PT1M54.2538315S
        System.out.println("Reading the file " + pathName);

/*      Instant start = Instant.now();
        FileProcessor.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));// PT1M54.2538315S*/


/*      Instant start = Instant.now();
        FileProcessorCF.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));//PT1M54.4662628S*/


/*      Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 100;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));//PT1M52.7579731S (Batchsize=100)*/

/*      Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 1000;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));//PT1M36.1712256S (Batchsize=1000) */

        Instant start = Instant.now();
        FileProcessorCF_Batched.batchSize = 10000;
        FileProcessorCF_Batched.processFile( pathName);
        Instant end = Instant.now();
        System.out.println("The process time is " + Duration.between(start, end));//PT57.7568317S (Batchsize=10000)
    }
}