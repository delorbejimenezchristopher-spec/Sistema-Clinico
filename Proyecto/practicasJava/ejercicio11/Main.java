
public class Main {

    public static void main(String[] args) {

        LanguageList languageList = new LanguageList();

        System.out.println(languageList.isEmpty());

        languageList.addLanguage("Kotlin");
        languageList.addLanguage("Python");
        languageList.addLanguage("Scala");

        System.out.println(languageList.isEmpty());
        System.out.println(languageList.firstLanguage());
        System.out.println(languageList.count());
        System.out.println(languageList.containsLanguage("Python"));
        System.out.println(languageList.containsLanguage("Ruby"));

        languageList.removeLanguage("Scala");

        System.out.println(languageList.count());

        languageList.addLanguage("Java");

        System.out.println(languageList.isExciting());
    }
}
