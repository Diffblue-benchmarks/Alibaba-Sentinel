package com.alibaba.csp.sentinel.config;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.util.AppNameUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class SentinelConfigTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  /* testedClasses: SentinelConfig */
  // Test written by Diffblue Cover.
  @PrepareForTest(AppNameUtil.class)
  @Test
  public void getAppNameOutputNotNull() throws Exception {

    // Setup mocks
    PowerMockito.mockStatic(AppNameUtil.class);

    // Arrange
    PowerMockito.doReturn("?").when(AppNameUtil.class);
    AppNameUtil.getAppName();

    // Act
    final String actual = SentinelConfig.getAppName();

    // Assert result
    Assert.assertEquals("?", actual);
  }
}
