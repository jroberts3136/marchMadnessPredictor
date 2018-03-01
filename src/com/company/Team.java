package com.company;

/**
 * Created by jroberts3136 on 2/28/18.
 */
public class Team implements Comparable<Team>{
    public String name;
    public double ppg;
    public double opg;
    public double ppgopg;
    public double foulShots;
    public double tenure;
    public double rebound;
    public double seed;
    public double compareScore;

    public Team(String data){
        String[] teamData = data.split("\\t");

        name = teamData[0];
        ppg = Double.parseDouble(teamData[1]);
        opg = Double.parseDouble(teamData[2]);
        ppgopg = ppg/opg;
        foulShots = Double.parseDouble(teamData[3]);
        tenure = Double.parseDouble(teamData[4]);
        rebound = Double.parseDouble(teamData[5]);
        seed = Double.parseDouble(teamData[6]);
        compareScore = calculateCompare(teamData);
    }

    public double calculateCompare(String[] data){
        double score = (ppgopg * 3) + (foulShots) + (tenure * 0.5) + (rebound * 1.5);   //Sample calculation, subject to change
        return score;
    }

    @Override
    public int compareTo(Team o) {
        return return Double.compare(seed, o.seed);
    }
}