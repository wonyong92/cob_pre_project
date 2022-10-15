package com.team23.PreProject.token;


import org.springframework.data.jpa.repository.JpaRepository;

public interface login_repository extends JpaRepository<login,Integer> {
    login findByToken(String token);

    void deleteByToken(String s);
}
