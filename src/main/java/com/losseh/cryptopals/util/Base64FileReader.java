package com.losseh.cryptopals.util;

import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.Base64;

@RequiredArgsConstructor
public class Base64FileReader {

    private final FileReader fileReader;

    public byte[] readAndDecode(Path file) {
        String base64Content = fileReader.read(file);
        return fromBase64(base64Content);
    }


    private byte[] fromBase64(String s) {
        return Base64.getDecoder().decode(s);
    }
}
