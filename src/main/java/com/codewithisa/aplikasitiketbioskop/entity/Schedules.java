package com.codewithisa.aplikasitiketbioskop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;
    private String tanggalTayang;
    private String jamMulai;
    private String jamSelesai;
    private Integer hargaTiket;
    private Character studioName;
    @ManyToOne
    private Films films;
}
