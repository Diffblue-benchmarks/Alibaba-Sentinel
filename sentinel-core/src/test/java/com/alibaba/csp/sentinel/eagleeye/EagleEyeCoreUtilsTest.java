package com.alibaba.csp.sentinel.eagleeye;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EagleEyeCoreUtilsTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIsBlank() {
        Assert.assertTrue(EagleEyeCoreUtils.isBlank(""));
        Assert.assertTrue(EagleEyeCoreUtils.isBlank(" "));
        Assert.assertTrue(EagleEyeCoreUtils.isBlank(null));

        Assert.assertFalse(EagleEyeCoreUtils.isBlank("foo"));
    }

    @Test
    public void testCheckNotNullEmpty() {
        Assert.assertEquals("foo",
                EagleEyeCoreUtils.checkNotNullEmpty("foo", "bar"));

        thrown.expect(IllegalArgumentException.class);
        EagleEyeCoreUtils.checkNotNullEmpty("", "bar");
        EagleEyeCoreUtils.checkNotNullEmpty(" ", "bar");
        EagleEyeCoreUtils.checkNotNullEmpty(null, "bar");
        // Methods are not expected to return due to exception thrown
    }

    @Test
    public void testCheckNotNull() {
        Assert.assertEquals("", EagleEyeCoreUtils.checkNotNull("", "bar"));
        Assert.assertEquals(" ", EagleEyeCoreUtils.checkNotNull(" ", "bar"));
        Assert.assertEquals("foo",
                EagleEyeCoreUtils.checkNotNull("foo", "bar"));

        thrown.expect(IllegalArgumentException.class);
        EagleEyeCoreUtils.checkNotNull(null, "bar");
        // Method is not expected to return due to exception thrown
    }

    @Test
    public void testDefaultIfNull() {
        Assert.assertEquals("", EagleEyeCoreUtils.defaultIfNull("", "bar"));
        Assert.assertEquals(" ", EagleEyeCoreUtils.defaultIfNull(" ", "bar"));
        Assert.assertEquals("bar",
                EagleEyeCoreUtils.defaultIfNull(null, "bar"));
        Assert.assertEquals("foo",
                EagleEyeCoreUtils.defaultIfNull("foo", "bar"));
    }

    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(EagleEyeCoreUtils.isNotBlank(""));
        Assert.assertFalse(EagleEyeCoreUtils.isNotBlank(" "));
        Assert.assertFalse(EagleEyeCoreUtils.isNotBlank(null));

        Assert.assertTrue(EagleEyeCoreUtils.isNotBlank("foo"));
    }

    @Test
    public void testIsNotEmpty() {
        Assert.assertFalse(EagleEyeCoreUtils.isNotEmpty(""));
        Assert.assertFalse(EagleEyeCoreUtils.isNotEmpty(null));

        Assert.assertTrue(EagleEyeCoreUtils.isNotEmpty(" "));
        Assert.assertTrue(EagleEyeCoreUtils.isNotEmpty("foo"));
    }

    @Test
    public void testTrim() {
        Assert.assertNull(EagleEyeCoreUtils.trim(null));

        Assert.assertEquals("", EagleEyeCoreUtils.trim(""));
        Assert.assertEquals("", EagleEyeCoreUtils.trim(" "));
        Assert.assertEquals("foo", EagleEyeCoreUtils.trim("foo"));
        Assert.assertEquals("foo", EagleEyeCoreUtils.trim("foo "));
        Assert.assertEquals("foo", EagleEyeCoreUtils.trim(" foo"));
        Assert.assertEquals("foo", EagleEyeCoreUtils.trim(" foo "));
        Assert.assertEquals("f o o", EagleEyeCoreUtils.trim(" f o o "));
    }

    @Test
    public void testSplit() {
        Assert.assertNull(EagleEyeCoreUtils.split(null, 'a'));

        Assert.assertArrayEquals(new String[0],
                EagleEyeCoreUtils.split("", ','));
        Assert.assertArrayEquals(new String[]{"foo", "bar", "baz"},
                EagleEyeCoreUtils.split("foo,bar,baz", ','));
    }

    @Test
    public void testAppendWithBlankCheck() {
        Assert.assertEquals(new StringBuilder("bar").toString(),
                EagleEyeCoreUtils.appendWithBlankCheck(
                        null, "bar", new StringBuilder()).toString());
        Assert.assertEquals(new StringBuilder("foo").toString(),
                EagleEyeCoreUtils.appendWithBlankCheck(
                        "foo", "bar", new StringBuilder()).toString());
    }

    @Test
    public void testAppendWithNullCheck() {
        Assert.assertEquals(new StringBuilder("bar").toString(),
                EagleEyeCoreUtils.appendWithNullCheck(
                        null, "bar", new StringBuilder()).toString());
        Assert.assertEquals(new StringBuilder("foo").toString(),
                EagleEyeCoreUtils.appendWithNullCheck(
                        "foo", "bar", new StringBuilder()).toString());
    }

    @Test
    public void testAppendLog() {
        Assert.assertEquals(new StringBuilder("foo bar baz foo").toString(),
                EagleEyeCoreUtils.appendLog(
                        "foo\nbar\rbaz,foo", new StringBuilder(), ',')
                        .toString());
        Assert.assertEquals(new StringBuilder().toString(),
                EagleEyeCoreUtils.appendLog(null, new StringBuilder(), ',')
                        .toString());
    }

    @Test
    public void testFormatTime() {
        Assert.assertEquals("2019-06-15 20:13:14.000",
                EagleEyeCoreUtils.formatTime(1560600794000L));
    }

    @Test
    public void testGetSystemProperty() {
        Assert.assertNull(EagleEyeCoreUtils.getSystemProperty(null));
        Assert.assertNull(EagleEyeCoreUtils.getSystemProperty("foo"));
    }

    @Test
    public void testGetSystemPropertyForLong() {
        Assert.assertEquals(2L,
                EagleEyeCoreUtils.getSystemPropertyForLong(null, 2L));
        Assert.assertEquals(2L,
                EagleEyeCoreUtils.getSystemPropertyForLong("123L", 2L));
    }

    @Test
    public void testIsHexNumeric() {
        Assert.assertTrue(EagleEyeCoreUtils.isHexNumeric('2'));
        Assert.assertTrue(EagleEyeCoreUtils.isHexNumeric('b'));

        Assert.assertFalse(EagleEyeCoreUtils.isHexNumeric('z'));
        Assert.assertFalse(EagleEyeCoreUtils.isHexNumeric('%'));
    }

    @Test
    public void testIsNumeric() {
        Assert.assertTrue(EagleEyeCoreUtils.isNumeric('2'));

        Assert.assertFalse(EagleEyeCoreUtils.isNumeric('b'));
        Assert.assertFalse(EagleEyeCoreUtils.isNumeric('%'));
    }
}
