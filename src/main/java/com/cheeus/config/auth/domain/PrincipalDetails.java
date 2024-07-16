//package com.cheeus.config.auth.domain;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//public record PrincipalDetails(
//        Map<String, Object> attributes,
//        String registrationId) implements OAuth2User, UserDetails {
//
//    @Override
//    public String getName() {
//        return attributes.get(registrationId).toString();
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(
////                new SimpleGrantedAuthority(member.getRole()));
//        		new SimpleGrantedAuthority("ROLE_USER"));
//    }
//    
//    public String getRegistrationId() {
//    	return registrationId;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}