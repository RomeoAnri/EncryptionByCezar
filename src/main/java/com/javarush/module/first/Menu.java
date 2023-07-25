package com.javarush.module.first;

import java.util.Scanner;

public class Menu {
    private Model model = new Model();

    public void showMenu() {

        System.out.println("Данні починають зчитуватися з таємного архіву:");
        ReaderAndWriter readerWriter = new ReaderAndWriter();
        System.out.println(readerWriter.reader(model.getPathToWillBeReadFile()));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Негайно введіть ключ-шифрувальник (тільки невід'ємне число) :");
        int key = scanner.nextInt();
        if (key < 0) {
            System.out.println("Фатальна помилка (від'ємне число)");
            System.exit(1);
        }
        System.out.println("Щойно зашифрувалися наступні данні :");
        String codingMessage = EncryptingActions.encrypt(readerWriter.reader(model.getPathToWillBeReadFile()), key);
        System.out.println(codingMessage);
        readerWriter.writer(codingMessage);
        System.out.println("Данні записані на флєш...");
        System.out.println("Якщо вам необхідно зламати зашифровані данні... \nНапишіть YES чи NO");
        Scanner scanner1 = new Scanner(System.in);
        String userInput = scanner1.nextLine();
        if (userInput.equalsIgnoreCase("YES")) {
            System.out.println("Розшифровка є :" + "\n");
            System.out.println(EncryptingActions.brutForce(EncryptingActions
                    .encrypt(readerWriter.reader(model.getPathToWillBeReadFile()), key)));
        } else if (userInput.equalsIgnoreCase("NO")) {
            System.out.println("Введіть чисельний ключ :");
            int newKey = scanner.nextInt();
            if (EncryptingActions.decrypt(EncryptingActions.encrypt(readerWriter.reader(model.getPathToWillBeReadFile()), key), newKey)
                    .equalsIgnoreCase(readerWriter.reader(model.getPathToWillBeReadFile()))) {
                System.out.println("Вітаю! Данні успішно разшифровані.");
                System.out.println("Розшифровка є :" + "\n");
                System.out.println(EncryptingActions.decrypt(EncryptingActions.encrypt(readerWriter.reader(model.getPathToWillBeReadFile()), key), newKey));
                System.out.println("\n" + "😉👍👍👍👍👍👍👍👍👍👍👍👍👍👍😉");
            } else {
                System.out.println(EncryptingActions.decrypt(EncryptingActions.encrypt(readerWriter.reader(model.getPathToWillBeReadFile()), key), newKey));
                System.out.println("Чисельний ключ не є коректним");
            }
        }
    }
}
