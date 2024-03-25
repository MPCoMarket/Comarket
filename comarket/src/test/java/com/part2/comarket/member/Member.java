package com.part2.comarket.member;

class Member {

    private Long id = 1L;
    private final String email;
    private final String password;
    private final String user;

    public Member(String email, String password, String user) {
        this.email = email;
        this.password = password;
        this.user = user;
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
