package rmit.p1;

import java.util.Scanner;

//Information of a shop includes: code, address, owner, phone, email, account balance.
public class Shop {
    private String code, address, owner, phone, email;
    private double accountBalance;

    public Shop inputShopDetails(){
        Shop newShop = new Shop();

        //Ask for shop code
        Scanner s1 = new Scanner(System.in);
        System.out.println("Input shop code: ");
        newShop.code = s1.nextLine();

        //Ask for shop address
        Scanner s2 = new Scanner(System.in);
        System.out.println("Input shop address: ");
        newShop.address = s2.nextLine();

        //Ask for shop owner's name
        Scanner s3 = new Scanner(System.in);
        System.out.println("Input shop owner: ");
        newShop.owner = s3.nextLine();

        //Ask for shop phone number
        Scanner s4 = new Scanner(System.in);
        System.out.println("Input shop phone number: ");
        newShop.phone = s4.nextLine();

        //Ask for shop email
        Scanner s5 = new Scanner(System.in);
        System.out.println("Input shop email: ");
        newShop.email = s5.nextLine();

        //Ask for shop account balance
        while(true) {
            Scanner s6 = new Scanner(System.in);
            System.out.println("Input shop account balance: ");
            String a = s6.nextLine();
            try {
                newShop.accountBalance = Double.valueOf(a);
               break;
            } catch (Exception e) {
                System.out.println("Error! Please enter a number.\n");
            }
        }

        return newShop;
    }

    //Construct a new Shop
    public Shop(){};

    //Display the shop's details as a String
    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
