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
@IdClass(SeatsId.class)  // composite key
public class Seats {

    // di class ini tabel seats memiliki composite key yaitu studioName, nomorKursi, scheduleId.
    // hal ini digunakan untuk keperluan pemesanan tiket (semuanya harus bergabung untuk menjadi sesuatu yang unik).

    @Id
    private Character studioName;
    @Id
    private String nomorKursi;
    @Id
    private Long scheduleId;
}
