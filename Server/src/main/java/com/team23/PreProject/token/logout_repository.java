package com.team23.PreProject.token;


import org.springframework.data.jpa.repository.JpaRepository;

public interface logout_repository extends JpaRepository<logout,Integer> {
    logout findByToken(String token);

    void deleteByToken(String s);
}
