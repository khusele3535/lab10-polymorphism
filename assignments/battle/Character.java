public class Character {

    protected String name;
    protected int hp;

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    // Default attack deals 10 damage. Subclasses should override.
    public int attack(Character target) {
        int damage = 10;
        target.takeDamage(damage);
        return damage;
    }

    public void takeDamage(int amount) {
        this.hp = Math.max(0, this.hp - amount);
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }
}
