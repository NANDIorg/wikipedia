package by.NANDI.helpers;

import io.qameta.allure.Attachment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    // Set to false if all logs are not needed
    public static Boolean log = true;
    // Set to false if debug logs are not needed
    public static Boolean logDebug = true;
    // Set to true if log buffer is needed
    public static Boolean needlogBuffer = true;

    private static String logBufferStart = "<html>\n<head></head>\n<body>\n";
    private static String logBufferEnd = "</body></html>";
    private static String logBuffer = logBufferStart;

    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    // Reset
    private static final String RESET = "\033[0m";

    // Regular Colors
    private static final String BLACK = "\033[0;30m";
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String PURPLE = "\033[0;35m";
    private static final String CYAN = "\033[0;36m";
    private static final String WHITE = "\033[0;37m";

    private static final String NOTIFICATION = "\n\t" + GREEN + "[NOTIFICATION] " + RESET;
    private static final String WARNING = YELLOW + "[WARNING] " + RESET;
    private static final String ERROR = RED + "[ERROR] " + RESET;
    private static final String INFO = BLUE + "[INFO] " + RESET;
    private static final String START = PURPLE + "[START]" + RESET;
    private static final String END = PURPLE + "[END] " + RESET;
    private static final String DEBUG = CYAN + "[DEBUG] " + RESET;

    private static final String NOTIFICATION_HTML = "<p><span style=\"color:#00FF00;\">[NOTIFICATION] </span>%s</p>\n";
    private static final String WARNING_HTML = "<p><span style=\"color:#FF8000;\">[WARNING] </span>%s</p>\n";
    private static final String ERROR_HTML = "<p><span style=\"color:#FF0000;\">[ERROR] </span>%s</p>\n";
    private static final String INFO_HTML = "<p><span style=\"color:#0000FF;\">[INFO] </span>%s</p>\n";
    private static final String END_HTML = "<p><span style=\"color:#800080;\">[END] </span>%s</p>\n";
    private static final String DEBUG_HTML = "<p><span style=\"color:#40E0D0;\">[DEBUG] </span>%s</p>\n"; // Actually turquoise

    @Attachment(value = "Log", type = "text/html")
    public static String attachLog() {
        String tmp = logBuffer + logBufferEnd;
        logBuffer = logBufferStart;
        return tmp;
    }

    public static Boolean hasLog() {
        return !(logBuffer.equals(logBufferStart));
    }

    private static void log(String message, String type, String typehtml) {
        if (log) {
            System.out.println(type + message);
            if (needlogBuffer && typehtml != null) {
                logBuffer += String.format(typehtml, xterm2html(message));
            }
        }
    }

    private static String xterm2html(String message) {
        return message.replace("\n", "</br>")
            .replace(RESET, "</span>")
            .replace(RED, "<span style=\"color:#FF0000;\">")
            .replace(GREEN, "<span style=\"color:#00FF00;\">");
    }

    private static String applyColor(String message, String color) {
        return color + message + RESET;
    }

    /**
     * Logs a notification to console, marked by green colour and tabulation
     * @param message text to log
     */
    public static void notify(String message) {
        log(message, NOTIFICATION, NOTIFICATION_HTML);
    }

    /**
     * Logs a warning to console, marked by yellow colour
     * @param message text to log
     */
    public static void warn(String message) {
        log(message, WARNING, WARNING_HTML);
    }

    /**
     * Logs an error to console, marked by red colour
     * @param message text to log
     */
    public static void error(String message) {
        log(message, ERROR, ERROR_HTML);
    }

    /**
     * Logs information to console, marked by blue colour
     * @param message text to log
     */
    public static void info(String message) {
        String time = BLUE + "[" + timeFormat.format(new Date()) + "] " + RESET;
        log(message, INFO + time, INFO_HTML);
    }

    public static void start(String message) {
        log(message, START, null);
    }

    public static void end(String message) {
        log(message, END, null);
    }

    public static void debug(String message) {
        if (logDebug) {
            String time = CYAN + "[" + timeFormat.format(new Date()) + "] " + RESET;
            log(message, DEBUG + time, DEBUG_HTML);
        }
    }

    private static String black(String message) {
        return applyColor(message, BLACK);
    }

    private static String red(String message) {
        return applyColor(message, RED);
    }

    private static String green(String message) {
        return applyColor(message, GREEN);
    }

    private static String blue(String message) {
        return applyColor(message, BLUE);
    }

    private static String yellow(String message) {
        return applyColor(message, YELLOW);
    }

    /**
     * Returns "true" or "false" in string form colored green or red accordingly
     * @param bool any boolean
     * @return same bool, but in string form and with colour applied
     */
    public static String boolColor(Boolean bool) {
        return bool ? green("" + true) : red("" + false);
    }

    /**
     * Returns "TRUE" or "FALSE" colored green or red accordingly
     * @param bool any boolean
     * @return same bool, but in string form and with colour applied
     */
    public static String boolColorUppercase(Boolean bool) {
        String strBool = ("" + bool).toUpperCase();
        if (bool) {
            return green(strBool);
        } else {
            return red(strBool);
        }
    }

    /**
     * Colours the text according to given bool
     * @param bool any boolean
     * @param text any string
     * @return same text, but red if false or green if true
     */
    public static String boolTextColor(Boolean bool, String text) {
        if (bool) {
            return green("" + text);
        } else {
            return red("" + text);
        }
    }
}