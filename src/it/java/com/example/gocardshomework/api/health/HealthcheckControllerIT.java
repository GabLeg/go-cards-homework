package com.example.gocardshomework.api.health;

import com.example.gocardshomework.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HealthcheckControllerIT extends IntegrationTestParent {

  @Test
  void whenHealthcheck_thenReturnHttp200() throws Exception {
    this.mockMvc.perform(get("/actuator/health")).andExpect(status().isOk());
  }
}
