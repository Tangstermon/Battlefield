import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Anthony
 *
 */
public class Battlefield {

	private int[][][] arr;
	private int[][][] computerArr;
	private Random rand = new Random();
	private Scanner scanner = new Scanner(System.in);
	int max =5;
	int Deathstar = 1;
	int Cruiser = 1;
	int Frigate = 1;
	int Bombers = 2;
	int Fighters = 4;
	int allShipsOnBoard = 9;
	
	/**
	 * Constructor which initialises the player grid and computer grid to 0
	 * 
	 */
	public Battlefield(){
		arr = new int[5][5][5];
		for(int z =0; z< arr.length; z++){		//initialising the grid to 0
			for(int y= 0; y < arr[z].length; y++){
				for(int x = 0; x<arr[z][y].length; x++){
					arr[z][y][x] = 0;
				}
			}
		}
		
		computerArr = new int[5][5][5];
		for(int z =0; z< arr.length; z++){		//initialising the grid to 0
			for(int y= 0; y < arr[z].length; y++){
				for(int x = 0; x<arr[z][y].length; x++){
					arr[z][y][x] = 0;
				}
			}
		}
	}

	/**
	 * Prints the grid of the player
	 * 
	 * 
	 */
	public void printGrid(){

		for(int i = 0; i < arr.length; i++){	//print out the Z 	
			System.out.print("       Z=" +i);
			System.out.print("  ");
		}

		System.out.println("\n");

		for(int z = 0; z< arr.length; z++){	// print out Y
			System.out.print("Y= " +z);	
			for(int y = 0; y< arr[z].length; y++){
				System.out.print("  ");		//prints out spaces
				for(int x = 0; x < arr[z][y].length; x++){ 	//prints out the board
					System.out.print(" " +arr[z][y][x]);
				}

			}
			System.out.println();
		}

		System.out.print("\n");
		System.out.print("     ");

		for(int j = 0; j < arr.length; j++){	//prints out the X at the bottom
			System.out.print("X=");	
			for(int k= 0; k<arr[j].length; k++){
				System.out.print(k);
				System.out.print(" ");

			}

		}
		System.out.print("\n");
		System.out.print("________________________________________________________________");
		System.out.print("\n");
	}

	/**
	 * places the DeathStar according to user input
	 * @return true/false if it is possible to place it 
	 */
	public boolean placeDeathStar(){

		int xCoor = 5;
		int yCoor = 5;
		int zCoor = 5;
		
		
		if(Deathstar !=0){
		while(xCoor < 0 || xCoor > 4 || yCoor < 0 || yCoor >4 || zCoor <0 || zCoor >4){
			System.out.println();
			System.out.println("xCoor, yCoor, zCoor of death star:");
			xCoor = scanner.nextInt();
			yCoor = scanner.nextInt();
			zCoor = scanner.nextInt();
			Deathstar--;
			allShipsOnBoard--;
			if(xCoor >= 0 && xCoor <=4 && yCoor >=0 && yCoor <=4 && zCoor >=0 && zCoor <=4 && Check(xCoor, yCoor, zCoor)){
				for(int z = 0; z < arr.length; z++){
					for(int y =0; y< arr[z].length; y++){
						for(int x = 0; x <arr[z][y].length; x++){
							if(xCoor == 0 && zCoor ==4){
								arr[yCoor][zCoor][xCoor] = 8;
								arr[yCoor][zCoor][xCoor+1] =8;
								arr[yCoor+1][zCoor][xCoor] = 8;
								arr[yCoor+1][zCoor][xCoor+1] =8;

								arr[yCoor][zCoor-1][xCoor] = 8;
								arr[yCoor][zCoor-1][xCoor+1] =8;
								arr[yCoor+1][zCoor-1][xCoor] = 8;
								arr[yCoor+1][zCoor-1][xCoor+1] =8;

							}else if (yCoor ==0){
								arr[yCoor][zCoor][xCoor] = 8;
								arr[yCoor+1][zCoor][xCoor] =8;
								arr[yCoor][zCoor][xCoor-1] = 8;
								arr[yCoor+1][zCoor][xCoor-1] =8;

								arr[yCoor][zCoor-1][xCoor] = 8;
								arr[yCoor+1][zCoor-1][xCoor] =8;
								arr[yCoor][zCoor-1][xCoor-1] = 8;
								arr[yCoor+1][zCoor-1][xCoor-1] =8;
							}
							else{
								arr[yCoor][zCoor][xCoor] = 8;
								arr[yCoor+1][zCoor][xCoor-1] = 8;
								arr[yCoor+1][zCoor][xCoor] = 8;
								arr[yCoor][zCoor][xCoor-1] = 8;

								arr[yCoor][zCoor+1][xCoor] = 8;
								arr[yCoor+1][zCoor+1][xCoor-1] = 8;
								arr[yCoor+1][zCoor+1][xCoor] = 8;
								arr[yCoor][zCoor+1][xCoor-1] = 8;
							}

						}
					}
				}	}

				printGrid();
				//scanner.close();
				return true;

			}
		}
		
		return false;				
	}


