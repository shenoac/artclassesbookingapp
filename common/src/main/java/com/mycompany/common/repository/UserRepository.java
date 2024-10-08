package com.mycompany.common.repository;

import com.mycompany.common.dto.UserBasicDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped  // Ensures that the interface can be injected
public interface UserRepository {
    UserBasicDTO findUserByEmail(String email);
}
