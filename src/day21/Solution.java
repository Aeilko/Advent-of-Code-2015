package day21;

public class Solution {

    public static final int[] WEAPONS = {4,5,6,7,8};
    public static final int[] WEAPONSCOST = {8,10,25,40,74};

    public static final int[] ARMOR = {0,1,2,3,4,5};
    public static final int[] ARMORCOST = {0,13,31,53,75,102};

    public static final int[] RINGCOST = {0,25,50,100,20,40,80};

    public static final int BOSSHEALTH = 100;
    public static final int BOSSARMOR = 2;
    public static final int BOSSDAMAGE = 8;

    public static void main(String[] args){
        int minCost = Integer.MAX_VALUE;
        int maxCost = 0;
        for (int i = 0; i < WEAPONS.length; i++){
            for (int j = 0; j < ARMOR.length; j++){
                for (int r1 = 0; r1 < RINGCOST.length; r1++){
                    for (int r2 = 0; r2 < RINGCOST.length; r2++){
                        if (r1 == r2)
                            continue;

                        int cost = WEAPONSCOST[i] + ARMORCOST[j] + RINGCOST[r1] + RINGCOST[r2];
                        if (cost >= minCost && cost <= maxCost)
                            continue;

                        int armor = ARMOR[j] + ringArmor(r1) + ringArmor(r2);
                        int damage = WEAPONS[i] + ringDamage(r1) + ringDamage(r2);

                        int result = playGame(100, armor, damage, BOSSHEALTH, BOSSARMOR, BOSSDAMAGE);
                        if (result > 0 && cost < minCost){
                            minCost = cost;
                        }
                        if(result < 0 && cost > maxCost){
                            maxCost = cost;
                        }
                    }
                }
            }
        }
        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + minCost + "\n");

        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + maxCost + "\n");
    }

    public static int playGame(int heroHealth, int heroArmor, int heroDamage, int bossHealth, int bossArmor, int bossDamage){
        int hHealth = heroHealth;
        int bHealth = bossHealth;

        int hDamage = heroDamage - bossArmor;
        hDamage = (hDamage < 1 ? 1 : hDamage);

        int bDamage = bossDamage - heroArmor;
        bDamage = (bDamage < 1 ? 1 : bDamage);

        while(bHealth > 0 && hHealth > 0){
            // Player attacks
            bHealth -= hDamage;
            if (bHealth < 1)
                break;

            // Boss attacks
            hHealth -= bDamage;
        }

        return (hHealth > 0 ? hHealth : bHealth*-1);
    }

    public static int ringDamage(int r){
        return (r > 3 ? 0 : r);
    }

    public static int ringArmor(int r){
        return (r < 4 ? 0: r-3);
    }

}
