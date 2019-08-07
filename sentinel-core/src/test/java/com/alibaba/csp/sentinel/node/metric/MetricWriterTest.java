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

  /* testedClasses: MetricWriter */
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

  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   MetricWriter.class, File.class})
  @Test
  public void
  formMetricFileNameInputNotNullPositiveOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    final String appName = "......\u0000\u0000\u0000\u0000";
    final int pid = 1;
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("?")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final String actual = MetricWriter.formMetricFileName(appName, pid);

    // Assert result
    Assert.assertEquals("------\u0000\u0000\u0000\u0000-metrics.log", actual);
  }

  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   MetricWriter.class, File.class})
  @Test
  public void
  formMetricFileNameInputNullPositiveOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    final String appName = null;
    final int pid = 1;
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("?")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final String actual = MetricWriter.formMetricFileName(appName, pid);

    // Assert result
    Assert.assertEquals("-metrics.log", actual);
  }
}
