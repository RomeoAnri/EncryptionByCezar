package com.javarush.module.first;

public class EncryptingActions {
    private static Model model = new Model();
    private static ReaderAndWriter readerAndWriter = new ReaderAndWriter();

    public static String encrypt(String text, int key) {
        StringBuilder builder = new StringBuilder();
        char[] charArrayForEncryption = text.toCharArray();
        for (int i = 0; i < charArrayForEncryption.length; i++) {
            builder.append(shiftChar(charArrayForEncryption[i], key));
        }
        return builder.toString();
    }

    private static char shiftChar(char c, int key) {
        char charForEncryption = c;
        int index = model.getAlphabet().indexOf(c);    //if the model.getAlphabet().indexOf() does not contain the given character then returns -1
        if (index != -1) {
            int newIndex = (index + key + model.getLengthOfAlphabet()) % model.getLengthOfAlphabet();
            charForEncryption = model.getAlphabet().charAt((newIndex + model.getLengthOfAlphabet()) % model.getLengthOfAlphabet());
        }
        return charForEncryption;
    }

    public static String decrypt(String s, int key) {
        return encrypt(s, -key);
    }

    public static String brutForce(String imputeText) {
        int hackerKey = 0;
        while (hackerKey < Model.getLengthOfAlphabet()) {
            String decryptedText = decrypt(imputeText, hackerKey);
            if (isCorrectDecryption(decryptedText)) {
                return decryptedText;
            }
            hackerKey++;
        }
        return "Decryption failed. Key not found.";
    }

    private static boolean containsMatchedToLibraryWord(String[] strArray) {
        String[] libraryArray = readerAndWriter.reader(model.getPathToLibraryOfWords()).split(", ");
        for (int count = 0; count < strArray.length; count++) {
            for (String tested : libraryArray) {
                if (tested.equalsIgnoreCase(strArray[count])) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int countSpaces(String phrase) {
        int initCount = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                initCount += 1;
            }
        }
        return initCount;
    }

    public static boolean isCorrectDecryption(String decryptedText) {
        String[] arrayOfSeparatedWords = decryptedText.split(" ");
        return ((arrayOfSeparatedWords.length - countSpaces(decryptedText)) == 1) && containsMatchedToLibraryWord(arrayOfSeparatedWords);
    }
}
