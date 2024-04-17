package com.part2.comarket.company.command.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@NoArgsConstructor
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
//    private String name;
//    private String registeredNumber;
//    private String location;
//    private String ownerName;
    private String secretKey = "KRIkVUsRgiCov+6evBHHjtLsvgktOTqOBVXK02dZVXk=";

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

//    public void update(CompanyPatchDTO request) {
//        this.name = !request.name().isBlank() ? request.name() : this.name;
//        this.registeredNumber = !request.registeredNumber().isBlank() ? request.registeredNumber() : this.registeredNumber;
//        this.location = !request.location().isBlank() ? request.location() : this.location;
//        this.ownerName = !request.ownerName().isBlank() ? request.ownerName() : this.ownerName;
//    }
}
