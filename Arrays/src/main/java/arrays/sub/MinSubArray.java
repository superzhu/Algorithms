package arrays.sub;

/**
 * http://www.programcreek.com/2014/05/leetcode-minimum-size-subarray-sum-java/
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 */
public class MinSubArray {
    public int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        // initialize min length to be the input array length
        int result = nums.length;

        int i = 0;
        int sum = nums[0];

        for(int upper=0; upper<nums.length; ){
            if(i==upper){
                if(nums[i]>=target){
                    return 1; //if single elem is large enough
                }else{
                    upper++;

                    if(upper<nums.length){
                        sum = sum + nums[upper];
                    }else{
                        return result;
                    }
                }
            }else{
                //if sum is large enough, move left cursor
                if(sum >= target){
                    result = Math.min(upper-i+1, result);
                    sum = sum - nums[i];
                    i++;
                    //if sum is not large enough, move right cursor
                }else{
                    upper++;

                    if(upper<nums.length){
                        sum = sum + nums[upper];
                    }else{
                        if(i==0){
                            return 0;
                        }else{
                            return result;
                        }
                    }
                }
            }
        }

        return result;
    }
}
