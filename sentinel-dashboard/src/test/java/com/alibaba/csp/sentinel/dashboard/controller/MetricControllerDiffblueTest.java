package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.repository.metric.MetricsRepository;
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

@ContextConfiguration(classes = {MetricController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class MetricControllerDiffblueTest {
  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @Autowired
  private MetricController metricController;

  @MockBean
  private MetricsRepository<MetricEntity> metricsRepository;

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); given ArrayList() add 'foo'")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_givenArrayListAddFoo() throws Exception {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    stringList.add("queryTopResourceMetric(), resources.size()={}");
    stringList.add("?");
    stringList.add("foo");
    stringList.add("?");
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(stringList);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(-1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"pageIndex\":1,\"metric\":{},\"totalPage\":0,\"pageSize\":"
                    + "-1,\"totalCount\":2}}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); then content string a string")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_thenContentStringAString() throws Exception {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("?");
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(stringList);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"pageIndex\":1,\"metric\":{},\"totalPage\":0,\"pageSize\":1"
                    + ",\"totalCount\":0}}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_thenContentStringSuccessTrueCode0MsgSuccessDataNull() throws Exception {
    // Arrange
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); when empty string")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_whenEmptyString() throws Exception {
    // Arrange
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code pageIndex} is valueOf minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); when param(String, String[]) 'pageIndex' is valueOf minus one")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_whenParamPageIndexIsValueOfMinusOne() throws Exception {
    // Arrange
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(-1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>When valueOf {@code false}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); when valueOf 'false'; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_whenValueOfFalse_thenContentStringAString() throws Exception {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("?");
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(stringList);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(false));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"pageIndex\":1,\"metric\":{},\"totalPage\":0,\"pageSize\":1"
                    + ",\"totalCount\":0}}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>When valueOf {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); when valueOf MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_whenValueOfMax_value() throws Exception {
    // Arrange
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(Long.MAX_VALUE));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(1))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"time intervalMs is too big, must <= 1h\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}.
   * <ul>
   *   <li>When valueOf twenty.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String)}
   */
  @Test
  @DisplayName("Test queryTopResourceMetric(String, Integer, Integer, Boolean, Long, Long, String); when valueOf twenty")
  @Tag("MaintainedByDiffblue")
  void testQueryTopResourceMetric_whenValueOfTwenty() throws Exception {
    // Arrange
    when(metricsRepository.listResourcesOfApp(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryTopResourceMetric.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("desc", String.valueOf(true));
    MockHttpServletRequestBuilder paramResult3 = paramResult2.param("endTime", String.valueOf(1L));
    MockHttpServletRequestBuilder paramResult4 = paramResult3.param("pageIndex", String.valueOf(1));
    MockHttpServletRequestBuilder paramResult5 = paramResult4.param("pageSize", String.valueOf(20))
        .param("searchKey", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult5.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryByAppAndResource(String, String, Long, Long)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryByAppAndResource(String, String, Long, Long)}
   */
  @Test
  @DisplayName("Test queryByAppAndResource(String, String, Long, Long); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testQueryByAppAndResource_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(metricsRepository.queryByAppAndResourceBetween(Mockito.<String>any(), Mockito.<String>any(), anyLong(),
        anyLong())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryByAppAndResource.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("endTime", String.valueOf(1L))
        .param("identity", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link MetricController#queryByAppAndResource(String, String, Long, Long)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryByAppAndResource(String, String, Long, Long)}
   */
  @Test
  @DisplayName("Test queryByAppAndResource(String, String, Long, Long); when empty string")
  @Tag("MaintainedByDiffblue")
  void testQueryByAppAndResource_whenEmptyString() throws Exception {
    // Arrange
    when(metricsRepository.queryByAppAndResourceBetween(Mockito.<String>any(), Mockito.<String>any(), anyLong(),
        anyLong())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryByAppAndResource.json")
        .param("app", "");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("endTime", String.valueOf(1L))
        .param("identity", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link MetricController#queryByAppAndResource(String, String, Long, Long)}.
   * <ul>
   *   <li>When valueOf {@link Long#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MetricController#queryByAppAndResource(String, String, Long, Long)}
   */
  @Test
  @DisplayName("Test queryByAppAndResource(String, String, Long, Long); when valueOf MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  void testQueryByAppAndResource_whenValueOfMax_value() throws Exception {
    // Arrange
    when(metricsRepository.queryByAppAndResourceBetween(Mockito.<String>any(), Mockito.<String>any(), anyLong(),
        anyLong())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/metric/queryByAppAndResource.json")
        .param("app", "foo");
    MockHttpServletRequestBuilder paramResult2 = paramResult.param("endTime", String.valueOf(Long.MAX_VALUE))
        .param("identity", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult2.param("startTime", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(metricController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"time intervalMs is too big, must <= 1h\",\"data\":null}"));
  }
}
