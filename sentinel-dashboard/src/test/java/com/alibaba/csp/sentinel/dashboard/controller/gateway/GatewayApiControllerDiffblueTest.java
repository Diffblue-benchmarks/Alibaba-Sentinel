package com.alibaba.csp.sentinel.dashboard.controller.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.api.AddApiReqVo;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.api.ApiPredicateItemVo;
import com.alibaba.csp.sentinel.dashboard.domain.vo.gateway.api.UpdateApiReqVo;
import com.alibaba.csp.sentinel.dashboard.repository.gateway.InMemApiDefinitionStore;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

@ContextConfiguration(classes = {GatewayApiController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class GatewayApiControllerDiffblueTest {
  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private GatewayApiController gatewayApiController;

  @MockBean
  private InMemApiDefinitionStore inMemApiDefinitionStore;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link GatewayApiController#queryApis(String, String, Integer)}.
   * <ul>
   *   <li>When {@code 127.0.0.1}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#queryApis(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryApis(String, String, Integer); when '127.0.0.1'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testQueryApis_when127001_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ApiDefinitionEntity>> actualQueryApisResult = new GatewayApiController().queryApis("App", "127.0.0.1",
        8080);

    // Assert
    assertEquals(
        "java.lang.NullPointerException, Cannot invoke \"com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient"
            + ".fetchApis(String, String, int)\" because \"this.sentinelApiClient\" is null",
        actualQueryApisResult.getMsg());
    assertNull(actualQueryApisResult.getData());
    assertEquals(-1, actualQueryApisResult.getCode());
    assertFalse(actualQueryApisResult.isSuccess());
  }

  /**
   * Test {@link GatewayApiController#queryApis(String, String, Integer)}.
   * <ul>
   *   <li>When {@code App}.</li>
   *   <li>Then return Msg is {@code ip can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#queryApis(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryApis(String, String, Integer); when 'App'; then return Msg is 'ip can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryApis_whenApp_thenReturnMsgIsIpCanTBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ApiDefinitionEntity>> actualQueryApisResult = new GatewayApiController().queryApis("App", "", null);

    // Assert
    assertEquals("ip can't be null or empty", actualQueryApisResult.getMsg());
    assertNull(actualQueryApisResult.getData());
    assertEquals(-1, actualQueryApisResult.getCode());
    assertFalse(actualQueryApisResult.isSuccess());
  }

  /**
   * Test {@link GatewayApiController#queryApis(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#queryApis(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryApis(String, String, Integer); when empty string; then return Msg is 'app can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryApis_whenEmptyString_thenReturnMsgIsAppCanTBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ApiDefinitionEntity>> actualQueryApisResult = new GatewayApiController().queryApis("", "", null);

    // Assert
    assertEquals("app can't be null or empty", actualQueryApisResult.getMsg());
    assertNull(actualQueryApisResult.getData());
    assertEquals(-1, actualQueryApisResult.getCode());
    assertFalse(actualQueryApisResult.isSuccess());
  }

  /**
   * Test {@link GatewayApiController#queryApis(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app can't be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#queryApis(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryApis(String, String, Integer); when empty string; then return Msg is 'app can't be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testQueryApis_whenEmptyString_thenReturnMsgIsAppCanTBeNullOrEmpty2() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ApiDefinitionEntity>> actualQueryApisResult = new GatewayApiController().queryApis(null, "", null);

    // Assert
    assertEquals("app can't be null or empty", actualQueryApisResult.getMsg());
    assertNull(actualQueryApisResult.getData());
    assertEquals(-1, actualQueryApisResult.getCode());
    assertFalse(actualQueryApisResult.isSuccess());
  }

  /**
   * Test {@link GatewayApiController#queryApis(String, String, Integer)}.
   * <ul>
   *   <li>When space.</li>
   *   <li>Then return Msg is {@code port can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#queryApis(String, String, Integer)}
   */
  @Test
  @DisplayName("Test queryApis(String, String, Integer); when space; then return Msg is 'port can't be null'")
  @Tag("MaintainedByDiffblue")
  void testQueryApis_whenSpace_thenReturnMsgIsPortCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ApiDefinitionEntity>> actualQueryApisResult = new GatewayApiController().queryApis("App", " ", null);

    // Assert
    assertEquals("port can't be null", actualQueryApisResult.getMsg());
    assertNull(actualQueryApisResult.getData());
    assertEquals(-1, actualQueryApisResult.getCode());
    assertFalse(actualQueryApisResult.isSuccess());
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AddApiReqVo} (default constructor) ApiName is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); given empty string; when AddApiReqVo (default constructor) ApiName is empty string")
  @Tag("MaintainedByDiffblue")
  void testAddApi_givenEmptyString_whenAddApiReqVoApiNameIsEmptyString() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"apiName can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#modifyApis(String, String, int, List)} return {@code false}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); given SentinelApiClient modifyApis(String, String, int, List) return 'false'; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testAddApi_givenSentinelApiClientModifyApisReturnFalse_thenContentStringAString() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.save(Mockito.<ApiDefinitionEntity>any())).thenReturn(new ApiDefinitionEntity());
    when(inMemApiDefinitionStore.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.modifyApis(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<ApiDefinitionEntity>>any())).thenReturn(false);

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(1);
    apiPredicateItemVo.setPattern("?");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo);

    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"gmtCreate"
                    + "\":null,\"gmtModified\":null,\"apiName\":null,\"predicateItems\":null}}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#modifyApis(String, String, int, List)} return {@code true}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); given SentinelApiClient modifyApis(String, String, int, List) return 'true'; then content string a string")
  @Tag("MaintainedByDiffblue")
  void testAddApi_givenSentinelApiClientModifyApisReturnTrue_thenContentStringAString() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.save(Mockito.<ApiDefinitionEntity>any())).thenReturn(new ApiDefinitionEntity());
    when(inMemApiDefinitionStore.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.modifyApis(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<ApiDefinitionEntity>>any())).thenReturn(true);

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(1);
    apiPredicateItemVo.setPattern("?");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo);

    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":null,\"gmtCreate"
                    + "\":null,\"gmtModified\":null,\"apiName\":null,\"predicateItems\":null}}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"app can't be null or empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgAppCanTBeNullOrEmptyDataNull() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp(null);
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"invalid matchStrategy: 3","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 3\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgInvalidMatchStrategy3DataNull() throws Exception {
    // Arrange
    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(3);
    apiPredicateItemVo.setPattern("?");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo);

    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"invalid matchStrategy: 3\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"ip can't be null or empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgIpCanTBeNullOrEmptyDataNull() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp(null);
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"pattern can't be null or empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"pattern can't be null or empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgPatternCanTBeNullOrEmptyDataNull() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.save(Mockito.<ApiDefinitionEntity>any())).thenReturn(new ApiDefinitionEntity());
    when(inMemApiDefinitionStore.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.modifyApis(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<ApiDefinitionEntity>>any())).thenReturn(true);

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(1);
    apiPredicateItemVo.setPattern("");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo);

    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"pattern can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"port can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgPortCanTBeNullDataNull() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(null);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"predicateItems can't empty","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"predicateItems can't empty\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_thenContentStringSuccessFalseCode1MsgPredicateItemsCanTEmptyDataNull() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName("Api Name");
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"predicateItems can't empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}.
   * <ul>
   *   <li>When {@link AddApiReqVo} (default constructor) ApiName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#addApi(HttpServletRequest, AddApiReqVo)}
   */
  @Test
  @DisplayName("Test addApi(HttpServletRequest, AddApiReqVo); when AddApiReqVo (default constructor) ApiName is 'null'")
  @Tag("MaintainedByDiffblue")
  void testAddApi_whenAddApiReqVoApiNameIsNull() throws Exception {
    // Arrange
    AddApiReqVo addApiReqVo = new AddApiReqVo();
    addApiReqVo.setApiName(null);
    addApiReqVo.setApp("App");
    addApiReqVo.setIp("127.0.0.1");
    addApiReqVo.setPort(8080);
    addApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(addApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/new.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"apiName can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo)")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"predicateItems can't empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link UpdateApiReqVo} (default constructor) App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); given empty string; when UpdateApiReqVo (default constructor) App is empty string")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_givenEmptyString_whenUpdateApiReqVoAppIsEmptyString() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#modifyApis(String, String, int, List)} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); given SentinelApiClient modifyApis(String, String, int, List) return 'false'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_givenSentinelApiClientModifyApisReturnFalse() throws Exception {
    // Arrange
    ApiDefinitionEntity apiDefinitionEntity = new ApiDefinitionEntity();
    apiDefinitionEntity.setPort(8080);
    when(inMemApiDefinitionStore.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(inMemApiDefinitionStore.save(Mockito.<ApiDefinitionEntity>any())).thenReturn(apiDefinitionEntity);
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());
    when(sentinelApiClient.modifyApis(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<ApiDefinitionEntity>>any())).thenReturn(false);

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(1);
    apiPredicateItemVo.setPattern("?");

    ApiPredicateItemVo apiPredicateItemVo2 = new ApiPredicateItemVo();
    apiPredicateItemVo2.setMatchStrategy(1);
    apiPredicateItemVo2.setPattern("Invalid matchStrategy: ");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo2);
    predicateItems.add(apiPredicateItemVo);

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,\"gmtCreate"
                    + "\":null,\"gmtModified\":null,\"apiName\":null,\"predicateItems\":null}}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Given {@link SentinelApiClient} {@link SentinelApiClient#modifyApis(String, String, int, List)} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); given SentinelApiClient modifyApis(String, String, int, List) return 'true'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_givenSentinelApiClientModifyApisReturnTrue() throws Exception {
    // Arrange
    ApiDefinitionEntity apiDefinitionEntity = new ApiDefinitionEntity();
    apiDefinitionEntity.setPort(8080);
    when(inMemApiDefinitionStore.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(inMemApiDefinitionStore.save(Mockito.<ApiDefinitionEntity>any())).thenReturn(apiDefinitionEntity);
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());
    when(sentinelApiClient.modifyApis(Mockito.<String>any(), Mockito.<String>any(), anyInt(),
        Mockito.<List<ApiDefinitionEntity>>any())).thenReturn(true);

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(1);
    apiPredicateItemVo.setPattern("?");

    ApiPredicateItemVo apiPredicateItemVo2 = new ApiPredicateItemVo();
    apiPredicateItemVo2.setMatchStrategy(1);
    apiPredicateItemVo2.setPattern("Invalid matchStrategy: ");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo2);
    predicateItems.add(apiPredicateItemVo);

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"id\":null,\"app\":null,\"ip\":null,\"port\":8080,\"gmtCreate"
                    + "\":null,\"gmtModified\":null,\"apiName\":null,\"predicateItems\":null}}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"api does not exist, id=1","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"api does not exist, id=1\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_thenContentStringSuccessFalseCode1MsgApiDoesNotExistId1DataNull() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(null);

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"api does not exist, id=1\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"id can't be null","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_thenContentStringSuccessFalseCode1MsgIdCanTBeNullDataNull() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(null);
    updateApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"id can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"Invalid matchStrategy: 3","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"Invalid matchStrategy: 3\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_thenContentStringSuccessFalseCode1MsgInvalidMatchStrategy3DataNull() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(3);
    apiPredicateItemVo.setPattern("?");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo);

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid matchStrategy: 3\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"Invalid matchStrategy: 3","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); then content string '{\"success\":false,\"code\":-1,\"msg\":\"Invalid matchStrategy: 3\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_thenContentStringSuccessFalseCode1MsgInvalidMatchStrategy3DataNull2() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    ApiPredicateItemVo apiPredicateItemVo = new ApiPredicateItemVo();
    apiPredicateItemVo.setMatchStrategy(3);
    apiPredicateItemVo.setPattern("?");

    ApiPredicateItemVo apiPredicateItemVo2 = new ApiPredicateItemVo();
    apiPredicateItemVo2.setMatchStrategy(1);
    apiPredicateItemVo2.setPattern("Invalid matchStrategy: ");

    ArrayList<ApiPredicateItemVo> predicateItems = new ArrayList<>();
    predicateItems.add(apiPredicateItemVo2);
    predicateItems.add(apiPredicateItemVo);

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp("App");
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(predicateItems);
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid matchStrategy: 3\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#updateApi(UpdateApiReqVo)}.
   * <ul>
   *   <li>When {@link UpdateApiReqVo} (default constructor) App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#updateApi(UpdateApiReqVo)}
   */
  @Test
  @DisplayName("Test updateApi(UpdateApiReqVo); when UpdateApiReqVo (default constructor) App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testUpdateApi_whenUpdateApiReqVoAppIsNull() throws Exception {
    // Arrange
    when(inMemApiDefinitionStore.findById(Mockito.<Long>any())).thenReturn(new ApiDefinitionEntity());

    UpdateApiReqVo updateApiReqVo = new UpdateApiReqVo();
    updateApiReqVo.setApp(null);
    updateApiReqVo.setId(1L);
    updateApiReqVo.setPredicateItems(new ArrayList<>());
    String content = new ObjectMapper().writeValueAsString(updateApiReqVo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/gateway/api/save.json")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(gatewayApiController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link GatewayApiController#deleteApi(Long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code id can't be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GatewayApiController#deleteApi(Long)}
   */
  @Test
  @DisplayName("Test deleteApi(Long); when 'null'; then return Msg is 'id can't be null'")
  @Tag("MaintainedByDiffblue")
  void testDeleteApi_whenNull_thenReturnMsgIsIdCanTBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<Long> actualDeleteApiResult = new GatewayApiController().deleteApi(null);

    // Assert
    assertEquals("id can't be null", actualDeleteApiResult.getMsg());
    assertNull(actualDeleteApiResult.getData());
    assertEquals(-1, actualDeleteApiResult.getCode());
    assertFalse(actualDeleteApiResult.isSuccess());
  }
}
