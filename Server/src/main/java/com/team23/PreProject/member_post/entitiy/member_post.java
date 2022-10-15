package com.team23.PreProject.member_post.entitiy;

//import com.fasterxml.jackson.annotation.JsonBackReference ;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.post.entity.post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class member_post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_post_id",nullable = false)
    Integer memberPostId;
    //@ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne(fetch = FetchType.EAGER)//casecade 잘못 적용되면 member까지 삭제가 진행된다
    ////@JsonBackReference ("a")
    @JoinColumn(name = "member_id")
    member member;
    //@ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne(fetch = FetchType.EAGER)
    ////@JsonBackReference ("b")
    @JoinColumn(name = "post_id")
    post post;
}
