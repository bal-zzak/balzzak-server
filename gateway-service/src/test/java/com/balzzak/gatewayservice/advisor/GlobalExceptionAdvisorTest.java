package com.balzzak.gatewayservice.advisor;

import com.balzzak.common.exception.CommonErrorCode;
import com.balzzak.gatewayservice.supports.TestController;
import com.balzzak.gatewayservice.supports.TestDto;
import com.balzzak.gatewayservice.supports.TestType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@ContextConfiguration(classes = {TestController.class, GlobalExceptionAdvisor.class})
class GlobalExceptionAdvisorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOK() throws Exception {
        // given
        // when
        this.mockMvc.perform(get("/api/tests/2").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)));
        // then
    }

    @Test
    void testHandleMissingServletRequestParameter() throws Exception {
        // given
        // when
        this.mockMvc.perform(get("/api/tests").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.MISSING_INPUT_VALUE.getCode())))
                .andDo(print());
        // then
    }

    @Test
    void testHandleMethodArgumentTypeMismatch() throws Exception {
        // given
        // when
        this.mockMvc.perform(get("/api/tests/ttt").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.MISMATCHING_TYPE_VALUE.getCode())))
                .andDo(print());
        // then
    }

    @Test
    void testHandleMethodArgumentNotValidException() throws Exception {
        // given
        TestDto testDto = new TestDto(10000, " ", TestType.ACCEPTANCE);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testDto);
        // when
        this.mockMvc.perform(post("/api/tests").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.INVALID_INPUT_VALUE.getCode())))
                .andDo(print());
        // then
    }

    @Test
    void testHandleHttpMessageNotReadable() throws Exception {
        // given
        String json = "{\"id\":20,\"content\":\"Hello World\",\"type\":\"HELLO\"}";
        // when
        this.mockMvc.perform(post("/api/tests").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.INVALID_INPUT_VALUE.getCode())))
                .andDo(print());
        // then
    }

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
    void testHandleHttpRequestMethodNotSupported() throws Exception {
        // given
        // when
        this.mockMvc.perform(delete("/wow").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.METHOD_NOT_ALLOWED.getCode())))
                .andDo(print());
        // then
    }

    @Test
    void testHandleHttpMediaTypeNotSupported() throws Exception {
        // given
        // when
        this.mockMvc.perform(post("/api/tests").content("").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.code", is(CommonErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode())))
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