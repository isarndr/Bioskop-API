package com.codewithisa.aplikasitiketbioskop.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// membuat tabel films di database

@Entity //create table films di postgres
@Data // getter setter
@NoArgsConstructor // constructor with no arguments
@AllArgsConstructor // constructor with all arguments
@Builder //untuk mempermudah set class fields
public class Films {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    @Schema(example = "1")  // untuk set example di swagger
    private Long filmCode;

    @Schema(example = "Nemo")
    private String filmName;

    @Schema(example = "true")
    private boolean sedangTayang;
}
