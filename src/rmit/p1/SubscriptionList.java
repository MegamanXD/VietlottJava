package rmit.p1;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionList {
    private List<Customer> subscription = new ArrayList<>();

    //Singleton Patter
    public static SubscriptionList INSTANCE = new SubscriptionList();
    private SubscriptionList(){}

    //Delete customer
    public void delete(Customer customer){
        subscription.remove(customer);
    }

    public List<Customer> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Customer> subscription) {
        this.subscription = subscription;
    }
}
