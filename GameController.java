/* Author: Shannon TJ 10101385 Tutorial 1
   Version: Mar 26, 2015

   Features: Displays an introduction to the program that explains the rules. Displays menus for SUV and sportscar movement. Gets user input for the SUV and sportscar menus. Repeats prompts until
   valid input is entered. Displays SUV and sportscar stats every turn. Cheat menu can be invoked from either car menu. Arctic and Desert tracks are displayed before the start of every turn. The game can be
   quit early from the SUV, Sportscar and Cheat menus. Displays appropriate messages when either car wins, when a draw/tie occurs, or when the simulation ends early. Program runs until a win occurs or the 
   user quits.  

   Limitations: The instructions are only displayed once, right after the user starts the program. Changing the fuel in the cheat menu does not error check for non-integer input. All of the output is text
   based. 
*/

import java.util.Scanner;

public class GameController
{
    private Scanner in;
    private StringBuffer userSelect = new StringBuffer();

    private boolean blizzard = false;
    private boolean heatwave = false;

    private int positionSUV;
    private int positionSports;

    //Instantiates the cars
    SUV suv1 = new SUV();
    Sports sports1 = new Sports();

    //Instantiates the racing tracks
    ArcticTrack arcticTrack1 = new ArcticTrack(suv1);
    DesertTrack desertTrack1 = new DesertTrack(sports1);

    private boolean playGame = true;

    public GameController()
    {
	Scanner in = new Scanner(System.in);

	//Shows instructions for the program
	showInstructions();

	do
	    {
		//Displays tracks
		System.out.println("ARCTIC TRACK");
		arcticTrack1.display();

		System.out.println("DESERT TRACK");
		desertTrack1.display();

		if(suv1.isEmpty() == false)
		    {
			//Displays SUV menu
			showSUVMenu();
			//Gets user input for SUV
			SUVInput();
			//Calculates whether a blizzard will happen next turn
			blizzard = arcticTrack1.getBlizzard();
		    }
		//Does not display menu when SUV fuel tank is empty
		else
		    {
			System.out.println();
			System.out.println("SUV MENU UNAVAILABLE: SUV out of fuel!");
		    }

		//Displays further prompts if the user continues the simulation
		if (playGame == true)
		    {
			if (sports1.isEmpty() == false)
			    {
				//Displays sportscar menu
				showSportsMenu();
				//Gets user input for sportscar
				sportsInput();
				//Calculates whether a heatwave will occur next turn
				heatwave = desertTrack1.getHeatwave();
			    }
			//Does not display menu when sportscar fuel tank is empty
			else
			    {
				System.out.println();
				System.out.println("SPORTSCAR MENU UNAVAILABLE: Sportscar out of fuel!");
			    }

			//Displays further prompts if the user continues the simulation
			if (playGame == true)
			    {
				//Update the tracks
				arcticTrack1.setLocation(suv1, positionSUV);
				desertTrack1.setLocation(sports1, positionSports);

				//Check if a win occurred
				checkWin();
			    }
		    }
	    }
	while (playGame == true);
    }

    /*showInstructions: Displays basic instructions explaining how to play the game
      Parameters: N/A
      Returns: N/A
     */
    public void showInstructions()
    {
	System.out.println("Welcome to Extreme Racing!");
	System.out.println();
	System.out.println("This is a text-based program in which you race an SUV and a");
	System.out.println("sportscar against each other. To make your car move, enter the");
	System.out.println("character that corresponds to your selection. There is also a 10%");
	System.out.println("chance of a weather event occurring each turn, in either track.");
	System.out.println("These events affect how the cars drive, so good luck - and don't be afraid to cheat!");

	System.out.println();
    }

    /*showSUVMenu: Displays the menu for SUV options
      Parameters: N/A
      Returns: N/A
     */
    public void showSUVMenu ()
    {
	System.out.println();
	System.out.println("SUV Driving Options:");
	System.out.println("(a)ll wheel drive mode");
	System.out.println("(d)rive normally");
	System.out.println("(q)uit simulation");
	System.out.println();
	System.out.print("Enter selection: ");
    }

