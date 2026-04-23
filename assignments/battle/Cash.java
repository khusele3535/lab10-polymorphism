package week10;

public class Cash extends Payment {
    @Override
    public void pay() {
        System.out.println("Paid by Cash");
    }
}
