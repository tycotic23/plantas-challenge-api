package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.dtos.UserLoginResponseDTO;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplement{
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;


    public Usuario register(Usuario user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserLoginResponseDTO verify(Usuario user) {

        Usuario finduser=userRepository.findByEmail(user.getEmail()).orElse(null);
        if(finduser==null){
            return new UserLoginResponseDTO("","");
        }
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        finduser.getUsername(),
                        user.getPassword()
                ));

        if(authentication.isAuthenticated()){
            String token=jwtService.generateToken(user.getUsername(),user.getId());
            return new UserLoginResponseDTO(finduser.getUsername(),token);
        }
        return new UserLoginResponseDTO("","");
    }
}
