package com.alibaba.csp.sentinel.dashboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.client.SentinelApiClient;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.domain.Result;
import com.alibaba.csp.sentinel.dashboard.repository.rule.RuleRepository;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
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

@ContextConfiguration(classes = {AuthorityRuleController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class AuthorityRuleControllerDiffblueTest {
  @MockBean
  private AppManagement appManagement;

  @Autowired
  private AuthorityRuleController authorityRuleController;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @MockBean
  private RuleRepository<AuthorityRuleEntity, Long> ruleRepository;

  @MockBean
  private SentinelApiClient sentinelApiClient;

  /**
   * Test {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);
    when(ruleRepository.saveAll(Mockito.<List<AuthorityRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchAuthorityRulesOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/authority/rules")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"given ip does not belong to given app\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine2() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<AuthorityRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchAuthorityRulesOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/authority/rules")
        .param("app", "")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine3() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<AuthorityRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchAuthorityRulesOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/authority/rules")
        .param("app", "foo")
        .param("ip", "");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer)")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine4() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<AuthorityRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchAuthorityRulesOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/authority/rules")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(0));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid parameter: port\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiQueryAllRulesForMachine(String, String, Integer)}
   */
  @Test
  @DisplayName("Test apiQueryAllRulesForMachine(String, String, Integer); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testApiQueryAllRulesForMachine_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(appManagement.isValidMachineOfApp(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);
    when(ruleRepository.saveAll(Mockito.<List<AuthorityRuleEntity>>any())).thenReturn(new ArrayList<>());
    when(sentinelApiClient.fetchAuthorityRulesOfMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
        .thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/authority/rules")
        .param("app", "foo")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource name cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule2() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp(null);
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule3() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(null);
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"rule can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} App is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity); given empty string; when AuthorityRuleEntity() App is empty string")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule_givenEmptyString_whenAuthorityRuleEntityAppIsEmptyString() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <ul>
   *   <li>Given minus one.</li>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} Port is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity); given minus one; when AuthorityRuleEntity() Port is minus one")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule_givenMinusOne_whenAuthorityRuleEntityPortIsMinusOne() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(-1);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <ul>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity); when AuthorityRuleEntity() App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule_whenAuthorityRuleEntityAppIsNull() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp(null);
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}.
   * <ul>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} Port is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiAddAuthorityRule(AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiAddAuthorityRule(AuthorityRuleEntity); when AuthorityRuleEntity() Port is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiAddAuthorityRule_whenAuthorityRuleEntityPortIsNull() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(null);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authority/rule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"resource name cannot be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule2() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp(null);
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"ip can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity)")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule3() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(null);
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"rule can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <ul>
   *   <li>Given empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity); given empty string")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenEmptyString() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} Port is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity); given zero; when AuthorityRuleEntity() Port is zero")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_givenZero_whenAuthorityRuleEntityPortIsZero() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(0);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <ul>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} App is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity); when AuthorityRuleEntity() App is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_whenAuthorityRuleEntityAppIsNull() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp(null);
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(8080);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"app can't be null or empty\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}.
   * <ul>
   *   <li>When {@link AuthorityRuleEntity#AuthorityRuleEntity()} Port is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiUpdateParamFlowRule(Long, AuthorityRuleEntity)}
   */
  @Test
  @DisplayName("Test apiUpdateParamFlowRule(Long, AuthorityRuleEntity); when AuthorityRuleEntity() Port is 'null'")
  @Tag("MaintainedByDiffblue")
  void testApiUpdateParamFlowRule_whenAuthorityRuleEntityPortIsNull() throws Exception {
    // Arrange
    AuthorityRuleEntity authorityRuleEntity = new AuthorityRuleEntity();
    authorityRuleEntity.setApp("App");
    authorityRuleEntity
        .setGmtCreate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity
        .setGmtModified(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    authorityRuleEntity.setId(1L);
    authorityRuleEntity.setIp("127.0.0.1");
    authorityRuleEntity.setPort(null);
    authorityRuleEntity.setRule(new AuthorityRule());
    String content = new ObjectMapper().writeValueAsString(authorityRuleEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/authority/rule/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityRuleController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"port can't be null\",\"data\":null}"));
  }

  /**
   * Test {@link AuthorityRuleController#apiDeleteRule(Long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Msg is {@code id cannot be null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthorityRuleController#apiDeleteRule(Long)}
   */
  @Test
  @DisplayName("Test apiDeleteRule(Long); when 'null'; then return Msg is 'id cannot be null'")
  @Tag("MaintainedByDiffblue")
  void testApiDeleteRule_whenNull_thenReturnMsgIsIdCannotBeNull() {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.
    //   Run dcover create --keep-partial-tests to gain insights into why
    //   a non-Spring test was created.

    // Arrange and Act
    Result<Long> actualApiDeleteRuleResult = new AuthorityRuleController().apiDeleteRule(null);

    // Assert
    assertEquals("id cannot be null", actualApiDeleteRuleResult.getMsg());
    assertNull(actualApiDeleteRuleResult.getData());
    assertEquals(-1, actualApiDeleteRuleResult.getCode());
    assertFalse(actualApiDeleteRuleResult.isSuccess());
  }
}
