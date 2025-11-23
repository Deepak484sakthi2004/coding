package leetcode;

import java.util.Arrays;

/** https://leetcode.com/problems/greatest-sum-divisible-by-three/description/?envType=daily-question&envId=2025-08-25

Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.

Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
*/

/**
 since it involves with combination of number with increasing pattern and the order is not assured : it falls under dp, greedy
 */

public class GreatestSumDivisibleByThree{

    protected int divisibleNumber;

    public GreatestSumDivisibleByThree(int digit){
        divisibleNumber = digit;
    }

    public int maxSumDivThree(int[] nums) {
        try{
            int[] arr = copyInputParametersForMutation(nums);
            int aggregatedMaxSum = computeMaxSumDivisible(arr);
            if(aggregatedMaxSum!=Integer.MIN_VALUE){
                return aggregatedMaxSum;
            }
            else {
                System.out.println("No max sum is computed");
                return -1;
            }
        } catch(Exception ex){
            System.out.println("Exception occurred while computing maxSumDivThree"+ex);
            throw ex;
        }
    }


    protected int computeMaxSumDivisible(int[] nums) {
        return computeMaxSumDivisible(nums, divisibleNumber);
    }

    protected int computeMaxSumDivisible(int[] nums, int digit) {
        if(nums.length<=0 || digit <=0) {
            System.out.printf("Invalid input array: %s, digit: %d%n", Arrays.toString(nums), digit);
            return Integer.MIN_VALUE;
        }
        return dpApproach(nums,digit);
    }

    private int dpApproach(int[] nums, int digit) {
        int[] dp = new int[digit];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int num : nums) {
            int[] next = Arrays.copyOf(dp, digit);
            for (int r = 0; r < digit; r++) {
                if (dp[r] != Integer.MIN_VALUE) {
                    int newR = (r + num) % digit;
                    next[newR] = Math.max(next[newR], dp[r] + num);
                }
            }
            dp = next;
        }

        return dp[0];
    }


    private int[] copyInputParametersForMutation(int[] nums) {

        int[] arr = Arrays.copyOf(nums, nums.length);
        // deep copy , but arrayName.clone() -> returns the shallow copy of the array for non-primitive datatypes, but for primitive arrays, it does deep copy

        if(Arrays.equals(arr,nums)){ // use deepEquals for non primitives
            System.out.println("they are same");
        }

        int[] arr1 = nums.clone();

        if(Arrays.equals(arr1,nums)){
            System.out.println("they are same");
        }

        return arr1;
    }
}



