package com.losseh.cryptopals.set1.challenge3;

import com.losseh.cryptopals.set1.model.ScoredSingleKeyDecryptionResult;
import com.losseh.cryptopals.util.Xor;
import lombok.RequiredArgsConstructor;
import one.util.streamex.EntryStream;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SingleByteXorCipher {

    private static final HexFormat HEX = HexFormat.of();
    private static final Set<Character> CHARACTERS = getEnglishLangCommonChars();

    private final Xor xor;

    public String decrypt(String cipherText) {
        return decryptWithDetails(cipherText).decipheredText();
    }

    public ScoredSingleKeyDecryptionResult decryptWithDetails(String cipherText) {
        return decryptWithDetails(HEX.parseHex(cipherText));
    }

    public ScoredSingleKeyDecryptionResult decryptWithDetails(byte[] cipherTextBytes) {
        Map<Byte, DecryptionResultWithCharFrequency> candidateMap = computeCandidateMap(cipherTextBytes);

        Map<Byte, Integer> scoreMap = EntryStream.of(candidateMap)
                .mapValues(DecryptionResultWithCharFrequency::characterFrequency)
                .mapValues(this::score)
                .toMap();

        List<ScoredSingleKeyDecryptionResult> decryptionResults = EntryStream.of(scoreMap)
                .sortedBy(Map.Entry::getValue)
                .mapKeyValue((key, score) ->
                        new ScoredSingleKeyDecryptionResult(key, candidateMap.get(key).decryptedText(), score))
                .toList();

        return decryptionResults.get(decryptionResults.size() - 1);
    }

    private Map<Byte, DecryptionResultWithCharFrequency> computeCandidateMap(byte[] cipherTextBytes) {
        Map<Byte, DecryptionResultWithCharFrequency> candidateMap = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            byte key = (byte) i;
            byte[] decryptedCandidateBytes = xor.xorRepeating(cipherTextBytes, new byte[] {key});
            String decryptedCandidateAscii = new String(decryptedCandidateBytes);
            Map<Character, Integer> charFrequency = getCharFrequency(decryptedCandidateAscii);
            candidateMap.put(key, new DecryptionResultWithCharFrequency(decryptedCandidateAscii, charFrequency));
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

    private static Set<Character> getEnglishLangCommonChars() {
        Set<Character> characterSet = new HashSet<>();
        characterSet.add(' ');
        for (int i = 0; i <= 'z' - 'a'; i++) {
            characterSet.add((char) ('a' + i));
        }
        return characterSet;
    }

    private record DecryptionResultWithCharFrequency(String decryptedText, Map<Character, Integer> characterFrequency) {
    }
}