    /*SUVInput: Allows the user to select an option on the SUV menu
      Parameters: N/A
      Returns: N/A
     */
    public void SUVInput ()
    {
	userSelect.delete(0, userSelect.length());
	userSelect = userSelect.append(in.nextLine());

	//Error checks for no selection
	while (userSelect.length()== 0)
	    {
		System.out.print("Error. Choose a selection: ");
		userSelect.delete(0, userSelect.length());
	     	userSelect = userSelect.append(in.nextLine());
	    }
	char input = userSelect.charAt(0);

	switch (input)
	    {
	    case 'a':
	    case 'A':
		//Sets SUV to AWD
		suv1.setAWD();
		//Updates SUV position
		positionSUV = suv1.move(blizzard, positionSUV);
		break;

	    case 'd':
	    case 'D':
		//Sets SUV to normal driving mode
		suv1.setNormal();
		//Updates SUV position
		positionSUV = suv1.move(blizzard, positionSUV);
		break;

	    case 'q':
	    case 'Q':
		//Displays status message
		printStatus();
		break;

	    case 'c':
	    case 'C':
		//Displays the cheats menu
		showCheats();
		break;

	    default:
		//Error checks for invalid input
		System.out.println();
		System.out.print("Error: Invalid input. Enter a valid selection: ");
		SUVInput();
	    }
    }

    /*showSportsMenu: Displays the menu for sportscar options
      Parameters: N/A
      Returns: N/A
     */
    public void showSportsMenu()
    {
	System.out.println("----------------------------------------------------");
	System.out.println();
	System.out.println("Sportscar Driving Options:");
	System.out.println("(d)rive normally");
	System.out.println("(q)uit simulation");
	System.out.println();
	System.out.print("Enter selection: ");
    }

    /*sportsInput: Allows the user to select an option on the sportscar menu
      Parameters: N/A
      Returns: N/A
     */
    public void sportsInput ()
    {
	userSelect.delete(0, userSelect.length());
	userSelect = userSelect.append(in.nextLine());

	//Error checks for no selection
	while (userSelect.length()== 0)
	    {
		System.out.print("Error. Choose a selection: ");
		userSelect.delete(0, userSelect.length());
		userSelect = userSelect.append(in.nextLine());
	    }
	char input = userSelect.charAt(0);

	switch (input)
	    {
	    case 'd':
	    case 'D':
		//Updates sportscar position
		positionSports = sports1.move(heatwave, positionSports);
		break;

	    case 'q':
	    case 'Q':
		//Display status message
		printStatus();
		break;

	    case 'c':
	    case 'C':
		//Display cheats menu
		showCheats();
		break;

	    default:
		//Error checks for invalid input
		System.out.println();
		System.out.print("Error: Invalid input. Enter a valid selection: ");
		sportsInput();
	    }
    }

    /*showCheats: Displays the menu for cheat options
      Parameters: N/A
      Returns: N/A
     */
    public void showCheats ()
    {
	System.out.println();
	System.out.println("CHEAT MENU SELECTION");
	System.out.println("(0) Toggle debugging messages on/off");
	System.out.println("(1) Change fuel of sportscar");
	System.out.println("(2) Change fuel of SUV");
	System.out.println("(3) Change location of sportscar");
	System.out.println("(4) Change location of SUV");
	System.out.println("(5) Make a blizzard in the arctic track");
	System.out.println("(6) Make a heatwave in the desert track");
	System.out.println();
	System.out.print("Enter selection: ");

	cheatsInput();
    }

    /*cheatsInput: Allows the user to select an option on the cheats menu
      Parameters: N/A
      Returns: N/A
     */
    public void cheatsInput ()
    {
	userSelect.delete(0, userSelect.length());
	userSelect = userSelect.append(in.nextLine());

	//Error checks for no selection
	while (userSelect.length()== 0)
	    {
		System.out.print("Error. Choose a selection: ");
		userSelect.delete(0, userSelect.length());
		userSelect = userSelect.append(in.nextLine());
	    }
	char input = userSelect.charAt(0);

	switch(input)
	    {
	    case '0':
		cheats0();
		break;

	    case '1':
		cheats1();
		break;

	    case '2':
		cheats2();
		break;

	    case '3':
		cheats3();
		break;
	
	    case '4':
		cheats4();
		break;

	    case '5':
		cheats5();
		break;

	    case '6':
		cheats6();      
		break;

	    case'q':
	    case'Q':
		//Displays status message
		printStatus();
		break;

	    default:
		//Error checks for invalid input
		System.out.println();
		System.out.print("Error: Invalid input. Enter a valid selection: ");
		cheatsInput();
	    }
    }

