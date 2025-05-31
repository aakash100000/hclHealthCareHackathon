package org.hcl.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING
    )
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserType type;
}
