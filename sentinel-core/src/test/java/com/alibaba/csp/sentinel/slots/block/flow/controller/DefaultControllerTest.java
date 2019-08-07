package com.alibaba.csp.sentinel.slots.block.flow.controller;

import com.alibaba.csp.sentinel.node.Node;
import com.alibaba.csp.sentinel.slots.block.flow.controller.DefaultController;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class DefaultControllerTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: DefaultController */
  // Test written by Diffblue Cover.
  @Test
  public void canPassInputNullPositiveFalseOutputFalse() {

    // Arrange
    final DefaultController objectUnderTest = new DefaultController(Double.NEGATIVE_INFINITY, 0);
    final Node node = null;
    final int acquireCount = 268_435_456;
    final boolean prioritized = false;

    // Act
    final boolean actual = objectUnderTest.canPass(node, acquireCount, prioritized);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void canPassInputNullPositiveOutputFalse() {

    // Arrange
    final DefaultController objectUnderTest = new DefaultController(Double.NEGATIVE_INFINITY, 0);
    final Node node = null;
    final int acquireCount = 32_768;

    // Act
    final boolean actual = objectUnderTest.canPass(node, acquireCount);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void canPassInputNullPositiveOutputTrue() {

    // Arrange
    final DefaultController objectUnderTest = new DefaultController(Double.NaN, 0);
    final Node node = null;
    final int acquireCount = 32_768;

    // Act
    final boolean actual = objectUnderTest.canPass(node, acquireCount);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void canPassInputNullPositiveTrueOutputFalse() {

    // Arrange
    final DefaultController objectUnderTest = new DefaultController(Double.NEGATIVE_INFINITY, 0);
    final Node node = null;
    final int acquireCount = 268_435_456;
    final boolean prioritized = true;

    // Act
    final boolean actual = objectUnderTest.canPass(node, acquireCount, prioritized);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void canPassInputNullPositiveTrueOutputTrue() {

    // Arrange
    final DefaultController objectUnderTest = new DefaultController(Double.NaN, 1);
    final Node node = null;
    final int acquireCount = 268_435_456;
    final boolean prioritized = true;

    // Act
    final boolean actual = objectUnderTest.canPass(node, acquireCount, prioritized);

    // Assert result
    Assert.assertTrue(actual);
  }
}
