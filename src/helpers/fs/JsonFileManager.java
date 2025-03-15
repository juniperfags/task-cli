package helpers.fs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileManager {

    private String fileName;

    public JsonFileManager(String fileName) {

        this.fileName = fileName;

    }

    public void createFileIfNotExists() {

        File file = new File(this.fileName);

        if (!file.exists()) {

            try (FileWriter writer = new FileWriter(file)) {

                System.out.println("Creating new file...");

            } catch (IOException e) {

                System.out.println("Error during the creation of the file");

                e.printStackTrace();

            }
        } else {

            System.out.println("File is ready at workspace...");

        }
    }

    public void writeContent(String content) {

        try {

            BufferedWriter buffer = new BufferedWriter(new FileWriter(this.fileName));

            buffer.write(content);
            buffer.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }
    }

    public String readContentFile() {

        StringBuilder jsonStringifedBuilder = new StringBuilder();

        try {

            BufferedReader buffer = new BufferedReader(new FileReader(this.fileName));

            String currentLine;

            while ((currentLine = buffer.readLine()) != null)

                jsonStringifedBuilder.append(currentLine);

            buffer.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }

        return jsonStringifedBuilder.toString();

    }

}