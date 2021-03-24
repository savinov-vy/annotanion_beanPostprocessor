package ru.savinov.springcontext;

public class FightClubImpl implements FightClub {

    private CombatCat loryCat;
    private CombatCat baxterCat;

    public void setLoryCat(CombatCat loryCat) {
        this.loryCat = loryCat;
    }

    public void setBaxterCat(CombatCat baxterCat) {
        this.baxterCat = baxterCat;
    }

    @Override
    public void fight() {
        System.out.println("First cat: " + loryCat.toString());
        System.out.println("Second cat: " + baxterCat.toString());

        String baxterName = baxterCat.getName();
        String loryName = loryCat.getName();
        Integer baxterStrength = baxterCat.getStrength();
        Integer loryStrength = loryCat.getStrength();
    }
}
