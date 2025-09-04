package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.auth.AuthService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest extends AbstractWebMvcAuthTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService<HttpServletRequest> authService;

    @Test
    public void loginSucceedsWithDefaultCredentials() throws Exception {
        mockMvc.perform(post("/auth/login").param("username", "sentinel").param("password", "sentinel"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.loginName").value("sentinel"));
    }

    @Test
    public void loginFailsWithWrongPassword() throws Exception {
        mockMvc.perform(post("/auth/login").param("username", "sentinel").param("password", "wrong"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.code").value(-1));
    }

    @Test
    public void logoutAlwaysSucceeds() throws Exception {
        mockMvc.perform(post("/auth/logout").session(new MockHttpSession()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void checkReturnsNotLoggedInWhenNoSessionUser() throws Exception {
        when(authService.getAuthUser(any())).thenReturn(null);
        mockMvc.perform(post("/auth/check"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.code").value(-1));
    }

    @Test
    @Ignore
    public void checkReturnsUserWhenLoggedIn() throws Exception {
        AuthService.AuthUser user = Mockito.mock(AuthService.AuthUser.class);
        when(user.getLoginName()).thenReturn("alice");
        when(authService.getAuthUser(any())).thenReturn(user);
        mockMvc.perform(post("/auth/check"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.loginName").value("alice"));
    }
}