	/**
	 * places the Death Star at random coordinates in the grid
	 * 
	 */
	public void randomDeathStar(){

		try{
			int randX = rand.nextInt(max);
			int randY = rand.nextInt(max);
			int randZ = rand.nextInt(max);


			for(int z = 0; z < 2; z++){
				for(int y =0; y< 2; y++){
					for(int x = 0; x <2; x++){
						if(Check(randZ, randY, randX) == false && randZ <4 && randY < 4 && randX < 4 ){

						arr[randZ+z][randY+y][randX+x] = 8;
						}
						//}
					}
				}
			}
		}catch(Exception e){
			System.out.println("Cannot place here");
			randomDeathStar();
		}

		printGrid();
	}

	/**
	 * places the ships of length l according to user input
	 * @return possible if it is possible to place in the grid
	 */
	public boolean shipLength(){
		 boolean possible = false;

		System.out.print("length: ");
		int length = scanner.nextInt();
		int quantity = 0;

		//placing fighters length 1
		if(length ==1){
			while(quantity <4){
				System.out.println("xCoor, yCoor, zcoor: ");
				int xCoor = scanner.nextInt();
				int yCoor = scanner.nextInt();
				int zCoor = scanner.nextInt();
				quantity++;
				Fighters--;
				allShipsOnBoard = 4;
				for(int z = 0; z < arr.length; z++){
					for(int y = 0; y < arr[z].length; y++){
						for(int x = 0; x < arr[z][y].length; x++){
							if(arr[yCoor][zCoor][xCoor] == 0){
								arr[yCoor][zCoor][xCoor] = 1;
							}

						}
					}
				}
			}	
		}

		try{
			
			if(length == 2){
				while(quantity < 2){ 
					System.out.println("xCoor, yCoor, zCoor");
					int xCoor = scanner.nextInt();
					int yCoor = scanner.nextInt();
					int zCoor = scanner.nextInt();
					System.out.println("Orientation (0 for vertical) (1 for horizontal)");
					int orientation = scanner.nextInt();
					quantity++;
					Bombers--;
					allShipsOnBoard--;
					for(int z = 0; z < arr.length; z++){
						for(int y = 0; y < arr[z].length; y++){
							for(int x = 0; x < arr[z][y].length; x++){
								if(orientation == 0 && yCoor < 4){
									possible = true;
									arr[yCoor][zCoor][xCoor] = 2;
									arr[yCoor+1][zCoor][xCoor] = 2;

								}else if(orientation == 1 && xCoor <4){
									possible = true;
									arr[yCoor][zCoor][xCoor] =2;
									arr[yCoor][zCoor][xCoor+1] =2;
								}
							}
						}
					}			
				}
			}
		}catch(Exception e){
			System.out.println("Not in the grid range, try again");
			shipLength();
		}


		if(length == 3){
			System.out.println("xCoor, yCoor, zCoor");
			int xCoor = scanner.nextInt();
			int yCoor = scanner.nextInt();
			int zCoor = scanner.nextInt();
			System.out.println("Orientation (0 for vertical) (1 for horizontal)");
			int orientation = scanner.nextInt();
			Frigate--;
			allShipsOnBoard--;
			for(int z = 0; z < arr.length; z++){
				for(int y = 0; y < arr[z].length; y++){
					for(int x = 0; x < arr[z][y].length; x++){
						if(orientation == 0 && yCoor <4 && yCoor > 0){
							possible = true;
							arr[yCoor][zCoor][xCoor] = 3;
							arr[yCoor+1][zCoor][xCoor] = 3;
							arr[yCoor-1][zCoor][xCoor] =3;
						}else if(orientation ==1 && xCoor <4 && xCoor >0){
							possible = true;
							arr[yCoor][zCoor][xCoor] = 3;
							arr[yCoor][zCoor][xCoor+1] = 3;
							arr[yCoor][zCoor][xCoor-1] =3;	
						}
					}
				}
			}	
		}



		if(length == 4){
			System.out.println("xCoor, yCoor, zCoor");
			int xCoor = scanner.nextInt();
			int yCoor = scanner.nextInt();
			int zCoor = scanner.nextInt();
			System.out.println("Orientation (0 for vertical) (1 for horizontal)");
			int orientation = scanner.nextInt();
			Cruiser--;
			allShipsOnBoard--;
			for(int z = 0; z < arr.length; z++){
				for(int y = 0; y < arr[z].length; y++){
					for(int x = 0; x < arr[z][y].length; x++){
						if(orientation == 0 && yCoor < 3 && yCoor >0){
							possible = true;
							arr[yCoor][zCoor][xCoor] = 4;
							arr[yCoor+1][zCoor][xCoor] = 4;
							arr[yCoor-1][zCoor][xCoor] = 4;
							arr[yCoor+2][zCoor][xCoor] =4;
						}else if(orientation == 1 && xCoor <3 && xCoor > 0){
							possible = true;
							arr[yCoor][zCoor][xCoor] = 4;
							arr[yCoor][zCoor][xCoor+1] = 4;
							arr[yCoor][zCoor][xCoor-1] = 4;
							arr[yCoor][zCoor][xCoor+2] =4;
						}
					}
				}
			}	
		}
		printGrid();
		//System.out.print(allShipsOnBoard);
	
		
		return possible;
	}

