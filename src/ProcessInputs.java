import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ProcessInputs {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("C:\\Users\\kambv\\Desktop\\merchants-guide-to-galaxy\\TW.txt");
        try (Scanner sc = new Scanner(file)) {
            Map<String, ElementValue> elementsMap = new HashMap<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line != null && !line.isEmpty()) {
                    InputParser.parse(line, elementsMap);
                }
            }
        }
    }
}