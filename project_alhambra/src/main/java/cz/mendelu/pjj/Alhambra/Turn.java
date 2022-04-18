package cz.mendelu.pjj.Alhambra;

import java.util.*;

public class Turn {

    private final Player player;
    private Bank bank;
    private String name;
    private Market market;
    private Color color;
    private int sumOfMoney;



//    private final List<MoneyCard> moneyOnHand = new ArrayList<>();
//    private final List<MoneyCard> moneyInBank = new ArrayList<>();
    //fixme toto dat do hraca resp. banky


    public Turn(Player player){
        this.player=player;
}

    /**
     * hashcode & equals
     * @author xlysova
     * @version etapa 3
     */
    public Turn(String name, Color color, Bank bank){
        this.name=name;
        this.color=color;
        this.bank=bank;
        this.player=null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return bank.equals(turn.bank) &&
                name.equals(turn.name) &&
                color == turn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, name, color);
    }


    /**
     * Metóda takeMoney() sa spustí, ak si hráč pri svojom ťahu vyberie možnosť 'TAKE MONEY'.
     * Pri výbere možnosti 'TAKE MONEY' si hráč z "Bank" zoberie peniaze, ktoré môžu mať 4 rôzne meny (farby)
     * a môžu nadobúdať rôzne hodnoty (od 1 do 9). Buď si hráč vezme 1 penažnú kartu, ktorej hodnota môže presiahnuť
     * hodnotu 5, alebo si vezme viac penažných kariet, ktorých maximálny súčet hodnôt je 5. (na mene penazí v tejto
     * metóde nezáleží, ide len o súčet hodnôt)
     *
     * @author xlysova
     * @version etapa 3
     */

    // FIXME ako pridem k sumOfMoney ??
    public void takeMoney(MoneyCard... args) {
        var turn=new Turn(player);
        while(sumOfMoney<=5) {
            turn.moveFromBankToHand();
        }
    }

    public void moveFromBankToHand() {
//        moneyOnHand().add(moneyInBank.remove(/*to na co klikne*/)); //FIXME
    }

    //TODO ako pridem k sumOfMoney ??
    public int countMoney(){
        return sumOfMoney;
    }

    /**
     * Metóda placeBuilding() umožní hráčovi umiestniť budovu do svojej Alhambry.
     * Bude sa musieť skontrolovať, či má hráč na svojej Alhambre prázdne políčko, na ktoré chce
     * stavbu položiť. Ak áno, stavbu položí, ak nie, stavbu nebude možné na políčko položiť.
     * Budeme musieť pristupovať k triede Alhambra a kontrolovať obsadenosť políčok.
     *
     * @author xlysova
     * @version etapa 1
     */
    void placeBuilding(Building building, char ch, int i) {
        player.addBuildingToA(building);
    }

    char whatLine() {
    return Building.getCH();
    }

    int whatColumn() {
        return Building.getI();
    }

    /**
     * Metoda exchangeBuilding() sa spustí, ak si hráč pri svojom ťahu vyberie možnosť 'REDESIGN ALHAMBRA'.
     * Hráč má 2 možnosti:
     * 1. výmena 2 budov, ktoré má vo svojej Alhambre (ak vezme budovu z políčka napr. 5f a zamení ju za budovu
     * z políčka napr. 1c, nová poloha prvej budovy musí byť 1c a druhej 5f)
     * 2. výmena budovy, ktorá sa nachádza na jeho Reserve Field-e s budovou, ktorá je v jeho Alhambre
     * (budova z Reserve Field-u musí ísť na pôvodné miesto stavby v Alhambre a stavba z Alhambry sa presunie na Reserve Field)
     *
     * @author xlysova
     * @version etapa 1
     */
    void exchangeBuildings(Building building1, Building building2) {
 //      int a = 0;
 //      int b = 0;
 //      int c = 0;
 //      building1.getPozicia()=a;
 //      building2.getPozicia()=b;
 //      building1.getPozicia()=c;
 //      a=b;
 //      c=b;
    }

    protected int numberOfMoneyCards(MoneyCard... args) {
        return args.length;
    }

    public Alhambra getAlhambra(char ch, int i) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    /**
     * 1. vytiahne do premennej budovu na policku v Alhambre (cez akoby getter)
     * 2. ulozi budovu z Reserve Fieldu na policko Alhambry (akoby setter)
     * potrebne na exchangeBuilding
     * @return budova, ktoru som zobrala z Alhambry
     */
    public Building switchBuildingRtoA(Building building, char ch, int i) {

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Building switchBuildingAtoA(Building building, char ch, int i){
        throw new UnsupportedOperationException("Not implemented yet.");
    }



    /**
     * Metoda pro zjištění zda máme peníze v dané měně, ve které je uvedena budova,
     * kterou chceme koupit
     * @return pokud je to možné koupí budovu
     *
     * @author xmoravc1
     * @version etapa 1
     */
    public Turn buyBuilding(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    /**
     * Metoda pro zjištění zda je možné přidat nové budovy na market
     * @return Přídá na budovu na Market pokud je to možné
     * @throws IndexOutOfBoundsException
     * s
     * @author xmoravc1
     * @version etapa 1
     */
    public Turn addBuildingToMarket(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Metóda nextTurn() sa zavolá po každom ťahu hráča. V prípade, ak hráč kúpi budovu za presnú čiastku penazí,
     * má možnosť zahrať ešte jedno kolo. Inak pokračuje v kole ďaľší hráč.
     *
     * @author xcervako
     * @version etapa 3
     */
    // FIXME + doplnenie penazi do banky
    public boolean nextTurn(Building building, int peniaze) {
        if (building.getPrice() == peniaze){
            return true;
        } else
            return false;
    }




    public boolean buyBuilding(Building building) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int getZaplatenaSuma(int price){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}



