package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        HashMap<String, Integer> competitorScoresMap = new HashMap<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                int competitorScoreSum = calculateScoreSum(lineData);
                competitorScoresMap.put(lineData[0], competitorScoreSum);
            }
        } catch (IOException ex) {
            System.out.println("File not found!");
        }

        return generateOrderedListFromHashMap(competitorScoresMap);
    }

    public static int calculateScoreSum(String[] dataReadFromFile) {
        int competitorScoreSum = 0;

        for (int i = 1; i < dataReadFromFile.length; i++) {
            competitorScoreSum += Integer.parseInt(dataReadFromFile[i]);
        }

        return competitorScoreSum;
    }

    public static List<String> generateOrderedListFromHashMap(HashMap<String, Integer> inputHashMap) {
        List<String> result = new ArrayList<>();

        inputHashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(stringIntegerEntry -> result.add(stringIntegerEntry.getKey()));

        return result;
    }

}
