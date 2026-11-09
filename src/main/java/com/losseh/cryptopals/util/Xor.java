package com.losseh.cryptopals.util;

import org.springframework.stereotype.Component;

import java.util.HexFormat;
import java.util.stream.Stream;

@Component
public class Xor {

    public String xor(String v1, String v2) {
        byte[] bytes1 = HexFormat.of().parseHex(v1);
        byte[] bytes2 = HexFormat.of().parseHex(v2);
        byte[] resultBytes = xor(bytes1, bytes2);
        return HexFormat.of().formatHex(resultBytes);
    }

    public byte[] xor(byte[] bytes1, byte[] bytes2) {
        int size1 = bytes1.length;
        int size2 = bytes2.length;
        if (size1 != size2) {
            throw new IllegalStateException(String.format("bytes1.length(%d) != bytes2.length(%d)", size1, size2));
        }
        byte[] result = new byte[size1];
        for (int i = 0; i < size1; i++) {
            result[i] = (byte) (bytes1[i] ^ bytes2[i]);
        }
        return result;
    }
}
