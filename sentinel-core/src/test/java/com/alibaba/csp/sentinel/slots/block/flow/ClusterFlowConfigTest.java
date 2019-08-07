package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class ClusterFlowConfigTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: ClusterFlowConfig */
  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();
    final ClusterFlowConfig o = new ClusterFlowConfig();

    // Act
    final boolean actual = objectUnderTest.equals(o);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullOutputFalse() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();
    final Object o = null;

    // Act
    final boolean actual = objectUnderTest.equals(o);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getFlowIdOutputNull() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final Long actual = objectUnderTest.getFlowId();

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getSampleCountOutputPositive() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final int actual = objectUnderTest.getSampleCount();

    // Assert result
    Assert.assertEquals(10, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getStrategyOutputZero() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final int actual = objectUnderTest.getStrategy();

    // Assert result
    Assert.assertEquals(0, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getThresholdTypeOutputZero() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final int actual = objectUnderTest.getThresholdType();

    // Assert result
    Assert.assertEquals(0, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getWindowIntervalMsOutputPositive() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final int actual = objectUnderTest.getWindowIntervalMs();

    // Assert result
    Assert.assertEquals(1000, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputPositive() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final int actual = objectUnderTest.hashCode();

    // Assert result
    Assert.assertEquals(31_101, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isFallbackToLocalWhenFailOutputTrue() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final boolean actual = objectUnderTest.isFallbackToLocalWhenFail();

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void toStringOutputNotNull() {

    // Arrange
    final ClusterFlowConfig objectUnderTest = new ClusterFlowConfig();

    // Act
    final String actual = objectUnderTest.toString();

    // Assert result
    Assert.assertEquals(
        "ClusterFlowConfig{flowId=null, thresholdType=0, fallbackToLocalWhenFail=true, strategy=0, sampleCount=10, windowIntervalMs=1000}",
        actual);
  }
}
