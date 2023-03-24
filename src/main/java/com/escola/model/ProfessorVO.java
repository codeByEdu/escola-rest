package com.escola.model;

public class ProfessorVO {
    private Integer id;
    private String nome;
    private String email;
    private String tipoProfessor;

    public String getTipoProfessor() {
        return tipoProfessor;
    }

    public void setTipoProfessor(String tipoProfessor) {
        this.tipoProfessor = tipoProfessor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
