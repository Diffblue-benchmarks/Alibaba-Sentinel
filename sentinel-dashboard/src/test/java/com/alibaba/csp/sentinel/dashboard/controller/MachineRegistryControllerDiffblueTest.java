package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MachineRegistryController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class MachineRegistryControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @Autowired
  private MachineRegistryController machineRegistryController;

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "https://example.org/example");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"invalid ip: https://example.org/example\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat2() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(-1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"your port not set yet\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid ip: ","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid ip: \",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_thenContentStringSuccessFalseCode1MsgInvalidIpDataNull() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid ip: \",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid ip: foo","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid ip: foo\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_thenContentStringSuccessFalseCode1MsgInvalidIpFooDataNull() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid ip: foo\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_thenContentStringSuccessTrueCode0MsgSuccessDataNull() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code app} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); when param(String, String[]) 'app' is empty string")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_whenParamAppIsEmptyString() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "")
        .param("hostname", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid appName\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code port} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); when param(String, String[]) 'port' is empty string")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_whenParamPortIsEmptyString() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9")
        .param("port", "")
        .param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid port\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code v} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); when param(String, String[]) 'v' is empty string")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_whenParamVIsEmptyString() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(1)).param("v", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code version} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); when param(String, String[]) 'version' is empty string")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_whenParamVersionIsEmptyString() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1))
        .param("v", "foo")
        .param("version", "");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}.
   * <ul>
   *   <li>When valueOf {@link Integer#MIN_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MachineRegistryController#receiveHeartBeat(String, Integer, Long, String, String, String, Integer)}
   */
  @Test
  @DisplayName("Test receiveHeartBeat(String, Integer, Long, String, String, String, Integer); when valueOf MIN_VALUE")
  @Tag("MaintainedByDiffblue")
  void testReceiveHeartBeat_whenValueOfMin_value() throws Exception {
    // Arrange
    when(appManagement.addMachine(Mockito.<MachineInfo>any())).thenReturn(1L);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/registry/machine")
        .param("app", "foo")
        .param("hostname", "foo")
        .param("ip", "9.9.9.9");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("port", String.valueOf(Integer.MIN_VALUE))
        .param("v", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("version", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(machineRegistryController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid port\",\"data\":null}"));
  }
}
