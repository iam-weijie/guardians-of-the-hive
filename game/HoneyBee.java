package game;

public abstract class HoneyBee extends Insect {
    
    public static double HIVE_DMG_REDUCTION;
    private int foodCost;

    public HoneyBee(Tile position, int healthPoints, int foodCost) {
        super(position, healthPoints);
        this.foodCost = foodCost;
    }

    public int getCost() {
        return foodCost;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        Tile currentTile = getPosition();
        if (currentTile.isHive()) {
            receivedDamage = (int) Math.floor(receivedDamage * (1 - HIVE_DMG_REDUCTION));
        }
        super.takeDamage(receivedDamage);
    }

}
