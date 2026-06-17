package com.akhilprog.authapp.dtos;

import com.akhilprog.authapp.entity.Provider;
import com.akhilprog.authapp.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private UUID id;
    private String email;
    private String name;
    private String password;;
    private String image;
    private boolean enable= true;
    private Instant createAt = Instant.now();
    private Instant updteAt = Instant.now();
    private Provider provider=Provider.LOCAL;
    private Set<RoleDto > roles = new HashSet<>();
}
