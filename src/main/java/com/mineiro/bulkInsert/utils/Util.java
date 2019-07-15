package com.mineiro.bulkInsert.utils;

import com.mineiro.bulkInsert.domain.Client;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
