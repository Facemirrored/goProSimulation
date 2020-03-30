package de.goProSimulation.fileStream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// TODO: Name an Projekt anpassen

/**
 * FileStream-Klasse zum lesen von allen Input-Text-Dateien sowie generieren von
 * Output-Text-Dateien
 */
public class PuzzleTextfileStream implements FileStream {

  private static PuzzleTextfileStream INSTANCE;
  private List<String> fileInputList;
  private boolean fileReadError = false;

  private PuzzleTextfileStream() {
    fileInputList = new ArrayList<>();
  }

  public static PuzzleTextfileStream getINSTANCE() {
    if (INSTANCE == null) {
      INSTANCE = new PuzzleTextfileStream();
    }
    return INSTANCE;
  }

  public boolean isFileReadError() {
    return fileReadError;
  }

  @Override
  public List<String> readFile(String path) {
    fileReadError = false;

    File inputPath = new File(path);

    final String[] inputFilesList = inputPath.list();
    assert inputFilesList != null;
    StringBuilder content = new StringBuilder();

    Arrays.stream(inputFilesList).forEach(file -> {
      content.setLength(0);
      System.out.println("---Lese INPUT-DATEI <" + file + ">");

      try (Stream<String> stream = Files
          .lines(Paths.get(path + "\\" + file), StandardCharsets.UTF_8)) {
        stream.forEach(s -> content.append(s).append("\n"));
        fileInputList.add(content.toString());
      } catch (IOException ioe) {
        fileReadError = true;
        System.out.println("Fehler beim lesen der Datei <" + path + "\\" + file
            + ">. Prüfen Sie die Gültigkeit der Datei und versuchen Sie es erneut.\n");
      }
    });

    return fileInputList;
  }

  @Override
  public void saveFile(List<String> outputFileContextList) {
    // TODO: write for eacht item textfile
  }
}
