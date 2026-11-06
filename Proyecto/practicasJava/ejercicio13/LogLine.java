
public class LogLine {

    private String logLine;

    public LogLine(String logLine) {
        this.logLine = logLine;
    }

    public LogLevel getLogLevel() {
        String level = logLine.substring(1, 4);

        switch (level) {
            case "TRC":
                return LogLevel.TRACE;
            case "DBG":
                return LogLevel.DEBUG;
            case "INF":
                return LogLevel.INFO;
            case "WRN":
                return LogLevel.WARNING;
            case "ERR":
                return LogLevel.ERROR;
            case "FTL":
                return LogLevel.FATAL;
            default:
                return LogLevel.UNKNOWN;
        }
    }

    public String getOutputForShortLog() {
        String message = logLine.substring(logLine.indexOf("]:") + 2);

        int encodedLevel;

        switch (getLogLevel()) {
            case TRACE:
                encodedLevel = 1;
                break;
            case DEBUG:
                encodedLevel = 2;
                break;
            case INFO:
                encodedLevel = 4;
                break;
            case WARNING:
                encodedLevel = 5;
                break;
            case ERROR:
                encodedLevel = 6;
                break;
            case FATAL:
                encodedLevel = 42;
                break;
            default:
                encodedLevel = 0;
        }

        return encodedLevel + ":" + message;
    }
}