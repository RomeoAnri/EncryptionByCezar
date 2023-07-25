package com.javarush.module.first;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderAndWriter {
    private Model model = new Model();

    public void writer(String textForWriting) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(model.getPathToWillBeWriteFile()))) {
            writer.write(textForWriting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reader(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
