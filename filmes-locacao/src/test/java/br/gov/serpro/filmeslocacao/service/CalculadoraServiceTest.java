package br.gov.serpro.filmeslocacao.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.NaoDividirPorZeroException;

public class CalculadoraServiceTest {

	private Calculadora calc;

	@Before
	public void setup() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		// cenario
		int a = 5;
		int b = 3;

		// acao
		int resultado = calc.somar(a, b);

		// verificacao
		Assert.assertEquals(8, resultado);

	}

	@Test
	public void deveSubtrairDoisValores() {
		// cenario
		int a = 8;
		int b = 5;

		// acao
		int resultado = calc.subtrair(a, b);

		// verificacao
		Assert.assertEquals(3, resultado);

	}

	@Test
	public void deveDividirDoisValores() throws NaoDividirPorZeroException {
		// cenario
		int a = 6;
		int b = 3;

		// acao
		int resultado = calc.divide(a, b);

		// verificacao
		Assert.assertEquals(2, resultado);
	}

	@Test(expected = NaoDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoDividirPorZeroException {
		int a = 10;
		int b = 0;

		calc.divide(a, b);
	}
}
