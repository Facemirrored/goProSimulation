package de.goProSimulation.puzzle.PuzzleFactory;

import de.goProSimulation.puzzle.model.PuzzleType;

public class PuzzleFactory {

  public static I_Puzzle getPuzzle(final PuzzleType puzzleType) {
    if (puzzleType == PuzzleType.FUENFECK_PUZZLE) {
      return new FuenfeckPuzzle();
    }

    throw new EnumConstantNotPresentException(PuzzleType.class,
        "Für dieses enum existiert kein PuzzleBuilder.");
  }
}