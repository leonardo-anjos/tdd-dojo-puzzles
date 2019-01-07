package br.gov.serpro.filmeslocacao.service;

import br.gov.serpro.filmeslocacao.entity.FilmeEntity;
import br.gov.serpro.filmeslocacao.entity.LocacaoEntity;
import br.gov.serpro.filmeslocacao.entity.UsuarioEntity;
import br.gov.serpro.filmeslocacao.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceTest {

    @Test
    public void alugarFilmeSucesso() {
        // cenario
        LocacaoService service = new LocacaoService();
        UsuarioEntity usuario = new UsuarioEntity("Michael");
        FilmeEntity filme = new FilmeEntity("Acqua Man", 2, 5.0);

        // macao
        LocacaoEntity locacao = service.alugarFilme(usuario, filme);

        // verificacao
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(),
                DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));

        
    }
}
