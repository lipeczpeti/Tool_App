package hu.unideb.inf.eszkozrest.Service;

import hu.unideb.inf.eszkozrest.Entity.AppUserDetails;
import hu.unideb.inf.eszkozrest.Entity.UserEntity;
import hu.unideb.inf.eszkozrest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MysqlUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(userEntity.get().getUsername(), userEntity.get().getPassword(), List.of(new SimpleGrantedAuthority(userEntity.get().getRole())));
    }
}

