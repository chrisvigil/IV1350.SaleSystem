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
    private final String logFileName;
    
    private PrintWriter logFile;
    
    public ErrorLogger() throws IOException{
        this.logFileName  = "error_log.txt";
        logFile = openLogFile();
    }
    
    public ErrorLogger(String logFileName) throws IOException{
        this.logFileName  = logFileName;
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
        return new PrintWriter( new FileWriter(logFileName, true));
    }
}