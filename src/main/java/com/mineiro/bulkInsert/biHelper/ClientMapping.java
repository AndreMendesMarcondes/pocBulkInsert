package com.mineiro.bulkInsert.biHelper;


import com.mineiro.bulkInsert.domain.Client;
import de.bytefish.pgbulkinsert.mapping.AbstractMapping;

public class ClientMapping extends AbstractMapping<Client> {

    public ClientMapping() {
        super("public","client");

        mapLong("id",Client::getId);
        mapText("name", Client::getName);
        mapText("email", Client::getEmail);
        mapText("street", Client::getStreet);
        mapInteger("age", Client::getAge);
    }
}
