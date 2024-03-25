package com.part2.comarket.common.config;

import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.application.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }
}
