package com.losseh.cryptopals.set1.model;

public record ScoredSingleKeyDecryptionResult(byte key, String decipheredText, int score) {
}
