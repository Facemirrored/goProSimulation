package de.goProSimulation.puzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Feld {

  private int id;
  private Karte karte;
  private List<Feld> nachbarfelder;


  public Feld(int id) {
    this.id = id;
    nachbarfelder = new ArrayList<>();
  }

  public Feld(int id, List<Feld> nachbarfelder) {
    this.id = id;
    this.nachbarfelder = nachbarfelder;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private int findeRelativeKantenposition(final Feld feld) {
    for (int i = 0; i < nachbarfelder.size(); ++i) {
      if (nachbarfelder.get(i).getId() == feld.getId()) {
        return i;
      }
    }

    // TODO: notfoundexception
    return -1;
  }

  public Karte getKarte() {
    return karte;
  }

  private boolean validiereNachbarkanten(final Karte karte) {
    for (int n = 0; n < nachbarfelder.size(); ++n) {
      Feld nachbar_n = nachbarfelder.get(n);
      if (nachbar_n.getKarte() == null) {
        continue;
      }

      // finde aktuelle Position vom Nachbarn ausgehend
      final int kantenpositionNachbar_n = nachbar_n.findeRelativeKantenposition(this);

      // validiere Kantenwerte
      if (nachbar_n.getKarte()
          .getKantenziffer(kantenpositionNachbar_n, nachbar_n.getKarte().getRotationsposition())
          != karte.getKantenziffer(n, karte.getRotationsposition())) {
        return false;
      }
    }

    return true;
  }

  public boolean setKarte(final Karte karte) {

    for (int r = 0; r < karte.getKanten().length; ++r) {
      karte.setRotationsposition(r);
      // prüfe alle kanten ob passt vom nachbarn
      if (validiereNachbarkanten(karte)) {
        this.karte = karte;
        return true;
      }
    }

    return false;
  }

  public void removeKarte() {
    this.karte = null;
  }

  public List<Feld> getNachbarfelder() {
    return nachbarfelder;
  }

  public void setNachbarfelder(List<Feld> nachbarfelder) {
    this.nachbarfelder = nachbarfelder;
  }
}
