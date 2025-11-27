package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in, "UTF-8")) {
            Translator translator = new Translator(); // Створюємо екземпляр перекладача
            
            // Слова
            translator.addWord("no", "ні");
            translator.addWord("yes", "так");
            translator.addWord("hello", "привіт");
            translator.addWord("my", "мій");
            translator.addWord("name", "ім'я");
            translator.addWord("is", "є");
            
            while (true) {  // Цикл для додавання нових слів
                System.out.println("\n Хочете додати нові слова в словник? (так/ні)");
                String ans = sc.nextLine().trim().toLowerCase();
                
                if (ans.equals("no")) // Якщо користувач відповів ні, виходимо з циклу
                    break;
                
                System.out.print("\n Введіть англійське слово: "); // Просим ввести англійське слово
                String eng = sc.nextLine().trim().toLowerCase(); // Зберігаєм слово
                
                System.out.print("\n Введіть український переклад: ");
                String ukr = sc.nextLine().trim().toLowerCase();
                
                translator.addWord(eng, ukr);// Додаємо у словник
                System.out.println("Додано: " + eng + " -> " + ukr + "\n");
                
                
                translator.printDictionary(); // виводимо весь словника
            }
            
            System.out.println("Введіть фразу англійською для перекладу:");
            String phrase = sc.nextLine();
            System.out.println("Переклад українською: " + translator.translate(phrase));
        }
    }
}

class Translator {
    private final Map<String, String> dictionary = new HashMap<>();

    public void addWord(String english, String ukrainian) { // Метод для додавання нових слів у словник
        dictionary.put(english.toLowerCase(), ukrainian);
    }

    public String translate(String phrase) { // Метод для перекладу фрази
        StringBuilder result = new StringBuilder();
        String[] words = phrase.split("\\s+"); // Розбиваємо фразу на окремі слова
        for (String w : words) {
            String key = w.toLowerCase();
            result.append(dictionary.getOrDefault(key, w)).append(" "); //Якщо слово є у словнику, додаємо переклад, інакше залишаємо оригінал
        }
        return result.toString().trim(); // Повертаємо перекладену фразу
    }

    public void printDictionary() { // Метод для друку всього словника
        System.out.println("Словник зараз містить:");
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
