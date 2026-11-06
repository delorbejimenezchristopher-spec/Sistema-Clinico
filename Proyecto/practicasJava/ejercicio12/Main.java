public class Main {

    public static void main(String[] args) {

        System.out.println(
                Secrets.shiftBack(0b1001, 2)
        );

        System.out.println(
                Secrets.setBits(0b0110, 0b0101)
        );

        System.out.println(
                Secrets.flipBits(0b1100, 0b0101)
        );

        System.out.println(
                Secrets.clearBits(0b0110, 0b0101)
        );
    }
}