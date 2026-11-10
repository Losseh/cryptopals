package com.losseh.cryptopals.set1.challenge4;

import com.losseh.cryptopals.set1.challenge3.ScoredDecipherResult;
import com.losseh.cryptopals.set1.challenge3.SingleByteXorCipher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class SingleByteXorCipherFinder {

    private final SingleByteXorCipher singleByteXorCipher;

    public String findAndDecipher(Path file) throws IOException {
        try (var lines = Files.lines(file)) {
            List<ScoredDecipherResult> decryptResults = lines.map(singleByteXorCipher::decryptWithDetails).toList();
            Map<Integer, List<ScoredDecipherResult>> decipherResultByScore = StreamEx.of(decryptResults)
                    .groupingBy(ScoredDecipherResult::getScore);
            List<ScoredDecipherResult> scoredDecipherResults = StreamEx.of(decipherResultByScore.keySet())
                    .maxByInt(x -> x)
                    .map(decipherResultByScore::get)
                    .orElseThrow();
            if (scoredDecipherResults.size() != 1) {
                log.info("found multiple (={}), results with equal score", scoredDecipherResults.size());
                return null;
            }

            ScoredDecipherResult result = scoredDecipherResults.get(0);
            log.info("winner score = {}, key = {}, deciphered = {}", result.getScore(), result.getKey(), result.getDecipheredText());
            return result.getDecipheredText();
        }
    }
}
