package model;

import validators.Validators;

public class Main {
    public static void main(String[] args) {
        Validators test = new Validators();

        Location yordan = new Location.Builder()
                .withKingdom("Yordan")
                .withMainRace(Location.Race.WITCHES)
                .withEnteringLevel(23)
                .withPartName("Ice Prisone")
                .build();

        Weapon C4 = test.validWeapon(new Weapon(Weapon.Type.EXPLOSIVE, "C4", 1000, 8600, 40, yordan));
        System.out.println(C4);


        Weapon ak47 = test.validWeapon( new Weapon(Weapon.Type.FIREARM, "AK-47", 25, 5));
        System.out.println(ak47);

        System.out.println(test.validLocation(yordan));


    }
}
