package com.losseh.cryptopals.set1.challenge8;

import com.losseh.cryptopals.util.FileReader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EcbDetectionUtilTest {

    private final EcbDetectionUtil testee = new EcbDetectionUtil(
            new FileReader()
    );

    @Test
    void shouldGetEcbLineIndexes() {
        Path file = Path.of("src", "test", "resources", "challenge8", "8.txt");

        Set<Integer> actual = testee.getEcbLineIndexes(file);

        assertThat(actual).isEqualTo(Set.of(132));
    }
}