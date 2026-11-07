package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BinaryConverter {

    public String convertToBinary(byte value) {
        return String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    public String convertToBinary(int value) {
        return convertToBinary(value, false);
    }

    public String convertToBinary(int value, boolean spaceBetweenBytes) {
        String v = String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
        if (!spaceBetweenBytes) {
            return v;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < v.length(); i++) {
            sb.append(v.charAt(i));
            if ((i + 1) % 8 == 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
