package week10;

public class Main {
    static void main() {
        System.out.println("--- 1. Override Basics ---");
        Dog d = new Dog();
        Cat c = new Cat();
        d.sound();
                c.sound();

        System.out.println("\n--- 2. Parent Reference ---");
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        a1.sound();
        a2.sound();

        System.out.println("\n--- 3. Payment System (Polymorphism) ---");
        Payment[] payments = {new Cash(), new Card()};
        for (Payment p : payments) {
            p.pay();
        }

        System.out.println("\n--- 4. Shape System ---");
        Shape[] shapes = {new Rectangle(), new Circle()};
        for (Shape s : shapes) {
            s.area();
        }
    }
}