	/**
	 * places ships of length l at random coordinates in the grid
	 */
	public void randomShip(){
		int min =1;
		
		int horizontal = 1;
 
		int randX = rand.nextInt(max);
		int randY = rand.nextInt(max);
		int randZ = rand.nextInt(max);
		//int ship =  rand.nextInt(max- min) +min;
		int ship1 = 2;
		int orientation = rand.nextInt(horizontal);
		
		int number = 2;
			

		for(int z = 0; z < arr.length; z++){
			for(int y = 0; y <arr[z].length; y++){
				for(int x = 0; x < arr[z][y].length; x++){
					
					if(ship1 == 2 && orientation == 1){
						arr[randZ][randY][randX] = 2;
						arr[randZ][randY][randX+1] = 2;
					}
					else if(ship1 == 2 && orientation == 0){
						arr[randZ][randY][randX] = 2;
						arr[randZ+1][randY][randX] = 2;
					}
					
					
				}
			}
		}
		printGrid();
	}
	
	/**
	 * Checks the grid if it is possible to allow ships t be placed there
	 * @param x  coordinate
	 * @param y  coordinate
	 * @param z  coordinate
	 * @return shipInPlace if possible or not to place there
	 */
	public boolean Check(int x, int y, int z){
		
		boolean shipInPlace = false;
		
		 if(arr[z][y][x] != 0){
			 shipInPlace = true;
		 }
		
		return shipInPlace;
	}
	
