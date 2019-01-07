package br.gov.serpro.filmeslocacao.service;

import static br.gov.serpro.filmeslocacao.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.gov.serpro.filmeslocacao.entity.FilmeEntity;
import br.gov.serpro.filmeslocacao.entity.LocacaoEntity;
import br.gov.serpro.filmeslocacao.entity.UsuarioEntity;
import exceptions.FilmesSemEstoqueException;
import exceptions.LocadoraException;

public class LocacaoService {

	public LocacaoEntity alugarFilme(UsuarioEntity usuario, List<FilmeEntity> filmes)
			throws FilmesSemEstoqueException, LocadoraException {

		if (usuario == null) {
			throw new LocadoraException("usuario vazio");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("filme vazio");
		}

		for (FilmeEntity filme: filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmesSemEstoqueException();
			}			
		}

		LocacaoEntity locacao = new LocacaoEntity();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		
		Double valorTotal = 0d;
		for (FilmeEntity filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
			locacao.setValor(valorTotal);			
		}

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

	public static void main(String[] args) {

	}
}
