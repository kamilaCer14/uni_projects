package cz.mendelu.pjj.Alhambra;

import java.util.Objects;

public class Building {
    String color;
    int price;
    int size;
    TypeOfBuilding type;
    static int i;
    static char ch;

    /**
     * @author xlysova
     * ulozim si vsetky budovy, ktoré existujú
     */

    public static final Building[] BUILDINGS = {
            new Building(TypeOfBuilding.PALACE, 6),
            new Building(TypeOfBuilding.GARDEN, 10),
            new Building(TypeOfBuilding.ARCADE, 8),
            new Building(TypeOfBuilding.PAVILON, 7),
            new Building(TypeOfBuilding.CASTLE, 9),
            new Building(TypeOfBuilding.TOWER, 8),
            //...
    };

    /**
     * @author xlysova
     */

    public Building(TypeOfBuilding type, int price){
        this.type = type;
        this.price = price;
    }

    public static char getCH() {
        return ch;
    }

    public static int getI() {
        return i;
    }

    /**
     * metoda toString
     * @author xlysova
     * @version etapa 3
     */

    @Override
    public String toString() {
        return "Building{" +
                "price=" + price +
                ", type=" + type +
                '}';
    }


    public Building(String color, int price, int size) {
        this.color = color;
        this.price = price;
        this.size = size;
    }

    /**
     * @author xcervako
     * @version etapa 3
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return price == building.price &&
                size == building.size &&
                Objects.equals(color, building.color);
    }

    /**
     * @author xcervako
     * @version etapa 3
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, price, size);
    }

    public Building(){}


    public int getPrice(){

        return price;
    }


    int getPozicia(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Metoda, která zjistí v jaké barvě je budova kterou chceme koupiit
     * @return Vrátí nám barvu dané budovy
     */
    public Building getColor(){
        throw new UnsupportedOperationException("Not implemented yet");
    }


}
