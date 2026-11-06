public class Blackjack {

    public static int parseCard(String card) {
        switch (card) {
            case "ace":
                return 11;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            case "ten":
            case "jack":
            case "queen":
            case "king":
                return 10;
            default:
                return 0;
        }
    }

    public static boolean isBlackjack(String card1, String card2) {
        return parseCard(card1) + parseCard(card2) == 21;
    }

    public static String largeHand(boolean isBlackJack, int dealerScore) {
        if (!isBlackJack) {
            return "S";
        }

        if (dealerScore == 11 || dealerScore == 10) {
            return "S";
        }

        return "W";
    }

    public static String smallHand(int handScore, int dealerScore) {
        if (handScore <= 11) {
            return "H";
        }

        if (handScore >= 17) {
            return "S";
        }

        if (dealerScore >= 7) {
            return "H";
        }

        return "S";
    }
}
