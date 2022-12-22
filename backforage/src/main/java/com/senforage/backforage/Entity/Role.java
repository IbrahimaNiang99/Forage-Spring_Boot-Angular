package com.senforage.backforage.Entity;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nomRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
