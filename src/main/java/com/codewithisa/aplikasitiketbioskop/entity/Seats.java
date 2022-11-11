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
@IdClass(SeatsId.class)
public class Seats {
    @Id
    private Character studioName;
    @Id
    private String nomorKursi;
    @Id
    @ManyToOne
    private Schedules schedule;
}
