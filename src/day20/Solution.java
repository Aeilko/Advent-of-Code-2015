package day20;

public class Solution {

    public static final int INPUT = 33100000;

    public static void main(String[] args){
        int r = (int) Math.sqrt(INPUT);
        while (true) {
            int x = divisorSum(r);
            if (x*10 >= INPUT) {
                break;
            }
            r += 1;
        }

        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + r + "\n");

        r = (int) Math.sqrt(INPUT);
        while (true) {
            int x = divisorSum2(r);
            if (x*11 >= INPUT) {
                break;
            }
            r += 1;
        }

        System.out.println("Part 2 - Success");
        System.out.println("Answer\t" + r + "\n");
    }

    public static int divisorSum(int num){
        int result = num+1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num%i == 0) {
                if (i*i == num){
                    result += i;
                }
                else{
                    result += i + num/i;
                }
            }
        }
        return result;
    }

    public static int divisorSum2(int num){
        int result = num;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num%i == 0 && i*i != num) {
                int x = num/i;
                if (x*50 >= num){
                    result += x;
                }
            }
        }
        return result;
    }
}
