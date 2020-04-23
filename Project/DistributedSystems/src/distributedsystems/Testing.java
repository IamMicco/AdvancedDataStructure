package distributedsystems;

import java.io.IOException;
import java.util.logging.*;

public class Testing {

    private final static Logger logr = Logger.getLogger("Testing");

    public static void main(String[] args) throws SecurityException, IOException {
        
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        logr.addHandler(ch);

        FileHandler fh = new FileHandler("myLogger.log");
        // fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.FINE);
        logr.addHandler(fh);


        logr.info("My first log");
    }
}