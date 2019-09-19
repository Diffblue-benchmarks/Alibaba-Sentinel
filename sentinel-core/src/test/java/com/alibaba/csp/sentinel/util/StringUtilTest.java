package com.alibaba.csp.sentinel.util;

import com.alibaba.csp.sentinel.util.StringUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class StringUtilTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void capitalizeInputNotNullOutputNotNull6() {

    // Act and Assert result
    Assert.assertEquals("", StringUtil.capitalize(""));
  }

  // Test written by Diffblue Cover.
  @Test
  public void capitalizeInputNullOutputNull() {

    // Act and Assert result
    Assert.assertNull(StringUtil.capitalize(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNotNullNotNullOutputFalse() {

    // Arrange
    final CharSequence str1 = "?";
    final CharSequence str2 = "?????????????????????????????????????????????????????????????????";

    // Act and Assert result
    Assert.assertFalse(StringUtil.equalsIgnoreCase(str1, str2));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNotNullNullOutputFalse() {

    // Arrange
    final CharSequence str1 = "?";

    // Act and Assert result
    Assert.assertFalse(StringUtil.equalsIgnoreCase(str1, null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNullNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.equalsIgnoreCase(null, null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullNotNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.equals("foo", "/"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullNotNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.equals(null, ""));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.equals(null, null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNotNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.isBlank("'"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNotNullOutputFalse2() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.isBlank("\n\u0000\u0000???"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNotNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.isBlank("\n"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.isBlank(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isEmptyInputNotNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.isEmpty("a'b'c"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isEmptyInputNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.isEmpty(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotBlankInputNotNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.isNotBlank("'"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotBlankInputNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.isNotBlank(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotEmptyInputNotNullOutputTrue() {

    // Act and Assert result
    Assert.assertTrue(StringUtil.isNotEmpty("/"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotEmptyInputNullOutputFalse() {

    // Act and Assert result
    Assert.assertFalse(StringUtil.isNotEmpty(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimInputNotNullOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("a'b'c", StringUtil.trim("a'b'c"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimInputNullOutputNull() {

    // Act and Assert result
    Assert.assertNull(StringUtil.trim(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimToEmptyInputNotNullOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("foo", StringUtil.trimToEmpty("foo"));
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimToEmptyInputNullOutputNotNull() {

    // Act and Assert result
    Assert.assertEquals("", StringUtil.trimToEmpty(null));
  }
}
