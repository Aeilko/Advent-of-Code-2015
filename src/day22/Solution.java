package day22;

import utils.WeightedClass;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution {

    public static int BOSSHEALTH = 58;
    public static int BOSSDAMGE = 9;

    public static void main(String[] args){
        int result = runGame(false);
        System.out.println("Part 1 - Success");
        System.out.println("Answer\t" + result + "\n");

        result = runGame(true);
        System.out.println("Part 2 - Success");
        System.out.println("Answer\t" + result + "\n");
    }

    public static int runGame(boolean hard){
        WizardGame start = new WizardGame(50, 0, 500, BOSSHEALTH, BOSSDAMGE, hard);
        PriorityQueue<WeightedClass<WizardGame>> queue = new PriorityQueue<>();
        queue.add(new WeightedClass<WizardGame>(start, 0));

        int result = -1;

        mainloop: while(!queue.isEmpty()){
            WeightedClass<WizardGame> item = queue.poll();
            WizardGame baseGame = item.object;
            for(int spell: baseGame.getPossibleSpells()){
                WizardGame wg = baseGame.copy();
                // Player turn
                int mana = wg.turn(spell) + item.weight;
                if(wg.isFinished()){
                    if(wg.getWinner() == 1){
                        // Player win
                        result = mana;
                        break mainloop;
                    }
                    else{
                        // Boss win, ignore this game
                    }
                }
                else{
                    // Boss turn
                    wg.turn(0);
                    if(wg.isFinished()){
                        if(wg.getWinner() == 1){
                            // Player win
                            result = mana;
                            break mainloop;
                        }
                        else{
                            // Boss win, ignore this game
                        }
                    }
                    else{
                        // No winner yet
                        queue.add(new WeightedClass<WizardGame>(wg, mana));
                    }
                }
            }

            if(queue.isEmpty()){
                System.out.println("Queue empty");
            }
        }

        return result;
    }
}

class WizardGame {
    private int playerHealth;
    private int playerArmor;
    private int playerMana;

    private int bossHealth;
    private int bossDamage;

    private int effectShield = 0;
    private int effectPoison = 0;
    private int effectRecharge = 0;

    private boolean isPlayerTurn = true;
    private boolean hardMode = false;

    public static final int MISSLEDAMAGE = 4;
    public static final int DRAINAMOUNT = 2;
    public static final int POISONDAMAGE = 3;
    public static final int EXTRAARMOR = 7;
    public static final int RECHARGEMANA = 101;

    public static final int SPELL_MISSILE = 1;
    public static final int SPELL_DRAIN = 2;
    public static final int SPELL_SHIELD = 3;
    public static final int SPELL_POISON = 4;
    public static final int SPELL_RECHARGE = 5;

    public static final int MANA_MISSILE = 53;
    public static final int MANA_DRAIN = 73;
    public static final int MANA_SHIELD = 113;
    public static final int MANA_POISON = 173;
    public static final int MANA_RECHARGE = 229;

    public WizardGame(int health, int armor, int mana, int bossHealth, int bossDamage, boolean hardMode) {
        this(health, armor, mana, bossHealth, bossDamage, hardMode, 0, 0, 0, true);
    }

    public WizardGame(int health, int armor, int mana, int bossHealth, int bossDamage, boolean hardMode, int shield, int poison, int recharge, boolean turn){
        this.playerHealth = health;
        this.playerArmor = armor;
        this.playerMana = mana;
        this.bossHealth = bossHealth;
        this.bossDamage = bossDamage;
        this.hardMode = hardMode;
        this.effectShield = shield;
        this.effectPoison = poison;
        this.effectRecharge = recharge;
        this.isPlayerTurn = turn;
    }

    public int turn(int spell){
        int result = 0;

        processEffects();
        if(isFinished())
            return result;

        if(this.isPlayerTurn){
            // player turn
            if(this.hardMode) {
                this.playerHealth -= 1;
                if (this.isFinished())
                    return result;
            }

            switch (spell) {
                case SPELL_MISSILE:
                    result = MANA_MISSILE;
                    this.bossHealth -= MISSLEDAMAGE;
                    break;
                case SPELL_DRAIN:
                    result = MANA_DRAIN;
                    this.bossHealth -= DRAINAMOUNT;
                    this.playerHealth += DRAINAMOUNT;
                    break;
                case SPELL_SHIELD:
                    result = MANA_SHIELD;
                    this.effectShield = 6;
                    this.playerArmor += EXTRAARMOR;
                    break;
                case SPELL_POISON:
                    result = MANA_POISON;
                    this.effectPoison = 6;
                    break;
                case SPELL_RECHARGE:
                    result = MANA_RECHARGE;
                    this.effectRecharge = 5;
                    break;
            }
            this.playerMana -= result;
        }
        else{
            // boss turn
            int damage = this.bossDamage - this.playerArmor;
            this.playerHealth -= (damage < 1 ? 1 : damage);
        }

        this.isPlayerTurn = !this.isPlayerTurn;

        return result;
    }

    public boolean isFinished(){
        return (this.playerHealth < 1 || this.bossHealth < 1);
    }

    public int getWinner(){
        if(this.playerHealth < 1)
            return -1;
        else if(this.bossHealth < 1)
            return 1;
        else
            return 0;
    }

    public void processEffects(){
        // Shield
        if (this.effectShield > 0){
            this.effectShield--;
            if (this.effectShield == 0)
                this.playerArmor -= WizardGame.EXTRAARMOR;
        }
        // Poison
        if (this.effectPoison > 0) {
            this.bossHealth -= WizardGame.POISONDAMAGE;
            this.effectPoison--;
        }
        // Recharge
        if (this.effectRecharge > 0){
            this.playerMana += WizardGame.RECHARGEMANA;
            this.effectRecharge--;
        }
    }

    public HashSet<Integer> getPossibleSpells(){
        HashSet<Integer> result = new HashSet<>();

        if(this.playerMana >= MANA_MISSILE)
            result.add(SPELL_MISSILE);
        if(this.playerMana >= MANA_DRAIN)
            result.add(SPELL_DRAIN);
        if(this.playerMana >= MANA_SHIELD && this.effectShield <= 1)
            result.add(SPELL_SHIELD);
        if(this.playerMana >= MANA_POISON && this.effectPoison <= 1)
            result.add(SPELL_POISON);
        if(this.playerMana >= MANA_RECHARGE && this.effectRecharge <= 1)
            result.add(SPELL_RECHARGE);

        return result;
    }

    public WizardGame copy(){
        return new WizardGame(this.playerHealth, this.playerArmor, this.playerMana, this.bossHealth, this.bossDamage, this.hardMode, this.effectShield, this.effectPoison, this.effectRecharge, this.isPlayerTurn);
    }
}
