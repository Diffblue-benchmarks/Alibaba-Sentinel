package com.alibaba.csp.sentinel.dashboard.controller.cluster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.state.AppClusterClientStateWrapVO;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.state.AppClusterServerStateWrapVO;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.state.ClusterUniversalStatePairVO;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.state.ClusterUniversalStateVO;
import com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClusterConfigController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class ClusterConfigControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private ClusterConfigController clusterConfigController;

  @MockBean
  private ClusterConfigService clusterConfigService;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link ClusterConfigController#apiModifyClusterConfig(String)}.
   * <p>
   * Method under test: {@link ClusterConfigController#apiModifyClusterConfig(String)}
   */
  @Test
  @DisplayName("Test apiModifyClusterConfig(String)")
  @Tag("MaintainedByDiffblue")
  void testApiModifyClusterConfig() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/cluster/config/modify_single")
        .contentType(MediaType.APPLICATION_JSON);
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(new ObjectMapper().writeValueAsString(" "));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(clusterConfigController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"can not cast to JSONObject.\",\"data\":null}"));
  }

  /**
   * Test {@link ClusterConfigController#apiModifyClusterConfig(String)}.
   * <ul>
   *   <li>When {@code Uri Vars}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiModifyClusterConfig(String)}
   */
  @Test
  @DisplayName("Test apiModifyClusterConfig(String); when 'Uri Vars'")
  @Tag("MaintainedByDiffblue")
  void testApiModifyClusterConfig_whenUriVars() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
        .post("/cluster/config/modify_single", "Uri Vars")
        .contentType(MediaType.APPLICATION_JSON);
    MockHttpServletRequestBuilder requestBuilder = contentTypeResult
        .content(new ObjectMapper().writeValueAsString(" "));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(clusterConfigController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"can not cast to JSONObject.\",\"data\":null}"));
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When {@code 127.0.0.1}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when '127.0.0.1'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_when127001_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState("App", "127.0.0.1", 8080);

    // Assert
    assertEquals(
        "Cannot invoke \"com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService.getClusterUniversalState"
            + "(String, String, int)\" because \"this.clusterConfigService\" is null",
        actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when empty string; then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_whenEmptyString_thenReturnMsgIsAppCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState("", "", null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when empty string; then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_whenEmptyString_thenReturnMsgIsAppCannotBeNullOrEmpty2() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState(null, "", null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return Msg is {@code ip cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when empty string; then return Msg is 'ip cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_whenEmptyString_thenReturnMsgIsIpCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState("App", "", null);

    // Assert
    assertEquals("ip cannot be null or empty", actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When space.</li>
   *   <li>Then return Msg is {@code Invalid parameter: port}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when space; then return Msg is 'Invalid parameter: port'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_whenSpace_thenReturnMsgIsInvalidParameterPort() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState("App", " ", null);

    // Assert
    assertEquals("Invalid parameter: port", actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return Msg is {@code Invalid parameter: port}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterState(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiGetClusterState(String, String, Integer); when zero; then return Msg is 'Invalid parameter: port'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterState_whenZero_thenReturnMsgIsInvalidParameterPort() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<ClusterUniversalStateVO> actualApiGetClusterStateResult = new ClusterConfigController()
        .apiGetClusterState("App", " ", 0);

    // Assert
    assertEquals("Invalid parameter: port", actualApiGetClusterStateResult.getMsg());
    assertNull(actualApiGetClusterStateResult.getData());
    assertEquals(-1, actualApiGetClusterStateResult.getCode());
    assertFalse(actualApiGetClusterStateResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}.
   * <ul>
   *   <li>When {@code App}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterServerStateOfApp(String); when 'App'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterServerStateOfApp_whenApp_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterServerStateWrapVO>> actualApiGetClusterServerStateOfAppResult = new ClusterConfigController()
        .apiGetClusterServerStateOfApp("App");

    // Assert
    assertEquals(
        "Cannot invoke \"com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService.getClusterUniversalState"
            + "(String)\" because \"this.clusterConfigService\" is null",
        actualApiGetClusterServerStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterServerStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterServerStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterServerStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterServerStateOfApp(String); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterServerStateOfApp_whenEmptyString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterServerStateWrapVO>> actualApiGetClusterServerStateOfAppResult = new ClusterConfigController()
        .apiGetClusterServerStateOfApp("");

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterServerStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterServerStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterServerStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterServerStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterServerStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterServerStateOfApp(String); when 'null'; then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterServerStateOfApp_whenNull_thenReturnMsgIsAppCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterServerStateWrapVO>> actualApiGetClusterServerStateOfAppResult = new ClusterConfigController()
        .apiGetClusterServerStateOfApp(null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterServerStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterServerStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterServerStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterServerStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}.
   * <ul>
   *   <li>When {@code App}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterClientStateOfApp(String); when 'App'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterClientStateOfApp_whenApp_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterClientStateWrapVO>> actualApiGetClusterClientStateOfAppResult = new ClusterConfigController()
        .apiGetClusterClientStateOfApp("App");

    // Assert
    assertEquals(
        "Cannot invoke \"com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService.getClusterUniversalState"
            + "(String)\" because \"this.clusterConfigService\" is null",
        actualApiGetClusterClientStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterClientStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterClientStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterClientStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterClientStateOfApp(String); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterClientStateOfApp_whenEmptyString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterClientStateWrapVO>> actualApiGetClusterClientStateOfAppResult = new ClusterConfigController()
        .apiGetClusterClientStateOfApp("");

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterClientStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterClientStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterClientStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterClientStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterClientStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterClientStateOfApp(String); when 'null'; then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterClientStateOfApp_whenNull_thenReturnMsgIsAppCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<AppClusterClientStateWrapVO>> actualApiGetClusterClientStateOfAppResult = new ClusterConfigController()
        .apiGetClusterClientStateOfApp(null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterClientStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterClientStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterClientStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterClientStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterStateOfApp(String)}.
   * <ul>
   *   <li>When {@code App}.</li>
   *   <li>Then return Msg is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterStateOfApp(String); when 'App'; then return Msg is a string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterStateOfApp_whenApp_thenReturnMsgIsAString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ClusterUniversalStatePairVO>> actualApiGetClusterStateOfAppResult = new ClusterConfigController()
        .apiGetClusterStateOfApp("App");

    // Assert
    assertEquals(
        "Cannot invoke \"com.alibaba.csp.sentinel.dashboard.service.ClusterConfigService.getClusterUniversalState"
            + "(String)\" because \"this.clusterConfigService\" is null",
        actualApiGetClusterStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterStateOfApp(String)}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterStateOfApp(String); when empty string")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterStateOfApp_whenEmptyString() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ClusterUniversalStatePairVO>> actualApiGetClusterStateOfAppResult = new ClusterConfigController()
        .apiGetClusterStateOfApp("");

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterStateOfAppResult.isSuccess());
  }

  /**
   * Test {@link ClusterConfigController#apiGetClusterStateOfApp(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClusterConfigController#apiGetClusterStateOfApp(String)}
   */
  @Test
  @DisplayName("Test apiGetClusterStateOfApp(String); when 'null'; then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiGetClusterStateOfApp_whenNull_thenReturnMsgIsAppCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ClusterUniversalStatePairVO>> actualApiGetClusterStateOfAppResult = new ClusterConfigController()
        .apiGetClusterStateOfApp(null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiGetClusterStateOfAppResult.getMsg());
    assertNull(actualApiGetClusterStateOfAppResult.getData());
    assertEquals(-1, actualApiGetClusterStateOfAppResult.getCode());
    assertFalse(actualApiGetClusterStateOfAppResult.isSuccess());
  }
}
