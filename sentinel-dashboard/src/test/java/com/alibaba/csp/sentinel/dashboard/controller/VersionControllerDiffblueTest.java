package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VersionController.class, FastJsonViewResponseBodyAdvice.class,
    FastJsonpResponseBodyAdvice.class, JSONPResponseBodyAdvice.class})
@ExtendWith(SpringExtension.class)
class VersionControllerDiffblueTest {
  @Autowired
  private FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice;

  @Autowired
  private FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice;

  @Autowired
  private JSONPResponseBodyAdvice jSONPResponseBodyAdvice;

  @Autowired
  private VersionController versionController;

  /**
   * Test {@link VersionController#apiGetVersion()}.
   * <p>
   * Method under test: {@link VersionController#apiGetVersion()}
   */
  @Test
  @DisplayName("Test apiGetVersion()")
  @Tag("MaintainedByDiffblue")
  void testApiGetVersion() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/version");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(versionController)
        .setControllerAdvice(fastJsonViewResponseBodyAdvice, fastJsonpResponseBodyAdvice, jSONPResponseBodyAdvice)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string("{\"success\":false,\"code\":1,\"msg\":\"getVersion failed: empty version\",\"data\":null}"));
  }
}
