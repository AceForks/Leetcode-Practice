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

    //for problem 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int i = 0;
        int head1 = 0, head2 = 0;
        double a = 0, b = 0;
        while(i <= (len1+len2)/2) {
            double temp;
            if(head1 >= len1) {
                temp = 1.0*nums2[head2];
                head2++;
            } else if(head2 >= len2) {
                temp = 1.0*nums1[head1];
                head1++;
            } else if(nums1[head1] < nums2[head2]) {
                temp = 1.0*nums1[head1];
                head1++;
            } else {
                temp = 1.0*nums2[head2];
                head2++;
            }
            if(i == (len1+len2)/2) {
                b = temp;
            }
            i++;
            if(i == (len1+len2)/2) {
                a = temp;
            }
        }
        if((len1+len2)%2 == 0) {
            return (a+b)/2;
        } else {
            return b;
        }
    }

    //for problem 5
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len == 0)
            return s;
        boolean isPal[][] = new boolean[len+1][len+1];
        for(int i = 0; i < len+1; i++)
            isPal[i][i] = true;
        for(int i = 0; i < len; i++)
            isPal[i][i+1] = true;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(s.charAt(j) == s.charAt(i))
                    isPal[j][i+1] = isPal[j+1][i];
            }
        }
        int temp = 1, l = 0, r = 1;
        for(int i = 1; i < len+1; i++) {
            for(int j = i-temp-1; j >= 0; j--) {
                if(isPal[j][i]) {
                    l = j;
                    r = i;
                    temp = r-l;
                }
            }
        }
        return s.substring(l, r);
    }

    //for problem 6
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        int len = s.length();
        int flag = 0;
        int sign = 1;
        String res[] = new String[numRows];
        for(int i = 0; i < numRows; i++)
            res[i] = new String();
        for(int i = 0; i < len; i++) {
            res[flag] = res[flag].concat(s.substring(i, i+1));
            flag += sign;
            if(flag == 0 || flag == numRows-1)
                sign = -sign;
        }
        String ans = new String();
        for(int i = 0; i < numRows; i++)
            ans = ans.concat(res[i]);
        return ans;
    }

    //for problem 7
    public int reverse(int x) {
        if(x == 0)
            return 0;
        int sign = 1;
        if(x < 0) {
            sign = -1;
            x = -x;
        }
        String s1 = ((Integer)x).toString();
        String s2 = new String();
        for(int i = s1.length(); i > 0; i--)
            s2 = s2.concat(s1.substring(i-1, i));
        try {
            return Integer.parseInt(s2)*sign;
        } catch(Exception e) {
            return 0;
        }
    }

    //for problem 8
    public int myAtoi(String str) {
        if(str == null)
            return 0;
        int len = str.length(), p = 0;
        double res = 0, sign = 1;
        while(p < len && str.charAt(p) == ' ')
            p++;
        if(p >= len)
            return 0;
        if(str.charAt(p) == '+')
            p++;
        else if(str.charAt(p) == '-') {
            sign = -1;
            p++;
        } else if(!Character.isDigit(str.charAt(p)))
            return 0;
        while(p < len && Character.isDigit(str.charAt(p))) {
            res = res*10+(double)(str.charAt(p)-'0');
            p++;
        }
        res *= sign;
        if(sign == 1)
            return (res > (double)Integer.MAX_VALUE)?Integer.MAX_VALUE:(int)res;
        else
            return (res < (double)Integer.MIN_VALUE)?Integer.MIN_VALUE:(int)res;
    }

    //for problem 9
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        if(x == 0)
            return true;
        int[] num = new int[10];
        int len = 0;
        while(x > 0) {
            num[len++] = x%10;
            x /= 10;
        }
        for(int i = 0; i < len/2; i++) {
            if(num[i] != num[len-i-1])
                return false;
        }
        return true;
    }

    //for problem 10
    private boolean checkPos(char s, char p) {
        if(p == '.' || s == p)
            return true;
        return false;
    }

    public boolean isMatch(String s, String p) {
        if(s == null && p == null)
            return true;
        if(s != null && p == null)
            return false;
        if(s == null) {
            if(p.length()%2 == 0) {
                for(int i = 1; i < p.length(); i += 2) {
                    if(p.charAt(i) != '*')
                        return false;
                }
                return true;
            }
            return false;
        }
        int sLen = s.length(), pLen = p.length();
        int sPos = 0, pPos = 0;
        char nowCheck = '.';
        while(sPos < sLen && pPos < pLen) {
            if(p.charAt(pPos) == '*') {
                if(pPos == 0)
                    return false;
                if(isMatch(s.substring(sPos, sLen), p.substring(pPos+1, pLen)))
                    return true;
                if(!checkPos(s.charAt(sPos), nowCheck))
                    return false;
                sPos++;
            } else {
                nowCheck = p.charAt(pPos);
                if(pPos < pLen-1 && p.charAt(pPos+1) == '*')
                    pPos++;
                else {
                    if(!checkPos(s.charAt(sPos), nowCheck))
                        return false;
                    sPos++;
                    pPos++;
                }
            }
        }
        if(sPos < sLen)
            return false;
        if(pPos < pLen) {
            if(p.charAt(pPos) == '*')
                pPos++;
            if(pPos == pLen-1 && p.charAt(pPos) != '*')
                return false;
            for(pPos += 1; pPos < pLen; pPos += 2) {
                if(p.charAt(pPos) != '*')
                    return false;
            }
            if(pPos == pLen && p.charAt(pLen-1) != '*')
                return false;
            return true;
        }
        return true;
    }

    //for problem 11
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n-1;
        int res = 0, temp = 0;
        while(l < r) {
            if(height[l] < height[r]) {
                temp = height[l]*(r-l);
                if(res < temp)
                    res = temp;
                l++;
            }
            else {
                temp = height[r]*(r-l);
                if(res < temp)
                    res = temp;
                r--;
            }
        }
        return res;
    }

    //for problem 12
    public String intToRoman(int num) {
        String[] src = new String[10];
        src[0] = "";
        for(int i = 1; i < 4; i++) {
            src[i] = src[i-1]+"I";
        }
        src[4] = "IV";
        src[5] = "V";
        for(int i = 6; i < 9; i++) {
            src[i] = src[i-1]+"I";
        }
        src[9] = "IX";

        int[] a = new int[4];
        for(int i = 0; i < 4; i++) {
            a[i] = num%10;
            num /= 10;
        }

        String res = "", temp = "";
        res = src[a[0]]+res;
        if(a[1] > 0) {
            temp = src[a[1]];
            res = temp.replace("X", "C").replace("V", "L").replace("I", "X")+res;
        }
        if(a[2] > 0) {
            temp = src[a[2]];
            res = temp.replace("X", "M").replace("V", "D").replace("I", "C")+res;
        }
        if(a[3] > 0) {
            temp = src[a[3]];
            res = temp.replace("I", "M")+res;
        }
        return res;
    }

    //for problem 13
    public int romanToInt(String s) {
        int l = s.length()-1;
        int res = 0;
        while(l >= 0) {
            char c = s.charAt(l);
            if(c == 'I') {
                res += 1;
            }
            else if(c == 'V') {
                res += 5;
                if(l > 0 && s.charAt(l-1) == 'I') {
                    res -= 1;
                    l--;
                }
            }
            else if(c == 'X') {
                res += 10;
                if(l > 0 && s.charAt(l-1) == 'I') {
                    res -= 1;
                    l--;
                }
            }
            else if(c == 'L') {
                res += 50;
                if(l > 0 && s.charAt(l-1) == 'X') {
                    res -= 10;
                    l--;
                }
            }
            else if(c == 'C') {
                res += 100;
                if(l > 0 && s.charAt(l-1) == 'X') {
                    res -= 10;
                    l--;
                }
            }
            else if(c == 'D') {
                res += 500;
                if(l > 0 && s.charAt(l-1) == 'C') {
                    res -= 100;
                    l--;
                }
            }
            else if(c == 'M') {
                res += 1000;
                if(l > 0 && s.charAt(l-1) == 'C') {
                    res -= 100;
                    l--;
                }
            }
            l--;
        }
        return res;
    }
}
