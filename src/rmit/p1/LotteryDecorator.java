package rmit.p1;

public class LotteryDecorator implements Decorator{
    private Decorator decorator;

    public LotteryDecorator(Decorator decorator){this.decorator = decorator;}

    //Use Decorator Pattern to call Observer Pattern
    @Override
    public int[] decorate(SubscriptionList subscriptionList) {
        for (Customer customer:subscriptionList.getSubscription()) {
            System.out.println("Notified " + customer);
        }
        return decorator.decorate(subscriptionList);
    }
}
