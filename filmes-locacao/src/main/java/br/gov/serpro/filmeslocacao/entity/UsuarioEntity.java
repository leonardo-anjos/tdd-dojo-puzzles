package br.gov.serpro.filmeslocacao.entity;

public class UsuarioEntity {

    private String nome;

    public UsuarioEntity() {}

    public UsuarioEntity(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
