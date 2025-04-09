package edu.ntnu.utils;

import com.opencsv.CSVWriter;
import edu.ntnu.model.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtils {
  public static void writePlayersToCSV(String filePath, List<Player> players) {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      writer.writeNext(new String[]{"Name", "Color"});
      for (Player player : players) {
        writer.writeNext(new String[]{player.getName(), player.getColor()});
      }
      System.out.println("CSV file written successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}