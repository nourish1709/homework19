package com.cursor.library.authentication;

import lombok.Getter;

@Getter
public enum UserAuthority {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write");

    private final String authority;

    UserAuthority(String authority) {
        this.authority = authority;
    }
}
