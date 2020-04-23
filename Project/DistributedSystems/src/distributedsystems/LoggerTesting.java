package distributedsystems;

import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class LoggerTesting {

    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static void setupLogger () {

        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        logr.addHandler(ch);

        try {
            
            FileHandler fh = new FileHandler("myLogger.log", true);
            fh.setFormatter(new SimpleFormatter() {

                private static final String format = "[%1$tF %1$tT] [%2$-4s] %3$s %4$d %n";

                @Override
                public synchronized String format(LogRecord lr) {

                    return String.format(format, 
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage(),
                            1289);
                }
            });
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (IOException e) {
            
            logr.log(Level.SEVERE, "File logger not working.", e);
        }

        logr.info("My first log");
    }
    public static void main(String[] args) {
        
        setupLogger();
    }
}