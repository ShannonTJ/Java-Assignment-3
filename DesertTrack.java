/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Mar 26, 2015

   Features: Generates heatwaves with a 10% chance of occurring. Every turn, sets all desert track positions to null and then updates the track with sportscar's current location. 

   Limitations: Only affects car placement in the desert track. Heatwave probability is overridden by the cheat menu (unless the user attempts to sabotage themselves). 
*/

import java.util.Random;

public class DesertTrack extends Track
{

    public DesertTrack(Car aCar)
    {
	setNull();
        setLocation(aCar, 0); 
    }

    /*getHeatwave: Determines whether a heatwave occurs during the next turn
      Parameters: N/A
      Returns: Boolean value corresponding to whether or not a heatwave occurs
     */
    public boolean getHeatwave ()
    {
	boolean heatwave;
	Random random1 = new Random();
	int probability = random1.nextInt(100) + 1;

	if (probability <= 10)
	    heatwave = true;
	else
	    heatwave = false;
	    
	return(heatwave);
    }

    /*setNull: Sets all positions in the desert track to null
      Parameters: N/A
      Returns: N/A
     */
    public void setNull()
    {
	int c;
	Car [] aTrack = getTrack();
	for (c = 0; c < SIZE; c++)
	    aTrack[c] = null;
    }

    /*setLocation: Sets all positions in the desert track to null. Updates a position in the desert track with the sportscar's current position
      Parameters: A car object, an integer value denoting the car's current index in the track array
      Returns: N/A
     */
    public void setLocation(Car aCar, int positionCar)
    {
	setNull();
        super.setLocation(aCar, positionCar); 
    }
}
