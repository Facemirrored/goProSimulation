package de.goProSimulation;

import de.goProSimulation.exception.FileStreamReadException;
import de.goProSimulation.exception.InvalidInputDataException;
import de.goProSimulation.fileStream.FileStream;
import de.goProSimulation.fileStream.TextfileStream;
import de.goProSimulation.puzzle.PuzzleController;
import de.goProSimulation.puzzle.PuzzleMapper;
import de.goProSimulation.puzzle.model.Karte;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

  public static void main(String[] args) {
    // TODO: scanner next using
    try (Scanner scanner = new Scanner(System.in)) {
      // validiere Argumente
      if (args.length != 2) {
        throw new IllegalArgumentException("Argumente stimmen nicht überein. "
            + "Bitte übergeben Sie beim ausführen den Pfad der Input- sowie Output-Dateien an.");
      }

      PuzzleMapper puzzleMapper = PuzzleMapper.getInstance();
      FileStream textfileStream = TextfileStream.getINSTANCE();

      // Lese Daten
      List<String> list = textfileStream.readFile(args[0]);

      List<String> outputStringList = new ArrayList<>();

      for (String puzzleContent : list) {
        try {
          System.out.println("Verarbeite nächste Datei...");

          // Mapping --> internes Datenmodell
          List<Karte> karten = puzzleMapper.mapToKarten(puzzleContent);

          // Erstelle und Berechne Puzzle
          PuzzleController puzzleController = new PuzzleController(karten);
          puzzleController.loesePuzzle(puzzleController.getKarteList());

          // Mapping --> externe Datenmodell
          outputStringList.add(puzzleMapper.mapToFile(puzzleContent, puzzleController));

        } catch (InvalidInputDataException iide) {
          System.out.println(iide.getMessage());
          outputStringList.add(iide.getMessage());
        }
      }

      textfileStream.saveFile(args[1], outputStringList);

    } catch (IllegalArgumentException | FileStreamReadException exception) {
      System.out.println(exception.getMessage());
      System.exit(-1);
    }
  }
}