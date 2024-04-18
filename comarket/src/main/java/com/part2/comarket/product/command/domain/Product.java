package com.part2.comarket.product.command.domain;

import com.part2.comarket.product.command.dto.request.ProductPatchRequestDTO;
import com.part2.comarket.product.command.dto.request.ProductPostRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 제목
    private String title;
    // 내용
    private String content;
    // 가격
    private int price;
    // 상태
    private String status;
    // 거래 희망 장소
    private String tradingLocation;
    // 신고 당한 횟수
    private int reputation;
    // 삭제 여부
    private char isDeleted;
    // 조회수
    private int viewCnt;

    public Product(String title, int price, String content) {
        this.title = title;
        this.price = price;
        this.content = content;
    }

    public void update(ProductPatchRequestDTO requestDto) {
        Optional.ofNullable(requestDto.title()).ifPresent(title->this.title = title);
        Optional.ofNullable(requestDto.content()).ifPresent(content->this.content = content);
        Optional.ofNullable(requestDto.price())
                .filter(price -> price >=0)
                .ifPresent(price->this.price = price);
        Optional.ofNullable(requestDto.tradingLocation()).ifPresent(tradingLocation->this.tradingLocation = tradingLocation);

    }
}
