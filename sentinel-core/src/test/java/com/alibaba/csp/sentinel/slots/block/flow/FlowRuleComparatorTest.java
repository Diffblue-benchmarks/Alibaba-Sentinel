package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleComparator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class FlowRuleComparatorTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: FlowRuleComparator */
  // Test written by Diffblue Cover.
  @Test
  public void compareInputNotNullNotNullOutputZero() {

    // Arrange
    final FlowRuleComparator objectUnderTest = new FlowRuleComparator();
    final FlowRule o1 = new FlowRule();
    final FlowRule o2 = new FlowRule();

    // Act
    final int actual = objectUnderTest.compare(o1, o2);

    // Assert result
    Assert.assertEquals(0, actual);
  }
}
