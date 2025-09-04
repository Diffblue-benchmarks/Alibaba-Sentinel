package com.alibaba.csp.sentinel.dashboard.controller;

import com.alibaba.csp.sentinel.dashboard.discovery.AppInfo;
import com.alibaba.csp.sentinel.dashboard.discovery.AppManagement;
import com.alibaba.csp.sentinel.dashboard.discovery.MachineInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppController.class)
public class AppControllerTest extends AbstractWebMvcAuthTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppManagement appManagement;

    @Test
    public void queryAppsReturnsNamesFromAppManagement() throws Exception {
        when(appManagement.getAppNames()).thenReturn(Arrays.asList("appB", "appA"));
        mockMvc.perform(get("/app/names.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data[0]").value("appB"));
    }

    @Test
    public void queryAppInfosReturnsSortedAppInfos() throws Exception {
        AppInfo a = new AppInfo("b");
        a.setAppType(0);
        a.getMachines().add(new MachineInfo());
        AppInfo b = new AppInfo("a");
        b.setAppType(0);
        when(appManagement.getBriefApps()).thenReturn(new HashSet<>(Arrays.asList(a, b)));
        mockMvc.perform(get("/app/briefinfos.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].app").value("a"))
            .andExpect(jsonPath("$.data[1].app").value("b"));
    }

    @Test
    public void getMachinesByAppReturnsNullWhenAppNotFound() throws Exception {
        when(appManagement.getDetailApp("nope")).thenReturn(null);
        mockMvc.perform(get("/app/nope/machines.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @Ignore
    public void getMachinesByAppReturnsSortedMachines() throws Exception {
        AppInfo info = new AppInfo("demo");
        MachineInfo m1 = new MachineInfo();
        m1.setApp("demo");
        m1.setIp("2.2.2.2");
        m1.setPort(8719);
        MachineInfo m2 = new MachineInfo();
        m2.setApp("demo");
        m2.setIp("1.1.1.1");
        m2.setPort(8718);
        info.getMachines().add(m1);
        info.getMachines().add(m2);
        when(appManagement.getDetailApp("demo")).thenReturn(info);
        mockMvc.perform(get("/app/demo/machines.json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].ip").value("1.1.1.1"))
            .andExpect(jsonPath("$.data[1].ip").value("2.2.2.2"));
    }

    @Test
    @Ignore
    public void removeMachineReturnsSuccessMsgOnTrue() throws Exception {
        AppInfo info = new AppInfo("demo");
        when(appManagement.getDetailApp("demo")).thenReturn(info);
        when(appManagement.removeMachine("demo", "1.1.1.1", 8719)).thenReturn(true);
        mockMvc.perform(get("/app/demo/machine/remove.json?ip=1.1.1.1&port=8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.msg").value("success"));
    }

    @Test
    public void removeMachineReturnsFailWhenRemovalFails() throws Exception {
        AppInfo info = new AppInfo("demo");
        when(appManagement.getDetailApp("demo")).thenReturn(info);
        when(appManagement.removeMachine("demo", "1.1.1.1", 8719)).thenReturn(false);
        mockMvc.perform(get("/app/demo/machine/remove.json?ip=1.1.1.1&port=8719"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.code").value(1));
    }
}
