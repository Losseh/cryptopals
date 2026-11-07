package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HexFormat;

public class HexToBase64 {

    private static final int BASE64_BITMASK = 0x3f;
    private static final String PAD_VALUE = "=";

    public String convert(String hexValue, Strategy strategy) {
        byte[] bytes = HexFormat.of().parseHex(hexValue);
        return switch (strategy) {
            case MINE -> convertMine(bytes);
            case JAVA -> convertWithLib(bytes);
        };
    }

    public String convertWithLib(byte[] bytes) {
        byte[] base64 = Base64.getEncoder().encode(bytes);
        return new String(base64, StandardCharsets.UTF_8);
    }

    // a1 a2 a3 a4 a5 a6 a7 a8 b1 b2 b3 b4 b5 b6 b7 b8 c1 c2 c3 c4 c5 c6 c7 c8
    // ------ A1 ------- ------ B1 ------- ------ C1 ------- ------ D1 -------

    private String convertMine(byte[] bytes) {
        int index = 0;
        int size = bytes.length;
        StringBuilder sb = new StringBuilder();
        while (index < size) {
            boolean isByte2 = (index + 1) < size;
            boolean isByte3 = (index + 2) < size;

            byte byte1 = bytes[index];
            byte byte2 = isByte2 ? bytes[index + 1] : 0x00;
            byte byte3 = isByte3 ? bytes[index + 2] : 0x00;

            int a1 = (byte1 >> 2) & BASE64_BITMASK;
            int b1 = ((byte1 << 4) | (byte2 >> 4)) & BASE64_BITMASK;
            int c1 = ((byte2 << 2) | (byte3 >> 6 )) & BASE64_BITMASK;
            int d1 = byte3 & BASE64_BITMASK;

            sb.append(toBase64(a1));
            sb.append(toBase64(b1));
            sb.append(isByte2 ? toBase64(c1) : PAD_VALUE);
            sb.append(isByte3 ? toBase64(d1) : PAD_VALUE);

            index += 3;
        }
        return sb.toString();
    }

    private String toBase64(int value) {
        if (value < 0 || value > 63) {
            throw new IllegalStateException("expected value in range [0, 63], but was = " + value);
        }
        if (value < 26) {
            return Character.toString('A' + value);
        }
        if (value < 52) {
            return Character.toString('a' + value - 26);
        }
        if (value < 62) {
            return Character.toString('0' + value - 52);
        }
        if (value == 62) {
            return "+";
        }
        return "/";
    }

    public enum Strategy {
        MINE, JAVA
    }
}
