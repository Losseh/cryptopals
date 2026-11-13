package com.losseh.cryptopals.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.stream.Collectors;

public class Base64FileReader {

    public byte[] readAndDecode(Path file) {
        String base64Content = read(file);
        return fromBase64(base64Content);
    }

    private String read(Path file) {
        try (var lines = Files.lines(file)) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] fromBase64(String s) {
        return Base64.getDecoder().decode(s);
    }
}
