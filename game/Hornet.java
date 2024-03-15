package game;

public class Hornet extends Insect {
    
    private int attackDamage;
    public static int BASE_FIRE_DMG;
    private boolean queen = false;
    private static int numberOfQueens = 0;

    public Hornet(Tile position, int healthPoints, int attackDamage) {
        super(position, healthPoints);
        this.attackDamage = attackDamage;
    }

    public boolean takeAction() {
        Tile currentTile = getPosition();
        HoneyBee targetBee = currentTile.getBee();
        
        if (this.isTheQueen()) {
            if (currentTile.isOnFire()) 
                this.takeDamage(BASE_FIRE_DMG);

            if (this.getHealth() <= 0)
                return false;

            if (targetBee != null) {
                targetBee.takeDamage(this.attackDamage);
            } else {
                Tile nextTile = currentTile.towardTheHive();
                if (nextTile != null) {
                    currentTile.removeInsect(this);
                    nextTile.addInsect(this);
                    currentTile = nextTile;
                    targetBee = currentTile.getBee();
                    //System.out.println("first");
                } 
            }
        }
        
        if (currentTile.isOnFire()) 
            this.takeDamage(BASE_FIRE_DMG);
            
        if (this.getHealth() <= 0)
            return false;

        if (targetBee != null) {
            targetBee.takeDamage(this.attackDamage);
        } else {
            Tile nextTile = currentTile.towardTheHive();
            if (nextTile != null) {
                currentTile.removeInsect(this);
                nextTile.addInsect(this); 
                //System.out.println("second");
            } else
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean isTheQueen() {
        return queen;
    }

    public void promote() {
        if (numberOfQueens == 0) {
            queen = true;
            numberOfQueens += 1;
        }    
    }

}