    /*cheats0: Turns debugging mode on or off
      Parameters: N/A
      Returns: N/A
     */
    public void cheats0()
    {
	//Turns debugging mode on
	if (Debug.debug == false)
	    {
		Debug.debug = true;
		System.out.println();
		System.out.println("<<DEBUGGING IS ON!>>");
	    }
	//Turns debugging mode off
	else
	    {
		Debug.debug = false;
		System.out.println();
		System.out.println("<<DEBUGGING IS OFF!>>");
	    }
    }

    /*cheats1: Allows the user to change the sportscar fuel value. Does not error check for non-integer values.
      Parameters: N/A
      Returns: N/A
     */
    public void cheats1()
    {
	//Gets user input for the new sportscar fuel value
	System.out.print("Enter a value for the sportscar fuel: ");
	int fuelInput = in.nextInt();
	sports1.setFuel(fuelInput);
	in.nextLine();

	//Error checks for a negative fuel value
	while(fuelInput < 0)
	    {
		System.out.print("Enter a positive value for the fuel: ");
		fuelInput = in.nextInt();
		sports1.setFuel(fuelInput);
		in.nextLine();
	    }

	if (Debug.debug == true)
	    {
		System.out.println("<<FUEL CHEAT DEBUGGING>>");
		System.out.println("New Sportscar Fuel: "+sports1.getFuel());
	    }
    }

    /*cheats2: Allows the user to change the SUV fuel value. Does not error check for non-integer values. 
      Parameters: N/A
      Returns: N/A
     */
    public void cheats2()
    {
	//Gets user input for the new SUV fuel value
	System.out.print("Enter a value for the SUV fuel: ");
	int fuelInput = in.nextInt();
	suv1.setFuel(fuelInput);
	in.nextLine();

	//Error checks for a negative fuel value
	while(fuelInput < 0)
	    {
		System.out.print("Enter a positive value for the fuel: ");
		fuelInput = in.nextInt();
		suv1.setFuel(fuelInput);
		in.nextLine();
	    }

	if (Debug.debug == true)
	    {
		System.out.println();
		System.out.println("<<FUEL CHEAT DEBUGGING>>");
		System.out.println("New SUV Fuel: "+suv1.getFuel());
	    }
    }

    /*cheats3: Allows the user to change the sportscar location
      Parameters: N/A
      Returns: N/A
     */
    public void cheats3()
    {
	System.out.print("Choose a new location for the sportscar: ");
	userSelect = userSelect.delete(0, userSelect.length());
	userSelect = userSelect.append(in.nextLine());

	//Error checks for no input
	while (userSelect.length()== 0)
	    {
		System.out.print("Error. Choose a selection: ");
		userSelect.delete(0, userSelect.length());
		userSelect = userSelect.append(in.nextLine());
	    }
	char input = userSelect.charAt(0);

	//Checks if input is valid
	//Sets car to specified location
	positionSports = setNumberLocation(input);

	if (positionSports == -1)
	    positionSports = setLetterLocation(input);

	if (positionSports == -1)
	    positionSports = setLetterLocation2(input);

	if (positionSports == -1)
	    {
		System.out.println("Error: Invalid selection.");
		cheats3();
	    }

	//Updates the sportscar's location
	desertTrack1.setLocation(sports1, positionSports);
    }

    /*cheats4: Allows the user to change the SUV location
      Parameters: N/A
      Returns: N/A
     */
    public void cheats4()
    {
	System.out.print("Choose a new location for the SUV: ");
	userSelect.delete(0, userSelect.length());
	userSelect = userSelect.append(in.nextLine());

	//Error checks for no input
	while (userSelect.length()== 0)
	    {
		System.out.print("Error. Choose a selection: ");
		userSelect.delete(0, userSelect.length());
		userSelect = userSelect.append(in.nextLine());
	    }
	char input = userSelect.charAt(0);

	//Checks if input is valid
	//Sets car to specified location
	positionSUV = setNumberLocation(input);

	if (positionSUV == -1)
	    positionSUV = setLetterLocation(input);

	if (positionSUV == -1)
	    positionSUV = setLetterLocation2(input);

	if (positionSUV == -1)
	    {
		System.out.println("Error: Invalid selection.");
		cheats4();
	    }

	//Updates the SUV's location
	arcticTrack1.setLocation(suv1, positionSUV);
    }

    /*cheats5: Makes a blizzard happen in the arctic track 
      Parameters: N/A
      Returns: N/A
     */
    public void cheats5()
    {
	blizzard = true;
    }

    /*cheats6: Makes a heatwave happen in the desert track
      Parameters: N/A
      Returns: N/A
     */
    public void cheats6()
    {
	heatwave = true;
    }

