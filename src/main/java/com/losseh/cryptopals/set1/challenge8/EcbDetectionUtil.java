package com.losseh.cryptopals.set1.challenge8;

import com.losseh.cryptopals.util.FileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class EcbDetectionUtil {

    private static final int BLOCK_HEX_DIGITS = 2*16;

    private final FileReader fileReader;

    public Set<Integer> getEcbLineIndexes(Path file) {
        List<String> lines = fileReader.readLines(file);
        Set<Integer> indexes = EntryStream.of(lines).filterValues(this::isEcb).keys().toSet();

        log.info("found ECB ciphertexts:");
        for (int index : indexes) {
            log.info("--- index = {}", index);

            List<String> blocks = getBlocks(lines.get(index));
            for (int i = 0; i < blocks.size(); i++) {
                log.info(" i = {}, block = {}", i, blocks.get(i));
            }
        }
        return indexes;
    }

    private boolean isEcb(String cipherText) {
        if (cipherText.length() % BLOCK_HEX_DIGITS != 0) {
            throw new IllegalStateException("expected cipherText to be of length n*16B but was not");
        }

        List<String> blocks = getBlocks(cipherText);
        Map<String, Integer> blockFrequency = StreamEx.of(blocks)
                .mapToEntry(Function.identity(), b -> 1)
                .grouping(HashMap::new, Collectors.summingInt(x -> x));

        return blockFrequency.values().stream().anyMatch(f -> f > 1);
    }

    private List<String> getBlocks(String cipherText) {
        int blocksCount = cipherText.length() / BLOCK_HEX_DIGITS;
        List<String> blocks = new ArrayList<>(blocksCount);
        for (int i = 0; i < blocksCount; i++) {
            blocks.add(cipherText.substring(i*BLOCK_HEX_DIGITS, (i+1)*BLOCK_HEX_DIGITS));
        }
        return blocks;
    }
}
