//package com.sundi.springbootdemo4.config.springsecurity;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Iterator;
//
///**
// * 投票器
// */
//@Component
//public class MyAccessDecisionManager implements AccessDecisionManager {
//    /**
//     * 投票  根据当前登录的用户拥有的角色  选择器传递过来的角色进行比对 然后进行访问的投票
//     * @param authentication
//     * @param o
//     * @param collection
//     * @throws AccessDeniedException
//     * @throws InsufficientAuthenticationException
//     */
//    @Override                       //当前用户的所有角色
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//        Iterator<ConfigAttribute> iterator = collection.iterator();
//        while (iterator.hasNext()){
//            ConfigAttribute next = iterator.next();
//            String roleName = next.getAttribute();
//            if (roleName.equals(RoleName.ROLE_LOGIN.getName())){
//                return;
//            }
//            if(roleName.equals(RoleName.ROLE_FX.getName())){
//                if(authentication instanceof AnonymousAuthenticationToken){
//                    throw new BadCredentialsException("尚未登录");
//                }else {
//                    return;
//                }
//            }
//            if(roleName.equals(RoleName.ROLE_NONE.getName())){
//                if(authentication instanceof AnonymousAuthenticationToken){
//                    throw new BadCredentialsException("未登录");
//                }
//                throw new AccessDeniedException("数据库里没有配置对应的url");
//            }
//
//            Iterator<? extends GrantedAuthority> iterator1 = authentication.getAuthorities().iterator();
//            while (iterator1.hasNext()){
//                GrantedAuthority next1 = iterator1.next();
//                if(next1.getAuthority().equals(roleName)){
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("您没有权限！");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return false;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
//}
