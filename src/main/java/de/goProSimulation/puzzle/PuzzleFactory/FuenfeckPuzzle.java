package de.goProSimulation.puzzle.PuzzleFactory;

import de.goProSimulation.puzzle.model.Feld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuenfeckPuzzle implements I_Puzzle {

  private List<Feld> feldList;

  public FuenfeckPuzzle() {
    feldList = new ArrayList<>();
    buildPuzzle();
  }

  @Override
  public List<Feld> getPuzzleFeld() {
    return feldList;
  }

  @Override
  public void buildPuzzle() {

    Feld fieldOne = new Feld(1);
    Feld fieldTwo = new Feld(2);
    Feld fieldThree = new Feld(3);
    Feld fieldFour = new Feld(4);
    Feld fieldFive = new Feld(5);
    Feld fieldSix = new Feld(6);
    Feld fieldSeven = new Feld(7);
    Feld fieldEight = new Feld(8);
    Feld fieldNine = new Feld(9);
    Feld fieldTen = new Feld(10);
    Feld fieldEleven = new Feld(11);
    Feld fieldTwolf = new Feld(12);
    fieldOne.
        setNachbarfelder(Arrays.asList(fieldTwo, fieldThree, fieldFour, fieldFive, fieldSix));
    fieldTwo
        .setNachbarfelder(Arrays.asList(fieldOne, fieldThree, fieldSix, fieldSeven, fieldEleven));
    fieldThree
        .setNachbarfelder(Arrays.asList(fieldOne, fieldTwo, fieldFour, fieldTen, fieldEleven));
    fieldFour.
        setNachbarfelder(Arrays.asList(fieldOne, fieldThree, fieldFive, fieldTen, fieldNine));
    fieldFive.
        setNachbarfelder(Arrays.asList(fieldOne, fieldFour, fieldSix, fieldEight, fieldNine));
    fieldSix.
        setNachbarfelder(Arrays.asList(fieldOne, fieldTwo, fieldFive, fieldSeven, fieldEight));
    fieldSeven
        .setNachbarfelder(Arrays.asList(fieldTwo, fieldSix, fieldEight, fieldEleven, fieldTwolf));
    fieldEight
        .setNachbarfelder(Arrays.asList(fieldFive, fieldSix, fieldSeven, fieldNine, fieldTwolf));
    fieldNine
        .setNachbarfelder(Arrays.asList(fieldFour, fieldFive, fieldEight, fieldTen, fieldTwolf));
    fieldTen
        .setNachbarfelder(Arrays.asList(fieldThree, fieldFour, fieldNine, fieldEleven, fieldTwolf));
    fieldEleven
        .setNachbarfelder(Arrays.asList(fieldTwo, fieldThree, fieldSeven, fieldTen, fieldTwolf));
    fieldTwolf
        .setNachbarfelder(Arrays.asList(fieldSeven, fieldEight, fieldNine, fieldTen, fieldEleven));

    feldList.addAll(Arrays
        .asList(fieldOne, fieldTwo, fieldThree, fieldFour, fieldFive, fieldSix, fieldSeven,
            fieldEight, fieldNine, fieldTen, fieldEleven, fieldTwolf));
  }

  @Override
  public boolean isSolved() {
    for (Feld feld : feldList) {
      if (feld.getKarte() == null) {
        return false;
      }
    }

    return true;
  }
}
