public class IsbnNormalizerValidator {

    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }

        String pubCodeUpper = trimmed.substring(0, 3).toUpperCase();
        String remainder = trimmed.substring(3);

        return pubCodeUpper + remainder;
    }

    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Check first 3 characters (Publisher Code) for letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters for digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: remaining body must be digits";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] ");
        sb.append("YEAR: ").append(year).append(" | ");
        sb.append("CATALOG: ").append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        String rawInput1 = " pen2026004251 ";
        String normalized1 = normalizeCode(rawInput1);
        System.out.println(validateAndFormat(normalized1));

        String rawInput2 = "12N2026004251";
        String normalized2 = normalizeCode(rawInput2);
        System.out.println(validateAndFormat(normalized2));
    }
}