	/**
	 * places all ships of length l in random places in the grid
	 */
	public void randomAllShips(){
		
		int randX = rand.nextInt(max);
		int randY = rand.nextInt(max);
		int randZ = rand.nextInt(max);
		int randOr = rand.nextInt(1);
		 
	
		int counter1 = 4;
		int counter2 = 2;
		int counter3 = 1;
		int counter4 = 1;
		
		while(counter1 !=0 ){
		randX = rand.nextInt(max);
		randY = rand.nextInt(max);
		randZ = rand.nextInt(max);
		
			 
			if(Check(randX, randY, randZ) == false){
				arr[randZ][randY][randX] = 1;
						counter1--;
			}		
		
		}
		
		while(counter2 !=0){
			randX = rand.nextInt(max);
			randY = rand.nextInt(max);
			randZ = rand.nextInt(max);
			randOr = rand.nextInt(1);
			
			if(Check(randX, randY, randZ) == false && randZ <4 && randOr ==0){
				arr[randZ][randY][randX] = 2;
				arr[randZ+1][randY][randX] = 2;
				counter2--;
			}else if(Check(randX, randY, randZ) == false && randX <4 && randOr ==1){
				arr[randZ][randY][randX] = 2;
				arr[randZ][randY][randX+1] = 2;
				counter2--;
				}
			}
	
		
		while(counter3 != 0){
				randX = rand.nextInt(max);
				randY = rand.nextInt(max);
				randZ = rand.nextInt(max);
				randOr = rand.nextInt(1);
				
			if(Check(randX, randY, randZ) == false && randX <4 && randX >0 && randOr ==1){
				
				arr[randZ][randY][randX] = 3;
				arr[randZ][randY][randX+1] = 3;
				arr[randZ][randY][randX-1]= 3;
				counter3--;
				
			}else if(Check(randX, randY, randZ) == false && randZ <4 && randZ >0 && randOr ==0){
				arr[randZ][randY][randX] = 3;
				arr[randZ+1][randY][randX] = 3;
				arr[randZ-1][randY][randX]= 3;
				counter3--;
			}
		}
		
		while(counter4 !=0){
			randX = rand.nextInt(max);
			randY = rand.nextInt(max);
			randZ = rand.nextInt(max);
			randOr = rand.nextInt(1);
			
			if(Check(randX, randY, randZ) == false && randX <3 && randX >0 && randOr ==1){
				
				arr[randZ][randY][randX] = 4;
				arr[randZ][randY][randX+1] = 4;
				arr[randZ][randY][randX-1]= 4;
				arr[randZ][randY][randX+2] = 4;
				counter4--;
			}else if(Check(randX, randY, randZ) == false && randZ <3 && randZ >0 && randOr ==0){
				
				arr[randZ][randY][randX] = 4;
				arr[randZ+1][randY][randX] = 4;
				arr[randZ-1][randY][randX]= 4;
				arr[randZ+2][randY][randX] = 4;
				counter4--;
				
			}
			
		}
		randomDeathStar();
		printGrid();
	}
	
	
	/**
	 * Checks if player hits a ship in computer grid
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 * @return attack if ship is hit
	 */
	public boolean attackPoint(int x, int y, int z){
		
		boolean attack = false;
	
		if(computerArr[y][z][x] != 0){
			attack = true;
			computerArr[y][z][x] = 0;
			System.out.println("Hit!");
						
		}else if(computerArr[y][z][x] ==0){
			attack = false;
			System.out.println("Miss");
		}
		
		return attack;
	}
	
