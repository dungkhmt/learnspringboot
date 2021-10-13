package com.hust.baseweb.service;

import com.hust.baseweb.entity.UserLogin;
import com.hust.baseweb.repo.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasewebUserDetailService implements UserDetailsService {
    @Autowired
    private UserLoginRepo userLoginRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginRepo.findByUserLoginId(s);

        UserDetails userDetails = new User(userLogin.getUserLoginId(),userLogin.getPassword(),
                AuthorityUtils.createAuthorityList(userLogin.getGroups().stream().map(g ->
                {return g.getGroupId();}).toArray(String[]::new)));

        return userDetails;
    }
}

