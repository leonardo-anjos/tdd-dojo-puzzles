package br.gov.serpro.filmeslocacao.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.gov.serpro.filmeslocacao.entity.FilmeEntity;
import br.gov.serpro.filmeslocacao.entity.LocacaoEntity;
import br.gov.serpro.filmeslocacao.entity.UsuarioEntity;
import exceptions.FilmesSemEstoqueException;
import exceptions.LocadoraException;

public class CalculoValorLocacaoTest {

	private LocacaoService service;

	@Parameter
	public List<FilmeEntity> filmes;

	@Parameter(value = 1)
	public Double valorLocacao;

	@Parameter(value = 2)
	public String cenario;

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	private static FilmeEntity FilmeEntity1 = new FilmeEntity("FilmeEntity 1", 2, 4.0);
	private static FilmeEntity FilmeEntity2 = new FilmeEntity("FilmeEntity 2", 2, 4.0);
	private static FilmeEntity FilmeEntity3 = new FilmeEntity("FilmeEntity 3", 2, 4.0);
	private static FilmeEntity FilmeEntity4 = new FilmeEntity("FilmeEntity 4", 2, 4.0);
	private static FilmeEntity FilmeEntity5 = new FilmeEntity("FilmeEntity 5", 2, 4.0);
	private static FilmeEntity FilmeEntity6 = new FilmeEntity("FilmeEntity 6", 2, 4.0);
	private static FilmeEntity FilmeEntity7 = new FilmeEntity("FilmeEntity 7", 2, 4.0);

	@Parameters(name = "{2}")
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] {
				{ Arrays.asList(FilmeEntity1, FilmeEntity2), 8.0, "2 filmes: Sem Desconto" },
				{ Arrays.asList(FilmeEntity1, FilmeEntity2, FilmeEntity3), 11.0, "3 filmes: 25%" },
				{ Arrays.asList(FilmeEntity1, FilmeEntity2, FilmeEntity3, FilmeEntity4), 13.0, "4 filmes: 50%" },
				{ Arrays.asList(FilmeEntity1, FilmeEntity2, FilmeEntity3, FilmeEntity4, FilmeEntity5), 14.0,
						"5 filmes: 75%" },
				{ Arrays.asList(FilmeEntity1, FilmeEntity2, FilmeEntity3, FilmeEntity4, FilmeEntity5, FilmeEntity6),
						14.0, "6 filmes: 100%" },
				{ Arrays.asList(FilmeEntity1, FilmeEntity2, FilmeEntity3, FilmeEntity4, FilmeEntity5, FilmeEntity6,
						FilmeEntity7), 18.0, "7 filmes: Sem Desconto" } });
	}

	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Usuario 1");

		// acao
		LocacaoEntity resultado = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(resultado.getValor(), CoreMatchers.is(valorLocacao));
	}
}
