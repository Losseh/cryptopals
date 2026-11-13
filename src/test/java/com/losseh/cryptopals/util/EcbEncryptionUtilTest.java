package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;

class EcbEncryptionUtilTest {

    private final EcbEncryptionUtil testee = new EcbEncryptionUtil();

    @Test
    void shouldEncryptWithECB() {
        byte[] actual = testee.encrypt("A".repeat(48).getBytes(), "abcdEFGHijklMNOP".getBytes());

        String actualHex = HexFormat.of().formatHex(actual);
        assertThat(actualHex.substring(0, 32)).isEqualTo("639ba865512311a2e6a60fac6c0efcf5");
        assertThat(actualHex.substring(32, 2*32)).isEqualTo("639ba865512311a2e6a60fac6c0efcf5");
        assertThat(actualHex.substring(2*32, 3*32)).isEqualTo("639ba865512311a2e6a60fac6c0efcf5");
        assertThat(actualHex.substring(3*32, 4*32)).isEqualTo("7476534a5f05c2f802da738aec3978cf");
    }
}