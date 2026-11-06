public class Main {

    public static void main(String[] args) {

        Warrior warrior = new Warrior();
        Wizard wizard = new Wizard();

        System.out.println(warrior);
        System.out.println(warrior.isVulnerable());

        System.out.println(wizard);
        System.out.println(wizard.isVulnerable());

        System.out.println(warrior.getDamagePoints(wizard));

        wizard.prepareSpell();

        System.out.println(wizard.isVulnerable());
        System.out.println(wizard.getDamagePoints(warrior));
    }
}