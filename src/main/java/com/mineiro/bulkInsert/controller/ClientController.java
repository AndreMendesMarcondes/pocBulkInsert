package com.mineiro.bulkInsert.controller;

import com.mineiro.bulkInsert.domain.Client;
import com.mineiro.bulkInsert.service.ClientService;
import com.mineiro.bulkInsert.utils.Util;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping()
    public String post(@RequestParam("file") MultipartFile mFile) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Util.startTime();
        if(!mFile.isEmpty())
        {
            var file = Util.fileToClientList(mFile);
            clientService.insert(file);
        }
        return Util.endTime();
    }

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }


}
