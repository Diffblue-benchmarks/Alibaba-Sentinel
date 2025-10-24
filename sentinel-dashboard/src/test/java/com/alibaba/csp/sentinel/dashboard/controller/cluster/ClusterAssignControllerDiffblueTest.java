package com.alibaba.csp.sentinel.dashboard.controller.cluster;

import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.ClusterAppAssignResultVO;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.ClusterAppFullAssignRequest;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.ClusterAppSingleServerAssignRequest;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.request.ClusterAppAssignMap;
import com.alibaba.csp.sentinel.dashboard.service.ClusterAssignService;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClusterAssignController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class ClusterAssignControllerDiffblueTest {
  @Autowired
  private ClusterAssignController clusterAssignController;

  @MockBean
  private ClusterAssignService clusterAssignService;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link ClusterAssignController#apiAssignAllClusterServersOfApp(String, ClusterAppFullAssignRequest)}.
   * <p>
   * Method under test: {@link ClusterAssignController#apiAssignAllClusterServersOfApp(String, ClusterAppFullAssignRequest)}
   */
  @Test
  @DisplayName("Test apiAssignAllClusterServersOfApp(String, ClusterAppFullAssignRequest)")
  @Tag("MaintainedByDiffblue")
  void testApiAssignAllClusterServersOfApp() throws Exception {
    // Arrange
    ClusterAppAssignResultVO clusterAppAssignResultVO = new ClusterAppAssignResultVO();
    clusterAppAssignResultVO.setFailedClientSet(new HashSet<>());
    clusterAppAssignResultVO.setFailedServerSet(new HashSet<>());
    clusterAppAssignResultVO.setTotalCount(3);
    when(clusterAssignService.applyAssignToApp(Mockito.<String>any(), Mockito.<List<ClusterAppAssignMap>>any(),
        Mockito.<Set<String>>any())).thenReturn(clusterAppAssignResultVO);

    ClusterAppFullAssignRequest clusterAppFullAssignRequest = new ClusterAppFullAssignRequest();
    clusterAppFullAssignRequest.setClusterMap(new ArrayList<>());
    clusterAppFullAssignRequest.setRemainingList(new HashSet<>());
    String content = new ObjectMapper().writeValueAsString(clusterAppFullAssignRequest);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cluster/assign/all_server/42")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(clusterAssignController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"failedServerSet\":[],\"failedClientSet\":[],\"totalCount"
                    + "\":3}}"));
  }

  /**
   * Test {@link ClusterAssignController#apiAssignSingleClusterServersOfApp(String, ClusterAppSingleServerAssignRequest)}.
   * <p>
   * Method under test: {@link ClusterAssignController#apiAssignSingleClusterServersOfApp(String, ClusterAppSingleServerAssignRequest)}
   */
  @Test
  @DisplayName("Test apiAssignSingleClusterServersOfApp(String, ClusterAppSingleServerAssignRequest)")
  @Tag("MaintainedByDiffblue")
  void testApiAssignSingleClusterServersOfApp() throws Exception {
    // Arrange
    ClusterAppAssignResultVO clusterAppAssignResultVO = new ClusterAppAssignResultVO();
    clusterAppAssignResultVO.setFailedClientSet(new HashSet<>());
    clusterAppAssignResultVO.setFailedServerSet(new HashSet<>());
    clusterAppAssignResultVO.setTotalCount(3);
    when(clusterAssignService.applyAssignToApp(Mockito.<String>any(), Mockito.<List<ClusterAppAssignMap>>any(),
        Mockito.<Set<String>>any())).thenReturn(clusterAppAssignResultVO);

    ClusterAppAssignMap clusterMap = new ClusterAppAssignMap();
    clusterMap.setBelongToApp(true);
    clusterMap.setClientSet(new HashSet<>());
    clusterMap.setIp("127.0.0.1");
    clusterMap.setMachineId("42");
    clusterMap.setMaxAllowedQps(10.0d);
    clusterMap.setNamespaceSet(new HashSet<>());
    clusterMap.setPort(8080);

    ClusterAppSingleServerAssignRequest clusterAppSingleServerAssignRequest = new ClusterAppSingleServerAssignRequest();
    clusterAppSingleServerAssignRequest.setClusterMap(clusterMap);
    clusterAppSingleServerAssignRequest.setRemainingList(new HashSet<>());
    String content = new ObjectMapper().writeValueAsString(clusterAppSingleServerAssignRequest);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cluster/assign/single_server/42")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(clusterAssignController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"failedServerSet\":[],\"failedClientSet\":[],\"totalCount"
                    + "\":3}}"));
  }

  /**
   * Test {@link ClusterAssignController#apiUnbindClusterServersOfApp(String, Set)}.
   * <p>
   * Method under test: {@link ClusterAssignController#apiUnbindClusterServersOfApp(String, Set)}
   */
  @Test
  @DisplayName("Test apiUnbindClusterServersOfApp(String, Set)")
  @Tag("MaintainedByDiffblue")
  void testApiUnbindClusterServersOfApp() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/cluster/assign/unbind_server/42")
        .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(objectMapper.writeValueAsString(new HashSet<>()));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(clusterAssignController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"bad request body\",\"data\":null}"));
  }
}
