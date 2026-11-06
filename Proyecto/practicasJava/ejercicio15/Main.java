import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<String> collection =
                GottaSnatchEmAll.newCollection(
                        Arrays.asList("Newthree", "Newthree", "Newthree")
                );

        System.out.println(collection);

        System.out.println(
                GottaSnatchEmAll.addCard("Scientuna", collection)
        );

        System.out.println(collection);

        Set<String> myCollection =
                new HashSet<>(Arrays.asList("Newthree"));

        Set<String> theirCollection =
                new HashSet<>(Arrays.asList("Scientuna"));

        System.out.println(
                GottaSnatchEmAll.canTrade(myCollection, theirCollection)
        );

        System.out.println(
                GottaSnatchEmAll.commonCards(
                        Arrays.asList(
                                new HashSet<>(Arrays.asList("Scientuna")),
                                new HashSet<>(Arrays.asList("Newthree", "Scientuna"))
                        )
                )
        );

        System.out.println(
                GottaSnatchEmAll.allCards(
                        Arrays.asList(
                                new HashSet<>(Arrays.asList("Scientuna")),
                                new HashSet<>(Arrays.asList("Newthree", "Scientuna"))
                        )
                )
        );
    }
}