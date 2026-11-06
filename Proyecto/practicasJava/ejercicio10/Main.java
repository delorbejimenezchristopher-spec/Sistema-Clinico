import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductionRemoteControlCar prod =
                new ProductionRemoteControlCar();

        prod.drive();
        prod.drive();

        System.out.println(prod.getDistanceTravelled());


        ExperimentalRemoteControlCar exp =
                new ExperimentalRemoteControlCar();

        exp.drive();

        System.out.println(exp.getDistanceTravelled());


        TestTrack.race(prod);
        TestTrack.race(exp);

        System.out.println(prod.getDistanceTravelled());


        ProductionRemoteControlCar prc1 =
                new ProductionRemoteControlCar();

        ProductionRemoteControlCar prc2 =
                new ProductionRemoteControlCar();

        prc1.setNumberOfVictories(2);
        prc2.setNumberOfVictories(3);

        List<ProductionRemoteControlCar> cars =
                new ArrayList<>();

        cars.add(prc1);
        cars.add(prc2);

        List<ProductionRemoteControlCar> rankings =
                TestTrack.getRankedCars(cars);

        System.out.println(
                rankings.get(0).getNumberOfVictories()
        );

        System.out.println(
                rankings.get(1).getNumberOfVictories()
        );
    }
}