package game;

public class SniperBee extends HoneyBee {
    
    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    private boolean aiming;

    public SniperBee(Tile position, int attackDamage, int piercingPower) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
        this.aiming = true;
    }

    public boolean takeAction() {
        Tile currentTile = getPosition();
        if (currentTile.isOnThePath() && !currentTile.isNest()) {
            if (aiming) {
                aiming = false;
                return false;
            }
            else {
                aiming = true;
                while (!currentTile.isNest()) {
                    Hornet[] swarm = currentTile.getHornets();
                    if (currentTile.getNumOfHornets() != 0) {
                        int n = Math.min(piercingPower, currentTile.getNumOfHornets());
                        for (int i = 0; i < n; i++) {
                            swarm[i].takeDamage(attackDamage);
                        }
                        return true;
                    }
                    currentTile = currentTile.towardTheNest();
                }
            }
        }
        return false;
    }

}
