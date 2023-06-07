package com.example.hw1;

public class EquationService {
    public double[] solve(double a, double b, double c) {
        if (!(a > 0 || a < 0)) {
            throw new IllegalArgumentException("a can't be 0");
        }
        double[] solution = new double[0];
        double discriminant = getDiscriminant(a, b, c);
        if (discriminant >= 0) {
            solution = new double[2];
            solution[0] = (-b + Math.sqrt(discriminant)) / (2 * a);
            solution[1] = (-b - Math.sqrt(discriminant)) / (2 * a);
        }
        return solution;
    }

    private double getDiscriminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }
}
