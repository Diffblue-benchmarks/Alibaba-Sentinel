package com.alibaba.csp.sentinel.node.metric;

import com.alibaba.csp.sentinel.node.metric.MetricWriter;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class MetricWriterTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void fileNameMatchesInputNotNullNotNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(MetricWriter.fileNameMatches(",", "foo"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void formIndexFileNameInputNotNullOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("foo.idx", MetricWriter.formIndexFileName("foo"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void formMetricFileNameInputNotNullPositiveOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("/-metrics.log", MetricWriter.formMetricFileName("/", 2));
  }

  // Test written by Diffblue Cover.
  @Test
  public void formMetricFileNameInputNotNullZeroOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("--metrics.log", MetricWriter.formMetricFileName(".", 0));
  }

  // Test written by Diffblue Cover.
  @Test
  public void formMetricFileNameInputNullZeroOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("-metrics.log", MetricWriter.formMetricFileName(null, 0));
  }
}
