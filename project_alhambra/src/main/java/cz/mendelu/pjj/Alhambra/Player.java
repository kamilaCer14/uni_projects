package cz.mendelu.pjj.Alhambra;

import java.util.*;

public class Player {

    static String name;
    static String surname;
    static Color myColor;

    private final Set<MoneyCard> peniaze = new HashSet<>();

    public final List<Building> alhambra = new ArrayList<>();

    public Player(String name, String surname, Color myColor) {
        this.name = name;
        this.surname = surname;
        this.myColor = myColor;
    }


    //TODO parametrickz konstr

    Player(){ }

    public static String getName() {
        return name;
    }

    public static Color getColor() {
        return myColor;
    }

    /** práca s kolekciou
     *  @author xcervako
     * @version etapa 3
     * */
    public void addCard(MoneyCard objekt){
        peniaze.add(objekt);
    }

    /** práca s kolekciou
     *  @author xcervako
     * @version etapa 3
     * */
    public void removeCard(MoneyCard objekt){
        peniaze.remove(objekt);
    }


    /**
     * @author xcervako
     * @version etapa 3
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", my color=" + myColor +
                '}';
    }


    /**
     * @author xcervako
     * @version etapa 3
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(surname, player.surname) &&
                myColor == player.myColor;
    }

    /**
     * @author xcervako
     * @version etapa 3
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, myColor);
    }


    /**
     * Továrenská metóda createPlayer() vytvorí hráča a priradí mu farbu z enum class Color.
     * @return Player
     */
    public static Player createPlayer(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    public int countMoney() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int getNumberOfMoneyCards() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

//todo vytvorit alhambru ako kolekciu v hracovi

    public List<Building> getAlhambra() { return Collections.unmodifiableList(alhambra); }


    public void addBuildingToA(Building building){
        alhambra.add(building);
    }
}
