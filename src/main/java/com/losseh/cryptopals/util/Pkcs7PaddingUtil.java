package com.losseh.cryptopals.util;

import java.util.Arrays;

public class Pkcs7PaddingUtil {

    public byte[] pad(byte[] bytes, int length) {
        if (bytes.length > length) {
            String message = String.format("bytes.length=%d > length=%d", bytes.length, length);
            throw new IllegalStateException(message);
        }

        byte[] result = new byte[length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);

        byte padByte = (byte) (length - bytes.length);
        Arrays.fill(result, bytes.length, length, padByte);

        return result;
    }
}
