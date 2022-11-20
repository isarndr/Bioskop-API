package com.codewithisa.aplikasitiketbioskop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatsId implements Serializable {

    private Character studioName;

    private String nomorKursi;

    private Long scheduleId;
}
