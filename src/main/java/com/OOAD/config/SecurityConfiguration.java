package com.OOAD.config;

import com.OOAD.controller.Code;
import com.OOAD.controller.Result;
import com.OOAD.domain.AuthorizeVO;
import com.OOAD.domain.SysUser;
import com.OOAD.filter.JwtAuthorizeFilter;
import com.OOAD.service.ISysUserService;
import com.OOAD.util.JwtUtils;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SecurityConfiguration {
    @Resource
    JwtUtils utils;
    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;
    @Resource
    ISysUserService service;
    public static Map<String, String> tokenMap = new ConcurrentHashMap<>();
    public static Map<String, String> ipTokenMap = new ConcurrentHashMap<>();
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                        .authorizeHttpRequests(conf -> conf

                        .requestMatchers("/auth/**")
                        .permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasRole("admin")
                                .requestMatchers(HttpMethod.POST, "/user").hasRole("admin")
                                .requestMatchers(HttpMethod.POST, "/user/upload").hasRole("admin")
                                .requestMatchers(HttpMethod.PUT, "/dorm").hasRole("admin")
                                .requestMatchers(HttpMethod.POST, "/dorm").hasRole("admin")
                                .requestMatchers(HttpMethod.DELETE, "/dorm/{id}").hasRole("admin")
                                .requestMatchers(HttpMethod.DELETE, "/openTime/{id}").hasRole("admin")
                                .requestMatchers(HttpMethod.PUT, "/openTime").hasRole("admin")
                                .requestMatchers(HttpMethod.POST, "/openTime").hasRole("admin")
                                .requestMatchers(HttpMethod.PUT, "/broadCast").hasRole("admin")
                                .requestMatchers(HttpMethod.POST, "/broadCast").hasRole("admin")
                                .requestMatchers(HttpMethod.DELETE, "/broadCast/{id}").hasRole("admin")
                                .anyRequest().authenticated()
                        )
                .formLogin(conf -> conf.loginProcessingUrl("/auth/login")
                        .failureHandler(this::onAuthenticationFailure)
                        .successHandler(this::onAuthenticationSuccess)
                        )
                .logout(conf -> conf.logoutUrl("/auth/logout").logoutSuccessHandler(
                        this::onLogoutSuccess
                ))
                .exceptionHandling(conf -> conf
                        .accessDeniedHandler(new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                Result result = new Result();
                                response.setHeader("Access-Control-Allow-Credentials", "true");
                                response.setContentType("application/json;charset=utf-8");
                                response.setHeader("Access-Control-Allow-Credentials", "true");
                                response.setHeader("Access-Control-Allow-Origin", "http://8.134.23.156:8080");
                                response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
                                response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");
                                result.setCode(403);
                                result.setMsg("没有权限");
                                result.setData("Err");
                                response.getWriter().write(JSON.toJSONString(result));
                            }
                        })
                        .authenticationEntryPoint(this::onAuthenticationFailure))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", "http://8.134.23.156:8080");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");
            User user = (User) authentication.getPrincipal();
        SysUser sysUser = service.findByName(user.getUsername());
        String token = utils.createJwt(user, sysUser.getId(), sysUser.getUsername());
           String ip = request.getRemoteAddr();
            System.out.println("ip:  "+ip);
            System.out.println("username: "+ user.getUsername());
            System.out.println(tokenMap.containsKey(user.getUsername()));
            System.out.println(ipTokenMap.containsKey(ip));
            if (tokenMap.containsKey(user.getUsername())) {
                utils.invalidJwt(tokenMap.get(user.getUsername()));
                tokenMap.put(user.getUsername(), "Bearer "+token);
            } else {
                tokenMap.put(user.getUsername(), "Bearer "+token);
            }
            if (ipTokenMap.containsKey(ip)) {
                utils.invalidJwt(ipTokenMap.get(ip));
                ipTokenMap.put(ip, "Bearer "+token);
            } else {
                ipTokenMap.put(ip, "Bearer "+token);
            }
            System.out.println(tokenMap);
            System.out.println(ipTokenMap);
        AuthorizeVO vo = new AuthorizeVO();
        vo.setExpire(utils.expireTime());
        vo.setRole(sysUser.getRole());
        vo.setToken(token);
        vo.setId(sysUser.getId());
        vo.setUsername(sysUser.getUsername());
        Result result = new Result();
        result.setMsg("OK");
        result.setCode(Code.LOGIN_OK);
        result.setData(vo);
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result result = new Result();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "http://8.134.23.156:8080");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");
        result.setCode(401);
        result.setMsg("登录信息错误");
        result.setData("Err");
        response.getWriter().write(JSON.toJSONString(result));
    }
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = new Result();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "http://8.134.23.156:8080");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");
        PrintWriter out = response.getWriter();
        String auth = request.getHeader("Authorization");
        if (utils.invalidJwt(auth)) {
            result.setCode(200);
            result.setMsg("登出成功");
            result.setData("OK");
            out.write(JSON.toJSONString(result));
        } else {
            result.setCode(400);
            result.setMsg("登出失败");
            result.setData("ERR");
            out.write(JSON.toJSONString(result));
        }
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://8.134.23.156:8080")); // 指定允许的域
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
