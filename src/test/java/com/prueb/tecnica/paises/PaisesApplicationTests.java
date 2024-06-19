package com.prueb.tecnica.paises;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class PaisesApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void mainMethodRunsSuccessfully() {
        PaisesApplication.main(new String[] {});
    }

    @Test
    void configureMethodReturnsSpringApplicationBuilder() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();

        PaisesApplication application = new PaisesApplication();
        SpringApplicationBuilder result = application.configure(builder);

        assertSame(builder, result);
    }
}
