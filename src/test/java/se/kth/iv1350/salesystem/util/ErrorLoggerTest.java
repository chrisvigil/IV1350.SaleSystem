package se.kth.iv1350.salesystem.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorLoggerTest {

    private ErrorLogger instance;
    private final String LOGNAME = "testLog.txt";
    private BufferedReader reader;
    private File file;

    @BeforeEach
    public void setUp() throws IOException {
        instance = new ErrorLogger(LOGNAME);
        reader = new BufferedReader(new FileReader(LOGNAME));
        file = new File(LOGNAME);
    }

    @AfterEach
    public void tearDown() throws IOException {
        reader.close();
        reader = null;
        instance = null;
        file.delete();
        file = null;
    }

    @Test
    public void testLogException() throws IOException {
        instance.logException(new Exception("test"));
        String actual;
        try {
            actual = reader.readLine();
            if (actual.isEmpty()) {
                fail("Did not write anything to logfile");
            }
            if (!actual.contains("test")) {
                fail("Did not write expected message to file");
            }
        } catch (NullPointerException ex) {
            fail("Log file was not created");
        }
    }

}
