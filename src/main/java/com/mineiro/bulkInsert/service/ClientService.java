package com.mineiro.bulkInsert.service;

import com.mineiro.bulkInsert.biHelper.ClientMapping;
import com.mineiro.bulkInsert.domain.Client;
import com.mineiro.bulkInsert.repository.ClientRepository;
import de.bytefish.pgbulkinsert.PgBulkInsert;
import de.bytefish.pgbulkinsert.util.PostgreSqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final DataSource dataSource;


    public ClientService(ClientRepository clientRepository, DataSource dataSource) {
        this.clientRepository = clientRepository;
        this.dataSource = dataSource;
    }

    public void insert(List<Client> clientsList) {
        clientRepository.saveAll(clientsList);
    }

    public void bulkInsert(List<Client> clientsList) throws SQLException {
        ClientMapping clientMapping = new ClientMapping();
        PgBulkInsert<Client> bulkInsert = new PgBulkInsert<>(clientMapping);
        log.info("bulkInsert, query={}", clientMapping.getCopyCommand());
        bulkInsert.saveAll(PostgreSqlUtils.getPGConnection(dataSource.getConnection()), clientsList.stream());
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void deleteAll() {
         clientRepository.deleteAll();
    }
}
