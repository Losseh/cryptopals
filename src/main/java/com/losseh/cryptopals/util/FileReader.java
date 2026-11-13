package com.losseh.cryptopals.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public String read(Path file) {
        try (var lines = Files.lines(file)) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readLines(Path file) {
        try (var lines = Files.lines(file)) {
            return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
