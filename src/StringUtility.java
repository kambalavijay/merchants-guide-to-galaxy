import java.util.Set;

public class StringUtility {

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    public static int indexOfIgnoreCase(String str, String subString) {
        return str.toLowerCase().indexOf(subString.toLowerCase());
    }

    public static boolean containsAllSetValuesIgnoreCase(String str, Set<String> values) {
        int count = 0;
        for (String value : values) {
            if (str.toLowerCase().contains(value.toLowerCase())){
                count++;
            }
        }
        return count == values.size();
    }

    public static boolean containsAnyOfSetValuesIgnoreCase(String str, Set<String> values) {
        for (String value : values) {
            if (str.toLowerCase().contains(value.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static String extractNumber(final String str) {

        if(str == null || str.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }
        return sb.toString();
    }

    public static String getMetalFromElements(String elements, int endIndex){
        elements = elements.substring(0, endIndex);
        String metal = elements.split(" ")[elements.split(" ").length - 1];
        return metal;
    }
}