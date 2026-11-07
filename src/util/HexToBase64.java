package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HexFormat;

public class HexToBase64 {

    public String hexToBase64(String hexValue) {
        byte[] bytes = HexFormat.of().parseHex(hexValue);
//        for (byte b: bytes) {
//            System.out.println(convertToBinary(b));
//        }

        byte[] base64 = Base64.getEncoder().encode(bytes);
        return new String(base64, StandardCharsets.UTF_8);
    }

    private String convertToBinary(byte value) {
        return String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
