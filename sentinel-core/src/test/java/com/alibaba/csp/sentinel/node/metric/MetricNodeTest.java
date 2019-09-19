package com.alibaba.csp.sentinel.node.metric;

import com.alibaba.csp.sentinel.node.metric.MetricNode;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class MetricNodeTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull() {

    // Act, creating object to test constructor
    final MetricNode actual = new MetricNode();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(0L, actual.getRt());
    Assert.assertEquals(0L, actual.getTimestamp());
    Assert.assertEquals(0L, actual.getBlockQps());
    Assert.assertEquals(0L, actual.getPassQps());
    Assert.assertEquals(0L, actual.getExceptionQps());
    Assert.assertEquals(0L, actual.getOccupiedPassQps());
    Assert.assertNull(actual.getResource());
    Assert.assertEquals(0L, actual.getSuccessQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void fromFatStringInputNullOutputNullPointerException() {

    // Act
    thrown.expect(NullPointerException.class);
    MetricNode.fromFatString(null);

    // The method is not expected to return due to exception thrown
  }

  // Test written by Diffblue Cover.
  @Test
  public void getBlockQpsOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getBlockQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getExceptionQpsOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getExceptionQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getOccupiedPassQpsOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getOccupiedPassQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getPassQpsOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getPassQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getResourceOutputNull() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertNull(metricNode.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getRtOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getRt());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getSuccessQpsOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getSuccessQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getTimestampOutputZero() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act and Assert result
    Assert.assertEquals(0L, metricNode.getTimestamp());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setBlockQpsInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setBlockQps(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getBlockQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setExceptionQpsInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setExceptionQps(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getExceptionQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setOccupiedPassQpsInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setOccupiedPassQps(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getOccupiedPassQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setPassQpsInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setPassQps(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getPassQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setResourceInputNotNullOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setResource("/");

    // Assert side effects
    Assert.assertEquals("/", metricNode.getResource());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setRtInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setRt(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getRt());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setSuccessQpsInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setSuccessQps(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getSuccessQps());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setTimestampInputPositiveOutputVoid() {

    // Arrange
    final MetricNode metricNode = new MetricNode();

    // Act
    metricNode.setTimestamp(2L);

    // Assert side effects
    Assert.assertEquals(2L, metricNode.getTimestamp());
  }

  // Test written by Diffblue Cover.
  @Test
  public void toStringOutputNotNull() {

    // Arrange
    final MetricNode metricNode = new MetricNode();
    metricNode.setRt(9L);
    metricNode.setTimestamp(0L);
    metricNode.setBlockQps(8L);
    metricNode.setPassQps(8L);
    metricNode.setExceptionQps(9L);
    metricNode.setOccupiedPassQps(3L);
    metricNode.setResource("????????????????????????????????????");
    metricNode.setSuccessQps(0L);

    // Act and Assert result
    Assert.assertEquals(
        "MetricNode{timestamp=0, passQps=8, blockQps=8, successQps=0, exceptionQps=9, rt=9, occupiedPassQps=3, resource='????????????????????????????????????'}",
        metricNode.toString());
  }
}
