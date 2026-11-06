public class Main {

    public static void main(String[] args) {

        System.out.println(Blackjack.parseCard("ace"));
        System.out.println(Blackjack.parseCard("queen"));

        System.out.println(
                Blackjack.isBlackjack("queen", "ace")
        );

        System.out.println(
                Blackjack.largeHand(true, 7)
        );

        System.out.println(
                Blackjack.smallHand(15, 12)
        );
    }
}