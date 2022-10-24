package day24;

import java.util.HashSet;
import java.util.TreeSet;

public class Solution {

    // This solution is really inefficient, but it works, and part 2 is faster than part 1, so I did not feel like
    // rewriting any of it just in case I would need it later.
    // So please don't ever return to this, since it's a terrible example.

    public static void main(String[] args){
        int[] nums = parse(INPUT);

        long result = run(nums, 3);
        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + result + "\n");

        result = result = run(nums, 4);
        System.out.println("Part 2 - Success");
        System.out.println("Answer\t" + result + "\n");
    }

    public static long run(int[] nums, int groups){
        int sum = 0;
        for (int i: nums) {
            sum += i;
        }
        int groupval = sum/groups;

        HashSet<TreeSet<Integer>> ss = smallest_subset(nums, groupval);
        long minQE = Long.MAX_VALUE;
        for(TreeSet<Integer> s: ss){
            long qe = 1;
            for(int i: s)
                qe *= i;

            if(qe < minQE){
                minQE = qe;
            }
        }

        return minQE;
    }

    public static final HashSet<TreeSet<Integer>> smallest_subset(int[] nums, int target_sum){
        HashSet<TreeSet<Integer>> result = new HashSet<>();

        HashSet<TreeSet<Integer>> cur_subsets = new HashSet<>();
        HashSet<TreeSet<Integer>> new_subsets = new HashSet<>();
        cur_subsets.add(new TreeSet<>());

        boolean found = false;
        while(!found) {
            for (TreeSet<Integer> set : cur_subsets) {
                for (int i : nums) {
                    if (!set.contains(i)) {
                        TreeSet<Integer> new_set = new TreeSet<>(set);
                        new_set.add(i);
                        int sum = sumTreeSet(new_set);
                        if(sum < target_sum && !found)
                            new_subsets.add(new_set);
                        else if(sum == target_sum){
                            found = true;
                            result.add(new_set);
                        }
                    }
                }
            }

            cur_subsets = new_subsets;
            new_subsets = new HashSet<>();
        }

        return result;
    }

    public static int sumTreeSet(TreeSet<Integer> in){
        int sum = 0;
        for(int i: in)
            sum += i;
        return sum;
    }

    public static int[] parse(String in){
        String[] n = in.split("\n");
        int[] result = new int[n.length];
        for(int i = 0; i < n.length; i++){
            result[i] = Integer.parseInt(n[i]);
        }
        return result;
    }
    public static final String INPUT = "1\n" +
            "2\n" +
            "3\n" +
            "7\n" +
            "11\n" +
            "13\n" +
            "17\n" +
            "19\n" +
            "23\n" +
            "31\n" +
            "37\n" +
            "41\n" +
            "43\n" +
            "47\n" +
            "53\n" +
            "59\n" +
            "61\n" +
            "67\n" +
            "71\n" +
            "73\n" +
            "79\n" +
            "83\n" +
            "89\n" +
            "97\n" +
            "101\n" +
            "103\n" +
            "107\n" +
            "109\n" +
            "113";
}
