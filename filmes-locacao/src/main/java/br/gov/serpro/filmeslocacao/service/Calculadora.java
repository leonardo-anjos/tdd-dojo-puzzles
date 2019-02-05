package br.gov.serpro.filmeslocacao.service;

import exceptions.NaoDividirPorZeroException;

public class Calculadora {

	public int somar(int a, int b) {
		return a + b;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public int divide(int a, int b) throws NaoDividirPorZeroException {
		if (b == 0) {
			throw new NaoDividirPorZeroException();
		}
		return a / b;
	}
}
