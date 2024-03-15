package game;

public class FireBee extends HoneyBee {
    
    private int maxAttackRange;
    public static int BASE_HEALTH;
    public static int BASE_COST;
    
    public FireBee(Tile position, int maxAttackRange) {
        super(position, BASE_HEALTH, BASE_COST);
        this.maxAttackRange = maxAttackRange;
    }

    public boolean takeAction() {
        Tile currentTile = getPosition();
        if (currentTile.isOnThePath() && !currentTile.isNest()) {
            for (int range = 0; range < maxAttackRange; range++) {
                System.out.println(range);
                currentTile = currentTile.towardTheNest();
                if (currentTile.isNest())
                    break;
                else if (currentTile.getNumOfHornets() != 0 && !currentTile.isOnFire()) {
                    currentTile.setOnFire();
                    return true;
                }        
            }
        }
        return false;
    }

}
