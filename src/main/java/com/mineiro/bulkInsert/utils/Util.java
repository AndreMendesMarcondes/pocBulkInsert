package com.mineiro.bulkInsert.utils;

import com.mineiro.bulkInsert.domain.Client;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

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
                client.setStreet(clientLine[3]);
                client.setAge(Integer.parseInt(clientLine[4]));

                clientList.add(client);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return clientList;
    }

}
