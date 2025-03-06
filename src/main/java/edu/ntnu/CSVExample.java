package edu.ntnu;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVExample {
  public static void main(String[] args) {
    String filePath = "output.csv";



    try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
      String[] header = {"Name", "color"};
      String[] row1 = {"Alice", "blue"};
      String[] row2 = {"Bob", "red"};

      writer.writeNext(header);
      writer.writeNext(row1);
      writer.writeNext(row2);

      System.out.println("CSV file written successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
