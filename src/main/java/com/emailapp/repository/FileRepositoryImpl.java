package com.emailapp.repository;

import com.emailapp.domain.FileEntity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileRepositoryImpl implements FileRepository {

    private static final String FILE_EXTENSION = ".csv";
    private static final String HEADER = "Message Id,Subject,Message content,From,To,Date of submission";

    @Override
    public void writeToFile(FileEntity fileEntity) {

        String filePath = fileEntity.getBaseDirectory() +
                DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()) +
                FILE_EXTENSION;

        File messagesFile = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(messagesFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            if (messagesFile.length() == 0) {
                printWriter.println(HEADER);
            }

            printWriter.println(fileEntity.getContent());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
