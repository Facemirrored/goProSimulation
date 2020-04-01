package de.goProSimulation.puzzle.model;

import java.util.Arrays;

public class Karte {

  private int kartennummer;
  private int[] kanten;
  private int rotationsposition = 0;

  public Karte(final int kartennummer, final int[] kanten) {
    this.kartennummer = kartennummer;
    this.kanten = kanten;
  }

  public int getKartennummer() {
    return kartennummer;
  }

  public int getRotationsposition() {
    return rotationsposition;
  }

  public void setRotationsposition(int rotationsposition) {
    this.rotationsposition = rotationsposition;
  }

  public int getKantenziffer(final int kante, final int rotation) {
    return kanten[(kante + rotation) % kanten.length];
  }

  public int[] getKanten() {
    return kanten;
  }

  public void setKanten(int[] kanten) {
    this.kanten = kanten;
  }

  @Override
  public String toString() {
    return "Karte{" +
        "kanten=" + Arrays.toString(kanten) +
        ", rotationsposition=" + rotationsposition +
        '}';
  }
}
