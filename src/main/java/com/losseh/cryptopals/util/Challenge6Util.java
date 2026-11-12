package com.losseh.cryptopals.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class Challenge6Util {

    public byte[] flatten(List<byte[]> lineBytes) {
        int size = lineBytes.stream().map(x -> x.length).reduce(0, Integer::sum);
        byte[] result = new byte[size];
        int resultInd = 0;
        for (byte[] lineByte : lineBytes) {
            for (byte b : lineByte) {
                result[resultInd++] = b;
            }
        }
        return result;
    }

    public List<byte[]> fromBase64(Path file) {
        try (var lines = Files.lines(file)) {
            return lines.map(this::fromBase64).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] fromBase64(String s) {
        return Base64.getDecoder().decode(s);
    }
}
