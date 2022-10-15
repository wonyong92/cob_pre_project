package com.team23.PreProject.search.repository;

import com.team23.PreProject.search.entity.dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface dic_repository extends JpaRepository<dictionary, Integer> {
    dictionary findByName(String name);


    //@Query("SELECT p.post_id FROM post p WHERE p.post_name REGEXP :phrase ")
    @Query(value = "SELECT p.post_id, p.post_name\n" +
            "FROM post as p\n" +
            "WHERE p.post_name REGEXP ?1 ;",nativeQuery = true)//native query only use order parameter
    List<Object> search(String phrase);
}
