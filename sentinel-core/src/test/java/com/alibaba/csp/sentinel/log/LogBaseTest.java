package com.alibaba.csp.sentinel.log;

import com.alibaba.csp.sentinel.log.LogBase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class LogBaseTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void getLogBaseDirOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("/root/logs/csp/", LogBase.getLogBaseDir());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getLogCharsetOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("utf-8", LogBase.getLogCharset());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getLogOutputTypeOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("file", LogBase.getLogOutputType());
  }

  // Test written by Diffblue Cover.
  @Test
  public void isLogNameUsePidOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(LogBase.isLogNameUsePid());
  }
}
