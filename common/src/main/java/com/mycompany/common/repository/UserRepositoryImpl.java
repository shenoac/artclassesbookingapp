package com.mycompany.common.repository;

import com.mycompany.common.dto.UserBasicDTO;
import com.mycompany.common.model.UserBasic;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped  // Add this annotation to make the implementation a CDI bean
public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserBasicDTO findUserByEmail(String email) {
        UserBasic user = UserBasic.find("email", email).firstResult();
        if (user != null) {
            return new UserBasicDTO(user.id, user.email);
        }
        return null;
    }
}
