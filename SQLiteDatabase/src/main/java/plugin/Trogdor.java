package plugin;

import java.util.Random;

/**
 * Created by tyler on 6/14/17.
 */

public class Trogdor
{
    public Devastation burninate()
    {
        Devastation devastation = new Devastation(new Random().nextInt(), new Random().nextInt());

        return devastation;
    }

    public class Devastation
    {
        int spread;
        int fatalities;

        public Devastation(int spread, int fatalities)
        {
            this.spread = spread;
            this.fatalities = fatalities;
        }

        public int getSpread()
        {
            return spread;
        }

        public void setSpread(int spread)
        {
            this.spread = spread;
        }

        public int getFatalities()
        {
            return fatalities;
        }

        public void setFatalities(int fatalities)
        {
            this.fatalities = fatalities;
        }
    }
}
