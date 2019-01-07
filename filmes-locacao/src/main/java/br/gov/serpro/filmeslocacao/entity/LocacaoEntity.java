package br.gov.serpro.filmeslocacao.entity;

import java.util.Date;

public class LocacaoEntity {

    private UsuarioEntity usuario;
    private FilmeEntity filme;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;

    public LocacaoEntity() {
    }

    public LocacaoEntity(UsuarioEntity usuario, FilmeEntity filme, Date dataLocacao, Date dataRetorno, Double valor) {
        this.usuario = usuario;
        this.filme = filme;
        this.dataLocacao = dataLocacao;
        this.dataRetorno = dataRetorno;
        this.valor = valor;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public FilmeEntity getFilme() {
        return filme;
    }

    public void setFilme(FilmeEntity filme) {
        this.filme = filme;
    }

}
