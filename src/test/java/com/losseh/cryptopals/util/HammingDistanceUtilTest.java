package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HammingDistanceUtilTest {

    private final HammingDistanceUtil hammingDistanceUtil = new HammingDistanceUtil();

    @Test
    void shouldComputeHammingDistance() {
        int actual = hammingDistanceUtil.compute("this is a test", "wokka wokka!!!");

        assertThat(actual).isEqualTo(37);
    }
}