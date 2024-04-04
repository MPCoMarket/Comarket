package com.part2.comarket.common.app;

import com.part2.comarket.company.infra.company.CrawlingSearchCompany;
import com.part2.comarket.company.query.application.SearchCompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SearchCompanyService searchCompanyService() {
        return new CrawlingSearchCompany();
    }
}
