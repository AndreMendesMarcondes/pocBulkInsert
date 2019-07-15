package com.mineiro.bulkInsert.service;

import com.mineiro.bulkInsert.domain.Client;
import com.mineiro.bulkInsert.repository.ClientRepository;
import com.mineiro.bulkInsert.utils.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public String insert(List<Client> clientsList){
        Util.startTime();
        clientRepository.saveAll(clientsList);
        return Util.endTime();
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }
}
