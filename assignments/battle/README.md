# Lab 10 — Battle System (Polymorphism)

**Нийт оноо:** 100 | **Сэдэв:** Polymorphism (method overriding, dynamic binding, upcasting)

## 🎭 Түүх

Dungeon of OOP-ын тулааны талбарт илдэчин Warrior, шидтэн Mage, сүүдрийн Rogue гурав зэрэгцэн зогсож байна. Бүгдэд нэг л команд өгнө — `attack(enemy)`. Гэвч тус бүр өөр өөр хохирол учруулах ёстой: илдэчин 20, шидтэн 15, Rogue 25 (critical!). Энэ бол **polymorphism** — `Character` гэж нэг нэрээр хандах боловч, runtime дээр тохирох subclass-ын method автоматаар дуудагдана. Үүнийг **dynamic binding** гэнэ.

---

## 📋 Өгөгдсөн: `Character.java`

Энэ нь **parent class** — өөрчлөхгүй. Доорх бүтэцтэй:

```java
public class Character {
    protected String name;
    protected int hp;

    public Character(String name, int hp) { ... }
    public int attack(Character target) { /* default: 10 damage */ }
    public void takeDamage(int amount) { /* hp 0 доош явахгүй */ }
    public int getHp() { ... }
    public String getName() { ... }
}
```

`attack` нь default-ээр **10 хохирол** учруулж, буцаана.

---

## 🟢 Core tasks (60 оноо)

### 1. `Warrior extends Character`

```java
public class Warrior extends Character {
    public Warrior(String name, int hp) { super(name, hp); }

    @Override
    public int attack(Character target) {
        // 20 damage
    }
}
```

- Constructor: `super(name, hp)` дуудна
- `@Override attack(Character target)` — **20 хохирол** учруулж, 20-г буцаана

### 2. `Mage extends Character`

- `@Override attack(Character target)` — **15 хохирол** (magic bolt)

### 3. `Rogue extends Character`

- `@Override attack(Character target)` — **25 хохирол** (critical strike!)

### 4. `Battle.partyAttack(Character[] party, Character enemy) → int`

- `party` массивын `Character` бүрд `attack(enemy)` дуудна (polymorphic!)
- Нийт учруулсан хохирлыг нийлбэрлэж буцаана

**Жишээ:**
```java
Character[] party = {
    new Warrior("Aragorn", 100),
    new Mage("Gandalf", 80),
    new Rogue("Legolas", 90)
};
Character enemy = new Character("Orc", 200);

int total = Battle.partyAttack(party, enemy);
// total == 20 + 15 + 25 == 60
// enemy.getHp() == 140
```

### 5. Dynamic binding

Энэ нь шалгах зорилготой:

```java
Character c = new Warrior("A", 100);   // upcast
Character e = new Character("E", 100);
int dmg = c.attack(e);
// dmg == 20 биш 10 (Warrior-ийн method дуудагдана!)
```

---

## 🟡 Stretch tasks (30 оноо)

### 6. `Character.describe() → String` (polymorphic)

⚠️ Энэ даалгаврын хувьд `Character.java`-д `describe()` method нэмэх **боломжгүй** (файл өөрчилдөггүй). Харин дэд классуудад `describe()` method нэмж, `Battle.describeParty` дотор reflection-оор дуудахгүй, шууд дуудна. **Асуулт:** Нэмнэ үү, үгүй юү? → **Нэмнэ.** Parent дээр `describe()` method байхгүй тул subclass-д зарлахдаа `@Override` биш энгийн method бичнэ. Тест нь `Character c = new Warrior(...); c.describe()` гэж дуудаж шалгахгүй — зөвхөн `Warrior w = ...; w.describe()` л дуудна.

- `Warrior.describe()` → `"wields a sword"`
- `Mage.describe()` → `"channels magic"`
- `Rogue.describe()` → `"sneaks in shadow"`

