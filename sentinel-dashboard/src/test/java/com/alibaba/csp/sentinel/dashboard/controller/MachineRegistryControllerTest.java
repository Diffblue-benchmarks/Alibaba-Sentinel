package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MachineRegistryController.class)
public class MachineRegistryControllerTest extends AbstractWebMvcAuthTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private AppManagement appManagement;

    @Test
    public void receiveHeartBeatRejectsInvalidIp() throws Exception {
        mockMvc.perform(get("/registry/machine").param("app","demo").param("ip","invalid_ip").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void receiveHeartBeatRejectsUnsetPort() throws Exception {
        mockMvc.perform(get("/registry/machine").param("app","demo").param("ip","127.0.0.1").param("port","-1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    @Ignore
    public void receiveHeartBeatAcceptsValidData() throws Exception {
        doNothing().when(appManagement).addMachine(any());
        mockMvc.perform(get("/registry/machine").param("app","demo").param("ip","127.0.0.1").param("port","8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.msg").value("success"));
    }
}
