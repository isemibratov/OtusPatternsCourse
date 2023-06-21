package com.example.hw1;

public class QuadEquationService {
    public double[] solve(double a, double b, double c) {
        double accuracy = 1e-5;
        if (Math.abs(a) <= accuracy) {
            throw new IllegalArgumentException("a can't be 0");
        }
        double discriminant = Math.pow(b, 2) - 4 * a * c;
        double[] solution = new double[0];
        if (discriminant < -accuracy) {
            solution = new double[0];
        }
        if (Math.abs(discriminant) <= accuracy) {
            solution = new double[]{-b / (2 * a), -b / (2 * a)};
        }
        if (discriminant >= accuracy) {
            solution = new double[]{(-b + Math.sqrt(discriminant)) / (2 * a), (-b - Math.sqrt(discriminant)) / (2 * a)};
        }
        return solution;
    }
}
