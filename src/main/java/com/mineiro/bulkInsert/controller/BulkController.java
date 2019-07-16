package com.mineiro.bulkInsert.controller;

import com.mineiro.bulkInsert.service.ClientService;
import com.mineiro.bulkInsert.utils.Util;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bulk")
public class BulkController {

    private final ClientService clientService;

    public BulkController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public String post(@RequestParam("file") MultipartFile mFile) {
        var stopWatch = new StopWatch("mineirao");

        try {
            stopWatch.start();
            if (!mFile.isEmpty()) {
                var file = Util.fileToClientList(mFile);
                clientService.bulkInsert(file);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            stopWatch.stop();
        }

        return stopWatch.getTotalTimeSeconds() + " segundos";
    }

}
