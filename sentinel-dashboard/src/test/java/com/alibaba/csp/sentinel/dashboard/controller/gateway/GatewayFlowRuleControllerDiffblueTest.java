package com.alibaba.csp.sentinel.dashboard.controller.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.rule.AddFlowRuleReqVo;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.rule.GatewayParamFlowItemVo;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.rule.UpdateFlowRuleReqVo;
import com.alibaba.csp.sentinel.dashboard.repository.gateway.InMemGatewayFlowRuleStore;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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

@ContextConfiguration(classes = {GatewayFlowRuleController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class GatewayFlowRuleControllerDiffblueTest {
  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private GatewayFlowRuleController gatewayFlowRuleController;

  @MockBean
  private InMemGatewayFlowRuleStore inMemGatewayFlowRuleStore;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}.
   * <ul>
   *   <li>When {@code 127.0.0.1}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryFlowRules(String, String, Integer); when '127.0.0.1'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testQueryFlowRules_when127001_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<GatewayFlowRuleEntity>> actualQueryFlowRulesResult = new GatewayFlowRuleController()
        .queryFlowRules("App", "127.0.0.1", 8080);

    // Assert
    assertEquals(
        "java.lang.NullPointerException, Cannot invoke \"com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient"
            + ".fetchGatewayFlowRules(String, String, int)\" because \"this.sentinelApiClient\" is null",
        actualQueryFlowRulesResult.getMsg());
    assertNull(actualQueryFlowRulesResult.getData());
    assertEquals(-1, actualQueryFlowRulesResult.getCode());
    assertFalse(actualQueryFlowRulesResult.isSuccess());
  }

  /**
   * Test {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}.
   * <ul>
   *   <li>When {@code App}.</li>
   *   <li>Then return Msg is {@code ip can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryFlowRules(String, String, Integer); when 'App'; then return Msg is 'ip can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryFlowRules_whenApp_thenReturnMsgIsIpCanTBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<GatewayFlowRuleEntity>> actualQueryFlowRulesResult = new GatewayFlowRuleController()
        .queryFlowRules("App", "", null);

    // Assert
    assertEquals("ip can't be null or empty", actualQueryFlowRulesResult.getMsg());
    assertNull(actualQueryFlowRulesResult.getData());
    assertEquals(-1, actualQueryFlowRulesResult.getCode());
    assertFalse(actualQueryFlowRulesResult.isSuccess());
  }

  /**
   * Test {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryFlowRules(String, String, Integer); when empty string; then return Msg is 'app can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryFlowRules_whenEmptyString_thenReturnMsgIsAppCanTBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<GatewayFlowRuleEntity>> actualQueryFlowRulesResult = new GatewayFlowRuleController().queryFlowRules("",
        "", null);

    // Assert
    assertEquals("app can't be null or empty", actualQueryFlowRulesResult.getMsg());
    assertNull(actualQueryFlowRulesResult.getData());
    assertEquals(-1, actualQueryFlowRulesResult.getCode());
    assertFalse(actualQueryFlowRulesResult.isSuccess());
  }

  /**
   * Test {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryFlowRules(String, String, Integer); when empty string; then return Msg is 'app can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryFlowRules_whenEmptyString_thenReturnMsgIsAppCanTBeNullOrEmpty2() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<GatewayFlowRuleEntity>> actualQueryFlowRulesResult = new GatewayFlowRuleController()
        .queryFlowRules(null, "", null);

    // Assert
    assertEquals("app can't be null or empty", actualQueryFlowRulesResult.getMsg());
    assertNull(actualQueryFlowRulesResult.getData());
    assertEquals(-1, actualQueryFlowRulesResult.getCode());
    assertFalse(actualQueryFlowRulesResult.isSuccess());
  }

  /**
   * Test {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}.
   * <ul>
   *   <li>When space.</li>
   *   <li>Then return Msg is {@code port can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#queryFlowRules(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryFlowRules(String, String, Integer); when space; then return Msg is 'port can't be null'")
  @Tag("MaintainedByDiffblue")
  void testQueryFlowRules_whenSpace_thenReturnMsgIsPortCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<GatewayFlowRuleEntity>> actualQueryFlowRulesResult = new GatewayFlowRuleController()
        .queryFlowRules("App", " ", null);

    // Assert
    assertEquals("port can't be null", actualQueryFlowRulesResult.getMsg());
    assertNull(actualQueryFlowRulesResult.getData());
    assertEquals(-1, actualQueryFlowRulesResult.getCode());
    assertFalse(actualQueryFlowRulesResult.isSuccess());
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule2() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(9);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid parseStrategy: 9\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule3() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource(null);
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule4() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(3);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid resourceMode: 3\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule5() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(null);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resourceMode can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AddFlowRuleReqVo} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); given empty string; when AddFlowRuleReqVo (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_givenEmptyString_whenAddFlowRuleReqVoAppIsEmptyString() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) MatchStrategy is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) MatchStrategy is three")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_givenGatewayParamFlowItemVoMatchStrategyIsThree() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(3);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) ParseStrategy is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) ParseStrategy is three")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_givenGatewayParamFlowItemVoParseStrategyIsThree() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(3);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) Pattern is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) Pattern is empty string")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_givenGatewayParamFlowItemVoPatternIsEmptyString() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) Pattern is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) Pattern is 'null'")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_givenGatewayParamFlowItemVoPatternIsNull() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern(null);

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"ip can't be null or empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_thenContentStringSuccessFalseCode1MsgIpCanTBeNullOrEmptyDataNull() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp(null);
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"port can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_thenContentStringSuccessFalseCode1MsgPortCanTBeNullDataNull() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp("App");
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(null);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}.
   * <ul>
   *   <li>When {@link AddFlowRuleReqVo} (default constructor) App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#addFlowRule(AddFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test addFlowRule(AddFlowRuleReqVo); when AddFlowRuleReqVo (default constructor) App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testAddFlowRule_whenAddFlowRuleReqVoAppIsNull() throws Exception {
    // Arrange
    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    AddFlowRuleReqVo addFlowRuleReqVo = new AddFlowRuleReqVo();
    addFlowRuleReqVo.setApp(null);
    addFlowRuleReqVo.setBurst(1);
    addFlowRuleReqVo.setControlBehavior(1);
    addFlowRuleReqVo.setCount(10.0d);
    addFlowRuleReqVo.setGrade(1);
    addFlowRuleReqVo.setInterval(42L);
    addFlowRuleReqVo.setIntervalUnit(42);
    addFlowRuleReqVo.setIp("127.0.0.1");
    addFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    addFlowRuleReqVo.setParamItem(paramItem);
    addFlowRuleReqVo.setPort(8080);
    addFlowRuleReqVo.setResource("Resource");
    addFlowRuleReqVo.setResourceMode(1);
    String content = new ObjectMapper().writeValueAsString(addFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule2() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(null);

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"gateway flow rule does not exist, id=1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo)")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule3() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(5);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid parseStrategy: 5\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link UpdateFlowRuleReqVo} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); given empty string; when UpdateFlowRuleReqVo (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_givenEmptyString_whenUpdateFlowRuleReqVoAppIsEmptyString() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) MatchStrategy is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) MatchStrategy is three")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_givenGatewayParamFlowItemVoMatchStrategyIsThree() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(3);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) ParseStrategy is three.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) ParseStrategy is three")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_givenGatewayParamFlowItemVoParseStrategyIsThree() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(3);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) Pattern is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) Pattern is empty string")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_givenGatewayParamFlowItemVoPatternIsEmptyString() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Given {@link GatewayParamFlowItemVo} (default constructor) Pattern is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); given GatewayParamFlowItemVo (default constructor) Pattern is 'null'")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_givenGatewayParamFlowItemVoPatternIsNull() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern(null);

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid intervalUnit: 42\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"id can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_thenContentStringSuccessFalseCode1MsgIdCanTBeNullDataNull() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp("App");
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(null);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}.
   * <ul>
   *   <li>When {@link UpdateFlowRuleReqVo} (default constructor) App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#updateFlowRule(UpdateFlowRuleReqVo)}
   */
  @Test
  @DisplayName("Test updateFlowRule(UpdateFlowRuleReqVo); when UpdateFlowRuleReqVo (default constructor) App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testUpdateFlowRule_whenUpdateFlowRuleReqVoAppIsNull() throws Exception {
    // Arrange
    when(inMemGatewayFlowRuleStore.findById(Mockito.<Long>any())).thenReturn(new GatewayFlowRuleEntity());

    GatewayParamFlowItemVo paramItem = new GatewayParamFlowItemVo();
    paramItem.setFieldName("Field Name");
    paramItem.setMatchStrategy(1);
    paramItem.setParseStrategy(1);
    paramItem.setPattern("Pattern");

    UpdateFlowRuleReqVo updateFlowRuleReqVo = new UpdateFlowRuleReqVo();
    updateFlowRuleReqVo.setApp(null);
    updateFlowRuleReqVo.setBurst(1);
    updateFlowRuleReqVo.setControlBehavior(1);
    updateFlowRuleReqVo.setCount(10.0d);
    updateFlowRuleReqVo.setGrade(1);
    updateFlowRuleReqVo.setId(1L);
    updateFlowRuleReqVo.setInterval(42L);
    updateFlowRuleReqVo.setIntervalUnit(42);
    updateFlowRuleReqVo.setMaxQueueingTimeoutMs(3);
    updateFlowRuleReqVo.setParamItem(paramItem);
    String content = new ObjectMapper().writeValueAsString(updateFlowRuleReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/flow/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayFlowRuleController#deleteFlowRule(Long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code id can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayFlowRuleController#deleteFlowRule(Long)}
   */
  @Test
  @DisplayName("Test deleteFlowRule(Long); when 'null'; then return Msg is 'id can't be null'")
  @Tag("MaintainedByDiffblue")
  void testDeleteFlowRule_whenNull_thenReturnMsgIsIdCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<Long> actualDeleteFlowRuleResult = new GatewayFlowRuleController().deleteFlowRule(null);

    // Assert
    assertEquals("id can't be null", actualDeleteFlowRuleResult.getMsg());
    assertNull(actualDeleteFlowRuleResult.getData());
    assertEquals(-1, actualDeleteFlowRuleResult.getCode());
    assertFalse(actualDeleteFlowRuleResult.isSuccess());
  }
}
