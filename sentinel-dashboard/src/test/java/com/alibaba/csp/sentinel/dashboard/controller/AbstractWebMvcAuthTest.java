package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.auth.AuthorizationInterceptor;
import com.alibaba.csp.sentinel.dashboard.auth.LoginAuthenticationFilter;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Provides pass-through authentication/authorization infrastructure beans required by WebConfig
 * so that @WebMvcTest slices can load without bringing full security configuration. Previous
 * implementation used mocks, which short-circuited the filter chain (mock Filter not invoking
 * chain.doFilter) and interceptor (mock preHandle returning default false), resulting in empty
 * response bodies and failing JSONPath assertions.
 */
@Import(AbstractWebMvcAuthTest.AuthTestConfig.class)
public abstract class AbstractWebMvcAuthTest {

    @TestConfiguration
    static class AuthTestConfig {
        @Bean
        public LoginAuthenticationFilter loginAuthenticationFilter() {
            // Pass-through filter that simply delegates to the rest of the chain.
            return new LoginAuthenticationFilter() {
                @Override
                public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                        throws IOException, ServletException {
                    chain.doFilter(request, response);
                }

                @Override
                public void init(FilterConfig filterConfig) throws ServletException { /* no-op */ }

                @Override
                public void destroy() { /* no-op */ }
            };
        }

        @Bean
        public AuthorizationInterceptor authorizationInterceptor() {
            // Allow all requests to proceed.
            return new AuthorizationInterceptor() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                    return true;
                }

                @Override
                public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                                       @Nullable ModelAndView modelAndView) { /* no-op */ }

                @Override
                public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) { /* no-op */ }
            };
        }

        @Bean
        public AppManagement appManagement() {
            // Default mock; individual tests can override with @MockBean if they need custom stubbing.
            return Mockito.mock(AppManagement.class);
        }
    }
}
