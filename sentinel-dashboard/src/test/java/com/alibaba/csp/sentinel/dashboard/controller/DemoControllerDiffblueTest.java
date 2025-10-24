package com.alibaba.csp.sentinel.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.alibaba.csp.sentinel.dashboard.controller.DemoController.RunTask;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DemoController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class DemoControllerDiffblueTest {
  @Autowired
  private DemoController demoController;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link DemoController#greeting()}.
   * <p>
   * Method under test: {@link DemoController#greeting()}
   */
  @Test
  @DisplayName("Test greeting()")
  @Tag("MaintainedByDiffblue")
  void testGreeting() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/greeting");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(0))
        .andExpect(MockMvcResultMatchers.view().name("index"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
  }

  /**
   * Test {@link DemoController#link()}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>When {@link MockMvcRequestBuilders#get(String, Object[])} {@code /demo/link} secure {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#link()}
   */
  @Test
  @DisplayName("Test link(); given 'true'; when get(String, Object[]) '/demo/link' secure 'true'")
  @Tag("MaintainedByDiffblue")
  void testLink_givenTrue_whenGetDemoLinkSecureTrue() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/link");
    requestBuilder.secure(true);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a call link"));
  }

  /**
   * Test {@link DemoController#link()}.
   * <ul>
   *   <li>When {@link MockMvcRequestBuilders#get(String, Object[])} {@code /demo/link} {@code Uri Vars} and {@code Uri Vars}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#link()}
   */
  @Test
  @DisplayName("Test link(); when get(String, Object[]) '/demo/link' 'Uri Vars' and 'Uri Vars'")
  @Tag("MaintainedByDiffblue")
  void testLink_whenGetDemoLinkUriVarsAndUriVars() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/link", "Uri Vars", "Uri Vars", -1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a call link"));
  }

  /**
   * Test {@link DemoController#link()}.
   * <ul>
   *   <li>When {@code Uri Vars}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#link()}
   */
  @Test
  @DisplayName("Test link(); when 'Uri Vars'")
  @Tag("MaintainedByDiffblue")
  void testLink_whenUriVars() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/demo/link", "Uri Vars");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a call link"));
  }

  /**
   * Test {@link DemoController#loop(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#loop(String, int)}
   */
  @Test
  @DisplayName("Test loop(String, int); when empty string")
  @Tag("MaintainedByDiffblue")
  void testLoop_whenEmptyString() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/demo/loop").param("name", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("time", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a loop thread"));
  }

  /**
   * Test {@link DemoController#loop(String, int)}.
   * <ul>
   *   <li>When {@link MockMvcRequestBuilders#get(String, Object[])} {@code /demo/loop}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#loop(String, int)}
   */
  @Test
  @DisplayName("Test loop(String, int); when get(String, Object[]) '/demo/loop'")
  @Tag("MaintainedByDiffblue")
  void testLoop_whenGetDemoLoop() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/demo/loop").param("name", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("time", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a loop thread"));
  }

  /**
   * Test {@link DemoController#loop(String, int)}.
   * <ul>
   *   <li>When {@link MockMvcRequestBuilders#get(String, Object[])} {@code /demo/loop} {@code Uri Vars} and {@code Uri Vars}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#loop(String, int)}
   */
  @Test
  @DisplayName("Test loop(String, int); when get(String, Object[]) '/demo/loop' 'Uri Vars' and 'Uri Vars'")
  @Tag("MaintainedByDiffblue")
  void testLoop_whenGetDemoLoopUriVarsAndUriVars() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/demo/loop", "Uri Vars", "Uri Vars")
        .param("name", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("time", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a loop thread"));
  }

  /**
   * Test RunTask {@link RunTask#RunTask(String, int, boolean)}.
   * <p>
   * Method under test: {@link RunTask#RunTask(String, int, boolean)}
   */
  @Test
  @DisplayName("Test RunTask new RunTask(String, int, boolean)")
  @Tag("MaintainedByDiffblue")
  void testRunTaskNewRunTask() {
    // Arrange and Act
    RunTask actualRunTask = new RunTask("Name", 1, true);

    // Assert
    assertEquals("Name", actualRunTask.name);
    assertEquals(1, actualRunTask.time);
    assertFalse(actualRunTask.stop);
    assertTrue(actualRunTask.slow);
  }

  /**
   * Test RunTask {@link RunTask#run()}.
   * <p>
   * Method under test: {@link RunTask#run()}
   */
  @Test
  @DisplayName("Test RunTask run()")
  @Tag("MaintainedByDiffblue")
  void testRunTaskRun() {
    // Arrange
    RunTask runTask = new RunTask("Name", 1, true);

    // Act
    runTask.run();

    // Assert
    assertTrue(runTask.stop);
  }

  /**
   * Test {@link DemoController#slow(String, int)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#slow(String, int)}
   */
  @Test
  @DisplayName("Test slow(String, int); when empty string")
  @Tag("MaintainedByDiffblue")
  void testSlow_whenEmptyString() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/demo/slow").param("name", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("time", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a loop thread"));
  }

  /**
   * Test {@link DemoController#slow(String, int)}.
   * <ul>
   *   <li>When {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DemoController#slow(String, int)}
   */
  @Test
  @DisplayName("Test slow(String, int); when 'foo'")
  @Tag("MaintainedByDiffblue")
  void testSlow_whenFoo() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/demo/slow").param("name", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("time", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(demoController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("successfully create a loop thread"));
  }
}
