package com.losseh.cryptopals.set1.challenge3;

import com.losseh.cryptopals.util.Xor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingleByteXorCipherTest {

    private static final String CIPHER_TEXT = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";

    private final SingleByteXorCipher singleByteXorCipher = new SingleByteXorCipher(new Xor());

    //set1/challenge3
    @Test
    void decrypt() {
        String actual = singleByteXorCipher.decrypt(CIPHER_TEXT);

        assertThat(actual).isEqualTo("Cooking MC's like a pound of bacon");
    }
}