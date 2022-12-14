package com.codewithisa.aplikasitiketbioskop.entity;

import com.codewithisa.aplikasitiketbioskop.entity.enumeration.ERoles;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;
}

