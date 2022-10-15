package com.team23.PreProject;

import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.token.logout_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class checkMember {
    @Autowired
    member_repository member_repository;
    @Autowired
    logout_repository logout_repository;
    public boolean checkMemberMemberId(Integer memberId,String token)

    {
        try {

            if (!member_repository.findById(memberId).get().getId().equals(SecurityContextHolder.getContext().getAuthentication().getName())&&logout_repository.findByToken(token)!=null) {
                return false;
            } else
                return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public boolean checkMemberStringId(String memberId,String token)

    {
        try{
        if(!member_repository.findByid(memberId).getId().equals(SecurityContextHolder.getContext().getAuthentication().getName())&&logout_repository.findByToken(token)!=null)
        {
            return false;
        }
        else
            return true;}
        catch(Exception e)
        {
            return false;
        }
    }


}
