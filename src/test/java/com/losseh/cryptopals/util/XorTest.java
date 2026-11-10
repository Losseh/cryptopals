package com.losseh.cryptopals.util;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

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

    //set1/challenge5
    @Test
    void shouldXorWithRepeatingKey() {
        String input = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";

        byte[] actual = testee.xorRepeating(input.getBytes(), "ICE".getBytes());

        assertThat(HexFormat.of().formatHex(actual))
                .isEqualTo("0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a2" +
                        "6226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b202831" +
                        "65286326302e27282f");
    }

    //set1/challenge5
    @Test
    void shouldXorEMailWithRepeatingKey() {
        byte[] actual = testee.xorRepeating("aszymanski90@gmail.com".getBytes(), "fUnNy".getBytes());

        assertThat(HexFormat.of().formatHex(actual))
                .isEqualTo("0726143714073b1d25105f652e2914073c02601a0938");
    }

    //set1/challenge5
    @Test
    void shouldXorEMailWithRepeatingKeyRoundTrip() {
        String mail = "aszymanski90@gmail.com";
        byte[] encrypted = testee.xorRepeating(mail.getBytes(), "fUnNy".getBytes());
        byte[] decrypted = testee.xorRepeating(encrypted, "fUnNy".getBytes());

        assertThat(new String(decrypted)).isEqualTo(mail);
    }
}