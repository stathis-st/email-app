package com.emailapp.repository;

import com.emailapp.domain.FileEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileRepositoryImpl implements FileRepository {

    @Override
    public void writeToFile(FileEntity fileEntity) {

        StringBuilder filePath = new StringBuilder();
        filePath.append(fileEntity.getBaseDirectory())
                .append(DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()))
                .append(".txt");

        File file = new File(filePath.toString());

        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true));
            printWriter.println(fileEntity.getContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
