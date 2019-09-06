package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class FlowRuleUtilTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void isValidRuleInputNotNullOutputFalse() {

    // Arrange
    final FlowRule rule = new FlowRule();

    // Act
    final boolean actual = FlowRuleUtil.isValidRule(rule);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isValidRuleInputNullOutputFalse() {

    // Arrange
    final FlowRule rule = null;

    // Act
    final boolean actual = FlowRuleUtil.isValidRule(rule);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputPositivePositiveOutputFalse() {

    // Arrange
    final int sampleCount = 2;
    final int windowIntervalMs = 1;

    // Act
    final boolean actual = FlowRuleUtil.isWindowConfigValid(sampleCount, windowIntervalMs);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputPositivePositiveOutputTrue() {

    // Arrange
    final int sampleCount = 2;
    final int windowIntervalMs = 1_073_741_824;

    // Act
    final boolean actual = FlowRuleUtil.isWindowConfigValid(sampleCount, windowIntervalMs);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputPositiveZeroOutputFalse() {

    // Arrange
    final int sampleCount = 1;
    final int windowIntervalMs = 0;

    // Act
    final boolean actual = FlowRuleUtil.isWindowConfigValid(sampleCount, windowIntervalMs);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputZeroZeroOutputFalse() {

    // Arrange
    final int sampleCount = 0;
    final int windowIntervalMs = 0;

    // Act
    final boolean actual = FlowRuleUtil.isWindowConfigValid(sampleCount, windowIntervalMs);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void validClusterRuleIdInputNullOutputFalse() {

    // Arrange
    final Long id = null;

    // Act
    final boolean actual = FlowRuleUtil.validClusterRuleId(id);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void validClusterRuleIdInputPositiveOutputTrue() {

    // Arrange
    final Long id = 1L;

    // Act
    final boolean actual = FlowRuleUtil.validClusterRuleId(id);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void validClusterRuleIdInputZeroOutputFalse() {

    // Arrange
    final Long id = 0L;

    // Act
    final boolean actual = FlowRuleUtil.validClusterRuleId(id);

    // Assert result
    Assert.assertFalse(actual);
  }
}
