package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.discovery.AppInfo;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;
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

@ContextConfiguration(classes = {AppController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class AppControllerDiffblueTest {
  @Autowired
  private AppController appController;

  @MockBean
  private AppManagement appManagement;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link AppController#queryApps(HttpServletRequest)}.
   * <p>
   * Method under test: {@link AppController#queryApps(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test queryApps(HttpServletRequest)")
  @Tag("MaintainedByDiffblue")
  void testQueryApps() throws Exception {
    // Arrange
    when(appManagement.getAppNames()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/names.json");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link AppController#queryAppInfos(HttpServletRequest)}.
   * <ul>
   *   <li>Given {@link AppInfo#AppInfo()} addMachine {@link MachineInfo} (default constructor).</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#queryAppInfos(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test queryAppInfos(HttpServletRequest); given AppInfo() addMachine MachineInfo (default constructor); then content string a string")
  @Tag("MaintainedByDiffblue")
  @Disabled
  void testQueryAppInfos_givenAppInfoAddMachineMachineInfo_thenContentStringAString() throws Exception {
    // Arrange
    AppInfo appInfo = new AppInfo();
    appInfo.addMachine(new MachineInfo());

    HashSet<AppInfo> appInfoSet = new HashSet<>();
    appInfoSet.add(appInfo);
    when(appManagement.getBriefApps()).thenReturn(appInfoSet);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/briefinfos.json");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"app\":\"\",\"appType\":0,\"machines\":[{\"app\":\"\",\"appType"
                    + "\":0,\"hostname\":\"\",\"ip\":\"\",\"port\":-1,\"lastHeartbeat\":0,\"heartbeatVersion\":0,\"version\":null,\"dead\":false"
                    + ",\"healthy\":false}],\"dead\":false,\"shown\":true}]}"));
  }

  /**
   * Test {@link AppController#queryAppInfos(HttpServletRequest)}.
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@link AppInfo#AppInfo()}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#queryAppInfos(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test queryAppInfos(HttpServletRequest); given HashSet() add AppInfo(); then content string a string")
  @Tag("MaintainedByDiffblue")
  @Disabled
  void testQueryAppInfos_givenHashSetAddAppInfo_thenContentStringAString() throws Exception {
    // Arrange
    HashSet<AppInfo> appInfoSet = new HashSet<>();
    appInfoSet.add(new AppInfo());
    when(appManagement.getBriefApps()).thenReturn(appInfoSet);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/briefinfos.json");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"app\":\"\",\"appType\":0,\"machines\":[],\"dead\":true,\"shown"
                    + "\":false}]}"));
  }

  /**
   * Test {@link AppController#queryAppInfos(HttpServletRequest)}.
   * <ul>
   *   <li>Given {@link HashSet#HashSet()} add {@link AppInfo#AppInfo()}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#queryAppInfos(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test queryAppInfos(HttpServletRequest); given HashSet() add AppInfo(); then content string a string")
  @Tag("MaintainedByDiffblue")
  @Disabled
  void testQueryAppInfos_givenHashSetAddAppInfo_thenContentStringAString2() throws Exception {
    // Arrange
    HashSet<AppInfo> appInfoSet = new HashSet<>();
    appInfoSet.add(new AppInfo());
    appInfoSet.add(new AppInfo());
    when(appManagement.getBriefApps()).thenReturn(appInfoSet);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/briefinfos.json");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"app\":\"\",\"appType\":0,\"machines\":[],\"dead\":true,\"shown"
                    + "\":false},{\"app\":\"\",\"appType\":0,\"machines\":[],\"dead\":true,\"shown\":false}]}"));
  }

  /**
   * Test {@link AppController#queryAppInfos(HttpServletRequest)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#queryAppInfos(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test queryAppInfos(HttpServletRequest); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testQueryAppInfos_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(appManagement.getBriefApps()).thenReturn(new HashSet<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/briefinfos.json");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link AppController#getMachinesByApp(String)}.
   * <ul>
   *   <li>Given {@link AppInfo#AppInfo()} addMachine {@link MachineInfo} (default constructor).</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#getMachinesByApp(String)}
   */
  @Test
  @DisplayName("Test getMachinesByApp(String); given AppInfo() addMachine MachineInfo (default constructor); then content string a string")
  @Tag("MaintainedByDiffblue")
  void testGetMachinesByApp_givenAppInfoAddMachineMachineInfo_thenContentStringAString() throws Exception {
    // Arrange
    AppInfo appInfo = new AppInfo();
    appInfo.addMachine(new MachineInfo());
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(appInfo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/{app}/machines.json", "App");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[{\"app\":\"\",\"hostname\":\"\",\"ip\":\"\",\"port\":-1,\"heartbeatVersion"
                    + "\":0,\"lastHeartbeat\":0,\"healthy\":false,\"version\":null}]}"));
  }

  /**
   * Test {@link AppController#getMachinesByApp(String)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":[]}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#getMachinesByApp(String)}
   */
  @Test
  @DisplayName("Test getMachinesByApp(String); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}'")
  @Tag("MaintainedByDiffblue")
  void testGetMachinesByApp_thenContentStringSuccessTrueCode0MsgSuccessData() throws Exception {
    // Arrange
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(new AppInfo());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/{app}/machines.json", "App");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":[]}"));
  }

  /**
   * Test {@link AppController#getMachinesByApp(String)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#getMachinesByApp(String)}
   */
  @Test
  @DisplayName("Test getMachinesByApp(String); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testGetMachinesByApp_thenContentStringSuccessTrueCode0MsgSuccessDataNull() throws Exception {
    // Arrange
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/app/{app}/machines.json", "App");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link AppController#removeMachineById(String, String, int)}.
   * <ul>
   *   <li>Given {@link AppManagement} {@link AppManagement#getDetailApp(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#removeMachineById(String, String, int)}
   */
  @Test
  @DisplayName("Test removeMachineById(String, String, int); given AppManagement getDetailApp(String) return 'null'")
  @Tag("MaintainedByDiffblue")
  void testRemoveMachineById_givenAppManagementGetDetailAppReturnNull() throws Exception {
    // Arrange
    when(appManagement.removeMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt())).thenReturn(true);
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(null);
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/app/{app}/machine/remove.json", "App")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link AppController#removeMachineById(String, String, int)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":1,"msg":"remove failed","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#removeMachineById(String, String, int)}
   */
  @Test
  @DisplayName("Test removeMachineById(String, String, int); then content string '{\"success\":false,\"code\":1,\"msg\":\"remove failed\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testRemoveMachineById_thenContentStringSuccessFalseCode1MsgRemoveFailedDataNull() throws Exception {
    // Arrange
    when(appManagement.removeMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt())).thenReturn(false);
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(new AppInfo());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/app/{app}/machine/remove.json", "App")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":1,\"msg\":\"remove failed\",\"data\":null}"));
  }

  /**
   * Test {@link AppController#removeMachineById(String, String, int)}.
   * <ul>
   *   <li>Then content string {@code {"success":true,"code":0,"msg":"success","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AppController#removeMachineById(String, String, int)}
   */
  @Test
  @DisplayName("Test removeMachineById(String, String, int); then content string '{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testRemoveMachineById_thenContentStringSuccessTrueCode0MsgSuccessDataNull() throws Exception {
    // Arrange
    when(appManagement.removeMachine(Mockito.<String>any(), Mockito.<String>any(), anyInt())).thenReturn(true);
    when(appManagement.getDetailApp(Mockito.<String>any())).thenReturn(new AppInfo());
    MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/app/{app}/machine/remove.json", "App")
        .param("ip", "foo");
    MockHttpServletRequestBuilder requestBuilder = paramResult.param("port", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(appController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }
}
