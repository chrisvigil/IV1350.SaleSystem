package se.kth.iv1350.salesystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 */
public class ErrorLogger {
    private static final String LOG_FILE_NAME = "error_log.txt";
    
    private PrintWriter logFile;
    
    public ErrorLogger() throws IOException{
        logFile = openLogFile();
    }
    
    public void logException(Exception ex) throws IOException{
        logFile = openLogFile();
        
        StringBuilder sb = new StringBuilder();
        sb.append(currentTime());
        sb.append(": Exception thrown: ");
        sb.append(ex.getMessage());
        logFile.println(sb);
        ex.printStackTrace(logFile);
        
        logFile.close();
    }
    
    private String currentTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = 
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        
        return now.format(formatter);
    }
    
    private PrintWriter openLogFile() throws IOException{
        return new PrintWriter( new FileWriter(LOG_FILE_NAME, true));
    }
}