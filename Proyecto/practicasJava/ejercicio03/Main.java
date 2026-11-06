public class Main {
      public static void main(String[] args) {

        System.out.println(
                LogLevels.message("[ERROR]: Invalid operation")
        );

        System.out.println(
                LogLevels.logLevel("[ERROR]: Invalid operation")
        );

        System.out.println(
                LogLevels.reformat("[INFO]: Operation completed")
        );
    }
}
