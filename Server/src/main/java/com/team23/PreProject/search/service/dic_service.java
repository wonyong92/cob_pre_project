package com.team23.PreProject.search.service;

import com.team23.PreProject.search.dto.dic_search_dto;
import com.team23.PreProject.search.entity.dictionary;
import com.team23.PreProject.search.repository.dic_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class dic_service {
    @Autowired
    dic_repository dic_repository;

    public Boolean addDic(String name) {
        dictionary dic = new dictionary();
        dic.setName(name);
        dic.setScore(10);
        dictionary find = dic_repository.findByName(name);
        if(find==null)
        {
            dic_repository.save(dic);
        }
        else
        {
            find.setScore(find.getScore()+1);
            dic_repository.flush();
        }
        return true;
    }
    public Boolean addDics(String phrase) {
        List<String> tokens = this.tokenizer(phrase);
        for(String name : tokens)
        {
            dictionary dic = new dictionary();
            dic.setName(name);
            dic.setScore(10);
            dictionary find = dic_repository.findByName(name);
            if(find==null)
            {
                dic_repository.save(dic);
            }
            else
            {
                find.setScore(find.getScore()+1);
                dic_repository.save(find);
            }
        }

        return true;
    }

    public List<String> tokenizer(String phrase)
    {
        List<String> tokens = Arrays.asList(phrase.split(" "));


        return tokens;
    }

    public List<Object> searchAll(String phrase)  {
        phrase = phrase.replace(" ","|");
        phrase = ""+phrase+"";
        System.out.println(phrase);
        List<Object> pis = dic_repository.search(phrase);
        List<dic_search_dto> dtos = new ArrayList<>();
//        dic_search_dto dto = new dic_search_dto();
//        for(Object obj : pis)
//        {
//            dto = obj.getClass().get;
//            System.out.println(dto.getPost_name());
//            dtos.add((dic_search_dto) obj);
//        }
        return pis;
    }
}
