package com.javarush.module.first;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {
    private Model model = new Model();

    public void showMenu() {

        ReaderAndWriter readerWriter = new ReaderAndWriter();
        Scanner scanner = new Scanner(System.in);
        String relativePathToTheFile = "";
        boolean isFileExists = false;
        do {
            System.out.println("Введіть назву текстового файлу (формат : папка/папка/папка/назва_файлу.розирення) :");
            String testedFilePath = scanner.nextLine();
            try {
                if (Files.exists(Paths.get(testedFilePath))) {
                    isFileExists = true;
                    relativePathToTheFile = testedFilePath;
                } else {
                    System.out.println("Файл не знайдено. Спробуйте ввести заново :");
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (!isFileExists);
        String originalMessage = readerWriter.reader(relativePathToTheFile);
        System.out.println(originalMessage);
        int key;
        while (true) {
            System.out.println("Спробуйте ввести ключ-шифрувальник (ціле додатнє число) :");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key > 0) {
                    break;
                }
            } else scanner.next();
        }
        System.out.println("Щойно зашифрувалися наступні данні :");
        String codedMessage = EncryptingActions.encrypt(originalMessage, key);
        System.out.println(codedMessage);
        readerWriter.writer(codedMessage);
        System.out.println("Данні записані на флєш...");
        boolean answerForDecrypyionIsYesOrNo = false;
        boolean answerIfWrongDecryptionIsYesOrNo = false;
        do {
            System.out.println("Якщо вам необхідно зламати зашифровані данні... \nНапишіть YES чи NO");
            String userInput = scanner.next();
            if (userInput.equalsIgnoreCase("YES") || userInput.equalsIgnoreCase("NO")) {
                answerForDecrypyionIsYesOrNo = true;
            }
            if (userInput.equalsIgnoreCase("YES")) {
                System.out.println("Розшифровка є :" + "\n");
                System.out.println(EncryptingActions.brutForce(codedMessage));
            } else if (userInput.equalsIgnoreCase("NO")) {
                int newKey;
                while (true) {
                    String decodedMessage;
                    System.out.println("Введіть чисельний ключ :");
                    if (scanner.hasNextInt()) {
                        newKey = scanner.nextInt();
                        if (newKey < 0) {
                            scanner.nextLine();
                        } else if (newKey > 0) {
                            decodedMessage = EncryptingActions.decrypt(codedMessage, newKey);
                            if (EncryptingActions.isCorrectDecryption(decodedMessage)) {
                                System.out.println("Вітаю! Данні успішно разшифровані.");
                                System.out.println("Розшифровка є :\n");
                                System.out.println(decodedMessage);
                                System.out.println("\n" + "😉👍👍👍👍👍👍👍👍👍👍👍👍👍👍😉");
                                break;
                            } else {
                                System.out.println(decodedMessage);
                                System.out.println("Чисельний ключ не є коректним");
                                do {
                                    {
                                        System.out.println("Бажаєте спробувати ще? ... \nНапишіть YES чи NO");
                                        String userAnswer = scanner.next();
                                        if (userAnswer.equalsIgnoreCase("NO") || userAnswer.equalsIgnoreCase("YES")) {
                                            answerIfWrongDecryptionIsYesOrNo = true;
                                        }
                                        if (userAnswer.equalsIgnoreCase("NO")) {
                                            break;
                                        } else if (userAnswer.equalsIgnoreCase("YES")) {
                                            System.out.println("Continuing...");
                                        }
                                    }
                                } while (!answerIfWrongDecryptionIsYesOrNo);
                            }
                        }
                    } else {
                        scanner.next();
                    }
                }
            }
        } while (!answerForDecrypyionIsYesOrNo);

    }
}
