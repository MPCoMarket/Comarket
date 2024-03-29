package com.part2.comarket.company.command.domain;


import com.part2.comarket.company.command.value.SecretKey;
import com.part2.comarket.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@NoArgsConstructor
@Getter
public class Company extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String registeredNumber;
    private String location;
    private String ownerName;
    @Embedded
    private SecretKey secretKey;

    public Company(String name, String registeredNumber, String location, String ownerName) {
        this.name = name;
        this.registeredNumber = registeredNumber;
        this.location = location;
        this.ownerName = ownerName;
        this.secretKey = SecretKey.generateKey();
    }
}
