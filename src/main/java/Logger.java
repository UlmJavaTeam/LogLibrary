import com.java.team.FileWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {
    /** single point of logger */
    private static Logger logger;

    /** defined log level */
    private LogLevel logLevel;

    /** FileWriter  */
    private FileWriter fileWriter;

    /** Format of date in line */
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Constructor
     * @param fileName name of file write to
     * @param logLevel log level
     **/
    private Logger (String fileName, LogLevel logLevel) throws IOException {
        this.logLevel = logLevel;
        fileWriter = new FileWriter(fileName, true);
    }

    /**
     * single instance of logger
     * @param level log level see @logLevel
     * @param fileName name of file which log information logged in.
     **/
    public static Logger getInstance (LogLevel level, String fileName) throws IOException {
        if (logger == null) logger = new Logger(fileName, level);
        return logger;
    }

    /**
     * get log text
     * @param text text
     * */
    private String getLogText (String text) {
        return String.format("%s : %s", text, LocalDate.now().format(dateTimeFormatter));
    }

    /**
     * log given text
     * @param logLevel log level
     * @param text log text
     **/
    private void log (LogLevel logLevel, String text) {
        if (logLevel.getLevelValue() < this.logLevel.getLevelValue()) {
            return;
        }
        try {
            fileWriter.write(text);
        } catch (IOException excp) {}
    }

    /**
     * log error
     * @param text log text
     * */
    public void logError(String text) {
        this.log(LogLevel.ERROR, text);
    }

    /**
     * log info
     * @param text log text
     * */
    public void logInfo(String text) {
        this.log(LogLevel.INFO, text);
    }

    /**
     * log warning
     * @param text log text
     * */
    public void logWarning(String text) {
        this.log(LogLevel.WARNING, text);
    }

    /**
     * log debug
     * @param text log text
     * */
    public void logDebug(String text) {
        this.log(LogLevel.DEBUG, text);
    }

}
