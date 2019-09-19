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

    // Act and Assert result
    Assert.assertFalse(FlowRuleUtil.isValidRule(rule));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isValidRuleInputNotNullOutputTrue() {

    // Arrange
    final FlowRule rule = new FlowRule("foo");

    // Act and Assert result
    Assert.assertTrue(FlowRuleUtil.isValidRule(rule));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputPositivePositiveOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(FlowRuleUtil.isWindowConfigValid(3, 2));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isWindowConfigValidInputPositivePositiveOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(FlowRuleUtil.isWindowConfigValid(3, 786_432));
  }

  // Test written by Diffblue Cover.
  @Test
  public void validClusterRuleIdInputNegativeOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(FlowRuleUtil.validClusterRuleId(-999_998L));
  }

  // Test written by Diffblue Cover.
  @Test
  public void validClusterRuleIdInputPositiveOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(FlowRuleUtil.validClusterRuleId(999_938L));
  }
}
