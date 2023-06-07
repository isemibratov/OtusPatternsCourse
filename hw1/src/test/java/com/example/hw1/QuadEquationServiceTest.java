package com.example.hw1;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class QuadEquationServiceTest {
    private final QuadEquationService quadEquationService = new QuadEquationService();

    @Test
    public void shouldSolveWithNoSolutions_aEq1_bEq0_cEq1() {
        // when
        double[] solution = quadEquationService.solve(1, 0, 1);

        // then
        assertAll(
                () -> assertNotNull(solution),
                () -> assertEquals(0, solution.length)
        );
    }

    @Test
    public void shouldSolveWithSolutions_aEq1_bEq0_cEqNeg1() {
        // when
        double[] solution = quadEquationService.solve(1, 0, -1);

        // then
        assertAll(
                () -> assertNotNull(solution),
                () -> assertEquals(2, solution.length),
                () -> assertEquals(1, solution[0]),
                () -> assertEquals(-1, solution[1])
        );
    }

    @Test
    public void shouldSolveWithSolutions_aEq1_bEq2_cEqNeg1() {
        // when
        double[] solution = quadEquationService.solve(1, 2, 1);

        // then
        assertAll(
                () -> assertNotNull(solution),
                () -> assertEquals(2, solution.length),
                () -> assertEquals(-1, solution[0]),
                () -> assertEquals(-1, solution[1])
        );
    }



    @Test
    public void shouldThrowExceptionWhen_aEq0() {
        // when+then
        assertThrows(IllegalArgumentException.class, () -> quadEquationService.solve(0, 2, 1));
    }
}