	/**
	 * places the ships randomly in the computer grid
	 * 
	 */
	public void computerGrid(){
		int randX = rand.nextInt(max);
		int randY = rand.nextInt(max);
		int randZ = rand.nextInt(max);
		int randOr = rand.nextInt(1);
		 
	
		int counter1 = 4;
		int counter2 = 2;
		int counter3 = 1;
		int counter4 = 1;
		
		while(counter1 !=0 ){
		randX = rand.nextInt(max);
		randY = rand.nextInt(max);
		randZ = rand.nextInt(max);
		
			 
			if(Check(randX, randY, randZ) == false){
				computerArr[randZ][randY][randX] = 1;
						counter1--;
			}		
		
		}
		
		while(counter2 !=0){
			randX = rand.nextInt(max);
			randY = rand.nextInt(max);
			randZ = rand.nextInt(max);
			randOr = rand.nextInt(1);
			
			if(Check(randX, randY, randZ) == false && randZ <4 && randOr ==0){
				computerArr[randZ][randY][randX] = 2;
				computerArr[randZ+1][randY][randX] = 2;
				counter2--;
			}else if(Check(randX, randY, randZ) == false && randX <4 && randOr ==1){
				computerArr[randZ][randY][randX] = 2;
				computerArr[randZ][randY][randX+1] = 2;
				counter2--;
				}
			}
	
		
		while(counter3 != 0){
				randX = rand.nextInt(max);
				randY = rand.nextInt(max);
				randZ = rand.nextInt(max);
				randOr = rand.nextInt(1);
				
			if(Check(randX, randY, randZ) == false && randX <4 && randX >0 && randOr ==1){
				
				computerArr[randZ][randY][randX] = 3;
				computerArr[randZ][randY][randX+1] = 3;
				computerArr[randZ][randY][randX-1]= 3;
				counter3--;
				
			}else if(Check(randX, randY, randZ) == false && randZ <4 && randZ >0 && randOr ==0){
				computerArr[randZ][randY][randX] = 3;
				computerArr[randZ+1][randY][randX] = 3;
				computerArr[randZ-1][randY][randX]= 3;
				counter3--;
			}
		}
		
		while(counter4 !=0){
			
			randX = rand.nextInt(max);
			randY = rand.nextInt(max);
			randZ = rand.nextInt(max);
			randOr = rand.nextInt(1);
			
			if(Check(randX, randY, randZ) == false && randX <3 && randX >0 && randOr ==1){
				
				computerArr[randZ][randY][randX] = 4;
				computerArr[randZ][randY][randX+1] = 4;
				computerArr[randZ][randY][randX-1]= 4;
				computerArr[randZ][randY][randX+2] = 4;
				counter4--;
			}else if(Check(randX, randY, randZ) == false && randZ <3 && randZ >0 && randOr ==0){
				
				computerArr[randZ][randY][randX] = 4;
				computerArr[randZ+1][randY][randX] = 4;
				computerArr[randZ-1][randY][randX]= 4;
				computerArr[randZ+2][randY][randX] = 4;
				counter4--;
				
			}
			
		}
	}
	
	/**
	 * If all ships are destroyed then game ends
	 * @return allDestroyed if true game ends
	 */
	public boolean allShipsDestroyed(){
		
		boolean allDestroyed = true;
		
		for(int z =0; z < arr.length; z++){
			for(int y = 0; y < arr[z].length; y++){
				for(int x = 0; x < arr[z][y].length; x++){
					if(arr[z][y][x] != 0){
						allDestroyed = false;
						break;
					}
				}
			}
		}
		return allDestroyed;
	}
	
	/**
	 * Checks the grid of player if ship is hit
	 * @return attack if true then player ship is hit
	 */
	public boolean computerAttack(){
		boolean attack = false;
		
		int randZ = rand.nextInt(max);
		int randY = rand.nextInt(max);
		int randX = rand.nextInt(max);
		
		if(arr[randZ][randY][randX] !=0){
			attack = true;
			arr[randZ][randY][randX] = 0;
			System.out.println("Your ship has been hit!!");
		}
		else if(arr[randZ][randY][randX] == 0){
			attack = false;
			System.out.println("Computer has missed");
		}
		
		return attack;
	}
	
	/**
	 * Plays the game and calls the methods to place all ships 
	 */
	public void playGame(){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Random position (1) or UserInput position (2) ");
		int a = scanner.nextInt();
		
		if(a == 1){
			computerGrid();
			randomAllShips();
		}else if(a == 2){
			computerGrid();
			shipLength();
			placeDeathStar();
		}
		
		while(allShipsDestroyed() == false){
			System.out.println("Enter coordinates to attack point in computer grid");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int z = scanner.nextInt();
			attackPoint(x, y, z);
			printGrid();
			computerAttack();
			
		}
		
	}
		

	}
//}
