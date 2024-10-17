package com.incubationHackathon.app.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CsrfControllerTest {

    private MockMvc mockMvc;

    private CsrfController csrfController;

    @BeforeEach
    public void setUp() {
        csrfController = new CsrfController();
        mockMvc = MockMvcBuilders.standaloneSetup(csrfController).build();
    }

    @Test
    public void testCsrf() throws Exception {
        // Given
        CsrfToken csrfToken = new CsrfToken() {
            @Override
            public String getToken() {
                return "mockCsrfToken";
            }

            @Override
            public String getParameterName() {
                return "_csrf";
            }

            @Override
            public String getHeaderName() {
                return "X-CSRF-Token";
            }
        };

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute(CsrfToken.class.getName())).thenReturn(csrfToken);

        // When & Then
        mockMvc.perform(get("/csrf")
                        .requestAttr(CsrfToken.class.getName(), csrfToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is("mockCsrfToken")))
                .andExpect(jsonPath("$.headerName", is("X-CSRF-Token")))
                .andExpect(jsonPath("$.parameterName", is("_csrf")));
    }
}
