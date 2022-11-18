package com.codewithisa.aplikasitiketbioskop.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Long scheduleId;
    @Schema(example = "18 November 2022")
    private String tanggalTayang;
    @Schema(example = "20.00 WIB")
    private String jamMulai;
    @Schema(example = "22.30 WIB")
    private String jamSelesai;
    @Schema(example = "50000")
    private Integer hargaTiket;
    @Schema(example = "A")
    private Character studioName;
    @ManyToOne
    @JoinColumn(name = "film_code")
    @Schema(example = "1")
    private Films films;
}
