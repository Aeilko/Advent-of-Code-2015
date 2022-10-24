package day23;

public class Solution {

    public static void main(String[] args){
        String[] instructions = INPUT.split("\n");

        int result = run(instructions, 0);
        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + result + "\n");

        result = run(instructions, 1);
        System.out.println("Part 2 - Success");
        System.out.println("Answer\t" + result + "\n");
    }

    public static int run(String[] cmds, long a){
        int pointer = 0;
        int b = 0;

        while(pointer < cmds.length && pointer > -1){
            String[] cmd = cmds[pointer].split(" ", 2);
            int val;
            String[] params;
            switch(cmd[0]){
                case "hlf":
                    if(cmd[1].equals("a"))
                        a /= 2;
                    else
                        b /= 2;
                    pointer++;
                    break;
                case "tpl":
                    if(cmd[1].equals("a"))
                        a *= 3;
                    else
                        b *= 3;
                    pointer++;
                    break;
                case "inc":
                    if(cmd[1].equals("a"))
                        a++;
                    else
                        b++;
                    pointer++;
                    break;
                case "jmp":
                    val = Integer.parseInt(cmd[1]);
                    pointer += val;
                    break;
                case "jie":
                    params = cmd[1].split(", ");
                    val = Integer.parseInt(params[1]);
                    if(params[0].equals("a") && a%2 == 0)
                        pointer += val;
                    else if(params[0].equals("b") && b%2 == 0)
                        pointer += val;
                    else
                        pointer++;
                    break;
                case "jio":
                    params = cmd[1].split(", ");
                    val = Integer.parseInt(params[1]);
                    if(params[0].equals("a") && a == 1)
                        pointer += val;
                    else if (params[0].equals("b") && b == 1)
                        pointer += val;
                    else
                        pointer++;
                    break;
                default:
                    System.out.println("Unknown command '" + cmd[0] + "'");
                    pointer = -1;
            }
        }

        return b;
    }

    public static final String INPUT = "jio a, +16\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "inc a\n" +
            "jmp +23\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "inc a\n" +
            "tpl a\n" +
            "inc a\n" +
            "tpl a\n" +
            "tpl a\n" +
            "inc a\n" +
            "jio a, +8\n" +
            "inc b\n" +
            "jie a, +4\n" +
            "tpl a\n" +
            "inc a\n" +
            "jmp +2\n" +
            "hlf a\n" +
            "jmp -7";
}
