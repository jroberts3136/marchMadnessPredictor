package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        String fileName = "input.txt";
        Scanner fileSearch = new Scanner(new File(fileName));
        //fileSearch.nextLine(); If we have a header line
        Team [] division1 = fileRead(fileName, fileSearch);             //Implemented like this so scanner position remains and teams can be split into their respective divisions
        Team [] division2 = fileRead(fileName, fileSearch);
        Team [] division3 = fileRead(fileName, fileSearch);
        Team [] division4 = fileRead(fileName, fileSearch);
    }

    public static Team[] fileRead(String fileName, Scanner search) throws IOException{  //Reads input file and returns array of teams

        int indx = -1;
        Team [] teams = new Team[64];                                   //Creates array of teams

        for (int i = 0; i<16; i++){                                     //Each division will only have 16 teams to begin with
            indx++;
            teams[indx] = new Team(search.nextLine());
        }
        return teams;
    }

    public static Team determineWin (Team highSeed, Team lowSeed){ //Return team with higher compare score, in case of tie, return better seed
        if (highSeed.compareScore > lowSeed.compareScore){
            return highSeed;
        }
        else if (lowSeed.compareScore > highSeed.compareScore){
            return lowSeed;
        }
        else{
            return highSeed;
        }
    }
}
