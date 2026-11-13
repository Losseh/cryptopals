package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;

class Pkcs7PaddingUtilTest {

    private final HexFormat HEX = HexFormat.of();

    private final Pkcs7PaddingUtil testee = new Pkcs7PaddingUtil();

    @Test
    void shouldPad() {
        String input = "YELLOW SUBMARINE";

        byte[] actual = testee.pad(input.getBytes(), 20);

        String expected = HEX.formatHex(input.getBytes()) + "04".repeat(4);
        assertThat(HEX.formatHex(actual)).isEqualTo(expected);
    }
}