package com.example.lab2java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    /**
     * all data from the file will be converted to a string and written to this variable
     */
    String dataFromFile;
    /**
     * HashMap, where the number of each letter in the file will be recorded
     */
    Map<Character, Integer> mapWithTheNumberOfSymbols = new LinkedHashMap<>();

    /**
     * a function that tries to open a file, read all the data and write it to a variable dataFromFile
     * @param fileName name of file or path
     * @throws FileNotFoundException if no such file
     * @throws IOException if something is wrong with file opening and reading
     */
    Parser(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                builder.append(newLine).append("\n");
            }
            dataFromFile = builder.toString();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("There is no file with such name!");
        }
        catch (IOException e) {
            throw new RuntimeException("Something is not good...");
        }
    }

    /**
     * we check the symbol whether it is an English letter or not
     * @param symbol
     * @return true or false
     */
    public boolean isEnglishLetter(char symbol) {
        return Character.isLetter(symbol) && (int) symbol <= 122;
    }

    /**
     * a function that runs through a string and counts the number of occurrences of each letter in it
     */
    public void stringHandler() {
        int lengthOfFile = dataFromFile.length();
        for (int x = 0; x < lengthOfFile; x++) {
            char c = dataFromFile.charAt(x);
            if (!isEnglishLetter(c))
                continue;
            mapWithTheNumberOfSymbols.put(c, mapWithTheNumberOfSymbols.getOrDefault(c, 0) + 1);
        }
    }

    /**
     * a function that opens a new file or creates it if there is no file with that name, and writes the results of the calculation there.
     * @param filename the name of new file
     * @throws IOException if there are some problems with file
     */
    public void writeInFile(String filename) throws IOException {
        Path filePath = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (var entry : mapWithTheNumberOfSymbols.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Something is not good...");
        }
    }
}
