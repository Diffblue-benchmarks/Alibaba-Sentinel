package com.alibaba.csp.sentinel.node.metric;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.alibaba.csp.sentinel.log.LogConfigLoader;
import com.alibaba.csp.sentinel.node.metric.MetricWriter;
import com.alibaba.csp.sentinel.util.ConfigUtil;
import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.lang.reflect.Method;

@RunWith(PowerMockRunner.class)
public class MetricWriterTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void fileNameMatchesInputNotNullNotNullOutputFalse() {

    // Arrange
    final String fileName = "3";
    final String baseFileName = "a,b,c";

    // Act
    final boolean actual = MetricWriter.fileNameMatches(fileName, baseFileName);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void formIndexFileNameInputNotNullOutputNotNull() {

    // Arrange
    final String metricFileName = "3";

    // Act
    final String actual = MetricWriter.formIndexFileName(metricFileName);

    // Assert result
    Assert.assertEquals("3.idx", actual);
  }
}
