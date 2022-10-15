package com.team23.PreProject.Oauth;


import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final member_repository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        member memberEntity = memberRepository.findByid(username);
        return new PrincipalDetails(memberEntity);
    }
}
