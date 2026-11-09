package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HexToBase64Test {

    private static final String CRYPTOPALS_HEX_VALUE = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
    private static final String CRYPTOPALS_BASE64_VALUE = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

    private final HexToBase64 testee = new HexToBase64();

    // set1/task1
    @Test
    void shouldConvertedValuesWithBothStrategiesBeEqualCase1() {
        String actual = testee.convert("afc359", HexToBase64.Strategy.MINE);

        String expected = testee.convert("afc359", HexToBase64.Strategy.JAVA);

        assertThat(actual).isEqualTo(expected);
    }

    // set1/task1
    @Test
    void shouldConvertedValuesWithBothStrategiesBeEqualCase2() {
        String actual = testee.convert("afc3", HexToBase64.Strategy.MINE);

        String expected = testee.convert("afc3", HexToBase64.Strategy.JAVA);

        assertThat(actual).isEqualTo(expected);
    }

    // set1/task1
    @Test
    void shouldConvertedValuesWithBothStrategiesBeEqualCase3() {
        String actual = testee.convert("af", HexToBase64.Strategy.MINE);

        String expected = testee.convert("af", HexToBase64.Strategy.JAVA);

        assertThat(actual).isEqualTo(expected);
    }

    // set1/task1
    @Test
    void shouldConvertCryptopalsTask1ValueWithMineStrategy() {
        String actual = testee.convert(CRYPTOPALS_HEX_VALUE, HexToBase64.Strategy.MINE);

        assertThat(actual).isEqualTo(CRYPTOPALS_BASE64_VALUE);
    }

    // set1/task1
    @Test
    void shouldConvertCryptopalsTask1ValueWithJavaStrategy() {
        String actual = testee.convert(CRYPTOPALS_HEX_VALUE, HexToBase64.Strategy.JAVA);

        assertThat(actual).isEqualTo(CRYPTOPALS_BASE64_VALUE);
    }
}