package com.team23.PreProject.search.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dic")
@Getter
@Setter
public class dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "score")
    Integer score = 10;
}
