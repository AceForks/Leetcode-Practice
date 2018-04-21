import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Problem {
    private Solution solution;

    public Problem() {
        solution = new Solution();
    }

    public void solve(int problemNum) {
        switch (problemNum) {
            case 1:
                twoSum();
                break;
            default:
                break;
        }
    }

    private void twoSum() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++) {
            nums[i] = scanner.nextInt();
        }
        int target = scanner.nextInt();
        int[] res = solution.twoSum(nums, target);
        System.out.println(((Integer) res[0]).toString()+" "+ ((Integer) res[1]).toString());
    }
}
