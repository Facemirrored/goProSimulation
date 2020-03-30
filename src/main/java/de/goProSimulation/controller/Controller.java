package de.goProSimulation.controller;

import de.goProSimulation.fileStream.PuzzleTextfileStream;
import java.util.List;

public class Controller {

  public static void main(String[] args) {
    // Lese Datei

    PuzzleTextfileStream textfileStream = PuzzleTextfileStream.getINSTANCE();
    List<String> list = textfileStream.readFile(args[0]);
    if (!textfileStream.isFileReadError()) {
      System.out.println("InputRead\n" + list.toString());
    }

    // Mapping --> internes Datenmodell

    // Erstelle und Berechne Puzzle

    // Mapping --> externe Datenmodell

    // Schreibe Output
  }
}