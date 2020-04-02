package de.goProSimulation.puzzle.mapper;

import de.goProSimulation.exception.InvalidInputDataException;
import de.goProSimulation.puzzle.PuzzleController;
import de.goProSimulation.puzzle.model.Feld;
import de.goProSimulation.puzzle.model.Karte;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleMapper {

  private static PuzzleMapper INSTANCE;
  private List<String[]> kantenStringList;

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
    kantenStringList = new ArrayList<>();
    List<Karte> karteList = new ArrayList<>();
    String[] lines = inhalt.split("\n");
    int counter = 1;
    for (String line : lines) {
      if (!line.contains("%")) {
        if (!line.matches("^[0-9 ]*")) {
          throw new InvalidInputDataException(
              "Datei enthält nicht erlaubte Zeichen. Bitte prüfen Sie diese. Inhalt der Datei:\n\n"
                  + inhalt);
        }

        // entferne leerzeichen am Anfang der Zeile
        String[] kantenString = (line.replaceAll("^[ ][ ]*", ""))
            .split("[ ][ ]*");
        kantenStringList.add(kantenString);
        int[] kanten = new int[kantenString.length];

        // erstelle kanten aus ziffern
        for (int i = 0; i < kantenString.length; ++i) {
          kanten[i] = Integer.parseInt(kantenString[i]);
        }

        karteList.add(new Karte(counter, kanten));
        counter++;
      }
    }

    return karteList;
  }

  public String mapToFile(final String eingabeContent, final PuzzleController puzzleController) {

    List<Feld> puzzlefeld = puzzleController.getPuzzle().getPuzzleFeld();
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("---------------EINGABE----------------\n");

    Stream.of(eingabeContent.split("\n"))
        .filter(line -> line.contains("%"))
        .forEach(line -> stringBuilder.append(line).append("\n"));

    for (int i = 0; i < kantenStringList.size(); ++i) {
      stringBuilder.append((i < 10) ? "\tKarte  " : "\tKarte ").append(i).append(": ")
          .append(Arrays.toString(kantenStringList.get(i)))
          .append("\n");
    }

    stringBuilder.append("--------------------------------------\n\n");

    if (puzzleController.isGeloest()) {
      stringBuilder.append("****\tLösung gefunden\t****")
          .append("\n\nEine mögliche Zuordnung der Karten auf die Puzzlefelder:\n\n")
          .append(
              "\tFeld\t|\tKarte\t|\tGrundkante an 5-Eck\n===========================================================\n");
      for (int i = 0; i < puzzlefeld.size(); ++i) {
        Feld feld = puzzlefeld.get(i);

        stringBuilder.append("\t").append(i < 10 ? " " : "").append(i).append("\t|\t")
            .append(feld.getKarte().getKartennummer() < 10 ? " " : "")
            .append(feld.getKarte().getKartennummer()).append("\t|\t")
            .append(feld.getNachbarfelder()
                .get(feld.getKarte().getKanten()[feld.getKarte().getRotationsposition()]).getId())
            .append("\n");

      }
    } else {
      stringBuilder.append("****\tKeine Lösung gefunden\t****");
    }

    return stringBuilder.toString();
  }
}
