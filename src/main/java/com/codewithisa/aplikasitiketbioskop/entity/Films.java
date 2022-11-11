package com.codewithisa.aplikasitiketbioskop.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filmCode;
    private String filmName;
    private boolean sedangTayang;
}
