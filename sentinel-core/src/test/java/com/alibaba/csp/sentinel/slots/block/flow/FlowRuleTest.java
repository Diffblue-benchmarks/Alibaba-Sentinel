package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.node.DefaultNode;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.TrafficShapingController;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class FlowRuleTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: FlowRule */
  // Test written by Diffblue Cover.

  @Test
  public void constructorInputNotNullOutputVoid() {

    // Arrange
    final String resourceName = ",";

    // Act, creating object to test constructor
    final FlowRule objectUnderTest = new FlowRule(resourceName);

    // Assert side effects
    Assert.assertEquals(",", objectUnderTest.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();
    final FlowRule o = new FlowRule();

    // Act
    final boolean actual = objectUnderTest.equals(o);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullOutputFalse() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();
    final Object o = null;

    // Act
    final boolean actual = objectUnderTest.equals(o);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getClusterConfigOutputNull() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final ClusterFlowConfig actual = objectUnderTest.getClusterConfig();

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getControlBehaviorOutputZero() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.getControlBehavior();

    // Assert result
    Assert.assertEquals(0, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getCountOutputZero() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final double actual = objectUnderTest.getCount();

    // Assert result
    Assert.assertEquals(0.0, actual, 0.0);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getGradeOutputPositive() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.getGrade();

    // Assert result
    Assert.assertEquals(1, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getMaxQueueingTimeMsOutputPositive() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.getMaxQueueingTimeMs();

    // Assert result
    Assert.assertEquals(500, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRaterOutputNull() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final TrafficShapingController actual = objectUnderTest.getRater();

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRefResourceOutputNull() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final String actual = objectUnderTest.getRefResource();

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getStrategyOutputZero() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.getStrategy();

    // Assert result
    Assert.assertEquals(0, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getWarmUpPeriodSecOutputPositive() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.getWarmUpPeriodSec();

    // Assert result
    Assert.assertEquals(10, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputZero() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final int actual = objectUnderTest.hashCode();

    // Assert result
    Assert.assertEquals(0, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isClusterModeOutputFalse() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();

    // Act
    final boolean actual = objectUnderTest.isClusterMode();

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void passCheckInputNullNullZeroNullOutputTrue() {

    // Arrange
    final FlowRule objectUnderTest = new FlowRule();
    final Context context = null;
    final DefaultNode node = null;
    final int acquireCount = 0;
    final Object[] args = null;

    // Act
    final boolean actual = objectUnderTest.passCheck(context, node, acquireCount, args);

    // Assert result
    Assert.assertTrue(actual);
  }
}
