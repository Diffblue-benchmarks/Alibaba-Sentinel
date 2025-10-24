package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.command.vo.NodeVo;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import java.util.ArrayList;
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

@ContextConfiguration(classes = {ResourceController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class ResourceControllerDiffblueTest {
  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @Autowired
  private ResourceController resourceController;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}.
   * <p>
   * Method under test: {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}
   */
  @Test
  @DisplayName("Test fetchResourceChainListOfMachine(String, Integer, String, String)")
  @Tag("MaintainedByDiffblue")
  void testFetchResourceChainListOfMachine() throws Exception {
    // Arrange
    when(sentinelApiClient.fetchClusterNodeOfMachine(Mockito.<String>any(), anyInt(), anyBoolean()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/resource/machineResource.json")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1))
        .param("searchKey", "foo")
        .param("type", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(resourceController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}.
   * <ul>
   *   <li>Given {@link NodeVo} (default constructor) AverageRt is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}
   */
  @Test
  @DisplayName("Test fetchResourceChainListOfMachine(String, Integer, String, String); given NodeVo (default constructor) AverageRt is one")
  @Tag("MaintainedByDiffblue")
  void testFetchResourceChainListOfMachine_givenNodeVoAverageRtIsOne() throws Exception {
    // Arrange
    NodeVo nodeVo = new NodeVo();
    nodeVo.setAverageRt(3L);
    nodeVo.setBlockQps(3L);
    nodeVo.setExceptionQps(3L);
    nodeVo.setId("42");
    nodeVo.setOneMinuteBlock(3L);
    nodeVo.setOneMinuteException(3L);
    nodeVo.setOneMinutePass(3L);
    nodeVo.setOneMinuteTotal(3L);
    nodeVo.setParentId("42");
    nodeVo.setPassQps(3L);
    nodeVo.setResource("?");
    nodeVo.setSuccessQps(3L);
    nodeVo.setThreadNum(10);
    nodeVo.setTimestamp(10L);
    nodeVo.setTotalQps(3L);

    NodeVo nodeVo2 = new NodeVo();
    nodeVo2.setAverageRt(1L);
    nodeVo2.setBlockQps(1L);
    nodeVo2.setExceptionQps(1L);
    nodeVo2.setId("?");
    nodeVo2.setOneMinuteBlock(1L);
    nodeVo2.setOneMinuteException(1L);
    nodeVo2.setOneMinutePass(1L);
    nodeVo2.setOneMinuteTotal(1L);
    nodeVo2.setParentId("?");
    nodeVo2.setPassQps(1L);
    nodeVo2.setResource("root");
    nodeVo2.setSuccessQps(1L);
    nodeVo2.setThreadNum(3);
    nodeVo2.setTimestamp(3L);
    nodeVo2.setTotalQps(1L);

    ArrayList<NodeVo> nodeVoList = new ArrayList<>();
    nodeVoList.add(nodeVo2);
    nodeVoList.add(nodeVo);
    when(sentinelApiClient.fetchClusterNodeOfMachine(Mockito.<String>any(), anyInt(), anyBoolean()))
        .thenReturn(nodeVoList);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/resource/machineResource.json")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1))
        .param("searchKey", "foo")
        .param("type", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(resourceController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}.
   * <ul>
   *   <li>Given {@link NodeVo} (default constructor) AverageRt is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}
   */
  @Test
  @DisplayName("Test fetchResourceChainListOfMachine(String, Integer, String, String); given NodeVo (default constructor) AverageRt is three")
  @Tag("MaintainedByDiffblue")
  void testFetchResourceChainListOfMachine_givenNodeVoAverageRtIsThree() throws Exception {
    // Arrange
    NodeVo nodeVo = new NodeVo();
    nodeVo.setAverageRt(3L);
    nodeVo.setBlockQps(3L);
    nodeVo.setExceptionQps(3L);
    nodeVo.setId("42");
    nodeVo.setOneMinuteBlock(3L);
    nodeVo.setOneMinuteException(3L);
    nodeVo.setOneMinutePass(3L);
    nodeVo.setOneMinuteTotal(3L);
    nodeVo.setParentId("42");
    nodeVo.setPassQps(3L);
    nodeVo.setResource("?");
    nodeVo.setSuccessQps(3L);
    nodeVo.setThreadNum(10);
    nodeVo.setTimestamp(10L);
    nodeVo.setTotalQps(3L);

    ArrayList<NodeVo> nodeVoList = new ArrayList<>();
    nodeVoList.add(nodeVo);
    when(sentinelApiClient.fetchClusterNodeOfMachine(Mockito.<String>any(), anyInt(), anyBoolean()))
        .thenReturn(nodeVoList);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/resource/machineResource.json")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1))
        .param("searchKey", "foo")
        .param("type", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(resourceController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ResourceController#fetchResourceChainListOfMachine(String, Integer, String, String)}
   */
  @Test
  @DisplayName("Test fetchResourceChainListOfMachine(String, Integer, String, String); when empty string")
  @Tag("MaintainedByDiffblue")
  void testFetchResourceChainListOfMachine_whenEmptyString() throws Exception {
    // Arrange
    when(sentinelApiClient.fetchClusterNodeOfMachine(Mockito.<String>any(), anyInt(), anyBoolean()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/resource/machineResource.json")
        .param("ip", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1))
        .param("searchKey", "foo")
        .param("type", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(resourceController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid param, give ip, port\",\"data\":null}"));
  }
}
