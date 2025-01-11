package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Usuario;

public interface UserService {
    Usuario findUser(long id);

    Usuario findUserByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(long id);

    boolean existsByUsername(String username);

    public Usuario updateUserEmail(String username, String email);

}
