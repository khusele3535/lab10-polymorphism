public class Battle {

    // TODO: static int partyAttack(Character[] party, Character enemy)
    // - party дахь Character бүрд attack(enemy) дуудна (polymorphic loop)
    // - Нийт учруулсан хохирлын нийлбэрийг буцаана
    // - Жишээ: Warrior(20) + Mage(15) + Rogue(25) = 60

    // ─────── 🟡 Stretch (30 оноо) ───────

    // TODO: static String describeParty(Character[] party)
    // - Character бүрийн describe() method-ийг дуудаж, үр дүнг нэг String болгоно
    // - Жишээ: "wields a sword | channels magic | sneaks in shadow"
    // - Хооронд " | " тусгаарлагч ашиглана

    // ─────── 🔴 Bonus (10 оноо) ───────

    // TODO: static int countMages(Character[] party)
    // - party дотор хэдэн Mage instance байгааг тоолно
    // - instanceof pattern matching ашиглана (Java 16+):
    //     if (c instanceof Mage m) { ... }

}
