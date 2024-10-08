package com.mycompany.common.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;


@Entity
@Table(name = "ecommerceapp_user")  // Same table as in the user service
public class UserBasic extends PanacheEntity {

    @Column(unique = true)
    public String email;

    // No need to include other fields like password or role, just the basic info
    public UserBasic() {
        // Default constructor required by JPA
    }

    public UserBasic(String email) {
        this.email = email;
    }
}

