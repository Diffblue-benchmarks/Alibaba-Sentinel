package com.alibaba.csp.sentinel.node.metric;

import com.alibaba.csp.sentinel.node.metric.MetricNode;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MetricNodeTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void fromFatStringInputNullOutputNullPointerException() {

    // Arrange
    final String line = null;

    // Act
    thrown.expect(NullPointerException.class);
    MetricNode.fromFatString(line);

    // Method is not expected to return due to exception thrown
  }

  // Test written by Diffblue Cover.
  @Test
  public void fromThinStringInputNullOutputNullPointerException() {

    // Arrange
    final String line = null;

    // Act
    thrown.expect(NullPointerException.class);
    MetricNode.fromThinString(line);

    // Method is not expected to return due to exception thrown
  }

  // Test written by Diffblue Cover.
  @Test
  public void getBlockQpsOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getBlockQps();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getExceptionQpsOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getExceptionQps();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getOccupiedPassQpsOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getOccupiedPassQps();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getPassQpsOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getPassQps();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getResourceOutputNull() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final String actual = objectUnderTest.getResource();

    // Assert result
    Assert.assertNull(actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRtOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getRt();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getSuccessQpsOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getSuccessQps();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void getTimestampOutputZero() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();

    // Act
    final long actual = objectUnderTest.getTimestamp();

    // Assert result
    Assert.assertEquals(0L, actual);
  }

  // Test written by Diffblue Cover.
  @Test
  public void setResourceInputNotNullOutputVoid() {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();
    final String resource = "3";

    // Act
    objectUnderTest.setResource(resource);

    // Assert side effects
    Assert.assertEquals("3", objectUnderTest.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void toStringOutputNotNull() throws InvocationTargetException {

    // Arrange
    final MetricNode objectUnderTest = new MetricNode();
    objectUnderTest.setRt(1L);
    objectUnderTest.setTimestamp(3L);
    objectUnderTest.setBlockQps(1L);
    objectUnderTest.setPassQps(786_438L);
    objectUnderTest.setExceptionQps(69_893_478L);
    objectUnderTest.setOccupiedPassQps(5L);
    objectUnderTest.setResource("1a 2b 3c");
    objectUnderTest.setSuccessQps(8L);

    // Act
    final String actual = objectUnderTest.toString();

    // Assert result
    Assert.assertEquals(
        "MetricNode{timestamp=3, passQps=786438, blockQps=1, successQps=8, exceptionQps=69893478, rt=1, occupiedPassQps=5, resource=\'1a 2b 3c\'}",
        actual);
  }
}
