package rmit.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//A class that performs the actions of adding, editing, deleting, and viewing the list of customers
public class CustomerList {
    private List<Customer> customerList = new ArrayList<>();

    //Singleton Pattern
    public static CustomerList INSTANCE = new CustomerList();
    private CustomerList(){}

    //Add a new customer1
    public void addCustomer(SubscriptionList subscriptionList){
        Customer newCustomer = new Customer();
        customerList.add(newCustomer.inputCustomerDetails(subscriptionList));

        System.out.println("The customer list after addition is: ");
        viewCustomerList();
    }

    //View the list of customers
    public void viewCustomerList(){
        if (customerList.size() == 0)
            System.out.println("There is no customer yet");
        else{
            for (int i = 0; i <customerList.size() ; i++) {
                System.out.println("Customer " + i + ": " + customerList.get(i));
            }
        }
    }

    //Delete a customer from the list
    public void deleteCustomer(){
        viewCustomerList();
        int index;
        if (customerList.size() == 0)
            System.out.println("There's nothing for you to delete.");
        else {
            //Call Memento backup method
            System.out.println("Creating rollback point ...");
            List<Customer> backupPoint = customerBackup();

            //Deletion process
            while (true) {
                while (true) {
                    System.out.println("Which customer do you want to delete (0 to " + (customerList.size() - 1) + "): ");
                    Scanner s1 = new Scanner(System.in);
                    try {
                        index = s1.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid! Please enter a valid customer (0 to " + (customerList.size() - 1) + ")\n");
                    }
                }
                if (index < 0 || index > customerList.size() - 1) {
                    System.out.println("Invalid! Please enter a valid customer (0 to " + (customerList.size() - 1) + ")\n");
                } else
                    break;
            }
            customerList.remove(customerList.get(index));

            System.out.println("The customer list after deletion is: ");
            viewCustomerList();

            //Call Memento rollback method
            customerRestore(backupPoint);
            System.out.println("Customer list:");
            viewCustomerList();

            //Update Subscription List after deletion
            if(backupPoint.size() != customerList.size())
                SubscriptionList.INSTANCE.delete(backupPoint.get(index));
        }
    }

    //Edit the details of a customer
    public void editCustomerList(SubscriptionList subscriptionList){
        viewCustomerList();
        int index;
        if (customerList.size() == 0)
            System.out.println("There's nobody here for you to edit");
        else{
            //Call Memento backup method
            System.out.println("Creating rollback point ...");
            List<Customer> backupPoint = customerBackup();

            //Edition process
            while (true) {
                while (true) {
                    System.out.println("Which customer do you want to edit (0 to " + (customerList.size() - 1) + "): ");
                    Scanner s1 = new Scanner(System.in);

                    try {
                        index = s1.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid! Please enter a valid customer (0 to " + (customerList.size() - 1) + ")\n");
                    }
                }
                if (index < 0 || index > customerList.size() - 1) {
                    System.out.println("Invalid! Please enter a valid customer (0 to " + (customerList.size() - 1) + ")\n");
                } else
                    break;
            }

            Customer edittedCustomer = new Customer();
            customerList.set(index,edittedCustomer.inputCustomerDetails(subscriptionList));

            System.out.println("The customer list after edition is: ");
            viewCustomerList();

            //Call Memento rollback method
            customerRestore(backupPoint);
            System.out.println("Customer list:");
            viewCustomerList();

            //Update Subscription List after edition
            if(backupPoint.get(index).getName() != customerList.get(index).getName() ||
            backupPoint.get(index).getPhone() != customerList.get(index).getPhone() ||
            backupPoint.get(index).getEmail() != customerList.get(index).getEmail() ||
            backupPoint.get(index).getAddress() != customerList.get(index).getAddress())
                SubscriptionList.INSTANCE.delete(backupPoint.get(index));
        }
    }

    //Memento Pattern
        //Backup method
    public List<Customer> customerBackup(){
        List<Customer> customerListMemento = new ArrayList<>();
        for (int i = 0; i < customerList.size(); i++) {
            customerListMemento.add(customerList.get(i));
        }
        return customerListMemento;
    }
        //Restore method
    public void customerRestore(List<Customer> customerListMemento){
        Scanner s1 = new Scanner(System.in);
        System.out.print("Undo changes (y/n): ");
        String YesNo = s1.nextLine();

        switch (YesNo){
            case "y": case "Y":
                this.customerList = customerListMemento;
                break;
            case "n": case "N":
                break;
            default:
                customerRestore(customerListMemento);
                break;
        }
    }
}
