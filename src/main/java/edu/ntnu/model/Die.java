package edu.ntnu.model;

import java.util.Random;

/**
 * Klasse som representerer én terning.
 */
public class Die {
  private int lastRolledValue;
  private Random random;

  /**
   * Konstruktør for Die-klassen.
   */
  public Die() {
    this.random = new Random();
    this.lastRolledValue = 1; //Setter default verdi lik 1
  }

  /**
   * Metode for å kaste én terning.
   *
   * @return verdien som terningen gir (1-6)
   */
  public int roll() {
    lastRolledValue = random.nextInt(6) + 1;
    return lastRolledValue;
  }

  /**
   * Metode for å hente siste kastet verdi av terningen.
   *
   * @return siste kastet verdi av terningen
   */
  public int getValue() {
    return lastRolledValue;
  }
}