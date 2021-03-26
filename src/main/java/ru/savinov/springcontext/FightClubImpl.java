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

        if (loryStrength > baxterStrength) {
            System.out.println(loryName + " is win");
        } else if (baxterStrength > loryStrength) {
            System.out.println(baxterName + " is win");
        } else {
            System.out.println("Cat's strenght is the same");
        }
    }
}
