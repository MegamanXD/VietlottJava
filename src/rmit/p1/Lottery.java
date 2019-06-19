package rmit.p1;

import java.util.Arrays;

public class Lottery implements Decorator{
    //A simple method to randomly generate an Integer Array of unrepeated numbers
    private static int[] intArrayGenerator(int numberOfElements, int maxValue){
        int[] a = new int[numberOfElements];
        for (int i = 0; i <numberOfElements ; i++) {
            a[i]=(int)Math.round(Math.random()*maxValue);

            //Check if the randomized value is a repeated value
            for (int j = i-1; j >=0 ; j--) {
        if (a[i] == a[j]){
            a[i]=(int)Math.round(Math.random()*maxValue); //Randomize a new value if the value already existed

            //Reset the checking process with a new randomized value
            j=i; //since j-- will be performed, this will become j = i-1, which is a reset
        }
    }
}
        return a;
                }

    //Draw jackpot
    public static int[] draw(){
        final int[] jackpot = intArrayGenerator(6,45);
        System.out.println("The Jackpot is: " + Arrays.toString(jackpot));
        System.out.println("------------------------------------------------------------------------");
        return  jackpot;
    }

    //Buy lotteries
    public static int buy(int[]jackpot){
        int[] boughtLottery = {0,0,0,0,0,0};
        int timesBought = 0;

        //Keep buying until the Jackpot is hit
        while(!BackgroundProcesses.isTheSameArray(jackpot,boughtLottery)){
            boughtLottery = intArrayGenerator(6,45);
            timesBought++;
            System.out.println("Round " + timesBought + ": " + Arrays.toString(boughtLottery));
        }
        System.out.println("\nJACKPOT!!!!!");
        System.out.println("------------------------------------------------------------------------");

        //Display the Jackpot for comparison
        System.out.println("The Jackpot is: " + Arrays.toString(jackpot));
        System.out.println("------------------------------------------------------------------------");

        //Return timesBought for winnerAverage calculations
        return timesBought;
    }

    //Run the trigger 5 times and calculate the average number of times one needs to buy to become a winner
    public static double winnerAverage(int[] jackpot){
        int totalTimesBought = 0;
        for (int i = 0; i <5 ; i++) {
            totalTimesBought += buy(jackpot);
        }
        return 1.0*totalTimesBought/5;
    }

    //Decorator Pattern

    @Override
    public int[] decorate(SubscriptionList subscriptionList) {
        return draw();
    }
}
