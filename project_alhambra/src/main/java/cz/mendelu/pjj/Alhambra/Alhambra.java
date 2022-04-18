package cz.mendelu.pjj.Alhambra;

import java.io.*;
import java.util.*;

public class Alhambra {
    //int[][] cz.mendelu.pjj.Alhambra.domain.alhambra = new int[10][10];
    char ch;
    int i;
    private final Map map;

    /**
     * Save game to folder <i>save/</i>
     * @param alhambra the game
     *
     * @author xcervako
     * @version etapa 4
     */
    public static void save(Alhambra alhambra, String name) {
            try (var out = new ObjectOutputStream(new FileOutputStream(file(name))))  {
                out.writeObject(alhambra);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static File file(String name) {
        var dir = new File("save");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, name + ".alhambra");
    }



    public char getCh() {
        return ch;
    }

    public int getI() {
        return i;
    }

    private static Alhambra alhambra;


    String color;
    int playInPosition;
    String name;

    private final Set<Market> budovy = new HashSet<>();

    public Alhambra(char ch, int i) {
        this.ch = ch;
        this.i = i;
        this.map = new HashMap();
    }

    public Alhambra(Map map, String color, int playInPosition, String name) {
        this.map = map;
        this.color = color;
        this.playInPosition = playInPosition;
        this.name = name;
    }

    /**
     * @author xmoravc1
     * @version etapa 3
     */
    public Object getBuilding(char ch, int i) {
        return map.get(new Coordinate(ch, i));
    }

    /**
     * @author xmoravc1
     * @version etapa 3
     */
    public void pridejBudovu(Market objekt) {
        budovy.add(objekt);
    }

    /**
     * @author xmoravc1
     * @version etapa 3
     */
    public void odoberBudovu(Market objekt) {
        budovy.remove(objekt);
    }


    /**
     * @author xmoravc1
     * @version etapa 3
     */
    public String toString() {
        return "Alhambra{" +
                "barva='" + color + '\'' +
                ", hraje v pozici='" + playInPosition + '\'' +
                ", nazev Alhambry=" + name +
                '}';
    }


    /**
     * @author xmoravc1
     * @version etapa 3
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, playInPosition, name);
    }


    /**
     * @author xmoravc1
     * @version etapa 3
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alhambra alhambra = (Alhambra) o;
        return Objects.equals(color, alhambra.color) &&
                Objects.equals(playInPosition, alhambra.playInPosition) &&
                name == alhambra.name;
    }


    /**
     * Metoda vrací informaci o tom jesli je v Alhammbře umístěna fontána. První karta, která tam musí být,
     * bez ní nemůže hra začít
     *
     * @return true/false
     */
    public boolean hasFountain() {


        throw new UnsupportedOperationException("Not implemented yet");
    }


    /**
     * Metoda pro vytvoření Alhambra, místa kde se umisťují budovy. Na začátku je na ní umístěna
     * pouze jedna karta (Fontána). Jinak je prázdná.
     * Fontána je umístěna na pozici [6,e]
     *
     * @return Alhambra je připravena ke hře
     * @author xmoravc1
     * @version etapa 1
     */
    public static Alhambra createAlhambra() {
        throw new UnsupportedOperationException("Not implemented yet.");

    }


    /**
     * Metoda, která získá místo na Alhambře na souřadnicích.
     *
     * @param ch je písmeno v rozsahu: a,b,c,d,e,f,g,h,,i,j
     * @param i  je číslo v rozsahu: 1,2,3,4,5,6,7,8,9,10
     * @return Existující pole
     * @throws IndexOutOfBoundsException
     * @author xmoravc1
     * @version etapa 1
     */
    public Alhambra getPlace(char ch, int i) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    private Player currentPLayer;

    public Player getCurrentPLayer() {
        return currentPLayer;
    }

}