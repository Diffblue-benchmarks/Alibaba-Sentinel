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

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull() {

    // Act, creating object to test constructor
    final ClusterFlowConfig actual = new ClusterFlowConfig();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getFlowIdOutputNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertNull(clusterFlowConfig.getFlowId());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getSampleCountOutputPositive() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(10, clusterFlowConfig.getSampleCount());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getStrategyOutputZero() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(0, clusterFlowConfig.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getThresholdTypeOutputZero() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(0, clusterFlowConfig.getThresholdType());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getWindowIntervalMsOutputPositive() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(1000, clusterFlowConfig.getWindowIntervalMs());
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputPositive() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(31_101, clusterFlowConfig.hashCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void isFallbackToLocalWhenFailOutputTrue() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertTrue(clusterFlowConfig.isFallbackToLocalWhenFail());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setFallbackToLocalWhenFailInputFalseOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setFallbackToLocalWhenFail(false);

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setFlowIdInputPositiveOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setFlowId(2L);

    // Assert side effects
    Assert.assertEquals(new Long(2L), clusterFlowConfig.getFlowId());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertEquals(new Long(2L), actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setSampleCountInputPositiveOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setSampleCount(2);

    // Assert side effects
    Assert.assertEquals(2, clusterFlowConfig.getSampleCount());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(2, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setStrategyInputPositiveOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setStrategy(2);

    // Assert side effects
    Assert.assertEquals(2, clusterFlowConfig.getStrategy());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(2, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setThresholdTypeInputPositiveOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setThresholdType(2);

    // Assert side effects
    Assert.assertEquals(2, clusterFlowConfig.getThresholdType());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(2, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(1000, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setWindowIntervalMsInputPositiveOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act
    final ClusterFlowConfig actual = clusterFlowConfig.setWindowIntervalMs(2);

    // Assert side effects
    Assert.assertEquals(2, clusterFlowConfig.getWindowIntervalMs());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.getThresholdType());
    Assert.assertEquals(10, actual.getSampleCount());
    Assert.assertEquals(2, actual.getWindowIntervalMs());
    Assert.assertNull(actual.getFlowId());
    Assert.assertEquals(0, actual.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void toStringOutputNotNull() {

    // Arrange
    final ClusterFlowConfig clusterFlowConfig = new ClusterFlowConfig();

    // Act and Assert result
    Assert.assertEquals(
        "ClusterFlowConfig{flowId=null, thresholdType=0, fallbackToLocalWhenFail=true, strategy=0, sampleCount=10, windowIntervalMs=1000}",
        clusterFlowConfig.toString());
  }
}
