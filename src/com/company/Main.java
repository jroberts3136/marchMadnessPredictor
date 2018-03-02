package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        String fileName = "input.txt";
        Scanner fileSearch = new Scanner(new File(fileName));
        //fileSearch.nextLine(); If we have a header line
        //Implemented like this so scanner position remains and teams can be split into their respective divisions
        Team [] d1Round1 = fileRead(fileSearch);              //Top left of bracket
        Team [] d2Round1 = fileRead(fileSearch);              //Bottom left of bracket
        Team [] d3Round1 = fileRead(fileSearch);              //Top right of bracket
        Team [] d4Round1 = fileRead(fileSearch);              //Bottom right of bracket

        Arrays.sort(d1Round1);                                //Ensures arrays are sorted by seed
        Arrays.sort(d2Round1);
        Arrays.sort(d3Round1);
        Arrays.sort(d4Round1);

        Team [] d1Round2 = new Team[8];
        Team [] d2Round2 = new Team[8];
        Team [] d3Round2 = new Team[8];
        Team [] d4Round2 = new Team[8];

        Team [] d1Round3 = new Team[4];
        Team [] d2Round3 = new Team[4];
        Team [] d3Round3 = new Team[4];
        Team [] d4Round3 = new Team[4];

        Team [] d1Final = new Team[2];
        Team [] d2Final = new Team[2];
        Team [] d3Final = new Team[2];
        Team [] d4Final = new Team[2];

        Team [] d1d2Winners = new Team[2];      //Contains winner of division 1 and division 2
        Team [] d3d4Winners = new Team[2];      //Contains winner of division 3 and division 4

        Team [] championship = new Team[2];

        round(d1Round1,d1Round2);
        round(d2Round1,d2Round2);
        round(d3Round1,d3Round2);
        round(d4Round1,d4Round2);

        round(d1Round2,d1Round3);
        round(d2Round2,d2Round3);
        round(d3Round2,d3Round3);
        round(d4Round2,d4Round3);

        round(d1Round3,d1Final);
        round(d2Round3,d2Final);
        round(d3Round3,d3Final);
        round(d4Round3,d4Final);

        round(d1Final,d2Final,d1d2Winners);
        round(d3Final,d4Final,d3d4Winners);

        round(d1d2Winners,d3d4Winners,championship);

        Team champion = determineWin(championship[0],championship[1]);
    }

    public static Team[] fileRead(Scanner search){  //Reads input file and returns array of teams
        int indx = -1;
        Team [] teams = new Team[64];                                   //Creates array of teams

        for (int i = 0; i<16; i++){                                     //Each division will only have 16 teams to begin with
            indx++;
            teams[indx] = new Team(search.nextLine());
        }
        return teams;
    }

    public static Team determineWin (Team team1, Team team2){ //Return team with higher compare score
        if (team1.compareScore > team2.compareScore){
            return team1;
        }
        else{
            return team2;
        }
        /*else{                                 Chance of tie is very unlikely, will reimplement if needed
            return highSeed;
        }*/
    }

    public static void round(Team[] round, Team[] next){            //Determines advancing teams, for when all are in same array
        int highSeed = 0;
        int lowSeed = round.length-1;
        for (int i = 0; i<next.length;i++){
            next[i] = determineWin(round[highSeed],round[lowSeed]);
            highSeed++;
            lowSeed--;
        }
    }
    public static void round(Team[] division1, Team[]division2 , Team[] next){          //Determines advancing teams, for when teams are in different arrays
        next[0] = determineWin(division1[0], division1[1]);                             //All situations using this methods only have two teams in each array
        next[1] = determineWin(division2[0], division2[1]);
    }
}
