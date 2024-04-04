package midterm_1_practice;

public class Palindrome {
    
    public static boolean isPalindrome(String input) {
        int n = input.length();
        char[] inputarray = new char[n];
        for (int i = 0; i < n; i++) {
            inputarray[i] = input.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            if (inputarray[i] != inputarray[n-i-1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("was it a cat or a car i saw"));
        System.out.println(isPalindrome("hannah"));
        System.out.println(isPalindrome("step on no pets"));
    }
}
