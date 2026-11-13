package com.losseh.cryptopals.set1.challenge7;

import com.losseh.cryptopals.util.Base64FileReader;
import com.losseh.cryptopals.util.EcbEncryptionUtil;
import com.losseh.cryptopals.util.FileReader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class EcbCipherChallenge7DecryptorTest {

    private final EcbCipherChallenge7Decryptor testee = new EcbCipherChallenge7Decryptor(
            new EcbEncryptionUtil(),
            new Base64FileReader(
                    new FileReader()
            )
    );

    @Test
    void shouldDecrypt() {
        Path file = Path.of("src", "test", "resources", "challenge7", "7.txt");
        String key = "YELLOW SUBMARINE";

        String actual = testee.decrypt(file, key);

        assertThat(actual).contains("Sparkamatic, I'm hangin' tight like a fanatic");
    }
}