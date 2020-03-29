package de.goProSimulation;

import de.goProSimulation.fileStream.DatanameTextfileStream;
import java.util.List;

public class Application {

  public static void main(String[] args) {
    DatanameTextfileStream textfileStream = DatanameTextfileStream.getINSTANCE();
    List<String> list = textfileStream.readFile(args[0]);
    if (!textfileStream.isFileReadError()) {
      System.out.println("InputRead\n" + list.toString());
    }
  }
}