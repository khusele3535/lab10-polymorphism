# 🐉 Lab 10 — Polymorphism: Battle System

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=junit5)
![Auto-Grader](https://img.shields.io/badge/Auto--Grader-Enabled-blue)
![AI Detection](https://img.shields.io/badge/AI%20Detection-Enabled-red)

> Dungeon of OOP-ын тулааны талбарт олон төрлийн баатрууд зэрэгцэн зогсож байна — илдэчин, шидтэн, сүүдэрт нуугдагч. Гэвч бүгд нэг л командын адилаар `attack(enemy)` гэж дууддаг бол, яаж өөр өөр хохирол учруулах вэ? Хариулт нь **polymorphism** — нэг интерфэйс, олон хэлбэр. Энэ лаборатораар чи method overriding, dynamic binding, upcasting-ийг гартаа суулган, `Character[]` массив доторх баатруудаа полиморф байдлаар тулалдуулна.

## 📚 Суралцах материал

- **Теори:** [`UEFA-OPP-resources/docs/week-10-polymorphism/`](https://github.com/UEFA-OPP/UEFA-OPP-resources/tree/main/docs/week-10-polymorphism)
- **Git workflow заавар:** [`UEFA-OPP-resources/docs/git-workflow/`](https://github.com/UEFA-OPP/UEFA-OPP-resources/tree/main/docs/git-workflow)

## 🏗️ Хавтасны бүтэц

```
lab10-template/
├── README.md                          # Энэ файл
├── .gitignore
├── assignments/
│   └── battle/
│       ├── Character.java             # ← Өгөгдсөн (бүү өөрчил)
│       ├── Warrior.java               # ← Та энд код бичнэ
│       ├── Mage.java                  # ← Та энд код бичнэ
│       ├── Rogue.java                 # ← Та энд код бичнэ
│       ├── Battle.java                # ← Та энд код бичнэ
│       └── README.md                  # Даалгаврын дэлгэрэнгүй заавар
├── tests/
│   └── BattleTest.java                # JUnit 5 тестүүд (бүү өөрчил)
├── scripts/
│   ├── run_tests.sh                   # Тест ажиллуулах скрипт
│   └── ai_detector.py                 # AI илрүүлэгч
└── .github/workflows/grade.yml        # GitHub Actions автомат шалгагч
```

## 🚀 Лаб хийх заавар (Алхам алхмаар)

### Алхам 1: Repo-г Fork хийх

1. Браузераар [`UEFA-OPP/lab10-template`](https://github.com/UEFA-OPP/lab10-template) руу орно
2. Баруун дээд буланд **Fork** товч дарна
3. Owner-ээр өөрийн account-ийг сонгоод **Create fork** дарна
4. Одоо `https://github.com/<таны-username>/lab10-template` гэсэн хуулбартай боллоо

### Алхам 2: Компьютер дээрээ Clone хийх

```bash
git clone https://github.com/<таны-username>/lab10-template.git
cd lab10-template
```

> SSH key тохируулсан бол `git@github.com:<таны-username>/lab10-template.git` ашиглаж болно.

### Алхам 3: Өөрийн нэрээр branch үүсгэх

```bash
# Жишээ: git checkout -b lab10/bat-erdene
git checkout -b lab10/<өөрийн-нэр>
```

> **Яагаад branch вэ?** `main` branch-д шууд push хийвэл PR үүсгэх боломжгүй. Заавал шинэ branch дээр ажиллана.

### Алхам 4: Даалгаврын зааврыг унших

```bash
cat assignments/battle/README.md
```

Энд баатар бүр ямар хохирол учруулах, `Battle.partyAttack` яаж ажиллах, override-ийн яг байрлалууд зэргийг дэлгэрэнгүй бичсэн байгаа.

### Алхам 5: Код бичих

`assignments/battle/` доторх `Warrior.java`, `Mage.java`, `Rogue.java`, `Battle.java` файлуудын бүх `// TODO` комментыг өөрийн кодоор соль. Ядаж дараах түвшнүүдийг хийж үзнэ үү:

- 🟢 **Core (60 оноо)** — 3 баатрын overriding, `Battle.partyAttack`, dynamic binding
- 🟡 **Stretch (30 оноо)** — `Enemy`, `describe()` polymorphism, `Battle.describeParty`
- 🔴 **Bonus (10 оноо)** — `AttackStrategy` interface, `instanceof` pattern matching

### Алхам 6: Локал тест ажиллуулах

```bash
# Бүх тестийг ажиллуулах
bash scripts/run_tests.sh

# Тодорхой tier дангаар шалгах
bash scripts/run_tests.sh --tag core
bash scripts/run_tests.sh --tag stretch
bash scripts/run_tests.sh --tag bonus
```

**Жишээ output:**
```
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  Lab 10: Battle System
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
[core]    ✓ 9/9 tests passed  → 60.0/60
[stretch] ✓ 3/3 tests passed  → 30.0/30
[bonus]   △ 1/2 tests passed  → 5.0/10
─────────────────────────────────────
НИЙТ ОНОО: 95.0 / 100
```

### Алхам 7: Commit хийх

```bash
git add assignments/
git commit -m "Implement Battle System - <your name>"
```

> **Анхаар:** `tests/`, `scripts/`, `.github/` хавтсуудыг өөрчлөх/commit хийх хэрэггүй. Зөвхөн `assignments/battle/` доторх файлуудаа илгээнэ.
>
> Commit message-ийг **англиар** бичнэ (course convention).

### Алхам 8: GitHub руу Push хийх

```bash
git push origin lab10/<өөрийн-нэр>
```

### Алхам 9: Pull Request (PR) үүсгэх

1. `https://github.com/<таны-username>/lab10-template` руу орно
2. Шар өнгийн **"Compare & pull request"** товч дарна
3. Товч байхгүй бол: **Pull requests** → **New pull request**
   - **base repository:** `UEFA-OPP/lab10-template` | **base:** `main`
   - **head repository:** `<таны-username>/lab10-template` | **compare:** `lab10/<өөрийн-нэр>`
4. PR title-д **өөрийн нэр, бүлгийг** бичнэ. Жишээ: `Bat-Erdene - SE401`
5. **Create pull request** дарна

### Алхам 10: Автомат шалгалтын дүнг харах

PR үүсгэсний дараа GitHub Actions автоматаар ажиллана:

1. PR хуудасны доод талд **Checks** хэсэг гарна
2. ⏳ = ажиллаж байна | ✅ = амжилттай | ❌ = алдаатай
3. **Details** дарж дэлгэрэнгүй дүнг харна
4. PR-т автоматаар коммент бичигдэнэ:

| Tier | Tests | Score |
|------|-------|-------|
| 🟢 Core | 9/9 | 60.0 / 60 |
| 🟡 Stretch | 3/3 | 30.0 / 30 |
| 🔴 Bonus | 1/2 | 5.0 / 10 |
| **Total** | | **95.0 / 100** |
| AI Detection | | ✅ LOW (6) |

> **Алдаатай бол?** Кодоо засаад дахин commit + push хийнэ. PR автоматаар шинэчлэгдэж, тест дахин ажиллана.

## 📊 Оноо тооцох систем

| Tier | Жин | Тайлбар |
|------|-----|---------|
| 🟢 **Core** | **60%** | Үндсэн polymorphism — 3 баатрын override, `partyAttack`, dynamic binding |
| 🟡 **Stretch** | **30%** | Нэмэлт дэд класс `Enemy`, polymorphic `describe()` |
| 🔴 **Bonus** | **10%** | Strategy interface, `instanceof` pattern matching |

**Формула:**
```
score = (core_passed / core_total) * 60
      + (stretch_passed / stretch_total) * 30
      + (bonus_passed / bonus_total) * 10
```

## 🤖 AI Detection policy

AI detector кодын 11 шалгуурыг шинжилж оноо өгнө (0-121):

| Оноо | Түвшин | Үр дагавар |
|------|--------|------------|
| 0-19 | ✅ **LOW** | Асуудалгүй. Сайн! |
| 20-39 | ⚠️ **MEDIUM** | Багш кодыг шалгана. Хариулт хүсч магадгүй. |
| 40+ | 🚨 **HIGH** | Онооноос **50% хасна**. Повторный submission шаардлагатай. |

## ⚠️ Дүрэм

1. **Тест файлыг өөрчлөхгүй** — `tests/BattleTest.java`-г хөндөхгүй
2. **`Character.java`-г өөрчлөхгүй** — энэ бол parent class, өгөгдсөн
3. **Зөвхөн `Warrior.java`, `Mage.java`, `Rogue.java`, `Battle.java`-д код бичнэ**
4. **AI ашиглахгүй** — ChatGPT, Copilot, Claude, Gemini зэргийг хэрэглэхгүй
5. **Өөрийн branch дээр ажиллана** (`main` биш)
6. **Deadline-аа баримтална** — хожимдуулсан submission оноо хасагдана
7. **Commit message, код — англиар** | **Коммент — англи/монгол хамаагүй**

## 🛠️ Шаардлага

- **Java 17+** — `java -version` гэж шалгана
- **Python 3.11+** — `python3 --version` (AI detector ажиллуулахад)
- **Bash** — тест скрипт ажиллуулахад
- **curl** — JUnit jar автомат татахад
- **Git** — clone, commit, push хийхэд

## 📞 Асуулт байвал

Багшаасаа асуу. Discord / classroom channel-аар бичиж болно. Амжилт хүсье, адвенчурер! 🗡️🛡️
