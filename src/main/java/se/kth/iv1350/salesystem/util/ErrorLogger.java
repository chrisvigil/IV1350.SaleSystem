package se.kth.iv1350.salesystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Logs exceptions to a file in the root directory of the application. 
 * Exceptions are logged with time, exception message and stack trace.
 */
public class ErrorLogger {
    private final String logFileName;
    private final String STANDARDLOG = "mainErrorLog.txt";
    
    private PrintWriter logFile;
    
    /**
     * Creates a logger the logs to the default log file named "mainErrorLog.txt"
     * 
     * @throws IOException If log file could not be found or created.
     */
    public ErrorLogger() throws IOException{
        this.logFileName  = STANDARDLOG;
        logFile = openLogFile();
        logFile.close();
    }
    
    /**
     * Creates a logger the logs that logs to a specified log file
     * 
     * @param logFileName The log file to log to.
     * @throws IOException If log file could not be found or created.
     */
    public ErrorLogger(String logFileName) throws IOException{
        this.logFileName  = logFileName;
        logFile = openLogFile();
        logFile.close();
    }
    
    /**
     * Logs an exception to the <code>ErrorLogger</code>'s log file.
     * @param exception The exception to log
     * @param cause Action performed when exception occurred
     * @throws IOException If log file could not be found or created.
     */
    public void logException(Exception exception) throws IOException{
        logFile = openLogFile();
        
        StringBuilder sb = new StringBuilder();
        sb.append(currentTime());
        sb.append(": ").append(exception.toString());
        logFile.println(sb);
        exception.printStackTrace(logFile);
        
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