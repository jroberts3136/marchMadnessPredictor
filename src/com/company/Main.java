package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        String fileName = "input.txt";
        Team [] teams = fileRead(fileName);
    }

    public static Team[] fileRead(String fileName) throws IOException{  //Reads input file and returns array of teams
        Scanner fileSearch = new Scanner(new File(fileName));
        int indx = -1;
        Team [] teams = new Team[64];                                   //Creates array of teams

        //fileSearch.nextLine(); If we have a header line
        while (fileSearch.hasNext()){                                   //
            indx++;
            teams[indx] = new Team(fileSearch.nextLine());
        }
        return teams;
    }
}
