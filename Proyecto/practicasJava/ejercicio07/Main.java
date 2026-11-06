public class Main {

    public static void main(String[] args) {

        System.out.println(SqueakyClean.clean("my   Id"));

        System.out.println(SqueakyClean.clean("a-bc"));

        System.out.println(SqueakyClean.clean("H3ll0 W0rld"));

        System.out.println(SqueakyClean.clean("4 73s7"));

        System.out.println(SqueakyClean.clean("a$#.b"));
    }
}