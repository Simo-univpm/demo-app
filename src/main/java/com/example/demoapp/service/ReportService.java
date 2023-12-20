package com.example.demoapp.service;

import com.example.demoapp.model.entities.UserActivityEntity;
import com.example.demoapp.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Nota per entrambi i metodi getReport e getReportVerbose:
 * se il csv non è presente ne genera uno, altrimenti mette in append il risultato della chiamata
 * sul file già esistente e specificato nei percorsi FILEPATH o FILEPATH_VERBOSE.
 *
 * inoltre si può ottimizzare, questa classe apre il file due volte: una per verificare l'esistenza
 * dell'header e una per modificare il file... si potrebbe fare tutto in un colpo solo...
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserActivityRepository userActivityRepository;
    private String FILEPATH = "C:\\Users\\simone.francalancia\\OneDrive - Accenture\\Desktop\\report.csv";
    private String FILEPATH_VERBOSE = "C:\\Users\\simone.francalancia\\OneDrive - Accenture\\Desktop\\report_verbose.csv";


    public long writeReport(Timestamp startDate, Timestamp endDate, String key){

        long result = userActivityRepository.countOperationsByKey(startDate, endDate, key);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))) {

            if( ! hasHeader(FILEPATH)){

                // scrive intestazione se non è presente
                writer.write("Start Date, End Date, Key, Result");
                writer.newLine();

            }

            // costruisce e scrive la riga nel csv
            writer.write(startDate.toString() + ", " + endDate.toString() + ", " + key + " , " + result);
            writer.newLine();

        } catch (IOException e) {
            log.error("cannot open csv file at " + FILEPATH + " !!");
        }

        log.info("Report written at: " + FILEPATH);
        return result;

    }


    public void writeReportVerbose(Timestamp startDate, Timestamp endDate, String key){

        // piglia le tuple
        List<UserActivityEntity> result = userActivityRepository.findOperationsByDateRange(startDate, endDate, key);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH_VERBOSE, true))) {

            if( ! hasHeader(FILEPATH_VERBOSE)){

                // scrive intestazione se non è presente
                writer.write("Key, ID, Username, Activity Date");
                writer.newLine();

            }

            for(int i = 0; i < result.size(); i++){

                UserActivityEntity app = result.get(i);

                writer.write(key + ", " + app.getId() + ", " + app.getUsername() + ", " + app.getActivity_date());
                writer.newLine();

            }

        } catch (IOException e) {
            log.error("cannot open csv file at " + FILEPATH_VERBOSE + " !!");
        }

        log.info("Report verbose written at: " + FILEPATH_VERBOSE);

    }


    public static boolean hasHeader(String csvFilePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String firstLine = reader.readLine();
            if (firstLine != null && !firstLine.trim().isEmpty()) {
                // Assuming the header is present if the first line is not empty
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return false;
    }

}
