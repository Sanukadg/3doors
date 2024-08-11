import java.util.*;

public class Threedoor{

  static Scanner input = new Scanner(System.in);
  static int num_of_doors = 3;

  //menu
  public static int displayMenu(){
    System.out.println("Would you like to,\n 1.Play\n 2.Automate");
    int menuChoice = input.nextInt();
    return menuChoice;
  }


  // Method to randomly place the diamond
  public static int[] placeDiamond() {
    int[] doors = new int[num_of_doors]; // array of doors
    int rand_pos = (int)(Math.random() * num_of_doors); // correctly placing the diamond randomly
    doors[rand_pos] = -1; // placing the value -1 (the diamond) in the randomly chosen door
    return doors; // return the door arrangement
  }

  //method to get user's door choice
  public static int chooseDoorU(){
    System.out.print("Choose a door (0, 1, 2) : ");
    int choice = input.nextInt();
    return choice;  //return user's door choice
  }

  //method to choose door(auto)
  public static int chooseDoorA(){
    int choice = (int)(Math.random() * 3);
    return choice;
  }
  // Method to open an empty door for user(auto)
  public static int openDoorA(int[] door_array, int choice, char ifLog) {
    int openingDoor = -1; // Door to be revealed as empty
    for (int i = 0; i < door_array.length; i++) {
        // Find a door that isn't chosen and doesn't have the diamond
        if (i != choice && door_array[i] != -1) {
            openingDoor = i;
            break;
        }
    }
    if(Character.toUpperCase(ifLog)=='Y'){
      System.out.println("The door " + openingDoor + " is Empty.");
    }
  
    // Find the door that can be switched to
    int openableDoor = -1;
    for (int i = 0; i < door_array.length; i++) {
        if (i != choice && i != openingDoor) {
            openableDoor = i;
            break;
        }
    }
    return openableDoor;
  }

  // Method to open an empty door for user(auto)
  public static int openDoorM(int[] door_array, int choice) {
    int openingDoor = -1; // Door to be revealed as empty
    for (int i = 0; i < door_array.length; i++) {
        // Find a door that isn't chosen and doesn't have the diamond
        if (i != choice && door_array[i] != -1) {
            openingDoor = i;
            break;
        }
    }
    System.out.println("The door " + openingDoor + " is Empty.");

    // Find the door that can be switched to
    int openableDoor = -1;
    for (int i = 0; i < door_array.length; i++) {
        if (i != choice && i != openingDoor) {
            openableDoor = i;
            break;
        }
    }
    return openableDoor;
  }

  //method to ask if user wants to switch doors
  public static boolean askSwitchDoor(int choice, int openableDoor){
    boolean isSwitch;
    System.out.print("Would you like to switch your choice from "+ choice + " to "+ (openableDoor) +"? Y/N : ");
    char userChoice = input.next().charAt(0);
    if (Character.toUpperCase(userChoice) == 'Y'){
      isSwitch = true;
    }else{
      isSwitch = false;
    }
    return isSwitch;
  }

  //method to check if user won(auto)
  public static boolean checkWinA(int choice, int[] door_array, char ifLog){
    if(door_array[choice] == -1){
      if(Character.toUpperCase(ifLog)=='Y'){
        System.out.println("YOU WON!");
      }
      return true;
    }else{
      if(Character.toUpperCase(ifLog)=='Y'){
        System.out.println("YOU LOST.");
      }
      return false;
    }
  }

  public static boolean checkWinM(int choice, int[] door_array){
    if(door_array[choice] == -1){
      System.out.println("YOU WON!");
      return true;
    }else{
      System.out.println("YOU LOST.");
      return false;
    }
  }


  public static void main(String [] args){

    int menuChoice = displayMenu();
    int num_of_wins = 0;
    int round = 1;
    System.out.print("Number of rounds: "); //ask for the number of rounds
    int num_of_rounds = input.nextInt();

    if (menuChoice==1){ //playing manually

      for(int i=1; i<=num_of_rounds; i++){
        System.out.println("Round "+ round + " of "+ num_of_rounds);
        round++;
        int[] door_array = placeDiamond(); //receive the door arrangement
        int choice = chooseDoorU(); //recieve user's door choice
        int openableDoor = openDoorM(door_array, choice); //open one of the other doors and receive the one that didnt open 
        if (askSwitchDoor(choice, openableDoor)){ //switching doors
          choice = openableDoor;
        }
        if (checkWinM(choice, door_array)){
          num_of_wins+=1; //update num of wins
        }
      }
      float percentage = (num_of_wins / num_of_rounds) * 100;
      System.out.println("Win percentage: "+percentage +"%");
    }
    else{   //automating
      System.out.print("Switching strategy?(Y/N): ");
      char strat = input.next().charAt(0);

      System.out.print("Display Log?(Y/N) :");
      char ifLog = input.next().charAt(0);

      for(int i=1; i<=num_of_rounds; i++){
        System.out.println("Round "+ round + " of "+ num_of_rounds);
        round++;
        int[] door_array = placeDiamond(); //receive the door arrangement
        int choice = chooseDoorA(); //recieve door choice(automated)
        int openableDoor = openDoorA(door_array, choice, ifLog); //open one of the other doors and receive the one that didnt open 
        if (Character.toUpperCase(strat)=='Y'){ //switching doors
          choice = openableDoor;
        }
        if (checkWinA(choice, door_array, ifLog)){
          num_of_wins = num_of_wins+1; //update num of wins
        }
      }

      float percentage = ((float) num_of_wins / num_of_rounds) * 100;
      System.out.println();
      System.out.println("Win percentage: "+percentage +"%");
    }





  }

}
