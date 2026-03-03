 package com.github.vinnyda23;

/**
 * Utility class for input validation.
 * Prevents crashes when user enters wrong data types (e.g., String instead of int).
 * Demonstrates defensive programming practices.
 */
public class Validador {

    /**
     * Functional interface for custom validation functions.
     * Allows passing validation logic as parameters.
     */
    @FunctionalInterface
    public interface ValidadorFunction {
        boolean validar(String input);
    }

    /**
     * Parse String to Integer safely.
     * Returns null if conversion fails (not exception thrown).
     */
    public static Integer parseIntegerSafe(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Parse String to Double safely.
     * Returns null if conversion fails.
     */
    public static Double parseDoubleSafe(String input) {
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    /**
     * Validate if string is not empty.
     */
    public static boolean naoEstaVazio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Validate if string is a valid email format (basic validation).
     */
    public static boolean ehEmailValido(String email) {
        if (!naoEstaVazio(email)) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    /**
     * Validate if string is a valid CPF format (basic validation - just checks if it's 11 digits).
     */
    public static boolean ehCpfValido(String cpf) {
        if (!naoEstaVazio(cpf)) {
            return false;
        }
        // Remove formatting characters
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.length() == 11 && cpfLimpo.matches("\\d+");
    }

    /**
     * Validate if string is a valid year format (4 digits).
     */
    public static boolean ehAnoValido(String ano) {
        if (!naoEstaVazio(ano)) {
            return false;
        }
        Integer anoInt = parseIntegerSafe(ano);
        return anoInt != null && anoInt >= 1900 && anoInt <= 2100;
    }

    /**
     * Validate if double is positive.
     */
    public static boolean ehValorValido(double valor) {
        return valor > 0;
    }

    /**
     * Sanitize string input (trim and limit length to prevent issues).
     */
    public static String sanitizar(String texto, int comprimentoMaximo) {
        if (texto == null) {
            return "";
        }
        String sanitizado = texto.trim();
        if (sanitizado.length() > comprimentoMaximo) {
            return sanitizado.substring(0, comprimentoMaximo);
        }
        return sanitizado;
    }
}

