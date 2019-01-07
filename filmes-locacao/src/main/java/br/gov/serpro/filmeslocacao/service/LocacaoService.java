package br.gov.serpro.filmeslocacao.service;

import br.gov.serpro.filmeslocacao.entity.FilmeEntity;
import br.gov.serpro.filmeslocacao.entity.LocacaoEntity;
import br.gov.serpro.filmeslocacao.entity.UsuarioEntity;

import java.util.Date;

import static br.gov.serpro.filmeslocacao.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public LocacaoEntity alugarFilme(UsuarioEntity usuario, FilmeEntity filme) {
        LocacaoEntity locacao = new LocacaoEntity();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    public static void main(String[] args) {

    }
}
