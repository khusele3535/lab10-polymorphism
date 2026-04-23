package week10;

public class Card extends Payment {
    @Override
    public void pay() {
        System.out.println("Paid by Credit Card");
    }
}
