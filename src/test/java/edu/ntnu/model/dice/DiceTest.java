package edu.ntnu.model.dice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
  @Test
  void testSingleDieRollRange() {
    Die die = new Die();
    for (int i = 0; i < 100; i++) {
      int value = die.roll();
      assertTrue(value >= 1 && value <= 6, "Die roll out of bounds: " + value);
    }
  }

  @Test
  void testMultipleDiceRollTotalRange() {
    Dice dice = new Dice(3);
    int result = dice.roll();
    assertTrue(result >= 3 && result <= 18);
  }

  @Test
  void testGetIndividualDieValue() {
    Dice dice = new Dice(2);
    dice.roll();
    assertTrue(dice.getDie(0) >= 1 && dice.getDie(0) <= 6);
    assertTrue(dice.getDie(1) >= 1 && dice.getDie(1) <= 6);
  }
}
