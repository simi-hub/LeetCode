package Easy;
import java.util.*;

public class LeetEasy {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Array cannot be empty or null");

        int index1 = 0;
        int index2 = 0;
        int[] arr = new int[2];//creates a new array
        for (int i = 0; i < nums.length - 1; i++) {//traversing array from left
            for (int j = i + 1; j < nums.length; j++) {//traversing array from right
                if (nums[i] + nums[j] == target) {
                    index1 = i;
                    index2 = j;
                }
            }
        }
        arr[0] = index1;//stores both index in array
        arr[1] = index2;
        return arr;
    }

    public boolean isPalindrome(int x) {
        boolean status = false;
        if (x < 0 || x >= Integer.MAX_VALUE)
            return false;//constraints

        int reverse = 0, digit, original = x;

        while (x >= 1) {
            digit = x % 10;//finds the last digit in the number
            x /= 10; //number separated from last digit
            reverse = reverse * 10 + digit;
        }

        if (original == reverse)
            status = true;

        return status;
    }

    /**
     * @param num1,num2 are numbers to be added
     *                  simple method that sums and adds 2 numbers
     */
    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * building a new array of the same length, but new array does not have content of array but rather, stores index of
     * old array
     *
     * @param nums is old array
     * @return answer is new array that stores index of old array not content
     */
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    public int romanToInt(String letter) {
        if (letter == null)
            throw new NullPointerException("Letter cannot be null");

        int answer, previous, current;
        previous = getNum(letter.charAt(0));
        answer = previous;

        for (int i = 1; i < letter.length(); i++) {
            current = getNum(letter.charAt(i));//gets the letter at a specific index
            if (current > previous)
                answer += current - 2 * previous;//actual brain and algorithm logic
            else
                answer += current;
            previous = current;
        }
        return answer;
    }

    public int getNum(char k) {//helper method for romanToInt()
        return switch (k) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    /**
     * returns an array twice the size of param and duplicates content of param
     */
    public int[] getConcatenation(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("array cannot be empty or null");

        int[] ans = new int[2 * nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /**
     * @param s string to check if bracket is valid
     *          functions checks if brackets are followed by each other and open brackets are closed by bracket of same type
     */
    public boolean isValid(String s) {
        boolean check = false;

        if (s.length() == 0 || s.length() == 1)
            return false;//constraints

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')')
                check = true;
            else if (s.charAt(i) == '[' && s.charAt(i + 1) == ']')
                return true;
            else if (s.charAt(i) == '{' && s.charAt(i + 1) == '}')
                return true;
        }
        return check;
    }
    public int removeElement(int[] nums, int val) {
        int i,j = 0;

        if(nums.length == 0)
            return 0;

        for(i = 0; i < nums.length; i++){
            if(nums[i]!=val)
                nums[j++] = nums[i];

        }

        return j;
    }

    public int strStr(String haystack, String needle) {
        if(needle== "")
            return 0;

        if (!haystack.contains(needle))
            return -1;

        int n = needle.length();

        if(haystack.contains(needle.substring(0,n)))
            return haystack.indexOf(needle.substring(0));

        return 0;
    }



    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7};
        LeetEasy obj = new LeetEasy();
        int[] answer = obj.twoSum(num, 9);
        int[] answer1 = obj.buildArray(num);
        int[] answer2 = obj.getConcatenation(num);
        System.out.println(obj.isValid("(]"));
        System.out.println(Arrays.toString(answer));
        System.out.println(Arrays.toString(answer1));
        System.out.println(Arrays.toString(answer2));
        System.out.println(obj.isPalindrome(121));
        System.out.println(obj.sum(2, -2));
        System.out.println(obj.romanToInt("MCMXCIV"));
    }
}
/*THIS USES THE STACK DATA STRUCTURE TO HANDLE ISVALID()
    public boolean isValid(String s) {
             Stack<Character> leftSymbols = new Stack<>();
            // Loop for each character of the string
            for (char c : s.toCharArray()) {
                // If left symbol is encountered
                if (c == '(' || c == '{' || c == '[') {
                    leftSymbols.push(c);
                }
                // If right symbol is encountered
                else if (c == ')' && !leftSymbols.isEmpty() && leftSymbols.peek() == '(') {
                    leftSymbols.pop();
                } else if (c == '}' && !leftSymbols.isEmpty() && leftSymbols.peek() == '{') {
                    leftSymbols.pop();
                } else if (c == ']' && !leftSymbols.isEmpty() && leftSymbols.peek() == '[') {
                    leftSymbols.pop();
                }
                // If none of the valid symbols is encountered
                else {
                    return false;
                }
            }
            return leftSymbols.isEmpty();
    }
*
* */