package com.emailapp.individualproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Files {


    private static final String FILENAME_PART_1 = "statement_";
    private static final String FILENAME_PART_2 = ".txt";
    private static String fileName = null;
    private static PrintWriter LogFile = null;


    public void setFileName(String userName) {
        fileName = Files.FILENAME_PART_1 + userName + "_" +
                LocalDateTime.now().getDayOfMonth() + "_" +
                LocalDateTime.now().getMonthValue() + "_" +
                LocalDateTime.now().getYear() + Files.FILENAME_PART_2;
        openFile();
    }

    private boolean openFile() {
        boolean result = false;

        try {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                LogFile = new PrintWriter(new FileOutputStream(new File(fileName), true));
                result = true;
            } else {
                LogFile = new PrintWriter(fileName, "UTF-8");
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }

        return result;
    }

    public void logToFile(String s) {
        LogFile.append(s);
        //LogFile.println(s);
    }

    public void closeFile() {
        LogFile.close();
    }

}
