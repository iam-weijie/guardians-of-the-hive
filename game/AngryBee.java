package game;

public class AngryBee extends HoneyBee {
    
    private int attackDamage;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile position, int attackDamage) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
    }

    public boolean takeAction() {
        Tile currentTile = getPosition();
        if (currentTile.isOnThePath() && !currentTile.isNest()) {
            if (currentTile.getNumOfHornets() != 0) {
                Hornet targetHornet = currentTile.getHornet();
                targetHornet.takeDamage(attackDamage);
                return true;
            } 
            else {
                Tile nextTile = currentTile.towardTheNest();
                if (!nextTile.isNest() && nextTile.getNumOfHornets() != 0) {
                    Hornet targetHornet = nextTile.getHornet();
                    targetHornet.takeDamage(attackDamage);
                    return true;
                }
            }
        }
        return false;
    }

}
