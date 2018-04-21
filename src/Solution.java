import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Solution {
    //for problem 1
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for(int i = 0; i < nums.length-1; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return null;
    }

    //for problem 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1.next;
        ListNode temp2 = l2.next;
        ListNode res = new ListNode((l1.val+l2.val)%10);
        ListNode temp = res;
        int flag = (l1.val+l2.val)/10;
        while(temp1 != null || temp2 != null) {
            int num1 = (temp1 == null ? 0 : temp1.val);
            int num2 = (temp2 == null ? 0 : temp2.val);
            temp.next = new ListNode((num1+num2+flag)%10);
            flag = (num1+num2+flag)/10;
            temp = temp.next;
            temp1 = (temp1 == null ? null : temp1.next);
            temp2 = (temp2 == null ? null : temp2.next);
        }
        if(flag > 0) {
            temp.next = new ListNode(flag);
        }
        return res;
    }

    //for problem 3
    public int lengthOfLongestSubstring(String s) {
        if(s.equals("")) {
            return 0;
        }
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        int start = 0;
        int len = 0;
        int res = 1;
        char repeated = s.charAt(0);
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(charMap.containsKey(temp)) {
                if (charMap.get(temp) >= start) {
                    if (len > res) {
                        res = len;
                    }
                    start = (repeated == temp ? start + 1 : charMap.get(temp) + 1);
                    repeated = s.charAt(start);
                    len = i - start;
                }
            }
            charMap.put(temp, i);
            len++;
        }
        if(len > res) {
            res = len;
        }
        return res;
    }
}
