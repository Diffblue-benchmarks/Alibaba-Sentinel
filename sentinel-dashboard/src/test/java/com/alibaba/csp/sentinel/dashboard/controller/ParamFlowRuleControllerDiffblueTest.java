package com.alibaba.csp.sentinel.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppInfo;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.repository.rule.RuleRepository;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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

@ContextConfiguration(classes = {ParamFlowRuleController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class ParamFlowRuleControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @Autowired
  private ParamFlowRuleController paramFlowRuleController;

  @MockBean
  private RuleRepository<ParamFlowRuleEntity, Long> ruleRepository;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_thenReturnMsgIsAppCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ParamFlowRuleEntity>> actualApiQueryAllRulesForMachineResult = new ParamFlowRuleController()
        .apiQueryAllRulesForMachine("", "", null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiQueryAllRulesForMachineResult.getMsg());
    assertNull(actualApiQueryAllRulesForMachineResult.getData());
    assertEquals(-1, actualApiQueryAllRulesForMachineResult.getCode());
    assertFalse(actualApiQueryAllRulesForMachineResult.isSuccess());
  }

  /**
   * Test {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>Then return Msg is {@code app cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); then return Msg is 'app cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_thenReturnMsgIsAppCannotBeNullOrEmpty2() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ParamFlowRuleEntity>> actualApiQueryAllRulesForMachineResult = new ParamFlowRuleController()
        .apiQueryAllRulesForMachine(null, "", null);

    // Assert
    assertEquals("app cannot be null or empty", actualApiQueryAllRulesForMachineResult.getMsg());
    assertNull(actualApiQueryAllRulesForMachineResult.getData());
    assertEquals(-1, actualApiQueryAllRulesForMachineResult.getCode());
    assertFalse(actualApiQueryAllRulesForMachineResult.isSuccess());
  }

  /**
   * Test {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>Then return Msg is {@code ip cannot be null or empty}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); then return Msg is 'ip cannot be null or empty'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_thenReturnMsgIsIpCannotBeNullOrEmpty() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ParamFlowRuleEntity>> actualApiQueryAllRulesForMachineResult = new ParamFlowRuleController()
        .apiQueryAllRulesForMachine("App", "", null);

    // Assert
    assertEquals("ip cannot be null or empty", actualApiQueryAllRulesForMachineResult.getMsg());
    assertNull(actualApiQueryAllRulesForMachineResult.getData());
    assertEquals(-1, actualApiQueryAllRulesForMachineResult.getCode());
    assertFalse(actualApiQueryAllRulesForMachineResult.isSuccess());
  }

  /**
   * Test {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>When {@code 127.0.0.1}.</li>
   *   <li>Then return Msg is {@code Invalid parameter: port}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); when '127.0.0.1'; then return Msg is 'Invalid parameter: port'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_when127001_thenReturnMsgIsInvalidParameterPort() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ParamFlowRuleEntity>> actualApiQueryAllRulesForMachineResult = new ParamFlowRuleController()
        .apiQueryAllRulesForMachine("App", "127.0.0.1", null);

    // Assert
    assertEquals("Invalid parameter: port", actualApiQueryAllRulesForMachineResult.getMsg());
    assertNull(actualApiQueryAllRulesForMachineResult.getData());
    assertEquals(-1, actualApiQueryAllRulesForMachineResult.getCode());
    assertFalse(actualApiQueryAllRulesForMachineResult.isSuccess());
  }

  /**
   * Test {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return Msg is {@code Invalid parameter: port}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); when zero; then return Msg is 'Invalid parameter: port'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_whenZero_thenReturnMsgIsInvalidParameterPort() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<List<ParamFlowRuleEntity>> actualApiQueryAllRulesForMachineResult = new ParamFlowRuleController()
        .apiQueryAllRulesForMachine("App", "127.0.0.1", 0);

    // Assert
    assertEquals("Invalid parameter: port", actualApiQueryAllRulesForMachineResult.getMsg());
    assertNull(actualApiQueryAllRulesForMachineResult.getData());
    assertEquals(-1, actualApiQueryAllRulesForMachineResult.getCode());
    assertFalse(actualApiQueryAllRulesForMachineResult.isSuccess());
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"paramIdx should be valid\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule2() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp(null);
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule3() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule(null));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource name cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule4() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(null);
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"rule can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity); given empty string; when ParamFlowRuleEntity() App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule_givenEmptyString_whenParamFlowRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} Port is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity); given zero; when ParamFlowRuleEntity() Port is zero")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule_givenZero_whenParamFlowRuleEntityPortIsZero() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(0);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <ul>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity); when ParamFlowRuleEntity() App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule_whenParamFlowRuleEntityAppIsNull() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp(null);
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}.
   * <ul>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} Port is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiAddParamFlowRule(ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddParamFlowRule(ParamFlowRuleEntity); when ParamFlowRuleEntity() Port is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddParamFlowRule_whenParamFlowRuleEntityPortIsNull() throws Exception {
    // Arrange
    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(null);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paramFlow/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"paramIdx should be valid\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule2() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(null);

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"id 1 does not exist\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule3() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp(null);
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule4() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule(null));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource name cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule5() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(null);
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"rule can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link AppInfo#AppInfo()} addMachine {@link MachineInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); given AppInfo() addMachine MachineInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenAppInfoAddMachineMachineInfo() throws Exception {
    // Arrange
    AppInfo appInfo = new AppInfo();
    appInfo.addMachine(new MachineInfo());
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(appInfo);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<ParamFlowRuleEntity>any())).thenReturn(new ParamFlowRuleEntity());
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRule paramFlowRule = new ParamFlowRule("Resource Name");
    paramFlowRule.setParamIdx(1);

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(paramFlowRule);
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Cannot invoke \\\"java.lang.Integer.intValue()\\\" because \\\"port\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link AppManagement} {@link AppManagement#getDetailApp(String)} return {@link AppInfo#AppInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); given AppManagement getDetailApp(String) return AppInfo()")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenAppManagementGetDetailAppReturnAppInfo() throws Exception {
    // Arrange
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(new AppInfo());
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<ParamFlowRuleEntity>any())).thenReturn(new ParamFlowRuleEntity());
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRule paramFlowRule = new ParamFlowRule("Resource Name");
    paramFlowRule.setParamIdx(1);

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(paramFlowRule);
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Cannot invoke \\\"java.lang.Integer.intValue()\\\" because \\\"port\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given {@link AppManagement} {@link AppManagement#getDetailApp(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); given AppManagement getDetailApp(String) return 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenAppManagementGetDetailAppReturnNull() throws Exception {
    // Arrange
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(null);
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.save(Mockito.<ParamFlowRuleEntity>any())).thenReturn(new ParamFlowRuleEntity());
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRule paramFlowRule = new ParamFlowRule("Resource Name");
    paramFlowRule.setParamIdx(1);

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(paramFlowRule);
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Cannot invoke \\\"java.lang.Integer.intValue()\\\" because \\\"port\\\" is"
                    + " null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); given empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenEmptyString() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} Port is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); given zero; when ParamFlowRuleEntity() Port is zero")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenZero_whenParamFlowRuleEntityPortIsZero() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(0);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); when ParamFlowRuleEntity() App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_whenParamFlowRuleEntityAppIsNull() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp(null);
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(8080);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}.
   * <ul>
   *   <li>When {@link ParamFlowRuleEntity#ParamFlowRuleEntity()} Port is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiUpdateParamFlowRule(Long, ParamFlowRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, ParamFlowRuleEntity); when ParamFlowRuleEntity() Port is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_whenParamFlowRuleEntityPortIsNull() throws Exception {
    // Arrange
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());

    ParamFlowRuleEntity paramFlowRuleEntity = new ParamFlowRuleEntity();
    paramFlowRuleEntity.setApp("App");
    paramFlowRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    paramFlowRuleEntity.setId(1L);
    paramFlowRuleEntity.setIp("127.0.0.1");
    paramFlowRuleEntity.setPort(null);
    paramFlowRuleEntity.setRule(new ParamFlowRule("Resource Name"));
    String content = new ObjectMapper().writeValueAsString(paramFlowRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/paramFlow/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link ParamFlowRuleController#apiDeleteRule(Long)}.
   * <p>
   * Method under test: {@link ParamFlowRuleController#apiDeleteRule(Long)}
   */
  @Test
  @DisplayName("Test apiDeleteRule(Long)")
  @Tag("MaintainedByDiffblue")
  void testApiDeleteRule() throws Exception {
    // Arrange
    when(ruleRepository.delete(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());
    when(ruleRepository.findAllByMachine(Mockito.<MachineInfo>any())).thenReturn(new ArrayList<>());
    when(ruleRepository.findById(Mockito.<Long>any())).thenReturn(new ParamFlowRuleEntity());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/paramFlow/rule/{id}", 1L);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(paramFlowRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":false,\"code\":-1,\"msg\":\"Cannot invoke \\\"java.lang.Integer.intValue()\\\" because \\\"port\\\" is"
                    + " null\",\"data\":null}"));
  }
}
