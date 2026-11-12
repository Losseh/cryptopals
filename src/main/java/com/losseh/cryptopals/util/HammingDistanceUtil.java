package com.losseh.cryptopals.util;

public class HammingDistanceUtil {

    public int compute(String s1, String s2) {
        return compute(s1.getBytes(), s2.getBytes());
    }

    public int compute(byte[] bytes1, byte[] bytes2) {
        int length = bytes1.length;
        if (length != bytes2.length) {
            String message = String.format("s1.length != s2.length. s1.length = %d, s2.length = %d", length, bytes2.length);
            throw new IllegalStateException(message);
        }

        int result = 0;
        for (int i = 0; i < length; i++) {
            result += getDifferentBitsCount(bytes1[i], bytes2[i]);
        }
        return result;
    }

    private int getDifferentBitsCount(byte b1, byte b2) {
        int d = b1 ^ b2;
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result += (d & (1 << i)) > 0 ? 1 : 0;
        }
        return result;
    }
}
