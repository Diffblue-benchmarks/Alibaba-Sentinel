package com.alibaba.csp.sentinel.config;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class SentinelConfigTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void getAppTypeOutputZero() {

    // Act and Assert result
    Assert.assertEquals(0, SentinelConfig.getAppType());
  }
}
