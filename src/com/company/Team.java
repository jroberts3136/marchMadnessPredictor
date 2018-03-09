package com.company;

/**
 * Created by jroberts3136 on 2/28/18.
 */
public class Team implements Comparable<Team>{
    public String name;
    //public String standardName = standardizeName(name);
    //public double ppg;
    //public double opg;
    public double ppgopg;
    public double foulShots;
    public double tenure;
    public double rebound;
    public double seed;
    public double compareScore;

    //public int standardLength = 32;

    public Team(String data){
        String[] teamData = data.split("\\t");

        name = teamData[0];                         //May have to change based on how data ends up structured
        //ppg = Double.parseDouble(teamData[6]);
        //opg = Double.parseDouble(teamData[5]);
        ppgopg = Double.parseDouble(teamData[2]);
        foulShots = Double.parseDouble(teamData[3]);
        tenure = Double.parseDouble(teamData[1]);
        rebound = Double.parseDouble(teamData[4]);
        seed = Double.parseDouble(teamData[7]);
        compareScore = calculateCompare();
    }

    private double calculateCompare(){
        return (ppgopg * 10) + (foulShots * 7) + (tenure / 12) + (rebound / 250);
    }
    /*private String standardizeName(String name){
        int leftoverSpace = standardLength - name.length();
        if (leftoverSpace == 1){
            name += " ";
        }
        else{
            for (int i = 0; i < (leftoverSpace/2);i++){
                name = "_"+name+"_";
            }
            if (standardLength%2 == 1){
                name += "_";
            }
        }
        return name;
    }*/
    @Override
    public int compareTo(Team o) {          //Sorting by seed
        return Double.compare(seed, o.seed);
    }
}