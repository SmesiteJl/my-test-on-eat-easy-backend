package com.technokratos.eateasy.logstarter.aspect;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.technokratos.eateasy.logstarter.autoconfigure.LoggingAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(
    classes = LoggingAspectBeanTest.TestConfig.class,
    properties = "app.logging.enabled=true")
@ImportAutoConfiguration(LoggingAutoConfiguration.class)
class LoggingAspectBeanTest {

  @Configuration
  static class TestConfig {
    // Empty configuration
  }

  @Autowired private ApplicationContext context;

  @Test
  void loggingAspectShouldBePresentWhenEnabled() {
    assertNotNull(context.getBean(LoggingAspect.class));
  }
}
