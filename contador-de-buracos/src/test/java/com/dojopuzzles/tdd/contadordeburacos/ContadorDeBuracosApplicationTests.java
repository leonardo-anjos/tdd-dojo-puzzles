package com.dojopuzzles.tdd.contadordeburacos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContadorDeBuracosApplicationTests {

	@Test
	public void Palavra_Com_Letra_C_Sem_Buraco_Rotorna_0() {
		String palavra = "C";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(0, cont);
	}

	@Test
	public void Palavra_Com_Letra_A_Retorna_1() {
		String palavra = "A";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(1, cont);
	}

	@Test
	public void Palavra_Com_Letra_B_Retorna_2() {
		String palavra = "B";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(2, cont);
	}

	@Test
	public void Palavra_Com_Letra_A_e_B_Retorna_3() {
		String palavra = "AB";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(3, cont);
	}

	@Test
	public void Palavra_Com_2_Letras_B_Retorna_4() {
		String palavra = "BB";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(4, cont);
	}
	
	@Test
	public void cincoBuracos() {
		String palavra = "leonardo";
		VerificadorDeBuraco vdb = new VerificadorDeBuraco();
		int cont = vdb.quantidadeDeBuracos(palavra);

		Assert.assertEquals(5, cont);
	}
	

}
