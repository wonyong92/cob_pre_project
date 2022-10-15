package com.team23.PreProject.tag.controller;

import com.team23.PreProject.tag.dto.tag_find;
import com.team23.PreProject.tag.dto.tag_info;
import com.team23.PreProject.tag.dto.tag_info_dto;
import com.team23.PreProject.tag.entity.tag;
import com.team23.PreProject.tag.service.tag_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class tag_controller {
    @Autowired
    tag_service tag_service;

    @GetMapping("/DBtest/tagFind")
    public ResponseEntity tagFind(@RequestParam String name)
    {
        tag tag = tag_service.findTag(name);
        if(tag == null)
            return new ResponseEntity("not exists", HttpStatus.OK);
        tag_find result = new tag_find();
        result.setName(tag.getName());
        result.setDescription(tag.getContent());
        result.setId(tag.getId());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/DBtest/tagFinds")
    public ResponseEntity tagFinds()
    {
        List<tag> tags = tag_service.findTags();
        List<tag_info> tag_infos = new ArrayList<>();
        tag_info_dto tagss = new tag_info_dto();
        tag_info tag_info ;
        for(tag tag : tags)
        {
            tag_info = new tag_info();
            tag_info.setId(tag.getId());
            tag_info.setName(tag.getName());
            //tag_info.setContent(tag.getContent());
            tag_infos.add(tag_info);
        }
        tagss.setTags(tag_infos);

        return new ResponseEntity(tagss, HttpStatus.OK);
    }


}
