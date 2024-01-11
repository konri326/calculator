package pl.gontarczyk.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.gontarczyk.calculator.exception.IllegalDivisionException;
import pl.gontarczyk.calculator.exception.StrategyNotFoundException;
import pl.gontarczyk.calculator.model.EquationRequestDto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSolve_shouldReturnResultOfAddedValues() throws Exception {
        BigDecimal firstValue = new BigDecimal("2.53");
        BigDecimal secondValue = new BigDecimal("2.22");
        String operation = " + ";
        BigDecimal result = firstValue.add(secondValue);
        String equationString = firstValue + operation + secondValue;

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equation").value(equationString))
                .andExpect(jsonPath("$.firstValue").value(firstValue.toString()))
                .andExpect(jsonPath("$.operation").value(operation.trim()))
                .andExpect(jsonPath("$.secondValue").value(secondValue.toString()))
                .andExpect(jsonPath("$.result").value(result.toString()));
    }

    @Test
    void testSolve_shouldReturnResultOfDividedValues() throws Exception {
        BigDecimal firstValue = new BigDecimal("14");
        BigDecimal secondValue = new BigDecimal("6");
        String operation = " / ";
        BigDecimal result = firstValue.divide(secondValue, MathContext.DECIMAL64);
        String equationString = firstValue + operation + secondValue;

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equation").value(equationString))
                .andExpect(jsonPath("$.firstValue").value(firstValue.toString()))
                .andExpect(jsonPath("$.operation").value(operation.trim()))
                .andExpect(jsonPath("$.secondValue").value(secondValue.toString()))
                .andExpect(jsonPath("$.result").value(result.toString()));
    }

    @Test
    void testSolve_shouldReturnResultOfMultipliedValues() throws Exception {
        BigDecimal firstValue = new BigDecimal("2.53");
        BigDecimal secondValue = new BigDecimal("2.22");
        String operation = " * ";
        BigDecimal result = firstValue.multiply(secondValue);
        String equationString = firstValue + operation + secondValue;

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equation").value(equationString))
                .andExpect(jsonPath("$.firstValue").value(firstValue.toString()))
                .andExpect(jsonPath("$.operation").value(operation.trim()))
                .andExpect(jsonPath("$.secondValue").value(secondValue.toString()))
                .andExpect(jsonPath("$.result").value(result.toString()));
    }

    @Test
    void testSolve_shouldReturnResultOfSubtractedValues() throws Exception {
        BigDecimal firstValue = new BigDecimal("14");
        BigDecimal secondValue = new BigDecimal("6");
        String operation = " - ";
        BigDecimal result = firstValue.subtract(secondValue);
        String equationString = firstValue + operation + secondValue;

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equation").value(equationString))
                .andExpect(jsonPath("$.firstValue").value(firstValue.toString()))
                .andExpect(jsonPath("$.operation").value(operation.trim()))
                .andExpect(jsonPath("$.secondValue").value(secondValue.toString()))
                .andExpect(jsonPath("$.result").value(result.toString()));
    }

    @Test
    void testSolve_shouldThrowStrategyNotfoundException() throws Exception {
        BigDecimal firstValue = new BigDecimal("14");
        BigDecimal secondValue = new BigDecimal("6");
        String operation = " @ ";
        String equationString = firstValue + operation + secondValue;
        String exceptionMsg = "The calculator doesn't have this function!";

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof StrategyNotFoundException))
                .andExpect(result -> assertEquals(exceptionMsg, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void testSolve_shouldThrowIllegalDivisionException() throws Exception {
        BigDecimal firstValue = new BigDecimal("14");
        BigDecimal secondValue = new BigDecimal("0");
        String operation = " / ";
        String equationString = firstValue + operation + secondValue;
        String exceptionMsg = "You cannot divide by 0!";

        mockMvc.perform(post("/api/v1/equation/solve")
                        .content(objectMapper.writeValueAsString(EquationRequestDto.builder()
                                .equation(equationString)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalDivisionException))
                .andExpect(result -> assertEquals(exceptionMsg, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}