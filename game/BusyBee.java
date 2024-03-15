package game;

public class BusyBee extends HoneyBee {
    
    public static int BASE_HEALTH;
    public static int BASE_COST;
    public static int BASE_AMOUNT_COLLECTED;

    public BusyBee(Tile position) {
        super(position, BASE_HEALTH, BASE_COST);
    }

    public boolean takeAction() {
        Tile currentTile = getPosition();
        currentTile.storeFood(BASE_AMOUNT_COLLECTED);
        return true;
    }

}
