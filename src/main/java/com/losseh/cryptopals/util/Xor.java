package com.losseh.cryptopals.util;

import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class Xor {

    public String xor(String text, String key) {
        byte[] textBytes = HexFormat.of().parseHex(text);
        byte[] keyBytes = HexFormat.of().parseHex(key);
        byte[] resultBytes = xor(textBytes, keyBytes);
        return HexFormat.of().formatHex(resultBytes);
    }

    public byte[] xorRepeating(byte[] textBytes, byte[] keyBytes) {
        int size1 = textBytes.length;
        int size2 = keyBytes.length;
        if (textBytes.length < keyBytes.length) {
            throw new IllegalStateException(String.format("bytes1.length(%d) < bytes2.length(%d)", size1, size2));
        }
        byte[] result = new byte[size1];
        for (int i = 0; i < size1; i++) {
            result[i] = (byte) (textBytes[i] ^ keyBytes[i % size2]);
        }
        return result;
    }

    public byte[] xor(byte[] textBytes, byte[] keyBytes) {
        int size1 = textBytes.length;
        int size2 = keyBytes.length;
        if (size1 != size2) {
            throw new IllegalStateException(String.format("textBytes.length(%d) != keyBytes.length(%d)", size1, size2));
        }
        byte[] result = new byte[size1];
        for (int i = 0; i < size1; i++) {
            result[i] = (byte) (textBytes[i] ^ keyBytes[i]);
        }
        return result;
    }
}
