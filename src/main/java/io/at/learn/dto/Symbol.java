package io.at.learn;

import lombok.Getter;

@Getter
public enum Symbol {

    X("X"),
    O("O"),
    EMPTY("");

    private final String str;

    Symbol(String str) {
        this.str = str;
    }

}
