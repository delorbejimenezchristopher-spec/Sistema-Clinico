public class Main {

    public static void main(String[] args) {

        LogLine info = new LogLine("[INF]: File deleted");
        LogLine error = new LogLine("[ERR]: Stack Overflow");
        LogLine unknown = new LogLine("[XYZ]: Overly specific, out of context message");

        System.out.println(info.getLogLevel());
        System.out.println(error.getLogLevel());
        System.out.println(unknown.getLogLevel());

        System.out.println(error.getOutputForShortLog());
        System.out.println(info.getOutputForShortLog());
        System.out.println(unknown.getOutputForShortLog());
    }
}