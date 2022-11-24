package com.codewithisa.aplikasitiketbioskop.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FilmRequest {
    @NotBlank
    @Size(max = 20)
    private String filmName;

    @NotBlank
    private Boolean sedangTayang;

    private Character studioName;

    private String tanggalTayang;

    private String jamMulai;

    private String jamSelesai;

    private Integer hargaTiket;
}
