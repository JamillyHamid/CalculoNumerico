package br.com.projeto;

import java.util.*;

public class Bissecao {
    static final int MAX_GRAU = 10;
    static final int BUSCA_MIN = -100;
    static final int BUSCA_MAX = 100;

    // Função polinomial
    public static double f(double x, double[] coef) {
        double resultado = 0;
        for (int i = 0; i < coef.length; i++) {
            resultado += coef[i] * Math.pow(x, i);
        }
        return resultado;
    }

    // Método da Bissecção
    public static Map<String, Object> bisseccao(double a, double b, double precisao, double[] coef) {
        int iteracoes = 0;
        double erro = Double.MAX_VALUE;
        double c = 0;

        while ((b - a) / 2 > precisao) {
            c = (a + b) / 2;
            double fc = f(c, coef);
            erro = Math.abs(b - a) / 2;

            if (fc == 0.0 || erro < precisao)
                break;

            if (f(a, coef) * fc < 0)
                b = c;
            else
                a = c;

            iteracoes++;
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("raiz", (a + b) / 2);
        resultado.put("erro", erro);
        resultado.put("iteracoes", iteracoes);
        return resultado;
    }

    // Busca de intervalos onde há mudança de sinal
    public static List<double[]> encontrarIntervalos(double[] coef) {
        List<double[]> intervalos = new ArrayList<>();

        for (int i = BUSCA_MIN; i < BUSCA_MAX; i++) {
            double fa = f(i, coef);
            double fb = f(i + 1, coef);
            if (fa * fb < 0) {
                intervalos.add(new double[]{i, i + 1});
            }
        }

        return intervalos;
    }

}
