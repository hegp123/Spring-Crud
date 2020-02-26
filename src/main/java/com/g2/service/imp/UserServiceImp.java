package com.g2.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g2.entity.UserRole;
import com.g2.repository.IUserRepository;

@Service("userService")
public class UserServiceImp implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.g2.entity.User user = userRepository.findByUserName(userName);
        List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
        return buildUser(user, authorities);
    }

    /**
     * estos tres campos: accountNonExpired, credentialsNonExpired, accountNonLocked
     * pudimos haberlos colocado tambien en nuestra tabla user, pero como no lo
     * tiene le colocamos true a los tres
     * 
     * @param user
     * @param authorities
     * @return
     */
    private User buildUser(com.g2.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> auths = new HashSet<>();

        userRoles.forEach(userRole -> {
            auths.add(new SimpleGrantedAuthority(userRole.getRole()));
        });

        return new ArrayList<GrantedAuthority>(auths);
    }

}
