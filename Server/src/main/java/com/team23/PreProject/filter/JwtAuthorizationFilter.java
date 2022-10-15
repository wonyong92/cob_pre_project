package com.team23.PreProject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team23.PreProject.Oauth.PrincipalDetails;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private member_repository member_Repository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, member_repository memberRepository) {
        super(authenticationManager);
        this.member_Repository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청 됨.");

        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {

            System.out.println("잘못된 토큰.");
            chain.doFilter(request, response);

        }
        else {
            // 헤더에서 토큰 겟

            String jwtToken = jwtHeader.replace("Bearer ", "");
            String username = null;
            // 토큰 해석
                try {
                    username = JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("username").asString();
                }
                catch(Exception e)
                {
                    response.getWriter().write("{\"msg\":\"expired or invalid token, Do re-login!\"} ");
//            response.getWriter().write("\"msg\" :"+"\"check token\"}");
                }

            // 토큰에 해당하는 유저 정보로 인증 정보 생성
            try {
                if (username != null) {
                    member memberEntity = member_Repository.findByid(username);

                    PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    chain.doFilter(request, response);
                } else {
//                response.getWriter().write("{ ");
//                response.getWriter().write("\"msg\" :" + "\"check token\"}");
//                chain.doFilter(request, response);

                }
            }catch(Exception e)
            {
                response.getWriter().write("{ ");
                response.getWriter().write("\"msg\" :" + "\"check token or not exists information)\"}");
                //chain.doFilter(request, response);
            }
        }
    }
}