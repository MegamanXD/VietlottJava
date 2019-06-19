package rmit.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//A class that performs the actions of adding, editing, deleting, and viewing the list of Vietlott shops
public class ShopList {
    private List<Shop> shopList = new ArrayList<>();

    //Singleton Pattern
    public static ShopList INSTANCE = new ShopList();
    private ShopList(){}

    //Add a new shop
    public void addShop(){
        Shop newShop = new Shop();
        shopList.add(newShop.inputShopDetails());

        System.out.println("The shop list after addition is:");
        viewShopList();
    }

    //View the list of shops
    public void viewShopList(){
        if (shopList.size() == 0)
            System.out.println("There is no shop yet");
        else{
            for (int i = 0; i <shopList.size() ; i++) {
                System.out.println("Shop " + i + ": " + shopList.get(i));
            }
        }
    }

    //Delete a shop from the list
    public void deleteShop(){
        viewShopList();
        int index;
        if (shopList.size() == 0)
            System.out.println("There's nothing for you to delete");
        else{
            //Call Memento backup method
            System.out.println("Creating rollback point ...");
            List<Shop> backupPoint = shopBackup();

            //Deletion process
            while (true) {
                while (true) {
                    System.out.println("Which shop do you want to delete (0 to " + (shopList.size()-1) + "): ");
                    Scanner s1 = new Scanner(System.in);

                    try {
                        index = s1.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid! Please enter a valid shop (0 to " + (shopList.size() - 1) + ")\n");
                    }
                }
                if (index < 0 || index > shopList.size() - 1) {
                    System.out.println("Invalid! Please enter a valid shop (0 to " + (shopList.size() - 1) + ")\n");
                } else
                    break;
            }

            shopList.remove(shopList.get(index));

            System.out.println("The shop list after deletion is:");
            viewShopList();

            //Call Memento rollback method
            shopRestore(backupPoint);
            System.out.println("Shop list:");
            viewShopList();
        }
    }

    //Edit the details of a shop
    public void editShopList(){
        viewShopList();
        int index;
        if (shopList.size() == 0)
            System.out.println("There's no shop here for you to edit");
        else
        {
            //Call Memento backup method
            System.out.println("Creating rollback point ...");
            List<Shop> backupPoint = shopBackup();

            //Edition process
            while (true) {
                while (true) {
                    System.out.println("Which shop do you want to edit (0 to " + (shopList.size()-1) + "): ");
                    Scanner s1 = new Scanner(System.in);

                    try {
                        index = s1.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid! Please enter a valid shop (0 to " + (shopList.size() - 1) + ")\n");
                    }
                }
                if (index < 0 || index > shopList.size() - 1) {
                    System.out.println("Invalid! Please enter a valid shop (0 to " + (shopList.size() - 1) + ")\n");
                } else
                    break;
            }

            Shop edittedShop = new Shop();
            shopList.set(index,edittedShop.inputShopDetails());

            System.out.println("The shop list after edition is:");
            viewShopList();

            //Call Memento rollback method
            shopRestore(backupPoint);
            System.out.println("Shop list:");
            viewShopList();
        }
    }

    //Memento Pattern
        //Backup method
    public List<Shop> shopBackup(){
        List<Shop> shopListMemento = new ArrayList<>();
        for (int i = 0; i < shopList.size(); i++) {
            shopListMemento.add(shopList.get(i));
        }
        return shopListMemento;
    }
        //Restore method
    public void shopRestore(List<Shop> shopListMemento){
        Scanner s1 = new Scanner(System.in);
        System.out.print("Undo changes (y/n): ");
        String YesNo = s1.nextLine();

        switch (YesNo){
            case "y": case "Y":
                this.shopList = shopListMemento;
                break;
            case "n": case "N":
                break;
            default:
                shopRestore(shopListMemento);
                break;
        }
    }
}
