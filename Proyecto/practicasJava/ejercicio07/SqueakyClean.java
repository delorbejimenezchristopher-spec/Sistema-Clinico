public class SqueakyClean {

    public static String clean(String identifier) {

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (int i = 0; i < identifier.length(); i++) {

            char character = identifier.charAt(i);

            // Leetspeak
            switch (character) {
                case '4':
                    character = 'a';
                    break;
                case '3':
                    character = 'e';
                    break;
                case '0':
                    character = 'o';
                    break;
                case '1':
                    character = 'l';
                    break;
                case '7':
                    character = 't';
                    break;
            }

            // Espacio
            if (character == ' ') {
                result.append('_');
            }

            // Kebab-case
            else if (character == '-') {
                capitalizeNext = true;
            }

            // Letras
            else if (Character.isLetter(character)) {

                if (capitalizeNext) {
                    result.append(Character.toUpperCase(character));
                    capitalizeNext = false;
                } else {
                    result.append(character);
                }
            }
        }

        return result.toString();
    }
}