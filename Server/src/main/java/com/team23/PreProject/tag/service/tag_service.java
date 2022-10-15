package com.team23.PreProject.tag.service;

import com.team23.PreProject.tag.dto.tag_create;
import com.team23.PreProject.tag.entity.tag;
import com.team23.PreProject.tag.repository.tag_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tag_service {

    @Autowired
    tag_repository tag_repository;
    public tag createTag(tag_create dto)
    {

        tag tag = tag_repository.findByName(dto.getName());

        if(tag == null)
        {
            tag = new tag();
            tag.setContent(dto.getContent());
            tag.setName(dto.getName());
            tag.setContent(dto.getContent());

            tag_repository.save(tag);
        }

        return tag;
    }

    public tag findTag(String name)
    {
        tag tag = tag_repository.findByName(name);

        if(tag == null)
        {

        }

        return tag;
    }

    public List<tag> findTags() {
        return tag_repository.findAll();
    }
}
