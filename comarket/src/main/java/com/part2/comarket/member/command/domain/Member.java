package com.part2.comarket.member.command.domain;

import com.part2.comarket.company.command.domain.Company;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyId")
    private Company company;

    public Member(String email, String phoneNumber, String password, String userName) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
    }

    public Member() {}


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
