package com.losseh.cryptopals.set1.challenge7;

import com.losseh.cryptopals.util.Base64FileReader;
import com.losseh.cryptopals.util.EcbEncryptionUtil;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@RequiredArgsConstructor
public class EcbCipherChallenge7Decryptor {

    private final EcbEncryptionUtil ecbEncryptionUtil;
    private final Base64FileReader base64FileReader;

    public String decrypt(Path file, String key) {
        byte[] bytes = base64FileReader.readAndDecode(file);
        byte[] decrypted = ecbEncryptionUtil.decrypt(bytes, key.getBytes());
        return new String(decrypted);
    }
}
