import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InputParser {

    private static Set<String> questionSearchWords;
    private static Set<String> keySearchWords;

    static {
        keySearchWords = new HashSet<>();
        keySearchWords.add("how much");
        keySearchWords.add("how many");
        keySearchWords.add("credits");

        questionSearchWords = new HashSet<>();
        questionSearchWords.add("how much");
        questionSearchWords.add("how many");
        questionSearchWords.add(" ?");
    }

    public static void parse(String line, Map<String, ElementValue> elementsMap) {

        String roman = line.split(" ")[line.split(" ").length-1];
        String number = StringUtility.extractNumber(line);
        int numberIndex = StringUtility.indexOfIgnoreCase(line, number);
        int romanIndex = StringUtility.indexOfIgnoreCase(line, roman);
        int isIndex = StringUtility.indexOfIgnoreCase(line, " is");
        int howMuchIndex = StringUtility.indexOfIgnoreCase(line, "how much ");
        int howManyIndex = StringUtility.indexOfIgnoreCase(line, "how many ");
        int questionMarkIndex = StringUtility.indexOfIgnoreCase(line, " ?");
        int creditsIndex = StringUtility.indexOfIgnoreCase(line, " credits");

        if(isIndex != -1 && RomanUtility.isRoman(roman) && romanIndex != -1 &&
                isIndex < romanIndex &&
                !StringUtility.containsAllSetValuesIgnoreCase(line, keySearchWords)) {

            ElementValue elementValue = new ElementValue();
            elementValue.setIsMetal(false);
            elementValue.setRomanCorr(roman);
            elementValue.setValue(RomanUtility.convertRomanToDecimal(roman));
            elementsMap.put(line.split(" ")[0], elementValue);
        }
        else if(isIndex != -1 && numberIndex != -1 && creditsIndex != -1 &&
                isIndex < numberIndex && numberIndex < creditsIndex &&
                !StringUtility.containsAllSetValuesIgnoreCase(line, questionSearchWords)) {

            String elements = line.substring(0, isIndex);
            String metal = elements.split(" ")[elements.split(" ").length - 1];
            int metalIndex = elements.indexOf(metal);
            elements = elements.substring(0, --metalIndex);
            String[] elementsArray = elements.split(" ");
            StringBuilder romanEquivalent = new StringBuilder();

            if(StringUtility.containsAnyOfSetValuesIgnoreCase(elements, elementsMap.keySet())){
                for (int i = 0; i < elementsArray.length; i++){
                    ElementValue elementValue = elementsMap.get(elementsArray[i]);
                    if(elementValue != null && !elementValue.getIsMetal()){
                        romanEquivalent.append(elementValue.getRomanCorr());
                    }
                }
                int num = Integer.parseInt(number);
                int decimalEquivalent = RomanUtility.convertRomanToDecimal(romanEquivalent.toString());
                if(decimalEquivalent != 0){
                    ElementValue elementValue = new ElementValue();
                    elementValue.setRomanCorr("");
                    elementValue.setIsMetal(true);
                    elementValue.setValue((float)num/decimalEquivalent);
                    elementsMap.put(metal, elementValue);
                }
                else{
                    System.out.println("I have no ide what you are talking about...");
                }
            }
            else{
                System.out.println("I have no ide what you are talking about...");
            }
        }
        else if(howMuchIndex != -1 && isIndex != -1 && questionMarkIndex != -1 &&
                howManyIndex < isIndex && isIndex < questionMarkIndex){

            String elements = line.substring(isIndex+4, questionMarkIndex);
            String[] elementsArray = elements.split(" ");
            StringBuilder romanEquivalent = new StringBuilder();

            if(StringUtility.containsAnyOfSetValuesIgnoreCase(elements, elementsMap.keySet())){
                for (int i = 0; i < elementsArray.length; i++){
                    ElementValue elementValue = elementsMap.get(elementsArray[i]);
                    if(elementValue != null && !elementValue.getIsMetal()){
                        romanEquivalent.append(elementValue.getRomanCorr());
                    }
                }
                int decimalEquivalent = RomanUtility.convertRomanToDecimal(romanEquivalent.toString());
                if (decimalEquivalent != 0) {
                    System.out.println(elements + " is " + decimalEquivalent);
                }
                else{
                    System.out.println("I have no ide what you are talking about...");
                }
            }
            else{
                System.out.println("I have no ide what you are talking about...");
            }
        }
        else if(howManyIndex != -1 && creditsIndex != -1 &&  isIndex!= -1 && questionMarkIndex != -1 &&
                (howManyIndex < creditsIndex) && (creditsIndex < isIndex) && (isIndex < questionMarkIndex)) {

            String elements = line.substring(isIndex+4, questionMarkIndex);
            String metal = elements.split(" ")[elements.split(" ").length - 1];
            int metalIndex = elements.indexOf(metal);
            elements = elements.substring(0, --metalIndex);
            String[] elementsArray = elements.split(" ");
            StringBuilder romanEquivalent = new StringBuilder();

            if(StringUtility.containsAnyOfSetValuesIgnoreCase(elements, elementsMap.keySet())){
                for (int i = 0; i < elementsArray.length; i++){
                    ElementValue elementValue = elementsMap.get(elementsArray[i]);
                    if(elementValue != null && !elementValue.getIsMetal()){
                        romanEquivalent.append(elementValue.getRomanCorr());
                    }
                }
                int decimalEquivalent = RomanUtility.convertRomanToDecimal(romanEquivalent.toString());
                int credits = (int) ((int) decimalEquivalent * elementsMap.get(metal).getValue());
                System.out.println(elements + " " + metal + " is " + credits + " Credits");
            }
            else{
                System.out.println("I have no ide what you are talking about...");
            }
        }
        else{
            System.out.println("I have no ide what you are talking about...");
        }
    }
}