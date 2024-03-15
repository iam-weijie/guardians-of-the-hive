package game;

public abstract class Insect {
    
    private Tile position;
    private int healthPoints;

    public Insect(Tile position, int healthPoints) {
        if (position.getBee() != null && this instanceof HoneyBee) 
            throw new IllegalArgumentException("Cannot add another bee to the tile!");
        if (this instanceof Hornet && !position.isOnThePath()) 
            throw new IllegalArgumentException("Cannot add insect on non-path tiles!");
        else {
            this.position = position;
            this.healthPoints = healthPoints;
            position.addInsect(this);
        }
    }

    public final Tile getPosition() {
        return position;
    }

    public final int getHealth() {
        return healthPoints;
    }

    public void setPosition(Tile newPosition) {
        position = newPosition;
    }

    public void takeDamage(int receivedDamage) {
        this.healthPoints -= receivedDamage;
        if (this.healthPoints <= 0) 
            position.removeInsect(this);
    }

    public abstract boolean takeAction();

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass() && ((Insect)obj).getPosition() == this.getPosition() && ((Insect)obj).getHealth() == this.getHealth())
            return true;
        else
            return false;
    }

    public void regenerateHealth(double percentageHealth) {
        healthPoints = (int) Math.floor(healthPoints * (1 + percentageHealth));
    }

}
