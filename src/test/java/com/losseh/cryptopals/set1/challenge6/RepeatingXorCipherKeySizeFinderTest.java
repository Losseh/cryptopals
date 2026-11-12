package com.losseh.cryptopals.set1.challenge6;

import com.losseh.cryptopals.util.Challenge6Util;
import com.losseh.cryptopals.util.HammingDistanceUtil;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class RepeatingXorCipherKeySizeFinderTest {

    private final RepeatingXorCipherKeySizeFinder testee = new RepeatingXorCipherKeySizeFinder(
            new HammingDistanceUtil(),
            new Challenge6Util()
    );

    //set1/challenge6
    @Test
    void shouldFindKeySize() {
        Path file = Path.of("src", "test", "resources", "challenge6", "6.txt");
        int actual = testee.findKeySize(file);

        assertThat(actual).isEqualTo(29);
    }
}