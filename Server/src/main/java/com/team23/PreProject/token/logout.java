package com.team23.PreProject.token;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LOGOUT")
@Getter
@Setter
public class logout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Integer id;
    String token;
}
