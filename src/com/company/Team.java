package com.company;

/**
 * Created by jroberts3136 on 2/28/18.
 */
public class Team {
    public double compareScore;

    public Team(String data){
        String[] teamData = data.split("\\t");
        compareScore = calculateCompare(teamData);
    }

    public double calculateCompare(String[] data){

        double score;
        return score;
    }
}
