package com.jamersc.springboot.restcrud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "authority"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "authority", length = 50, nullable = false)
    private String authority;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
}
