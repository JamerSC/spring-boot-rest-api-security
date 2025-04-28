package com.jamersc.springboot.restcrud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "authority"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role", length = 50, nullable = false)
    private String role;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;
}
