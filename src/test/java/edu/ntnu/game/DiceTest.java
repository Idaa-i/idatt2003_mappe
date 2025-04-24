package edu.ntnu.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.model.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {
  private Dice dice;
  @BeforeEach
  void setUp() {
    dice = new Dice(2);
  }

  @Test
  void testRoll() {
    int total = dice.roll();
    assertTrue(total >= 2 && total <= 12);
  }

  @Test
  void testGetDieValid() {
    dice.roll();
    int value = dice.getDie(1);
    assertTrue(value >= 1 && value <= 6);
  }

  @Test
  void testGetDieInvalid() {
    IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> dice.getDie(10));
    assertEquals("Invalid die number.", exception.getMessage());
  }
}