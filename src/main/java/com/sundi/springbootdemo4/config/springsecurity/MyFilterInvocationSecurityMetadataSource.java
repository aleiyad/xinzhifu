//package com.sundi.springbootdemo4.config.springsecurity;
//
//import com.sundi.springbootdemo4.entity.Menu;
//import com.sundi.springbootdemo4.service.IMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 选择器  根据url选择角色 是否放行
// */
//@Component
//public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//    @Autowired
//    IMenuService menuService;
//
//    AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    List<String> urlList = Arrays.asList("/login_p","/login","/logout");
//
//
//    List<String> fxUrlList = Arrays.asList("/menu/getMenuListByUserName");
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//
//        //获取请求的url
//        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        if(urlList.contains(requestUrl)){
//             return SecurityConfig.createList(RoleName.ROLE_LOGIN.getName());
//        }
//        if(fxUrlList.contains(requestUrl)){
//            return SecurityConfig.createList(RoleName.ROLE_FX.getName());
//        }
//
//        List<Menu> menuAll = menuService.getMenuAll();
//
//        for(Menu menu:menuAll){
//            if(antPathMatcher.match(menu.getUrl(),requestUrl) && menu.getRoles().size()>0){
//                String[] roleNames= new String[menu.getRoles().size()];
//                for (int i=0;i<menu.getRoles().size();i++){
//                    roleNames[i]=menu.getRoles().get(i).getName();
//                }
//                SecurityConfig.createList(roleNames);
//            }
//        }
//
//        return SecurityConfig.createList(RoleName.ROLE_NONE.getName());
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
//}
