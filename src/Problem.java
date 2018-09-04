import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Problem {
    private Solution solution;
    private Scanner scanner;

    public Problem() {
        solution = new Solution();
        scanner = new Scanner(System.in);
    }

    public void solve(int problemNum) {
        switch (problemNum) {
            case 1:
                twoSum();
                break;
            case 2:
                addTwoNumbers();
                break;
            case 3:
                lengthOfLongestSubstring();
                break;
            case 5:
                longestPalindrome();
            default:
                break;
        }
    }

    //for problem 1
    private void twoSum() {
        int len = scanner.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = scanner.nextInt();
        }
        int target = scanner.nextInt();
        int[] res = solution.twoSum(nums, target);
        System.out.println(((Integer) res[0]).toString()+" "+ ((Integer) res[1]).toString());
    }

    //for problem 2
    private void addTwoNumbers() {
        ListNode l1, l2;
        int len1 = scanner.nextInt();
        int len2 = scanner.nextInt();
        l1 = new ListNode(scanner.nextInt());
        ListNode temp1 = l1;
        for(int i = 0; i < len1-1; i++) {
            temp1.next = new ListNode(scanner.nextInt());
            temp1 = temp1.next;
        }
        l2 = new ListNode(scanner.nextInt());
        ListNode temp2 = l2;
        for(int i = 0; i < len2-1; i++) {
            temp2.next = new ListNode(scanner.nextInt());
            temp2 = temp2.next;
        }
        ListNode res = solution.addTwoNumbers(l1, l2);
        ListNode temp = res;
        while(temp != null) {
            System.out.print(((Integer) temp.val).toString()+" ");
            temp = temp.next;
        }
    }

    //for problem 3
    private void lengthOfLongestSubstring() {
        String s = scanner.nextLine();
        int res = solution.lengthOfLongestSubstring(s);
        System.out.print(res);
    }

    //for problem 5
    private void longestPalindrome() {
        String s = scanner.nextLine();
        String res = solution.longestPalindrome(s);
        System.out.print(res);
    }
}
