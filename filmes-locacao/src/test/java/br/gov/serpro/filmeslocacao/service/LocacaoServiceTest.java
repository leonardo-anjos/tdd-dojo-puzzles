package br.gov.serpro.filmeslocacao.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.gov.serpro.filmeslocacao.entity.FilmeEntity;
import br.gov.serpro.filmeslocacao.entity.LocacaoEntity;
import br.gov.serpro.filmeslocacao.entity.UsuarioEntity;
import br.gov.serpro.filmeslocacao.matchers.MatchersProprios;
import br.gov.serpro.filmeslocacao.utils.DataUtils;
import exceptions.FilmesSemEstoqueException;
import exceptions.LocadoraException;

public class LocacaoServiceTest {

	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exeption = ExpectedException.none();

	@Before
	public void setupMount() {
		service = new LocacaoService();
	}

	@Test
	public void locacaoFilmeSucesso() throws Exception {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 1, 5.0));

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
		error.checkThat(locacao.getDataRetorno(), MatchersProprios.ehHojeComDiferencaDias(1));

	}

	// tratando excecao de forma elegante
	@Test(expected = FilmesSemEstoqueException.class)
	public void lancarExcecaoNaLocacaoDoFilmeSemEstoque() throws Exception {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 0, 5.0));

		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);
	}

	@Test
	public void naoAlugarFilmeSemUsuario() throws FilmesSemEstoqueException {
		// cenario
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 10, 5.0));

		// acao
		try {
			LocacaoEntity locacao = service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is("usuario vazio"));
		}
	}

	@Test
	public void naoAlugarFilmeSemFilme() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		exeption.expect(LocadoraException.class);
		exeption.expectMessage("filme vazio");

		service.alugarFilme(usuario, null);
	}

	@Test
	public void alugarMaisDeUmFilmePorVez() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		FilmeEntity aquaman = new FilmeEntity("Acqua Man", 3, 5.0);
		FilmeEntity spiderman = new FilmeEntity("Spider Man", 3, 5.0);
		FilmeEntity superman = new FilmeEntity("Super Man", 3, 5.0);

		List<FilmeEntity> filmes = new ArrayList<>();
		filmes.add(aquaman);
		filmes.add(spiderman);
		filmes.add(superman);

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		error.checkThat(locacao.getValor(), CoreMatchers.is(locacao.getValor()));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));
	}

	@Test
	public void desconto25PorCentroNoFilmeTres() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 1, 4.0),
				new FilmeEntity("Spider Man", 1, 4.0), new FilmeEntity("Super Man", 1, 4.0));

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(11.00)); // 4 + 4 + 3 =
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));
	}

	@Test
	public void desconto50PorCentroNoFilmeQuatro() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 1, 4.0),
				new FilmeEntity("Spider Man", 1, 4.0), new FilmeEntity("Super Man", 1, 4.0));

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(11.00)); // 4 + 4 + 3 =
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));
	}

	@Test
	public void desconto75PorCentroNoFilmeCinco() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 1, 4.0),
				new FilmeEntity("Spider Man", 1, 4.0), new FilmeEntity("Super Man", 1, 4.0));

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(11.00)); // 4 + 4 + 3 =
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));
	}

	@Test
	public void sextoFilmeGratis() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Michael");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("Acqua Man", 1, 4.0),
				new FilmeEntity("Spider Man", 1, 4.0), new FilmeEntity("Super Man", 1, 4.0));

		// acao
		LocacaoEntity locacao = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(11.00)); // 4 + 4 + 3 =
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));
	}

	@Test
	public void devolverFimeSegundaFeiraAoAlugarSabado() throws FilmesSemEstoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		// cenario
		UsuarioEntity usuario = new UsuarioEntity("Gohan filho do goku");
		List<FilmeEntity> filmes = Arrays.asList(new FilmeEntity("A procura das estrelas do dragão", 1, 5.0));

		// acao
		LocacaoEntity retorno = service.alugarFilme(usuario, filmes);

		// verificacao
		Assert.assertThat(retorno.getDataRetorno(), MatchersProprios.caiNumaSegunda());

	}

}
