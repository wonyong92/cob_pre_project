package com.team23.PreProject.post_tag.entity;

import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.tag.entity.tag;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "post_tag")
@Getter
@Setter
public class post_tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_TAG_ID")
    Integer post_tag_id;


    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    com.team23.PreProject.tag.entity.tag tag;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    com.team23.PreProject.post.entity.post post;

}
