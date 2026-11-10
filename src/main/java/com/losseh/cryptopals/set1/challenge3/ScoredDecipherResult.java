package com.losseh.cryptopals.set1.challenge3;

import lombok.Value;

@Value
public class ScoredDecipherResult {
    String key;
    String decipheredText;
    int score;
}
