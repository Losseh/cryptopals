package com.losseh.cryptopals.set1.model;

public record DecryptionResult(byte[] key, String decryptedText) {
}