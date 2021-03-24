package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        HashMap<String, Integer> competitorScoresMap = new HashMap<>();
        List<String> result = new ArrayList<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                int competitorScoreSum = Integer.parseInt(lineData[1]) + Integer.parseInt(lineData[2]) + Integer.parseInt(lineData[3]) +
                        Integer.parseInt(lineData[4]) + Integer.parseInt(lineData[5]);
                        competitorScoresMap.put(lineData[0], competitorScoreSum);
            }
        } catch (IOException ex) {
            System.out.println("File not found!");
        }

        competitorScoresMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(stringIntegerEntry -> result.add(stringIntegerEntry.getKey()));
        return result;
    }

}
