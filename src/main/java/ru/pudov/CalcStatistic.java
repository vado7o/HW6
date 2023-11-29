package ru.pudov;

import java.text.DecimalFormat;

public class CalcStatistic {
    private final int changeWon;
    private final int changeLoose;
    private final int stayWon;
    private final int stayLoose;
    private final DecimalFormat dec = new DecimalFormat("#0.00");

    public CalcStatistic(int changeWon, int changeLoose, int stayWon, int stayLoose) {
        this.changeWon = changeWon;
        this.changeLoose = changeLoose;
        this.stayWon = stayWon;
        this.stayLoose = stayLoose;
    }

    public String calcWin() {
        return dec.format((double) (changeWon + stayWon) / (stayLoose + changeLoose + changeWon + stayWon) * 100);
    }

    public String calcLoose() {
        return dec.format((double) (changeLoose + stayLoose) / (changeWon + stayWon + changeLoose + stayLoose) * 100);
    }
    public String calcWinAfterChange() {
        return dec.format((double) changeWon / (changeWon + stayWon + changeLoose + stayLoose) * 100);
    }
    public String calcWinNoChange() {
        return dec.format((double) stayWon / (changeWon + stayWon + changeLoose + stayLoose) * 100);
    }
    public String calcLooseAfterChange() {
        return dec.format((double) changeLoose / (changeWon + stayWon + changeLoose + stayLoose) * 100);
    }
    public String calcLooseNoChange() {
        return dec.format((double) stayLoose / (changeWon + stayWon + changeLoose + stayLoose) * 100);
    }
}
