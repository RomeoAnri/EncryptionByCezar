package com.javarush.module.first;

public class Model {
    private static final String alphabet = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ" +
            "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,\":-!? ";
    private static final int lengthOfAlphabet = 74;
    private String pathToWillBeWriteFile = "src/main/resources/cryptedText.txt";
    private String pathToWillBeReadFile = "src/main/resources/initialText.txt";
    private String pathToLibraryOfWords = "src/main/resources/libraryOfWords.txt";

    public static String getAlphabet() {
        return alphabet;
    }

    public static int getLengthOfAlphabet() {
        return lengthOfAlphabet;
    }

    public String getPathToWillBeWriteFile() {
        return pathToWillBeWriteFile;
    }

    public String getPathToWillBeReadFile() {
        return pathToWillBeReadFile;
    }

    public String getPathToLibraryOfWords() {
        return pathToLibraryOfWords;
    }
}
