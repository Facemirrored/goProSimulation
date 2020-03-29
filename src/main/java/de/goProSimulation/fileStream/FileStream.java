package de.goProSimulation.fileStream;

import java.util.List;

public interface FileStream {

  List<String> readFile(final String path);

  void saveFile(final List<String> outputFileContextList);
}