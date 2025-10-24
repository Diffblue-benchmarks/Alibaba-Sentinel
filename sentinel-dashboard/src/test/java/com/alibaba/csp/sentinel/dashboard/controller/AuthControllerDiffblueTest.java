package com.alibaba.csp.sentinel.dashboard.controller;

import static org.mockito.Mockito.when;
import com.alibaba.csp.sentinel.dashboard.auth.AuthService;
import com.alibaba.csp.sentinel.dashboard.auth.SimpleWebAuthServiceImpl;
import com.alibaba.csp.sentinel.dashboard.auth.SimpleWebAuthServiceImpl.SimpleWebAuthUserImpl;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
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

@ContextConfiguration(classes = {AuthController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
  @Autowired
  private AuthController authController;

  @MockBean
  private AuthService<HttpServletRequest> authService;

  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  /**
   * Test {@link AuthController#login(HttpServletRequest, String, String)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"Invalid username or password","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthController#login(HttpServletRequest, String, String)}
   */
  @Test
  @DisplayName("Test login(HttpServletRequest, String, String); then content string '{\"success\":false,\"code\":-1,\"msg\":\"Invalid username or password\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testLogin_thenContentStringSuccessFalseCode1MsgInvalidUsernameOrPasswordDataNull() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
        .param("password", "foo")
        .param("username", "sentinel");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid username or password\",\"data\":null}"));
  }

  /**
   * Test {@link AuthController#login(HttpServletRequest, String, String)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code password} is {@code sentinel}.</li>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthController#login(HttpServletRequest, String, String)}
   */
  @Test
  @DisplayName("Test login(HttpServletRequest, String, String); when param(String, String[]) 'password' is 'sentinel'; then content string a string")
  @Tag("MaintainedByDiffblue")
  @Disabled
  void testLogin_whenParamPasswordIsSentinel_thenContentStringAString() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
        .param("password", "sentinel")
        .param("username", "sentinel");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"superUser\":true,\"id\":\"sentinel\",\"loginName\":\"sentinel"
                    + "\",\"nickName\":\"sentinel\"}}"));
  }

  /**
   * Test {@link AuthController#login(HttpServletRequest, String, String)}.
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code username} is {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthController#login(HttpServletRequest, String, String)}
   */
  @Test
  @DisplayName("Test login(HttpServletRequest, String, String); when param(String, String[]) 'username' is 'foo'")
  @Tag("MaintainedByDiffblue")
  void testLogin_whenParamUsernameIsFoo() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
        .param("password", "foo")
        .param("username", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Invalid username or password\",\"data\":null}"));
  }

  /**
   * Test {@link AuthController#logout(HttpServletRequest)}.
   * <p>
   * Method under test: {@link AuthController#logout(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test logout(HttpServletRequest)")
  @Tag("MaintainedByDiffblue")
  void testLogout() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/logout");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content().string("{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":null}"));
  }

  /**
   * Test {@link AuthController#check(HttpServletRequest)}.
   * <ul>
   *   <li>Then content string a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthController#check(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test check(HttpServletRequest); then content string a string")
  @Tag("MaintainedByDiffblue")
  @Disabled
  void testCheck_thenContentStringAString() throws Exception {
    // Arrange
    when(authService.getAuthUser(Mockito.<HttpServletRequest>any())).thenReturn(new SimpleWebAuthUserImpl("janedoe"));
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/check");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"success\":true,\"code\":0,\"msg\":\"success\",\"data\":{\"superUser\":true,\"id\":\"janedoe\",\"loginName\":\"janedoe"
                    + "\",\"nickName\":\"janedoe\"}}"));
  }

  /**
   * Test {@link AuthController#check(HttpServletRequest)}.
   * <ul>
   *   <li>Then content string {@code {"success":false,"code":-1,"msg":"Not logged in","data":null}}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AuthController#check(HttpServletRequest)}
   */
  @Test
  @DisplayName("Test check(HttpServletRequest); then content string '{\"success\":false,\"code\":-1,\"msg\":\"Not logged in\",\"data\":null}'")
  @Tag("MaintainedByDiffblue")
  void testCheck_thenContentStringSuccessFalseCode1MsgNotLoggedInDataNull() throws Exception {
    // Arrange
    when(authService.getAuthUser(Mockito.<HttpServletRequest>any())).thenReturn(null);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/check");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":-1,\"msg\":\"Not logged in\",\"data\":null}"));
  }
}
