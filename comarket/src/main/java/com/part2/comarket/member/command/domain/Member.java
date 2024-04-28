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
    private String userName;
    private String phoneNumber;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyId")
    private Company company;

    public Member(String email, String userName, String phoneNumber, String password) {
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Member(Long id, String userName, String email, String phoneNumber, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
