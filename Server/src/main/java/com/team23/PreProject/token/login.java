package com.team23.PreProject.token;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LOGIN")
@Getter
@Setter
public class login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    Integer id;
    String token;
}
