package com.losseh.cryptopals.set1.challenge6;

import com.losseh.cryptopals.set1.model.DecryptionResult;
import com.losseh.cryptopals.set1.challenge3.SingleByteXorCipher;
import com.losseh.cryptopals.util.Base64FileReader;
import com.losseh.cryptopals.util.Xor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.HexFormat;

@Slf4j
@RequiredArgsConstructor
public class RepeatingXorCipherDecryptor {

    private final Base64FileReader base64FileReader;
    private final SingleByteXorCipher singleByteXorCipher;
    private final Xor xor;

    public DecryptionResult decrypt(Path file, int keySize) {
        byte[] allBytes = base64FileReader.readAndDecode(file);

        byte[] key = new byte[keySize];
        for (int keyOffset = 0; keyOffset < keySize; keyOffset++) {
            byte[] keyOffsetBytes = getBytes(allBytes, keySize, keyOffset);
            key[keyOffset] = singleByteXorCipher.decryptWithDetails(keyOffsetBytes).key();
        }

        log.info("key={}", HexFormat.of().formatHex(key));

        byte[] decryptedBytes = xor.xorRepeating(allBytes, key);
        return new DecryptionResult(key, new String(decryptedBytes));
    }

    private byte[] getBytes(byte[] bytes, int keySize, int keyOffset) {
        int integralSize = bytes.length / keySize;
        boolean fitsIntoRemainder = keyOffset < (bytes.length % keySize);
        int size = integralSize + (fitsIntoRemainder ? 1 : 0);
        byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            int keyIndex = i*keySize + keyOffset;
            result[i] = bytes[keyIndex];
        }
        return result;
    }
}
