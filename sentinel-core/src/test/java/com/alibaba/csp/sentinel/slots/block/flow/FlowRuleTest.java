package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class FlowRuleTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void constructorInputNotNullOutputNotNull() {

    // Act, creating object to test constructor
    final FlowRule actual = new FlowRule("/");

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertEquals("/", actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull() {

    // Act, creating object to test constructor
    final FlowRule actual = new FlowRule();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getClusterConfigOutputNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertNull(flowRule.getClusterConfig());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getControlBehaviorOutputZero() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(0, flowRule.getControlBehavior());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getCountOutputZero() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(0.0, flowRule.getCount(), 0.0);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getGradeOutputPositive() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(1, flowRule.getGrade());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getMaxQueueingTimeMsOutputPositive() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(500, flowRule.getMaxQueueingTimeMs());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRaterOutputNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertNull(flowRule.getRater());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRefResourceOutputNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertNull(flowRule.getRefResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getStrategyOutputZero() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(0, flowRule.getStrategy());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getWarmUpPeriodSecOutputPositive() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertEquals(10, flowRule.getWarmUpPeriodSec());
  }

  // Test written by Diffblue Cover.
  @Test
  public void isClusterModeOutputFalse() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act and Assert result
    Assert.assertFalse(flowRule.isClusterMode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setClusterConfigInputNotNullOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();
    final ClusterFlowConfig clusterConfig = new ClusterFlowConfig();

    // Act
    final FlowRule actual = flowRule.setClusterConfig(clusterConfig);

    // Assert side effects
    Assert.assertNotNull(flowRule.getClusterConfig());
    Assert.assertEquals(0, flowRule.getClusterConfig().getThresholdType());
    Assert.assertEquals(10, flowRule.getClusterConfig().getSampleCount());
    Assert.assertEquals(1000, flowRule.getClusterConfig().getWindowIntervalMs());
    Assert.assertNull(flowRule.getClusterConfig().getFlowId());
    Assert.assertEquals(0, flowRule.getClusterConfig().getStrategy());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNotNull(actual.getClusterConfig());
    Assert.assertEquals(0, actual.getClusterConfig().getThresholdType());
    Assert.assertEquals(10, actual.getClusterConfig().getSampleCount());
    Assert.assertEquals(1000, actual.getClusterConfig().getWindowIntervalMs());
    Assert.assertNull(actual.getClusterConfig().getFlowId());
    Assert.assertEquals(0, actual.getClusterConfig().getStrategy());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setClusterModeInputFalseOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setClusterMode(false);

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setControlBehaviorInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setControlBehavior(2);

    // Assert side effects
    Assert.assertEquals(2, flowRule.getControlBehavior());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(2, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setCountInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setCount(2.0);

    // Assert side effects
    Assert.assertEquals(2.0, flowRule.getCount(), 0.0);

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(2.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setGradeInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setGrade(2);

    // Assert side effects
    Assert.assertEquals(2, flowRule.getGrade());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(2, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setMaxQueueingTimeMsInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setMaxQueueingTimeMs(2);

    // Assert side effects
    Assert.assertEquals(2, flowRule.getMaxQueueingTimeMs());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(2, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setRefResourceInputNotNullOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setRefResource("/");

    // Assert side effects
    Assert.assertEquals("/", flowRule.getRefResource());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertEquals("/", actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setStrategyInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setStrategy(2);

    // Assert side effects
    Assert.assertEquals(2, flowRule.getStrategy());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(10, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(2, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setWarmUpPeriodSecInputPositiveOutputNotNull() {

    // Arrange
    final FlowRule flowRule = new FlowRule();

    // Act
    final FlowRule actual = flowRule.setWarmUpPeriodSec(2);

    // Assert side effects
    Assert.assertEquals(2, flowRule.getWarmUpPeriodSec());

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getClusterConfig());
    Assert.assertEquals(500, actual.getMaxQueueingTimeMs());
    Assert.assertNull(actual.getRater());
    Assert.assertEquals(1, actual.getGrade());
    Assert.assertEquals(0.0, actual.getCount(), 0.0);
    Assert.assertEquals(2, actual.getWarmUpPeriodSec());
    Assert.assertEquals(0, actual.getControlBehavior());
    Assert.assertEquals(0, actual.getStrategy());
    Assert.assertNull(actual.getRefResource());
    Assert.assertEquals("default", actual.getLimitApp());
    Assert.assertNull(actual.getResource());
  }
}
