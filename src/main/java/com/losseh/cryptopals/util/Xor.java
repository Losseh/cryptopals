package com.losseh.cryptopals.util;

import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class Xor {

    public String xor(String v1, String v2) {
        int size1 = v1.length();
        if (size1 != v2.length()) {
            throw new IllegalStateException("v1.length != v2.length");
        }

        byte[] bytes1 = HexFormat.of().parseHex(v1);
        byte[] bytes2 = HexFormat.of().parseHex(v2);

        int bytesSize = bytes1.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytesSize; i++) {
            sb.append(HexFormat.of().toHexDigits((byte) (bytes1[i] ^ bytes2[i])));
        }

        return sb.toString();
    }
}
