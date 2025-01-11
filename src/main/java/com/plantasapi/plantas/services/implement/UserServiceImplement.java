package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.repositories.UserRepository;
import com.plantasapi.plantas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Usuario findUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user=userRepository.findByUsername(username).orElse(null);
        if(user ==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Usuario updateUserEmail(String username, String email) {
        return userRepository.findByUsername(username)
                .map(user->{
                    user.setEmail(email);
                    return userRepository.save(user);
                }).orElseGet(()->{
                    return null;
                });
    }
}
