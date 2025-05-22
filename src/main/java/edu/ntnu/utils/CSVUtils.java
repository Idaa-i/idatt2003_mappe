package edu.ntnu.utils;

import com.opencsv.CSVWriter;
import edu.ntnu.model.Player;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class responsible for writing player data to a CSV file.
 */
public class CSVUtils {
  /**
   * Method for writing a list of players to a CSV file at the specified file path.
   * Each row in the CSV will contain the player's name and color
   *
   * @param filePath the path to the CSV file to write
   * @param players  the list of players to write to the file
   */
  public static void writePlayersToCSV(String filePath, List<Player> players) {
    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      writer.writeNext(new String[] {"Name", "Color"});
      for (Player player : players) {
        writer.writeNext(new String[] {player.getName(), player.getColor()});
      }
      System.out.println("CSV file written successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}