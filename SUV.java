/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Mar 26, 2015

   Features: Turns AWD on or off. Moves SUV based on whether it's in AWD or normal mode and whether there's a blizzard or not. Moves SUV in special cases, such as when the SUV is close to the finish line. 
   Updates the SUV's total fuel every turn. Tracks how far the SUV moves and how much fuel it consumes each turn. Displays these stats, along with the SUV's total fuel. Can determine if the fuel tank is 
   empty. 

   Limitations: SUV's fuel consumption rate is fixed. SUV's appearance is fixed. SUV movement is not affected when total fuel goes below consumption rate but is above zero. 
*/

public class SUV extends Car
{
    private boolean AWD = false;

    private int fuel;
    private int distancePerTurn = 0;
    private int fuelPerTurn = 0;

    private static final int STARTING_FUEL = 50;
    private static final int CONSUMPTION_RATE = 3;

    public SUV()
    {
	setAppearance('V');
	setFuel(STARTING_FUEL);
    }

    /*setAWD: Puts SUV in AWD mode
      Parameters: N/A
      Returns: N/A
     */
    public void setAWD ()
    {
	if (AWD == false)
	    AWD = true;
    }

    /*setNormal: Puts SUV in normal driving mode
      Parameters: N/A
      Returns: N/A
     */
    public void setNormal ()
    {
	if (AWD == true)
	    AWD = false;
    }

    /*move: Moves the SUV different amounts depending on whether a blizzard occurs, and AWD mode is on/off. Also determines how far the SUV is from the finish line and updates SUV position accordingly.
      Parameters: Boolean value representing whether a blizzard occurs during that turn, integer value representing the index of the car in the track array
      Returns: Integer value representing the SUV's current position
     */
    public int move(boolean blizzard, int positionSUV)
    {
	//Consumes fuel at the standard consumption rate
	consumeFuel();

	//Checks if blizzard occurs and AWD is on
	if (blizzard == true && AWD == true)
	    {
		System.out.println("Blizzard hits but SUV moves slowly but surely");
		positionSUV++;
		setDistance(1);
	    }
	//Checks if blizzard occurs and AWD is off
	else if (blizzard == true && AWD == false)
	    {
		System.out.println("Blizzard hits and SUV spins its wheels.");
		setDistance(0);
	    }
	//Checks if blizzard does not occur
	else
	    {
		//Checks if SUV is one position away from the finish line
		if(positionSUV == 23)
		    {
			//Moves the SUV so that it is not placed outside of the track's bounds
			positionSUV++;
			setDistance(1);
		    }
		else
		    {
			//Moves SUV the standard amount
			positionSUV = positionSUV + STANDARD_DISTANCE;
			setDistance(STANDARD_DISTANCE);
		    }
	    }	    
	//Displays SUV's stats 
	System.out.print(displayStats());
	//Returns SUV's current position
	return(positionSUV);
    }

     /*isEmpty: Determines whether the fuel tank is empty
      Parameters: N/A
      Returns: A boolean value corresponding to whether or not the tank is empty
     */
    public boolean isEmpty()
    {
	boolean empty;
	if (fuel <= 0)
	    empty = true;
	else
	    empty = false;
	return(empty);
    }

    /*consumeFuel: Consumes fuel at a constant rate. Updates fuel value.
      Parameters: N/A
      Returns: N/A
     */
    public void consumeFuel()
    {
	if (fuel >= CONSUMPTION_RATE)
	    {
		setFuel(fuel - CONSUMPTION_RATE);
		fuelPerTurn = CONSUMPTION_RATE;
	    }
	else
	    {
		fuelPerTurn = fuel;
		setFuel(0);
	    }
    }

    /*setFuel: Sets the fuel to a new value
      Parameters: Integer value corresponding to the new fuel value
      Returns: N/A
     */
    public void setFuel(int newFuel)
    {
	super.setFuel(newFuel);
	fuel = getFuel();
    }

   /* setDistance: Updates the distance moved per turn by the SUV
      Parameters: Integer value corresponding to the distance moved
      Returns: N/A
     */
    public void setDistance(int distance)
    {
	distancePerTurn = distance;
    }

    /*displayStats: Displays the SUV's fuel, fuel use, and distance moved per turn
      Parameters: N/A
      Returns: A string displaying total fuel, fuel use, and distance moved per turn
     */
    public String displayStats()
    {
	String s = "";
        s = s+"Current Fuel: "+fuel+"\nFuel Use: "+fuelPerTurn+"\nDistance SUV Moved: "+distancePerTurn+"\n"; 
	return(s);
    }
}
