package de.goProSimulation.puzzle.PuzzleFactory;

import de.goProSimulation.puzzle.model.Feld;
import java.util.List;

public interface I_Puzzle {

  List<Feld> getPuzzleFeld();

  void buildPuzzle();

  boolean isSolved();
}
