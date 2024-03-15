package game;

public class SwarmOfHornets {
    
    private Hornet[] hornets;
    private int size;
    public static double QUEEN_BOOST;

    public SwarmOfHornets() {
        this.hornets = new Hornet[0];
        this.size = 0;
    }

    public int sizeOfSwarm() {
        return size;
    }

    public Hornet[] getHornets() {
        return hornets;
    }

    public Hornet getFirstHornet() {
        if (size > 0) {
            return hornets[0];
        } else {
            return null;
        }
    }

    public void addHornet(Hornet newHornet) {
        if (hornets.length <= size) {
            Hornet[] newHornets = new Hornet[size + 1];
            System.arraycopy(hornets, 0, newHornets, 0, size);
            hornets = newHornets;
        }

        hornets[size++] = newHornet;

        if (newHornet.isTheQueen()) {
            for (Hornet hornet : hornets) {
                if (!hornet.isTheQueen())
                    hornet.regenerateHealth(QUEEN_BOOST);
            }
        }
    }

    public boolean removeHornet(Hornet hornetToRemove) {
        for (int i = 0 ; i < size; i++) {
            if (hornets[i].equals(hornetToRemove)) {
                Hornet[] newHornets = new Hornet[size-1];
                System.arraycopy(hornets, 0, newHornets, 0, i);
                if (i < size - 1)
                    System.arraycopy(hornets, i+1, newHornets, i, size-i-1);
                hornets = newHornets;
                size--;
                return true;
            }
        }
        return false;
    }

}
