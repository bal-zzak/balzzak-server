package com.balzzak.gatewayservice.advisor;

import com.balzzak.data.exception.CommonErrorCode;
import com.balzzak.gatewayservice.supports.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@ContextConfiguration(classes = {TestController.class, GlobalExceptionAdvisor.class})
class GlobalExceptionAdvisorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandleNoHandlerFoundException() throws Exception {
        // given
        // when
        this.mockMvc.perform(get("/t").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.NO_HANDLER_FOUND.getCode())))
                .andDo(print());
        // then
    }

    @Test
    void testHandleException() throws Exception {
        // given
        // when
        this.mockMvc.perform(get("/wow").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.INTERNAL_SERVER_ERROR.getCode())))
                .andDo(print());
        // then
    }
}