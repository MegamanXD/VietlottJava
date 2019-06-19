package rmit.p1;
import java.util.*;

//Info of a customer includes name, birthdate, address, phone, email
public class Customer {
    private String name, birthdate, address, phone, email;

    //Since getting the birthdate to be in the correct format alone requires extensive coding, creating
    //a separate method to get the birthdate would make the codes look more intuitive
    private String getBirthdate(){
        int day,month,year,totalDaysInMonth;

        //First, get the user's Year of birth
        while (true) {
            while (true) {
                Scanner s1 = new Scanner(System.in);
                System.out.print("Input customer YEAR of birth: ");
                String a = s1.nextLine();
                try {
                    year = Integer.valueOf(a);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid! Please enter a valid year (1800-2018)\n");
                }
            }
            if (year < 1800 || year > 2018) {
                System.out.println("Invalid! Please enter a valid year (1800-2018)\n");
            } else
                break;
        }

        //Second, get the user's Month of birth
        while (true) {
            while (true) {
                Scanner s1 = new Scanner(System.in);
                System.out.print("Input customer MONTH of birth: ");
                String a = s1.nextLine();
                try {
                    month = Integer.valueOf(a);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid! Please enter a valid month (1-12)\n");
                }
            }
            if (month < 1 || month > 12) {
                System.out.println("Invalid! Please enter a valid month (1-12)\n");
            } else
                break;
        }

        //Third, based on the inputted Month and Year of birth, determine the valid range for
        //Day of birth
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                totalDaysInMonth = 31;
                break;
            case 4: case 6: case 9: case 11:
                totalDaysInMonth = 30;
                break;
            case 2:
                totalDaysInMonth = 28 + (year%4==0?1:0);
                break;
            default:
                totalDaysInMonth = 0;
        }

        //Last, get the user's Day of birth
        while (true) {
            while (true) {
                Scanner s1 = new Scanner(System.in);
                System.out.print("Input customer DAY of birth: ");
                String a = s1.nextLine();
                try {
                    day = Integer.valueOf(a);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid! Please enter a valid day (1-" + totalDaysInMonth + ")\n");
                }
            }
            if (day < 1 || day > totalDaysInMonth) {
                System.out.println("Invalid! Please enter a valid day (1-" + totalDaysInMonth + ")\n");
            } else
                break;
        }

        //Return the birthdate as a string
        return day + "/" + month + "/" + year;
    }

    //A simple method to ask user to input the details of a customer
    public Customer inputCustomerDetails(SubscriptionList subscriptionList){
        //Ask for customer name
        Customer newCustomer = new Customer();
        Scanner s1 = new Scanner(System.in);
        System.out.println("Input customer name: ");
        newCustomer.name = s1.nextLine();

        //Ask for customer's birthdate
        newCustomer.birthdate = getBirthdate();

        //Ask for customer's address
        Scanner s3 = new Scanner(System.in);
        System.out.println("Input customer address: ");
        newCustomer.address = s3.nextLine();

        //Ask for customer's phone number
        Scanner s4 = new Scanner(System.in);
        System.out.println("Input customer phone number: ");
        newCustomer.phone = s4.nextLine();

        //Ask for customer's email
        Scanner s5 = new Scanner(System.in);
        System.out.println("Input customer email: ");
        newCustomer.email = s5.nextLine();

        newCustomer.subscribe(subscriptionList);
        return newCustomer;
    }

    //Construct a new Customer
    public Customer(){};

    //Display a customer's details as a string
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //Subscription for Observer Pattern
    private void subscribe(SubscriptionList subscriptionList){
        Scanner s1 = new Scanner(System.in);
        System.out.println("Subscribe to Jackpot drawing (y/n)? ");
        String subscribeChoice = s1.nextLine();

        switch (subscribeChoice){
            case "y": case "Y":
                subscriptionList.getSubscription().add(this);
                break;
            case "n": case "N":
                break;
            default:
                System.out.println("Invalid choice. Please enter either y/Y or n/N");
                subscribe(subscriptionList);
        }
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
