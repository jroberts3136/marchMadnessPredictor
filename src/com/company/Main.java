package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner fileSearch = new Scanner(new File("2018 stats as of 3%2F5%2F18 - Sheet1.tsv"));
        //fileSearch.nextLine(); If we have a header line
        //Implemented like this so scanner position remains and teams can be split into their respective divisions
        Team [] d1Round1 = fileRead(fileSearch);                //Top left of bracket, South
        Team [] d2Round1 = fileRead(fileSearch);                //Bottom left of bracket, West
        Team [] d3Round1 = fileRead(fileSearch);                //Top right of bracket, East
        Team [] d4Round1 = fileRead(fileSearch);                //Bottom right of bracket, Midwest

        Arrays.sort(d1Round1);                                  //Ensures arrays are sorted by seed
        Arrays.sort(d2Round1);
        Arrays.sort(d3Round1);
        Arrays.sort(d4Round1);

        Team [] d1Round2 = new Team[8];                         //Does not need to be resorted
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

        round(d1Round1,d1Round2);               //"Plays" rounds to determine advancing teams
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

        FileWriter fw = new FileWriter("bracketOutput.txt");
        PrintWriter writer = new PrintWriter(fw);

        writer.println("Round 1 - Division 1:\n");              //Prints results to text file
        printRound(d1Round1,writer);
        writer.println("Round 1 - Division 2:\n");
        printRound(d2Round1,writer);
        writer.println("Round 1 - Division 3:\n");
        printRound(d3Round1,writer);
        writer.println("Round 1 - Division 4:\n");
        printRound(d1Round1,writer);

        writer.println("Round 2 - Division 1:\n");
        printRound(d1Round2,writer);
        writer.println("Round 2 - Division 2:\n");
        printRound(d2Round2,writer);
        writer.println("Round 2 - Division 3:\n");
        printRound(d3Round2,writer);
        writer.println("Round 2 - Division 4:\n");
        printRound(d4Round2,writer);

        writer.println("Round 3 - Division 1:\n");
        printRound(d1Round3,writer);
        writer.println("Round 3 - Division 2:\n");
        printRound(d2Round3,writer);
        writer.println("Round 3 - Division 3:\n");
        printRound(d3Round3,writer);
        writer.println("Round 3 - Division 4:\n");
        printRound(d4Round3,writer);

        writer.println("Round 4 - Division 1:\n");
        printRound(d1Final,writer);
        writer.println("Round 4 - Division 2:\n");
        printRound(d2Final,writer);
        writer.println("Round 4 - Division 3:\n");
        printRound(d3Final,writer);
        writer.println("Round 4 - Division 4:\n");
        printRound(d4Final,writer);

        writer.println("Round 5 - Division 1 v. Division 2:\n");
        printRound(d1d2Winners,writer);
        writer.println("Round 5 - Division 3 v. Division 4:\n");
        printRound(d3d4Winners,writer);

        writer.println("Round 6 - Championship Game:\n");
        printRound(championship,writer);

        writer.println("Champion:\n");
        writer.println(champion.name);

        writer.close();
        fw.close();
    }

    public static Team[] fileRead(Scanner search){  //Reads input file and returns array of teams
        int indx = -1;
        Team [] teams = new Team[16];                                   //Creates array of teams

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
    public static void printRound(Team [] round, PrintWriter writer){
        for (int i = 0; i<round.length;i++) {
            writer.print(round[i].name);
            if (i != (round.length-1)){
                writer.print(", ");
            }
        }
        writer.print("\n\n");
    }
}
