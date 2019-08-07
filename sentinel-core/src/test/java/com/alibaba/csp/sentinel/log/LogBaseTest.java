package com.alibaba.csp.sentinel.log;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.alibaba.csp.sentinel.log.LogBase;
import com.alibaba.csp.sentinel.log.LogConfigLoader;
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
public class LogBaseTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: LogBase */
  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   LogBase.class, File.class})
  @Test
  public void
  getLogBaseDirOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("??????????????????????????")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final String actual = LogBase.getLogBaseDir();

    // Assert result
    Assert.assertEquals("??????????????????????????logs/csp/", actual);
  }

  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   LogBase.class, File.class})
  @Test
  public void
  getLogCharsetOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("??????????????????????????")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final String actual = LogBase.getLogCharset();

    // Assert result
    Assert.assertEquals("utf-8", actual);
  }

  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   LogBase.class, File.class})
  @Test
  public void
  getLogOutputTypeOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("??????????????????????????")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final String actual = LogBase.getLogOutputType();

    // Assert result
    Assert.assertEquals("file", actual);
  }

  // Test written by Diffblue Cover.
  @PrepareForTest({LogConfigLoader.class, ConfigUtil.class, Throwable.class, System.class,
                   LogBase.class, File.class})
  @Test
  public void
  isLogNameUsePidOutputFalse() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(ConfigUtil.class);
    PowerMockito.mockStatic(System.class);
    PowerMockito.mockStatic(LogConfigLoader.class);

    // Arrange
    PowerMockito.doReturn(null).when(LogConfigLoader.class);
    LogConfigLoader.getProperties();
    PowerMockito.when(System.getProperty(or(isA(String.class), isNull(String.class))))
        .thenReturn(null);
    final Method addSeparatorMethod =
        DTUMemberMatcher.method(ConfigUtil.class, "addSeparator", String.class);
    PowerMockito.doReturn("??????????????????????????")
        .when(ConfigUtil.class, addSeparatorMethod)
        .withArguments(or(isA(String.class), isNull(String.class)));

    // Act
    final boolean actual = LogBase.isLogNameUsePid();

    // Assert result
    Assert.assertFalse(actual);
  }
}
