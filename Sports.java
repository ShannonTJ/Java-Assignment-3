/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Mar 26, 2015

   Features: Updates the total fuel value and fuel consumed per turn depending on whether a heatwave occurs or not. Moves sportscar in normal conditions and in special cases, such as when the sportscar is 
   close to the finish line. Tracks how far the sportscar moves and how much fuel it consumes each turn. Displays these stats, along with the sportscar's total fuel. Can determine if the fuel tank is empty.
   Has additional error checking if sportscar is at the finish line, due to the ordering of events in the program.  

   Limitations: Sportscar's heatwave consumption and normal consumption are fixed values. Sportscar's appearance is fixed. Sportscar movement is not affected when total fuel goes below consumption rate 
   but is above zero.  
*/

public class Sports extends Car
{
    private int fuel;
    private int fuelPerTurn;
    private int distancePerTurn;

    private static final int STARTING_FUEL = 30;
    private static final int STANDARD_DISTANCE = 3;
    private static final int HEATWAVE_CONSUMPTION = 4;
    private static final int NORMAL_CONSUMPTION = 2;

    public Sports()
    {
	setAppearance('P');
	setFuel(STARTING_FUEL);
    }

    /*move: Moves the sportscar different amounts depending on how far it is from the finish line. Calls methods that update fuel values. 
      Parameters: Boolean value representing whether a heatwave occurs during that turn, integer value representing the index of the car in the track array
      Returns: Integer value representing the sportscar's current position
     */
    public int move(boolean heatwave, int positionSports)
    {
	//Sets distance per turn to the default value
	setDistance(STANDARD_DISTANCE);

	//Checks if a heatwave occurs
	if (heatwave == true)
	    {
		System.out.println("A heatwave hammers the desert track!");

		//Moves sportscar so that it is not placed outside the track's bounds
		if (positionSports == 22)
		    {
			consumeMoreFuel();
			positionSports = positionSports + 2;
			setDistance(2);
		    }
		//Moves sportscar so that it is not placed outside the track's bounds
		else if (positionSports == 23)
		    {
			consumeMoreFuel();
			positionSports++;
			setDistance(1);
		    }
		//Does not move sportscar or consume fuel if it is at the finish line
		else if (positionSports == 24)	
		    {	   
			setDistance(0);
			fuelPerTurn = 0;
		    }
		//Moves sportscar the standard amount
		else
		    {
			consumeMoreFuel();
			positionSports = positionSports + STANDARD_DISTANCE;
		    }	    
	    }
	//Checks if a heatwave does not occur
	else
	    {
		//Moves sportscar so that it is not placed outside the track's bounds
		if (positionSports == 22)
		    {
			consumeFuel();
			positionSports = positionSports + 2;
			setDistance(2);
		    }
		//Moves sportscar so that it is not placed outside the track's bounds
		else if (positionSports == 23)
		    {
			consumeFuel();
			positionSports++;
			setDistance(1);
		    }
		//Does not move sportscar or consume fuel if it is at the finish line
		else if (positionSports == 24)
		    {		   
			setDistance(0);
			fuelPerTurn = 0;
		    }
		    
		//Moves sportscar the standard amount
		else
		    {
			consumeFuel();
			positionSports = positionSports + STANDARD_DISTANCE;
		    }		    
	    }
	//Displays debugging messages
	System.out.print(displayStats());
	return(positionSports);
    }

     /*isEmpty: Determines whether the fuel tank is empty
      Parameters: N/A
      Returns: A boolean value corresponding to whether the tank is empty or not
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
	if (fuel >= NORMAL_CONSUMPTION)
	    {
		setFuel(fuel - NORMAL_CONSUMPTION);
		fuelPerTurn = NORMAL_CONSUMPTION;
	    }
	else
	    {
		fuelPerTurn = fuel;
		setFuel(0);
	    }
    }

    /*consumeFuel: Consumes fuel during a heatwave at a constant rate. Updates fuel value. 
      Parameters: N/A
      Returns: N/A
     */
    public void consumeMoreFuel()
    {
	if (fuel >= HEATWAVE_CONSUMPTION)
	    {
		setFuel(fuel - HEATWAVE_CONSUMPTION);
		fuelPerTurn = HEATWAVE_CONSUMPTION;
	    }
	else
	    {
		fuelPerTurn = fuel;
		setFuel(0);
	    }
    }

   /* setDistance: Updates the distance moved by the sportscar
      Parameters: Integer value corresponding to the distance moved
      Returns: N/A
     */
    public void setDistance(int distance)
    {
	distancePerTurn = distance;
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

    /*displayStats: Displays the SUV's fuel, fuel use, and distance moved per turn
      Parameters: N/A
      Returns: A string displaying total fuel, fuel use, and distance moved per turn
     */
    public String displayStats()
    {
	String s = "";
	s = s+"Current Fuel: "+fuel+"\nFuel Use: "+fuelPerTurn+"\nDistance Sportscar Moved: "+distancePerTurn+"\n"; 
	return(s);
    }
}
