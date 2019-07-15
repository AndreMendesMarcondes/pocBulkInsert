package com.mineiro.bulkInsert.utils;

import com.mineiro.bulkInsert.domain.Client;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Util {

    private static long time;

    public static void startTime(){
        time = System.currentTimeMillis();
    }

    public static String  endTime(){
        long end = System.currentTimeMillis();
        float sec = (end - time) / 1000F;
        return (sec + " segundos");
    }

    public static List<Client> fileToClientList(MultipartFile mFile) throws IOException {

        List<Client> clientList = new ArrayList<>();
        BufferedReader br;
        try {

            String line;
            InputStream is = mFile.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                var clientLine = line.split(",");

                var client = new Client();
                client.setId(Long.parseLong(clientLine[0]));
                client.setName(clientLine[1]);
                client.setEmail(clientLine[2]);

                clientList.add(client);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return clientList;
    }

}
