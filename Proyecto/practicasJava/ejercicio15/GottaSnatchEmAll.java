import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GottaSnatchEmAll {

    public static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    public static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    public static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        for (String card : myCollection) {
            if (!theirCollection.contains(card)) {
                for (String theirCard : theirCollection) {
                    if (!myCollection.contains(theirCard)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> common = new HashSet<>(collections.get(0));

        for (Set<String> collection : collections) {
            common.retainAll(collection);
        }

        return common;
    }

    public static Set<String> allCards(List<Set<String>> collections) {
        Set<String> all = new HashSet<>();

        for (Set<String> collection : collections) {
            all.addAll(collection);
        }

        return all;
    }
}