    /*setNumberLocation: Gets the integer value corresponding to a position on a track
      Parameters: A character representing a position on the track
      Returns: An integer representing an index in the track's array
     */
    public int setNumberLocation(char input)
    {
	int positionCar;
	switch (input)
	    {
	    case '1':
		positionCar = 0;
		break;
	    case '2':
		positionCar = 1;
		break;
	    case '3':
		positionCar = 2;
		break;
	    case '4':
		positionCar = 3;
		break;
	    case '5':
		positionCar = 4;
		break;
	    case '6':
		positionCar = 5;
		break;
	    case '7':
		positionCar = 6;
		break;
	    case '8':
		positionCar = 7;
		break;
	    case '9':
		positionCar = 8;
		break;
	    default:
		positionCar = -1;
	    }
	return(positionCar);
    }

    /*setLetterLocation: Gets the integer value corresponding to a position on a track
      Parameters: A character representing a position on the track
      Returns: An integer representing an index in the track's array
     */
    public int setLetterLocation(char input)
    {
	int positionCar;
	switch(input)
	    {
	    case 'A':
	    case 'a':
		positionCar = 9;
		break;
	    case 'B':
	    case 'b':
		positionCar = 10;
		break;
	    case 'C':
	    case 'c':
		positionCar = 11;
		break;
	    case 'D':
	    case 'd':
		positionCar = 12;
		break;
	    case 'E':
	    case 'e':
		positionCar = 13;
		break;
	    case 'F':
	    case 'f':
		positionCar = 14;
		break;
	    case 'G':
	    case 'g':
		positionCar = 15;
		break;
	    case 'H':
	    case 'h':
		positionCar = 16;
		break;
	    default:
		positionCar = -1;
	    }
	return(positionCar);
    }

    /*setLetterLocation2: Gets the integer value corresponding to a position on a track
      Parameters: A character representing a position on the track
      Returns: An integer representing an index in the track's array
     */
  public int setLetterLocation2(char input)
    {
	int positionCar;
	switch(input)
	    {
	    case 'I':
	    case 'i':
		positionCar = 17;
		break;
	    case 'J':
	    case 'j':
		positionCar = 18;
		break;
	    case 'K':
	    case 'k':
		positionCar = 19;
		break;
	    case 'L':
	    case 'l':
		positionCar = 20;
		break;
	    case 'M':
	    case 'm':
		positionCar = 21;
		break;
	    case 'N':
	    case 'n':
		positionCar = 22;
		break;
	    case 'O':
	    case 'o':
		positionCar = 23;
		break;
	    case 'P':
	    case 'p':
		positionCar = 24;
		break;
	    default:
		positionCar = -1;
	    }
	return(positionCar);
    }

    /*checkWin: Determines whether a win has occurred. Quits the game if one of the cars has reached the finish line or if both cars are out of fuel. Displays winning messages. 
      Parameters: N/A
      Returns: N/A
     */
    public void checkWin()
    {
	//Prints a debugging message if debugging is on
	if (Debug.debug == true)
	    {
		System.out.println();
		System.out.println("<<OUT OF FUEL DEBUGGING>>");
		System.out.println("SUV Fuel: "+suv1.getFuel());
		System.out.println("Sports Fuel: "+sports1.getFuel());
	    }

	System.out.println("----------------------------------------------------");
	System.out.println();

	//Race ends in a tie
	if (positionSUV == 24 && positionSports == 24)
	    {
		System.out.println("BOTH CARS FINISHED AT THE SAME TIME: The race is a draw!");
		playGame = false;
	    }

	//SUV wins
	else if (positionSUV == 24 && positionSports < 24)
	    {	    
		System.out.println("SUV FINISHED FIRST: SUV is the winner!");
		playGame = false;
	    }

	//Sportscar wins
	else if (positionSUV < 24 && positionSports == 24)
	    {
		System.out.println("SPORTSCAR FINISHED FIRST: Sportscar is the winner!");
		playGame = false;
	    }

	//Race ends in a tie if both cars run out of fuel
	else if (suv1.getFuel() <= 0 && sports1.getFuel() <= 0)
	    {
		System.out.println("BOTH CARS OUT OF FUEL: The race is a tie!");
		playGame = false;
	    }
    }

    /*printStatus: Displays status message about an unfinished race. Quits the game.
      Parameters: N/A
      Returns: N/A
     */
    public void printStatus()
    {
	System.out.println("Quitting before the simulation ended...");
	System.out.println("UNFINISHED RACE: The race is a draw!");
	playGame = false;
    }
}
