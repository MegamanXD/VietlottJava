package rmit.p1;

import java.util.Arrays;
import java.util.Scanner;

public class BackgroundProcesses {

    //This is simply a method to display the main menu to the screen
    public static void displayMainMenu(){
        System.out.println("\nPlease choose an option");
        System.out.println("1.  Add/Edit/Delete/View the list of customers");
        System.out.println("2.  Add/Edit/Delete/View the list of Vietlott shops");
        System.out.println("3.  Conduct Jackpot drawing");
        System.out.println("4.  Trigger the buying process until the Jackpot is matched");
        System.out.println("5.  Run the trigger 5 times and Calculate the average number of times one needs to buy");
        System.out.println("    to become the winner");
        System.out.println("6.  Exit the program");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    //This is the major method that does everything after the main menu
    public static void askForInputOption(int[] jackpot, CustomerList customerList,
                                         ShopList shopList, SubscriptionList subscriptionList) {
        String inputOption;
        int[] emptyArray = new int[6];

        //Ask user to input a main menu option and scan the answer as a String
        System.out.print("\nEnter main menu option: ");
        Scanner s1 = new Scanner(System.in);
        inputOption = s1.nextLine();

        //Depending on the user input, switch the program to execute the next actions
        switch (inputOption){
            case "1":
                //Option 1: Add/Edit/Delete/View the list of customers
                displayCustomerCRUD();
                customerListOption(jackpot,customerList,shopList,subscriptionList);
                break;
            case "2":
                //Option 2: Add/Edit/Delete/View the list of Vietlott shops
                displayShopCRUD();
                shopListOption(jackpot,customerList,shopList,subscriptionList);
                break;
            case "3":
                //Case 3: Conduct Jackpot drawing
                Decorator decorator = new LotteryDecorator(new Lottery());
                jackpot = decorator.decorate(subscriptionList);
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "4":
                //Option 4: Trigger the buying process until the Jackpot is matched

                //Since the buying process can only trigger if the jackpot is already known,
                //if the jackpot is not drawn yet (empty array), display error message, then request
                //the user to conduct a jackpot drawing.
                //Afterward, loop back to the Input Option to allow user to choose option 3
                if (isTheSameArray(jackpot,emptyArray)){
                    System.out.println("Error! You haven't conducted any the jackpot drawing!");
                    System.out.println("You need to perform option 3 first");
                    askForInputOption(jackpot,customerList,shopList,subscriptionList);
                }
                //If the jackpot is already drawn, execute the buying process
                else
                {
                    Lottery.buy(jackpot);
                    continueOrNot(jackpot,customerList,shopList,subscriptionList);
                }

                break;
            case "5":
                //Option 5: Run the trigger 5 times + calculate the average number of times one needs
                //          to buy to become the winner

                //Similar to option 4, option 5 can only function if option 3 is conducted first
                if (isTheSameArray(jackpot,emptyArray)){
                    System.out.println("Error! You haven't conducted any the jackpot drawing!");
                    System.out.println("You need to perform option 3 first");
                    askForInputOption(jackpot,customerList,shopList,subscriptionList);
                }
                //If the jackpot is already drawn, execute the buying process
                else{
                    System.out.println("The average number of rounds required to obtain a Jackpot is " + Lottery.winnerAverage(jackpot));
                    continueOrNot(jackpot,customerList,shopList,subscriptionList);
                }
                break;
            case "6": break;    //Option 6: Exit the program
            default:
                //If inputted choice is invalid, display error message, then loop back to ask user again
                //for valid input
                System.out.println("Invalid! Valid option is only 1-6");
                askForInputOption(jackpot,customerList,shopList,subscriptionList);
        }
    }

    //A simply method to check if 2 Integer Arrays are the same
    public static boolean isTheSameArray(int[]a, int[]b){
        int[] copyOfA = Arrays.copyOf(a,a.length);
        int[] copyOfB = Arrays.copyOf(b,b.length);

        Arrays.sort(copyOfA);
        Arrays.sort(copyOfB);
        for (int i = 0; i <copyOfA.length ; i++) {
            if (copyOfA[i] != copyOfB[i])
                return false;
        }
        return true;
    }

    //A simple method to display the customer CRUD
    private static void displayCustomerCRUD(){
        System.out.println("Choose your option");
        System.out.println("1.  Add a new customer to the list");
        System.out.println("2.  Delete a customer from the list");
        System.out.println("3.  View the list of customers");
        System.out.println("4.  Edit the a customer's information");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    //A simple method to execute the customer CRUD based on the user's choice
    private static void customerListOption(int[] jackpot, CustomerList customerList,
                                           ShopList shopList, SubscriptionList subscriptionList){
        System.out.print("Enter customer option: ");
        Scanner s1 = new Scanner(System.in);
        String customerOption = s1.nextLine();

        switch (customerOption){
            case "1":
                customerList.addCustomer(subscriptionList);
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "2":
                customerList.deleteCustomer();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "3":
                customerList.viewCustomerList();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "4":
                customerList.editCustomerList(subscriptionList);
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            default:
                System.out.println("Invalid! Valid option is only 1-4\n");
                customerListOption(jackpot,customerList,shopList,subscriptionList);
        }
    }

    //A simple method to display the shop CRUD
    private static void displayShopCRUD(){
        System.out.println("Choose your option");
        System.out.println("1.  Add a new shop to the list");
        System.out.println("2.  Delete a shop from the list");
        System.out.println("3.  View the list of Vietlott shops");
        System.out.println("4.  Edit the a shop's information");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    //A simple method to execute the shop CRUD based on the user's choice
    private static void shopListOption(int[] jackpot, CustomerList customerList,
                                       ShopList shopList,SubscriptionList subscriptionList){
        System.out.print("Enter shop option: ");
        Scanner s1 = new Scanner(System.in);
        String shopOption = s1.nextLine();

        switch (shopOption){
            case "1":
                shopList.addShop();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "2":
                shopList.deleteShop();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "3":
                shopList.viewShopList();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "4":
                shopList.editShopList();
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            default:
                System.out.println("Invalid! Valid option is only 1-4\n");
                shopListOption(jackpot,customerList,shopList,subscriptionList);
        }
    }

    //A simple method to ask if the user wants to continue using the program, or he/she wants to quit
    private static void continueOrNot(int[] jackpot, CustomerList customerList,
                                      ShopList shopList, SubscriptionList subscriptionList){
        System.out.print("\nContinue using the program (y/n)? ");
        Scanner s1 = new Scanner(System.in);
        String yesOrNot = s1.nextLine();

        switch (yesOrNot){
            case "y": case "Y":
                showMainMenuAgainOrNot(jackpot,customerList,shopList,subscriptionList);
                break;
            case "n": case "N":
                break;
            default:
                System.out.println("Invalid choice. Please enter either y/Y or n/N");
                continueOrNot(jackpot,customerList,shopList,subscriptionList);
        }
    }

    //A simple method to ask if the user want to display the main menu again or not.

    //This is necessary because after intensive error-checking processes, the main menu would be pushed
    //too far outside of the console, so showing it again would help the user to navigate better
    private static void showMainMenuAgainOrNot(int[] jackpot, CustomerList customerList,
                                               ShopList shopList, SubscriptionList subscriptionList){
        Scanner s1 = new Scanner(System.in);
        System.out.print("\nDo you want to show the Main Menu again (y/n)? " );
        String displayMainMenuOrNot = s1.nextLine();

        switch (displayMainMenuOrNot){
            case "y": case "Y":
                displayMainMenu(); askForInputOption(jackpot,customerList,shopList,subscriptionList); break;
            case "n": case "N":
                askForInputOption(jackpot,customerList,shopList,subscriptionList); break;
            default:
                System.out.println("Invalid choice. Please enter either y/Y or n/N");
                showMainMenuAgainOrNot(jackpot,customerList,shopList,subscriptionList);
        }
    }
}
