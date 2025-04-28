package edu.ntnu.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.model.Die;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DieTest {
  private Die die;

  @BeforeEach
  void setUp() {
    die = new Die();
  }

  @Test
  void testRoll() {
    int value = die.roll();
    assertTrue(value >= 1 && value <= 6);
  }

  @Test
  void testGetValueAfterRoll() {
    die.roll();
    int value = die.roll();
    assertTrue(value >= 1 && value <= 6);
  }

  @Test
  void testGetValueBeforeRoll() {
    int value = die.getValue();
    assertEquals(0,value);
  }
}