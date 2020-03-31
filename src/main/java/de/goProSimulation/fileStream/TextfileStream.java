package de.goProSimulation.fileStream;

import de.goProSimulation.exception.FileStreamReadException;
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
public class TextfileStream implements FileStream {

  private static TextfileStream INSTANCE;
  private List<String> fileInputList;

  private TextfileStream() {
    fileInputList = new ArrayList<>();
  }

  public static TextfileStream getINSTANCE() {
    if (INSTANCE == null) {
      INSTANCE = new TextfileStream();
    }
    return INSTANCE;
  }

  @Override
  public List<String> readFile(String path) throws FileStreamReadException {

    File inputPath = new File(path);

    final String[] inputFilesList = inputPath.list();
    assert inputFilesList != null;
    StringBuilder content = new StringBuilder();

    Arrays.stream(inputFilesList).forEach(file -> {
      content.setLength(0);
      System.out.println("---Lese INPUT-DATEI <" + file + ">---");

      try (Stream<String> stream = Files
          .lines(Paths.get(path + "\\" + file), StandardCharsets.UTF_8)) {
        stream.forEach(s -> content.append(s).append("\n"));

        fileInputList.add(content.toString());
      } catch (IOException ioe) {
        throw new FileStreamReadException("Fehler beim lesen der Datei <" + path + "\\" + file
            + ">. Prüfen Sie die Gültigkeit sowie Zugriff der Datei und versuchen Sie es erneut.\n");
      }
    });

    return fileInputList;
  }

  @Override
  public void saveFile(List<String> outputFileContextList) {
    // TODO: write for eacht item textfile
  }
}
