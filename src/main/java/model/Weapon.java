package model;

import validators.CategoryAttribute;

import javax.validation.constraints.*;
import java.util.Objects;

public class Weapon implements Comparable<Weapon> {

    public enum Type {
        ARTILLERY, FLAMETHROWER, EXPLOSIVE, FIREARM, BLADED, THROWING, IMPACT;
    }

   // @CategoryAttribute(enumClazz = Type.class, message = "Unknown weapon type")
    protected  Type type;

    protected  String name = "undefined";

    @NotNull
    @PositiveOrZero(message = "Must be greater than 0 or 0!")
    protected  int damages;

    @NotNull
    @Positive(message = "Must be greater than 0!")
    protected  int cost;

    @Min(value = 1, message = "Should be more then 1")
    @Max(value = 82, message = "Should be less then 82")
    protected  int level = 0;

    @NotNull
    protected  Location location;


    public Weapon(Type type, String name, int damages, int cost, int level, Location location) {
        this.type = type;
        this.name = name;
        this.damages = damages;
        this.cost = cost;
        this.level = level;
        this.location = location;
    }

    public Weapon(Type type, String name, int damages, int level) {
        this.type = type;
        this.name = name;
        this.damages = damages;
        this.level = level;
    }

    public Weapon() {
    }

    public Weapon(Type type, String name, int damages) {
        this.type = type;
        this.name = name;
        this.damages = damages;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return damages == weapon.damages &&
                cost == weapon.cost &&
                level == weapon.level &&
                type == weapon.type &&
                Objects.equals(name, weapon.name) &&
                Objects.equals(location, weapon.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name.toLowerCase(), damages, cost, level, location);
    }


    public static class Builder {
        private Weapon newWeapon;

        public Builder() {
            newWeapon = new Weapon();
        }

        public Builder withType(Type type){
            newWeapon.type = type;
            return this;
        }

        public Builder withName(String name){
            newWeapon.name = name;
            return this;
        }

        public Builder withDamages(int damages){
            newWeapon.damages = damages;
            return this;
        }

        public Builder withCost(int cost){
            newWeapon.cost = cost;
            return this;
        }

        public Builder withLevel(int level){
            newWeapon.level = level;
            return this;
        }

        public Builder withLocation(Location location){
            newWeapon.location = location;
            return this;
        }



        public Weapon build(){
            return newWeapon;
        }
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name = " + name +
                ", type = '" + type + '\'' +
                ", level = " + level +
                ", damages =" + damages +
                ", cost =" + cost +
                ", location =" + location +
                '}';
    }

    @Override
    public int compareTo(Weapon o) {
        if (level == o.level) {
            if (damages == o.damages) {
                if (cost == o.cost) {
                    if (name.length() == o.getName().length()) {
                        return name.compareTo(o.getName());
                    }
                    return name.length() - o.name.length();
                }
                return cost - o.cost;
            }
            return damages - o.damages;
        }
        return level - o.level;
    }


    public Weapon fromString(String input) {
        Weapon newObj = new Weapon();
        input = input.replaceAll("Weapon", "");
        input = input.replaceAll("[\\{}]", "");
        input = input.replaceAll("'", "");
        String locinp = input.split("Location")[1];
        input = input.split("Location")[0];


        String type = input.substring(input.indexOf("type = ")+7, input.indexOf(","));
        input = input.split(type+", ")[1];

        String name = input.substring(input.indexOf("name = ")+7, input.indexOf(","));
        input = input.split(name+", ")[1];

        int level = Integer.parseInt(input.substring(input.indexOf("level = ")+8, input.indexOf(",")));
        input = input.split("level = " + level + ", ")[1];

        int damages = Integer.parseInt(input.substring(input.indexOf("damages = ")+10, input.indexOf(",")));
        input = input.split("damages = " + damages + ", ")[1];

        int cost = Integer.parseInt(input.substring(input.indexOf("cost = ")+7, input.indexOf(",")));
        input = input.split("cost = " + cost + ", ")[1];

        newObj.type = Type.valueOf(type);
        newObj.name = name;
        newObj.level = level;
        newObj.damages = damages;
        newObj.cost = cost;
        newObj.location = new Location().fromString(locinp);

        return newObj;
    }


}
