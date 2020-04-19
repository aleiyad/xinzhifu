package com.sundi.springbootdemo4.config.springsecurity;

import com.alibaba.fastjson.JSON;
import com.sundi.springbootdemo4.config.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@Slf4j
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
//
//    @Autowired
//    MyAccessDecisionManager myAccessDecisionManager;




    //交给Spring去管理
    @Bean
    public PasswordEncoder passwordEncoder(){
                //用这个加密的密码
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        log.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        //.withObjectPostProcessor(getObjectPostProcessor())
        http.authorizeRequests().antMatchers("/login_p").
                permitAll().anyRequest().authenticated().
                and().formLogin().loginPage("/login_p").loginProcessingUrl("/login").
                successHandler(getAuthenticationSuccessHandler()).
                failureHandler(getAuthenticationFailureHandler()
                ).and().logout().logoutUrl("/logout").addLogoutHandler(getLogout()).
                and().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler()).
                and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable();
    }


    private AccessDeniedHandler getAccessDeniedHandler() {
        return new AccessDeniedHandler() {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(ResultEntity.ok(e.getMessage())));
        writer.flush();
        writer.close();
    }
};
    }

    private LogoutHandler getLogout() {
        return new LogoutHandler() {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSON.toJSONString(ResultEntity.ok("注销成功",authentication)));
        writer.flush();
        writer.close();
    }
};
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return new AuthenticationFailureHandler(){
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String msg="";
        if(e instanceof UsernameNotFoundException){
            msg="用户名不存在";
        }else if(e instanceof BadCredentialsException){
            msg="用户名或密码错误";
        }else if (e instanceof AccountExpiredException){
            msg="账户过期";
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(ResultEntity.error(msg)));
        writer.flush();
        writer.close();
    }

};
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(ResultEntity.ok("登录成功",authentication)));
        writer.flush();
        writer.close();
    }
};
    }

//    private ObjectPostProcessor<FilterSecurityInterceptor> getObjectPostProcessor() {
//        return new ObjectPostProcessor<FilterSecurityInterceptor>(){
//            @Override
//            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
//                o.setAccessDecisionManager(myAccessDecisionManager);
//                return o;
//            }
//        };
//    }

    //配置跨域访问资源
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");   //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");   //允许的请求方法，PSOT、GET等
        corsConfiguration.setAllowCredentials(true);
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }


}
