package de.goProSimulation.puzzle;

import de.goProSimulation.exception.FileStreamReadException;
import de.goProSimulation.exception.InvalidInputDataException;
import de.goProSimulation.fileStream.TextfileStream;
import de.goProSimulation.puzzle.mapper.PuzzleMapper;
import de.goProSimulation.puzzle.model.Karte;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

  private static TextfileStream textfileStream = TextfileStream.getINSTANCE();
  private static PuzzleMapper puzzleMapper = PuzzleMapper.getInstance();

  public static void main(String[] args) {
    // TODO: scanner next using
    try (Scanner scanner = new Scanner(System.in)) {
      // validiere Argumente
      if (args.length != 2) {
        throw new IllegalArgumentException("Argumente stimmen nicht überein. "
            + "Bitte übergeben Sie beim ausführen den Pfad der Input- sowie Output-Dateien an.");
      }

      // Lese Daten
      List<String> list = textfileStream.readFile(args[0]);

      List<String> outputStringList = new ArrayList<>();

      for (String puzzleContent : list) {
        try {
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