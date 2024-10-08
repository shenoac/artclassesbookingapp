package com.mycompany.common.dto;

public class UserBasicDTO {
    private Long id;
    private String email;

    // Constructor
    public UserBasicDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
