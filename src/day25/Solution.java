package day25;

public class Solution {

    public static final int ROW = 2981;
    public static final int COL = 3075;

    public static void main(String[] args){
        long start = 20151125;
        long multiply = 252533;
        long modulo = 33554393;

        long position = xthNumber(ROW, COL);

        long prev = start;
        for(int i = 1; i < position; i++){
            prev = (prev*multiply) % modulo;
        }

        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + prev + "\n");
    }

    public static long xthNumber(int row, int col){
        long summation = (col+1)*col/2;
        long result = summation;
        for(int i = 0; i < row-1; i++){
            result += col+i;
        }
        return result;
    }
}
