package com.alibaba.csp.sentinel.util;

import static org.mockito.AdditionalMatchers.or;

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
  public void capitalizeInputNullOutputNull() {

    // Arrange
    final String str = null;

    // Act
    final String actual = StringUtil.capitalize(str);

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNotNullNotNullOutputFalse2() {

    // Arrange
    final CharSequence str1 = "??";
    final CharSequence str2 = "?";

    // Act
    final boolean actual = StringUtil.equalsIgnoreCase(str1, str2);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNotNullNullOutputFalse() {

    // Arrange
    final CharSequence str1 = "?";
    final CharSequence str2 = null;

    // Act
    final boolean actual = StringUtil.equalsIgnoreCase(str1, str2);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsIgnoreCaseInputNullNullOutputTrue() {

    // Arrange
    final CharSequence str1 = null;
    final CharSequence str2 = null;

    // Act
    final boolean actual = StringUtil.equalsIgnoreCase(str1, str2);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullNotNullOutputTrue() {

    // Arrange
    final String str1 = ",";
    final String str2 = ",";

    // Act
    final boolean actual = StringUtil.equals(str1, str2);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullNotNullOutputFalse() {

    // Arrange
    final String str1 = null;
    final String str2 = "";

    // Act
    final boolean actual = StringUtil.equals(str1, str2);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullNullOutputTrue() {

    // Arrange
    final String str1 = null;
    final String str2 = null;

    // Act
    final boolean actual = StringUtil.equals(str1, str2);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNotNullOutputFalse() {

    // Arrange
    final String str = "\'";

    // Act
    final boolean actual = StringUtil.isBlank(str);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNotNullOutputTrue() {

    // Arrange
    final String str = "\u2001";

    // Act
    final boolean actual = StringUtil.isBlank(str);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isBlankInputNullOutputTrue() {

    // Arrange
    final String str = null;

    // Act
    final boolean actual = StringUtil.isBlank(str);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.

  @Test
  public void isEmptyInputNotNullOutputFalse() {

    // Arrange
    final String str = "1";

    // Act
    final boolean actual = StringUtil.isEmpty(str);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isEmptyInputNullOutputTrue() {

    // Arrange
    final String str = null;

    // Act
    final boolean actual = StringUtil.isEmpty(str);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotBlankInputNotNullOutputTrue() {

    // Arrange
    final String str = "\'";

    // Act
    final boolean actual = StringUtil.isNotBlank(str);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotBlankInputNullOutputFalse() {

    // Arrange
    final String str = null;

    // Act
    final boolean actual = StringUtil.isNotBlank(str);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotEmptyInputNotNullOutputTrue() {

    // Arrange
    final String str = "3";

    // Act
    final boolean actual = StringUtil.isNotEmpty(str);

    // Assert result
    Assert.assertTrue(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void isNotEmptyInputNullOutputFalse() {

    // Arrange
    final String str = null;

    // Act
    final boolean actual = StringUtil.isNotEmpty(str);

    // Assert result
    Assert.assertFalse(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimInputNotNullOutputNotNull() {

    // Arrange
    final String str = "\'";

    // Act
    final String actual = StringUtil.trim(str);

    // Assert result
    Assert.assertEquals("\'", actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimInputNullOutputNull() {

    // Arrange
    final String str = null;

    // Act
    final String actual = StringUtil.trim(str);

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimToEmptyInputNotNullOutputNotNull() {

    // Arrange
    final String str = "A1B2C3";

    // Act
    final String actual = StringUtil.trimToEmpty(str);

    // Assert result
    Assert.assertEquals("A1B2C3", actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void trimToEmptyInputNullOutputNotNull() {

    // Arrange
    final String str = null;

    // Act
    final String actual = StringUtil.trimToEmpty(str);

    // Assert result
    Assert.assertEquals("", actual);
  }
}
