/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Mar 26, 2015

   Features: Generates blizzards with a 10% chance of occurring. Every turn, sets all arctic track positions to null and then updates the track with SUV's current location. 

   Limitations: Only affects car placement in the arctic track. Blizzard probability is overridden by the cheat menu (unless the user attempts to sabotage themselves).  
*/

import java.util.Random;

public class ArcticTrack extends Track
{
    public ArcticTrack(Car aCar)
    {
	setNull();
        setLocation(aCar, 0);  
    }

    /*getBlizzard: Determines whether a blizzard occurs during the next turn
      Parameters: N/A
      Returns: Boolean value corresponding to whether or not a blizzard occurs
     */
    public boolean getBlizzard ()
    {
	boolean blizzard;
	Random random1 = new Random();
	int probability = random1.nextInt(100) + 1;

	if (probability <= 10)
	    blizzard = true;
	else
	    blizzard = false;

	return(blizzard);
    }

    /*setNull: Sets all positions in the arctic track to null
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

    /*setLocation: Sets all positions in the arctic track to null. Updates a position in the arctic track with the SUV's current position
      Parameters: A car object, an integer value denoting the car's current index in the track array
      Returns: N/A
     */
    public void setLocation(Car aCar, int positionCar)
    {
	setNull();
        super.setLocation(aCar, positionCar); 
    }
}
