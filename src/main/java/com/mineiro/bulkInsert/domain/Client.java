package com.mineiro.bulkInsert.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    private Long id;

    private String name;

    private String email;
}
