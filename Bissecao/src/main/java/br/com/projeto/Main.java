package br.com.projeto;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static br.com.projeto.Bissecao.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada dos coeficientes
        System.out.println("Informe o grau do polinômio (máximo 10): ");
        int grau = scanner.nextInt();
        if (grau > MAX_GRAU) {
            System.out.println("Grau máximo permitido é " + MAX_GRAU);
            return;
        }

        double[] coef = new double[grau + 1];
        System.out.println("Informe os coeficientes do termo de grau 0 até o grau " + grau + ":");
        for (int i = 0; i <= grau; i++) {
            System.out.print("Coeficiente de x^" + i + ": ");
            coef[i] = scanner.nextDouble();
        }

        // Precisão
        System.out.print("Informe a precisão desejada (ex: 0.0001): ");
        double precisao = scanner.nextDouble();

        // Encontrar intervalos
        List<double[]> intervalos = encontrarIntervalos(coef);
        if (intervalos.isEmpty()) {
            System.out.println("Nenhum intervalo com mudança de sinal encontrado.");
            return;
        } else {
            for (double[] intervalo : intervalos) {
                System.out.println("\n |" + intervalo[0] + " - " + intervalo[1] + " |");
            }
        }

        System.out.println("\n--- Resultados ---");
        int count = 1;
        for (double[] intervalo : intervalos) {
            Map<String, Object> resultado = bisseccao(intervalo[0], intervalo[1], precisao, coef);
            System.out.printf("Raiz %d:\n", count++);
            System.out.printf("  Intervalo: [%.2f, %.2f]\n", intervalo[0], intervalo[1]);
            System.out.printf("  Aproximação: %.10f\n", resultado.get("raiz"));
            System.out.printf("  Erro estimado: %.10f\n", resultado.get("erro"));
            System.out.printf("  Iterações: %d\n", resultado.get("iteracoes"));
            System.out.println();
        }
    }
}