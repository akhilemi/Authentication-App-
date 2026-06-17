package com.akhilprog.authapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;


import java.time.Instant;
import java.util.HashSet;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    @Column(unique = true, length = 300)
    private String email;
    @Column(name = "username", length = 500)
    private String name;
    private String password;;
    private String image;
    private boolean enable= true;
    private Instant createAt = Instant.now();
    private Instant updteAt = Instant.now();
    @Enumerated(EnumType.STRING)
    private Provider provider=Provider.LOCAL;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate(){
        Instant now = Instant.now();
        if (createAt == null) createAt = null;
        updteAt = now;
    }

    @PreUpdate
    protected  void onUpdate(){
        updteAt = Instant.now();
    }
}
