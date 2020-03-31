package de.goProSimulation.puzzle.mapper;

import de.goProSimulation.exception.InvalidInputDataException;
import de.goProSimulation.puzzle.PuzzleController;
import de.goProSimulation.puzzle.model.Karte;
import java.util.ArrayList;
import java.util.List;

public class PuzzleMapper {

  private static PuzzleMapper INSTANCE;

  private PuzzleMapper() {

  }

  public static PuzzleMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PuzzleMapper();
    }

    return INSTANCE;
  }

  // TODO: validiere alle fachlichkeiten (zb: ziffern gerade und ziffern von 0-2
  public List<Karte> mapToKarten(final String inhalt) throws InvalidInputDataException {
    List<Karte> karteList = new ArrayList<>();
    String[] lines = inhalt.split("\n");
    for (String line : lines) {
      if (!line.contains("%")) {
        if (!line.matches("^[0-9 ]*")) {
          throw new InvalidInputDataException(
              "Datei enthält nicht erlaubte Zeichen. Bitte prüfen Sie diese.");
        }

        // entferne leerzeichen am Anfang der Zeile
        String[] kantenString = (line.replaceAll("^[ ][ ]*", ""))
            .split("[ ][ ]*");
        int[] kanten = new int[kantenString.length];

        // erstelle kanten aus ziffern
        for (int i = 0; i < kantenString.length; ++i) {
          kanten[i] = Integer.parseInt(kantenString[i]);
        }

        karteList.add(new Karte(kanten));
      }
    }

    return karteList;
  }

  public String mapToFile(final PuzzleController puzzleController) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
