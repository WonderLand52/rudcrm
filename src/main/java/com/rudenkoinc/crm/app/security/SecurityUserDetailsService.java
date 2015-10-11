package com.rudenkoinc.crm.app.security;

import com.rudenkoinc.crm.app.dao.UserRepository;
import com.rudenkoinc.crm.app.model.User;
//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);


        if (user == null) {
            System.out.println("User == null!");
            String message = "Username not found" + email;
//            LOGGER.info(message);
            throw new UsernameNotFoundException(message);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        LOGGER.info("Found user in database: " + user);

        return new org.springframework.security.core.userdetails.User(email, user.getPasswordDigest(), authorities);
    }

}
