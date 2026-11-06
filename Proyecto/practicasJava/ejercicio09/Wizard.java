public class Wizard extends Fighter {

    private boolean spellPrepared = false;

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

    @Override
    public boolean isVulnerable() {
        return !spellPrepared;
    }

    public void prepareSpell() {
        spellPrepared = true;
    }

    @Override
    public int getDamagePoints(Fighter fighter) {
        if (spellPrepared) {
            return 12;
        }

        return 3;
    }
}