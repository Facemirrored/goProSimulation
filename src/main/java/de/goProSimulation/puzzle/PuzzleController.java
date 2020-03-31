package de.goProSimulation.puzzle;

import de.goProSimulation.puzzle.PuzzleFactory.I_Puzzle;
import de.goProSimulation.puzzle.PuzzleFactory.PuzzleFactory;
import de.goProSimulation.puzzle.model.Feld;
import de.goProSimulation.puzzle.model.Karte;
import de.goProSimulation.puzzle.model.PuzzleType;
import java.util.List;

public class PuzzleController {

  private I_Puzzle puzzle;
  private List<Karte> karteList;


  public PuzzleController() {
    puzzle = PuzzleFactory.getPuzzle(PuzzleType.FUENFECK_PUZZLE);
  }

  public PuzzleController(List<Karte> karteList) {
    this();
    this.karteList = karteList;
  }

  public List<Karte> getKarteList() {
    return karteList;
  }

  public boolean loesePuzzle(final List<Karte> karten) {
    return loesePuzzle(karten, 0);
  }

  private boolean loesePuzzle(final List<Karte> karten, final int index) {
    assert (puzzle != null);
    assert (karteList != null);

    if (puzzle.isSolved()) {
      return true;
    }

    final List<Feld> puzzleFeld = puzzle.getPuzzleFeld();
    Karte k = karten.get(index);

    for (Feld feld : puzzleFeld) {
      if (feld.getKarte() == null) {
        if (feld.setKarte(k)) {
          if (loesePuzzle(karten, index + 1)) {
            return true;
          }
          feld.removeKarte();
        }
      }
    }

    return false;
  }
}
