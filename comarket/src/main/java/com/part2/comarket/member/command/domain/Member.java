package com.part2.comarket.member.command.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 1L;
    private String email;
    private String password;
    private String name;

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
