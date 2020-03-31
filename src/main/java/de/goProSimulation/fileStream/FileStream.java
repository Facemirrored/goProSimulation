package de.goProSimulation.fileStream;

import de.goProSimulation.exception.FileStreamReadException;
import java.util.List;

public interface FileStream {

  List<String> readFile(final String path) throws FileStreamReadException;

  void saveFile(final List<String> outputFileContextList);
}