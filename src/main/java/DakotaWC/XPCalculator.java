package main.java.DakotaWC;

import org.rev317.min.api.methods.*;
import java.text.DecimalFormat;

public class XPCalculator {
    public static int baseLvl = 0;
    public static int currentLvl = 0;
    public static int gainedLvl = 0;
    public static int baseXP = 0;
    public static int gainedXP = 0;
    public static int nextLvl = 0;
    public static int logsCut = 0;

    public static String formatNumber(int number) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = number;
        if (i >= 1000000) {
            return nf.format((i / 1000000)) + "M";
        }
        if (i >= 1000) {
            return nf.format((i / 1000)) + "K";
        }
        return "" + number;
    }

    public static void calculateBaseXP(){
        baseXP = 0;
        baseXP = Skill.WOODCUTTING.getExperience();
    }

    public static void calculateGainedXP(){
        gainedXP = Skill.WOODCUTTING.getExperience() - baseXP;
    }

    public static void calculateBaseLvl(){
        baseLvl = Skill.WOODCUTTING.getLevel();
    }

    public static void calculateCurrentLvl(){
        currentLvl = Skill.WOODCUTTING.getLevel();
    }

    public static void calculateGainedLvls() {
       calculateCurrentLvl();
        gainedLvl = currentLvl - baseLvl;
    }

    public static void calculateNextLvl(){
        nextLvl = Skill.WOODCUTTING.getRemaining();
    }

    public static void addLogs(int n){
        logsCut += n;
    }
}