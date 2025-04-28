package com.jamersc.springboot.restcrud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "user_id", length = 50, nullable = false)
    private String userId;
    @Column(name = "pw", length = 68, nullable = false)
    private Character pw;
    @Column(name = "active", nullable = false)
    private boolean active;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;
}
