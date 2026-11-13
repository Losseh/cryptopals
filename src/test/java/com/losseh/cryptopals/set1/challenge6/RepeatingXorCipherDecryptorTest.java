package com.losseh.cryptopals.set1.challenge6;

import com.losseh.cryptopals.set1.challenge3.SingleByteXorCipher;
import com.losseh.cryptopals.set1.model.DecryptionResult;
import com.losseh.cryptopals.util.Base64FileReader;
import com.losseh.cryptopals.util.FileReader;
import com.losseh.cryptopals.util.Xor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HexFormat;

class RepeatingXorCipherDecryptorTest {

    private final Xor xor = new Xor();
    private final RepeatingXorCipherDecryptor testee = new RepeatingXorCipherDecryptor(
            new Base64FileReader(
                    new FileReader()
            ),
            new SingleByteXorCipher(xor),
            xor
    );

    @Test
    void shouldDecrypt() {
        Path file = Path.of("src", "test", "resources", "challenge6", "6.txt");
        DecryptionResult actual = testee.decrypt(file, 29);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(HexFormat.of().formatHex(actual.key()))
                .as("key")
                .isEqualTo("5465726d696e61746f7220583a204272696e6720746865206e6f697365");
        softly.assertThat(actual.decryptedText())
                .as("decrypted text")
                .contains("I'm like Samson -- Samson to Delilah");
        softly.assertAll();
    }
}