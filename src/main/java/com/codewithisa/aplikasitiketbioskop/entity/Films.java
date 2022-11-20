package com.codewithisa.aplikasitiketbioskop.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Entity mean untuk membuat table di database melalui Java
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //untuk mempermudah set class fields
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "1")  // untuk set example di swagger
    private Long filmCode;

    @Schema(example = "Nemo")
    private String filmName;

    @Schema(example = "true")
    private boolean sedangTayang;
}
