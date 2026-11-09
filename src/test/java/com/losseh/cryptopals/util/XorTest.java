package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XorTest {

    private final Xor testee = new Xor();

    // set1/task2
    @Test
    void shouldXorSimple() {
        String actual = testee.xor("03", "02");

        assertThat(actual).isEqualTo("01");
    }

    // set1/task2
    @Test
    void shouldXor() {
        String actual = testee.xor("1c0111001f010100061a024b53535009181c", "686974207468652062756c6c277320657965");

        assertThat(actual).isEqualTo("746865206b696420646f6e277420706c6179");
    }
}