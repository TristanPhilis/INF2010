package tp2;

import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Interview {

    /** TODO Worst Case : O(n)
     * Is valid if you flip the number upside down.
     */
    public static boolean isValidFlipped(String listOfNumbers) {
        String flipped = "";

        for(int i = listOfNumbers.length() - 1; i >= 0; i--){
            if(listOfNumbers.charAt(i) == '3' || listOfNumbers.charAt(i) == '4' || listOfNumbers.charAt(i) == '7') {
                return false;
            } else if(listOfNumbers.charAt(i) == '6') {
                flipped = flipped + '9';
            } else if(listOfNumbers.charAt(i) == '9') {
                flipped = flipped + '6';
            } else {
                flipped = flipped + (listOfNumbers.charAt(i));
            }
        }
        return flipped.equals(listOfNumbers);
    }


    /** TODO Worst Case : O(n)
     * Could be valid if you try to flip the number upside down with one of the combinations.
     */
    public static boolean isValidFlippedWithPermutation(String listOfNumbers) {
        if(listOfNumbers.length() == 0){return true;}//vide

        char[] indivNumbers = listOfNumbers.toCharArray();
        HashMap<Character, Integer> numberAmount;
        numberAmount = new HashMap<>();
        numberAmount.put('0', 0);
        numberAmount.put('1', 0);
        numberAmount.put('2', 0);
        numberAmount.put('3', 0);
        numberAmount.put('4', 0);
        numberAmount.put('5', 0);
        numberAmount.put('6', 0);
        numberAmount.put('7', 0);
        numberAmount.put('8', 0);
        numberAmount.put('9', 0);


        int temp = 0;
        int len = listOfNumbers.length();
        for (int i = 0; i < listOfNumbers.length(); i++){
            temp = numberAmount.get(indivNumbers[i]);
            numberAmount.put(indivNumbers[i], ++temp);
        }

        if (numberAmount.get('3') + numberAmount.get('4') + numberAmount.get('7') != 0) {
            return false;
        } else if (!numberAmount.get('6').equals(numberAmount.get('9'))) {
            return false;
        } else if (numberAmount.get('0') % 2 + numberAmount.get('1') % 2 + numberAmount.get('2') % 2 +
                numberAmount.get('5') % 2 + numberAmount.get('8') % 2 > 1) {
            return false;
        }

        return true;
    }
}
