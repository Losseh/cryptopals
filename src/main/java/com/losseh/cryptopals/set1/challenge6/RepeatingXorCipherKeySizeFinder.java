package com.losseh.cryptopals.set1.challenge6;

import com.losseh.cryptopals.util.Challenge6Util;
import com.losseh.cryptopals.util.HammingDistanceUtil;
import lombok.RequiredArgsConstructor;
import one.util.streamex.EntryStream;

import java.nio.file.Path;
import java.util.*;

@RequiredArgsConstructor
public class RepeatingXorCipherKeySizeFinder {

    private static final int MIN_KEY_SIZE = 2;
    private static final int MAX_KEY_SIZE = 40;

    private final HammingDistanceUtil hammingDistanceUtil;
    private final Challenge6Util challenge6Util;

    public int findKeySize(Path file) {
        List<byte[]> lineBytes = challenge6Util.fromBase64(file);
        byte[] bytes = challenge6Util.flatten(lineBytes);
//        log.info("l={}, {}", bytes.length, HEX.formatHex(bytes));

        Map<Integer, Float> normEditDistanceByKeySize = new HashMap<>();
        for (int keySize = MIN_KEY_SIZE; keySize <= MAX_KEY_SIZE; keySize++) {
            float distance = computeNormalizedHammingDistance(bytes, keySize);
//            log.info("keySize={}, distance={}", keySize, distance);
            normEditDistanceByKeySize.put(keySize, distance);
        }

        List<Map.Entry<Integer, Float>> sortedKeyDistancePairs = EntryStream.of(normEditDistanceByKeySize)
                .sortedBy(Map.Entry::getValue)
                .toList();
        return sortedKeyDistancePairs.get(0).getKey();
    }

    private float computeNormalizedHammingDistance(byte[] bytes, int keySize) {
        int accumulator = 0;
        int count = 2000 / 2 / keySize;
        int offset = 0;
        for (int i = 0; i < count; i++) {
            byte[] bytes1 = Arrays.copyOfRange(bytes, offset, offset + keySize);
            byte[] bytes2 = Arrays.copyOfRange(bytes, offset + keySize, offset + 2*keySize);
            accumulator += hammingDistanceUtil.compute(bytes1, bytes2);
            offset += 2 * keySize;
        }
        return (float) accumulator / count / 2 / keySize;
    }
}
