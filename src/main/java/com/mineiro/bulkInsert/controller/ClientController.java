package com.mineiro.bulkInsert.controller;

import com.mineiro.bulkInsert.domain.Client;
import com.mineiro.bulkInsert.service.ClientService;
import com.mineiro.bulkInsert.utils.Util;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public String post(@RequestParam("file") MultipartFile mFile) throws IOException {
        var stopWatch = new StopWatch("client");

        try {
            stopWatch.start();
            if (!mFile.isEmpty()) {
                var file = Util.fileToClientList(mFile);
                clientService.insert(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            stopWatch.stop();
        }

        return stopWatch.getTotalTimeSeconds() + " segundos";
    }


    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }
}
