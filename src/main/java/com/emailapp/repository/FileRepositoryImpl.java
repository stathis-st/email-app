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

    @Override
    public void writeToFile(FileEntity fileEntity) {

        StringBuilder filePath = new StringBuilder();
        filePath.append(fileEntity.getBaseDirectory())
                .append(DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()))
                .append(".csv");

        StringBuilder header = new StringBuilder();
        header.append("Message Id").append(",")
                .append("Subject").append(",")
                .append("Message content").append(",")
                .append("From").append(",")
                .append("To").append(",")
                .append("Date of submission");

        File messagesFile = new File(filePath.toString());

        try (FileWriter fileWriter = new FileWriter(messagesFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            if (messagesFile.length() == 0) {
                printWriter.println(header);
            }

            printWriter.println(fileEntity.getContent());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
