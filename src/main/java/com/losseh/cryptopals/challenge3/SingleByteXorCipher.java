package com.losseh.cryptopals.challenge3;

import com.losseh.cryptopals.util.Xor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import one.util.streamex.EntryStream;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SingleByteXorCipher {

    private static final HexFormat HEX = HexFormat.of();
    private static final Set<Character> CHARACTERS = getEnglishLangCommonChars();

    private final Xor xor;

    public String decrypt(String cipherText) {
        byte[] cipherTextBytes = HEX.parseHex(cipherText);
        int size = cipherTextBytes.length;

        Map<String, DecipherResult> candidateMap = computeCandidateMap(cipherTextBytes, size);

        Map<String, Integer> scoreMap = EntryStream.of(candidateMap)
                .mapValues(DecipherResult::getCharacterFrequency)
                .mapValues(this::score)
                .toMap();

        List<ScoredDecipherResult> scoredDecipherResults = EntryStream.of(scoreMap)
                .sortedBy(Map.Entry::getValue)
                .mapKeyValue((key, score) -> new ScoredDecipherResult(key, candidateMap.get(key).getDecipheredText(), score))
                .toList();

        return scoredDecipherResults.get(scoredDecipherResults.size() - 1).getDecipheredText();
    }

    private Map<String, DecipherResult> computeCandidateMap(byte[] cipherTextBytes, int size) {
        Map<String, DecipherResult> candidateMap = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            byte key = (byte) i;
            byte[] decryptedCandidateBytes = xor.xor(cipherTextBytes, repeat(key, size));
            String decryptedCandidateAscii = new String(decryptedCandidateBytes);
            Map<Character, Integer> charFrequency = getCharFrequency(decryptedCandidateAscii);
            candidateMap.put(HEX.toHexDigits(key), new DecipherResult(decryptedCandidateAscii, charFrequency));
        }
        return candidateMap;
    }

    private Integer score(Map<Character, Integer> characterFrequency) {
        return EntryStream.of(characterFrequency)
                .filterKeys(CHARACTERS::contains)
                .values()
                .reduce(Integer::sum)
                .orElse(0);
    }

    public Map<Character, Integer> getCharFrequency(String text) {
        return text.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(k -> k, k -> 1, Integer::sum));
    }

    private byte[] repeat(byte b, int n) {
        if (n < 1) {
            throw new IllegalStateException("expected n to be bigger than 0 but was " + n);
        }

        byte[] result = new byte[n];
        Arrays.fill(result, b);
        return result;
    }

    private static Set<Character> getEnglishLangCommonChars() {
        Set<Character> characterSet = new HashSet<>();
        characterSet.add(' ');
        for (int i = 0; i <= 'z' - 'a'; i++) {
            characterSet.add((char) ('a' + i));
        }
        return characterSet;
    }

    @Value
    private static class DecipherResult {
        String decipheredText;
        Map<Character, Integer> characterFrequency;
    }

    @Value
    private static class ScoredDecipherResult {
        String key;
        String decipheredText;
        int score;
    }
}
