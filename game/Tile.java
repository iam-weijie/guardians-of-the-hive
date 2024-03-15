package game;

public class Tile {
    
    private int food;
    private boolean beeHive;
    private boolean hornetNest;
    private boolean onPath;
    private Tile tileToHive;
    private Tile tileToNest;
    private HoneyBee honeyBee;
    private SwarmOfHornets hornets;
    private boolean tileOnFire = false;

    public Tile() {
        food = 0;
        beeHive = false;
        hornetNest = false;
        onPath = false;
        tileToHive = null;
        tileToNest = null;
        honeyBee = null;
        hornets = new SwarmOfHornets();
    }  
    
    public Tile(int food, boolean beeHive, boolean hornetNest, boolean onPath, Tile tileToHive, Tile tileToNest, HoneyBee honeyBee, SwarmOfHornets hornets) {
        this.food = food;
        this.beeHive = beeHive;
        this.hornetNest = hornetNest;
        this.onPath = onPath;
        this.tileToHive = tileToHive;
        this.tileToNest = tileToNest;
        this.honeyBee = honeyBee;
        this.hornets = hornets;
    }

    public boolean isHive() {
        return beeHive;
    }

    public boolean isNest() {
        return hornetNest;
    }

    public void buildHive() {
        beeHive = true;
    }

    public void buildNest() {
        hornetNest = true;
    }

    public boolean isOnThePath() {
        return onPath;
    }

    public Tile towardTheHive() {
        if (!isOnThePath() || isHive()) 
            return null;
        else
            return tileToHive;
    }

    public Tile towardTheNest() {
        if (!isOnThePath() || isNest())
            return null;
        else
            return tileToNest;
    }

    public void createPath(Tile tileToHive, Tile tileToNest) {
        if ((tileToHive == null && !beeHive) || (tileToNest == null && !hornetNest)) 
            throw new IllegalArgumentException("Tile is not a hive or a nest, and cannot be part of the path!");
        else {
            this.tileToHive = tileToHive;
            this.tileToNest = tileToNest;
            onPath = true;
        }
    }

    public int collectFood() {
        int collectedFood = food;
        food = 0;
        return collectedFood;
    }

    public void storeFood(int food) {
        this.food += food;
    }

    public int getNumOfHornets() {
        return hornets.sizeOfSwarm();
    }

    public HoneyBee getBee() {
        return honeyBee;
    }

    public Hornet getHornet() {
        return hornets.getFirstHornet();
    }

    public Hornet[] getHornets() {
        return hornets.getHornets();
    }

    public boolean addInsect(Insect insect) {
        if (insect instanceof HoneyBee) {
            if (honeyBee == null && !hornetNest) {
                honeyBee = (HoneyBee) insect;
                honeyBee.setPosition(this);
                return true;
            }
        } else if (insect instanceof Hornet) {
            if (onPath) {
                hornets.addHornet((Hornet) insect);
                insect.setPosition(this);
                return true;
            }
        } 
        return false;
    }

    public boolean removeInsect(Insect insect) {
        if (insect instanceof HoneyBee && honeyBee != null) {
            if (honeyBee.equals(insect)) {
                honeyBee.setPosition(null);
                this.honeyBee = null;
                return true;
            } 
        } else if (insect instanceof Hornet) {
            hornets.removeHornet((Hornet) insect);
            ((Hornet) insect).setPosition(null);
            return true;
        }
        return false;
    }

    public void setOnFire() {
        tileOnFire = true;
    }

    public boolean isOnFire() {
        return tileOnFire;
    }

}
