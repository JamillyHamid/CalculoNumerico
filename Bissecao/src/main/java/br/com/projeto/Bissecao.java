package br.com.projeto;

import java.util.*;

public class Bissecao {
    static final int MAX_GRAU = 10;
    static final int BUSCA_MIN = -1000;
    static final int BUSCA_MAX = 1000;

    public static double funcaoPolinomial(double x, double[] coef) {
        double resultado = 0;
        for (int i = 0; i < coef.length; i++) {
            double potencia = 1;
            for (int j = 0; j < i; j++) {
                potencia *= x;
            }
            resultado += coef[i] * potencia;
        }
        return resultado;
    }

    public static Map<String, Object> bisseccao(double a, double b, double precisao, double[] coef) {
        int iteracoes = 0;
        double erro = Double.MAX_VALUE;
        double media = 0;

        while ((b - a) / 2 > precisao) {
            media = (a + b) / 2;
            double fp = funcaoPolinomial(media, coef);
            double erroTemp = b - a;

            if (erroTemp < 0) {
                erroTemp = -erroTemp;
            }
            erro = erroTemp / 2;

            if (fp == 0.0 || erro < precisao) {
                break;
            }

            if (funcaoPolinomial(a, coef) * fp < 0) {
                b = media;
            }else {
                a = media;
            }

            iteracoes++;
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("raiz", (a + b) / 2);
        resultado.put("erro", erro);
        resultado.put("iteracoes", iteracoes);
        return resultado;
    }

    public static List<double[]> encontrarIntervalos(double[] coef) {
        List<double[]> intervalos = new ArrayList<>();

        for (int i = BUSCA_MIN; i < BUSCA_MAX; i++) {
            double fa = funcaoPolinomial(i, coef);
            double fb = funcaoPolinomial(i + 1, coef);
            if (fa * fb < 0) {
                intervalos.add(new double[]{i, i + 1});
            }
        }

        return intervalos;
    }

}
