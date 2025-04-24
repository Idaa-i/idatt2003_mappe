package edu.ntnu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse som representerer flere terninger.
 */
public class Dice {
  private List<Die> dice;

  /**
   * Konstruktør for Dice-klassen.
   * Initialiserer dice-listen med et gitt antall terninger.
   *
   * @param numberOfDice antallet terninger som skal opprettes.
   */
  public Dice(int numberOfDice) {
    dice = new ArrayList<>(numberOfDice);
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  /**
   * Metode for å kaste alle terningene og summere den totale verdien.
   *
   * @return den totale verdien av alle kastede terninger.
   */
  public int roll() {
    int total = 0;
    for (Die die : dice) {
      total += die.roll();
    }
    return total;
  }

  /**
   * Metode for å hente verdien av én spesifikk terning.
   *
   * @param dieNumber terningens indeks.
   * @return verdien som den spesifikke terningen ga.
   * @throws IllegalArgumentException ved ugyldig terning-nummer.
   */
  public int getDie(int dieNumber) {
    if (dieNumber >= 0 && dieNumber < dice.size()) {
      return dice.get(dieNumber).getValue();
    }
    throw new IllegalArgumentException("Invalid die number.");
  }
}