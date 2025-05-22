package edu.ntnu.model.dice;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a collection of dice.
 */
public class Dice {
  private List<Die> dice;

  /**
   * Constructor for Dice class.
   * Constructs a Dice-object wil a specifies number of dice
   *
   * @param numberOfDice the number of dice to create
   */
  public Dice(int numberOfDice) {
    dice = new ArrayList<>(numberOfDice);
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  /**
   * Method for rolling all dice and returns the total sum of their value.
   *
   * @return the total value of all rolled dice
   */
  public int roll() {
    int total = 0;
    for (Die die : dice) {
      total += die.roll();
    }
    return total;
  }

  /**
   * Method for retrieving the valye of a specific die.
   *
   * @param dieNumber the index of the die to retrive
   * @return the value of the specified die
   * @throws IllegalArgumentException if the die index is out of bounds
   */
  public int getDie(int dieNumber) {
    if (dieNumber >= 0 && dieNumber < dice.size()) {
      return dice.get(dieNumber).getValue();
    }
    throw new IllegalArgumentException("Invalid die number.");
  }
}