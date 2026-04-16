import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@DisplayName("Lab 10: Battle System (Polymorphism)")
public class BattleTest {

    private Warrior warrior;
    private Mage mage;
    private Rogue rogue;
    private Character enemy;

    @BeforeEach
    void setUp() {
        warrior = new Warrior("Aragorn", 100);
        mage = new Mage("Gandalf", 80);
        rogue = new Rogue("Legolas", 90);
        enemy = new Character("Orc", 200);
    }

    // ==================== 🟢 CORE ====================

    @Test
    @Tag("core")
    @DisplayName("Warrior нь Character-аас удамшдаг")
    void warriorExtendsCharacter() {
        assertTrue(warrior instanceof Character,
            "Warrior нь Character-аас extends хийх ёстой");
    }

    @Test
    @Tag("core")
    @DisplayName("Mage нь Character-аас удамшдаг")
    void mageExtendsCharacter() {
        assertTrue(mage instanceof Character,
            "Mage нь Character-аас extends хийх ёстой");
    }

    @Test
    @Tag("core")
    @DisplayName("Rogue нь Character-аас удамшдаг")
    void rogueExtendsCharacter() {
        assertTrue(rogue instanceof Character,
            "Rogue нь Character-аас extends хийх ёстой");
    }

    @Test
    @Tag("core")
    @DisplayName("Warrior.attack 20 хохирол буцаана")
    void warriorAttackDeals20() {
        int dmg = warrior.attack(enemy);
        assertEquals(20, dmg, "Warrior.attack буцаах ёстой: 20");
        assertEquals(180, enemy.getHp(), "Enemy hp: 200 - 20 = 180");
    }

    @Test
    @Tag("core")
    @DisplayName("Mage.attack 15 хохирол буцаана")
    void mageAttackDeals15() {
        int dmg = mage.attack(enemy);
        assertEquals(15, dmg, "Mage.attack буцаах ёстой: 15");
        assertEquals(185, enemy.getHp(), "Enemy hp: 200 - 15 = 185");
    }

    @Test
    @Tag("core")
    @DisplayName("Rogue.attack 25 хохирол буцаана (crit)")
    void rogueAttackDeals25() {
        int dmg = rogue.attack(enemy);
        assertEquals(25, dmg, "Rogue.attack буцаах ёстой: 25");
        assertEquals(175, enemy.getHp(), "Enemy hp: 200 - 25 = 175");
    }

    @Test
    @Tag("core")
    @DisplayName("Dynamic binding: Character c = new Warrior(...); c.attack() → 20")
    void dynamicBindingWorks() {
        Character c = new Warrior("UpcastWarrior", 100);
        int dmg = c.attack(enemy);
        assertEquals(20, dmg,
            "Dynamic binding нь Warrior.attack-ийг дуудах ёстой (10 биш 20)");
    }

    @Test
    @Tag("core")
    @DisplayName("Battle.partyAttack нийт хохирлыг зөв нийлбэрлэнэ")
    void partyAttackReturnsTotalDamage() {
        Character[] party = { warrior, mage, rogue };
        int total = Battle.partyAttack(party, enemy);
        assertEquals(60, total, "20 + 15 + 25 == 60");
        assertEquals(140, enemy.getHp(), "Enemy hp: 200 - 60 = 140");
    }

    @Test
    @Tag("core")
    @DisplayName("Battle.partyAttack нь polymorphic — дарааллыг баримтална")
    void partyAttackIteratesPolymorphically() {
        // Only mages — total must be exactly 15 * 3 = 45
        Character[] party = {
            new Mage("M1", 50),
            new Mage("M2", 50),
            new Mage("M3", 50)
        };
        int total = Battle.partyAttack(party, enemy);
        assertEquals(45, total, "3 mage = 45 damage");
    }

    // ==================== 🟡 STRETCH ====================

    @Test
    @Tag("stretch")
    @DisplayName("Enemy класс байгаа ба Character-аас удамшдаг")
    void enemyExtendsCharacter() throws Exception {
        Class<?> enemyClass = Class.forName("Enemy");
        assertTrue(Character.class.isAssignableFrom(enemyClass),
            "Enemy нь Character-аас extends хийх ёстой");
        // Enemy.attack deals 12 damage
        Object enemyInstance = enemyClass
            .getConstructor(String.class, int.class)
            .newInstance("Goblin", 50);
        Method attackM = enemyClass.getMethod("attack", Character.class);
        Object result = attackM.invoke(enemyInstance, warrior);
        assertEquals(12, ((Integer) result).intValue(),
            "Enemy.attack буцаах ёстой: 12");
    }

    @Test
    @Tag("stretch")
    @DisplayName("describe() — Warrior/Mage/Rogue polymorphic")
    void describeIsPolymorphic() throws Exception {
        Method wd = Warrior.class.getMethod("describe");
        Method md = Mage.class.getMethod("describe");
        Method rd = Rogue.class.getMethod("describe");

        assertEquals("wields a sword", wd.invoke(warrior));
        assertEquals("channels magic", md.invoke(mage));
        assertEquals("sneaks in shadow", rd.invoke(rogue));
    }

    @Test
    @Tag("stretch")
    @DisplayName("Battle.describeParty бүх баатрын describe-г concat хийнэ")
    void describePartyConcatenates() {
        Character[] party = { warrior, mage, rogue };
        String result = Battle.describeParty(party);
        assertTrue(result.contains("wields a sword"),
            "Warrior describe багтах ёстой");
        assertTrue(result.contains("channels magic"),
            "Mage describe багтах ёстой");
        assertTrue(result.contains("sneaks in shadow"),
            "Rogue describe багтах ёстой");
    }

    // ==================== 🔴 BONUS ====================

    @Test
    @Tag("bonus")
    @DisplayName("AttackStrategy interface байна")
    void attackStrategyInterfaceExists() throws Exception {
        Class<?> strategyClass = Class.forName("AttackStrategy");
        assertTrue(strategyClass.isInterface(),
            "AttackStrategy нь interface байх ёстой");
        Method m = strategyClass.getMethod("calculateDamage");
        assertEquals(int.class, m.getReturnType(),
            "calculateDamage нь int буцаах ёстой");
    }

    @Test
    @Tag("bonus")
    @DisplayName("Battle.countMages нь instanceof pattern matching-аар Mage тоолно")
    void countMagesUsesInstanceof() {
        Character[] party = {
            new Warrior("W1", 100),
            new Mage("M1", 80),
            new Rogue("R1", 90),
            new Mage("M2", 80),
            new Mage("M3", 80)
        };
        int count = Battle.countMages(party);
        assertEquals(3, count, "3 Mage instance байгаа");
    }
}
