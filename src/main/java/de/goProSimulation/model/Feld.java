package de.goProSimulation.model;

import java.util.List;

public class Feld {

  private int id;
  private Karte karte;
  private List<Feld> nachbarfelder;
  private int rotationsposition;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Karte getKarte() {
    return karte;
  }

  public void setKarte(Karte karte) {
    this.karte = karte;
  }

  public List<Feld> getNachbarfelder() {
    return nachbarfelder;
  }

  public void setNachbarfelder(List<Feld> nachbarfelder) {
    this.nachbarfelder = nachbarfelder;
  }

  public int getRotationsposition() {
    return rotationsposition;
  }

  public void setRotationsposition(int rotationsposition) {
    this.rotationsposition = rotationsposition;
  }
}
