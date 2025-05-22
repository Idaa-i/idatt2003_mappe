package edu.ntnu.model.dice;

import java.util.Random;

/**
 * Class representing a single die.
 */
public class Die {
  private int lastRolledValue;
  private Random random;

  /**
   * Constructor for Die class.
   * Constructs a Die-object
   */
  public Die() {
    this.random = new Random();
    this.lastRolledValue = 1;
  }

  /**
   * Method for rolling the die and update its value.
   *
   * @return the result of the roll
   */
  public int roll() {
    lastRolledValue = random.nextInt(6) + 1;
    return lastRolledValue;
  }

  /**
   * Method for returning the last rolled value of the die.
   *
   * @return the most recent value rolled
   */
  public int getValue() {
    return lastRolledValue;
  }
}