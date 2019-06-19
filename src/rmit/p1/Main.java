package rmit.p1;

public class Main {

    public static void main(String[] args) {
        //Initiate the variables for our background processes
        int[] jackpot = new int[6];
        CustomerList customerList = CustomerList.INSTANCE;
        ShopList shopList = ShopList.INSTANCE;
        SubscriptionList subscriptionList = SubscriptionList.INSTANCE;

        //Execute the background processes
        BackgroundProcesses.displayMainMenu();
        BackgroundProcesses.askForInputOption(jackpot,customerList,shopList,subscriptionList);
    }
}