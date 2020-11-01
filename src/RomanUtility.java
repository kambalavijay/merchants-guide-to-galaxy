import java.util.Map;
import java.util.regex.Pattern;

public class RomanUtility {

    private static Pattern p = Pattern.compile("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    public static boolean isRoman(String romanString){
        return p.matcher(romanString).matches();
    }

    public static String convertElementsToRoman(String elementsString, Map<String, ElementValue> elementsMap){
        String[] elements = elementsString.split(" ");
        StringBuilder roman = new StringBuilder();
        for (int i =0; i < elements.length; i++){
            ElementValue elementValue = elementsMap.get(elements[i]);
            if(!elementValue.getIsMetal()){
                roman.append(elementValue.getRomanCorr());
            }
        }
        return roman.toString();
    }

    public static int convertRomanToDecimal(String roman){
        // Initialize result
        if(!RomanUtility.isRoman(roman)){
            return 0;
        }
        int res = 0;
        for (int i=0; i<roman.length(); i++) {
            // Getting value of symbol s[i]
            int s1 = valueOf(roman.charAt(i));
            // Getting value of symbol s[i+1]
            if (i+1 <roman.length()) {
                int s2 = valueOf(roman.charAt(i+1));
                // Comparing both values
                if (s1 >= s2) {
                    // Value of current symbol is greater
                    // or equalto the next symbol
                    res = res + s1;
                }
                else {
                    res = res + s2 - s1;
                    i++; // Value of current symbol is
                    // less than the next symbol
                }
            }
            else {
                res = res + s1;
                i++;
            }
        }

        return res;

    }

    private static int valueOf(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }
}
