package com.part2.comarket.product.command.repository;

import com.part2.comarket.product.command.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
