package edu.ntnu;

import com.opencsv.CSVWriter;
import edu.ntnu.model.Player;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVExample {
  private static List<Player> players = new ArrayList<>();

  public static void main(String[] args) {
    String filePath = "output.csv";
    writePlayersToCSV(filePath);
  }

  public static void addPlayer(Player player) {
    players.add(player);
  }

  public static void writePlayersToCSV(String filePath) {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      String[] header = new String[] {"Name", "Color"};
      writer.writeNext(header);

      for (Player player : players) {
        String[] row = {player.getName()};
        writer.writeNext(row);
      }
      System.out.println("CSV file written successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


