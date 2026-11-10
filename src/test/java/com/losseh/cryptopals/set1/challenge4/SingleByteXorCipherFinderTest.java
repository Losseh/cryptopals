package com.losseh.cryptopals.set1.challenge4;

import com.losseh.cryptopals.set1.challenge3.SingleByteXorCipher;
import com.losseh.cryptopals.util.Xor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class SingleByteXorCipherFinderTest {

    private final SingleByteXorCipherFinder testee = new SingleByteXorCipherFinder(
            new SingleByteXorCipher(
                    new Xor()
            )
    );

    @Test
    public void shouldFindCorrectAnswer() throws IOException {
        Path file = Path.of("src", "test", "resources", "4.txt");
        String actual = testee.findAndDecipher(file);

        assertThat(actual).isEqualTo("Now that the party is jumping\n");
    }
}