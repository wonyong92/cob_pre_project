package com.team23.PreProject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team23.PreProject.Oauth.PrincipalDetails;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.token.logout_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;



//JsonBackReference 
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            //리퀘스트에서 멤버 뽑아내기
            member member = om.readValue(request.getInputStream(), member.class);
            //해당 정보로 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
            //토큰 등록
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 10)))
                .withClaim("id", principalDetails.getMember().getId())
                .withClaim("username", principalDetails.getMember().getId())
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        response.addHeader("Authorization", "Bearer " + jwtToken);
        response.getWriter().write("{ ");
        response.getWriter().write("\"userid\" :"+"\""+principalDetails.getMember().getId()+"\"");
        response.getWriter().write(", ");
        response.getWriter().write(" ");
        response.getWriter().write("\"memberId\" :"+"\""+principalDetails.getMember().getMemberId()+"\"");
        response.getWriter().write(", ");
        response.getWriter().write("\"token\" :"+"\"Bearer "+jwtToken+"\"");
        response.getWriter().write("} ");


    }
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException, ServletException {
//        response.getWriter().write("{ ");
//        response.getWriter().write("\"msg\" :"+"\"check token\"}");
//        SecurityContextHolder.clearContext();
//
//
//
//
//    }

}