### 7. `Enemy extends Character`

- Constructor: `super(name, hp)`
- `@Override attack(Character target)` → **12 хохирол**
- `@Override takeDamage(int amount)` → `super.takeDamage(amount)` дуудаад дараа нь `System.out.println(name + " roars!")` хэвлэнэ

### 8. `Battle.describeParty(Character[] party) → String`

- Energetic бүрт `describe()` дуудаад үр дүнг `" | "` тусгаарлагчаар нийлүүлнэ
- Жишээ: `"wields a sword | channels magic | sneaks in shadow"`

> **Тайлбар:** Stretch тестэд Character[] party-д зөвхөн Warrior/Mage/Rogue багтана. `describe()` нь тэдгээрт байх ёстой. Pattern matching эсвэл шууд method call-аар шийдэж болно.

---

## 🔴 Bonus tasks (10 оноо)

### 9. `AttackStrategy` interface

```java
public interface AttackStrategy {
    int calculateDamage();
}
```

- `Character`-д **strategy талбар нэмэх боломжгүй** (өгөгдсөн файлыг өөрчилдөггүй), тул bonus хэсэгт хэрхэн хэрэгжүүлэх нь таны шийдэх зүйл. Зөвшөөрөгдсөн арга:
  - Шинэ class `StrategicCharacter extends Character` үүсгээд, `private AttackStrategy strategy`, `setStrategy(AttackStrategy)`, `@Override attack(Character)` гэж дотор нь strategy ашиглах.
  - Strategy null бол default 10 хохирол.

### 10. `Battle.countMages(Character[] party) → int`

- `instanceof` pattern matching-ээр Mage instance-уудыг тоолно:

```java
int count = 0;
for (Character c : party) {
    if (c instanceof Mage m) {
        count++;
    }
}
return count;
```

---

## 🧪 Тест ажиллуулах

```bash
# Бүх tier
bash scripts/run_tests.sh

# Зөвхөн core
bash scripts/run_tests.sh --tag core

# Зөвхөн stretch
bash scripts/run_tests.sh --tag stretch

# Зөвхөн bonus
bash scripts/run_tests.sh --tag bonus
```

---

## ✅ Шалгуурын жагсаалт (Checklist)

### Core
- [ ] `Warrior extends Character` + `attack` 20 damage
- [ ] `Mage extends Character` + `attack` 15 damage
- [ ] `Rogue extends Character` + `attack` 25 damage
- [ ] `Battle.partyAttack` полиморф итерац, нийлбэр буцаах
- [ ] Dynamic binding: `Character c = new Warrior(...); c.attack(e)` → 20

### Stretch
- [ ] `Enemy extends Character` + override attack/takeDamage
- [ ] `Warrior/Mage/Rogue.describe()`
- [ ] `Battle.describeParty` concat

### Bonus
- [ ] `AttackStrategy` interface + `StrategicCharacter` runtime swap
- [ ] `Battle.countMages` + `instanceof` pattern matching

---

## 🚫 Түгээмэл алдаанууд

1. **`@Override` орхих** — compiler-т тусгай warning өгдөг, гэхдээ method-ийн нэр, параметр буруу бол catch хийж чадахгүй. Бичсэн нь дээр.
2. **`extends Character` бичихгүй** — subclass биш бол polymorphism ажиллахгүй
3. **Constructor-д `super(...)` орхих** — compile алдаа өгнө
4. **`attack`-аас void буцаах** — `int` буцаах ёстой (хохирлын тоо)
5. **Parent-ийн default 10 damage-г override хийгээгүй** — Warrior гэхдээ 10 damage л учруулж байгаа
6. **`Character.java`-г өөрчлөх** — бүү өөрчил, auto-grader файлыг эх хувиарь нь ашигладаг
7. **`countMages`-д `c.getClass() == Mage.class`** — энэ нь ажиллах ч pattern matching хэрэглэвэл илүү сайн
