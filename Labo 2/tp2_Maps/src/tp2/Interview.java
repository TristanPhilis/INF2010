package tp2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Interview {

    /** TODO Worst Case : O(n)
     * Is valid if you flip the number upside down.
     */
    public static boolean isValidFlipped(String listOfNumbers) {
        String flipped = "";


        for(int i = listOfNumbers.length() - 1; i >= 0; i--){
            if(i == 3 || i == 4 || i == 7) {
                return false;
            } else{
                flipped.concat(Character.toString(listOfNumbers.charAt(i)));
            }
        }
        return flipped.equals(listOfNumbers);
    }

    /** TODO Worst Case : O(n)
     * Could be valid if you try to flip the number upside down with one of the combinations.
     */
    public static boolean isValidFlippedWithPermutation(String listOfNumbers) {

        return false;
    }
}
