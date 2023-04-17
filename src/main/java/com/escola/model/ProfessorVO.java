package com.escola.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProfessorVO {
    private Integer id;
    private String nome;
    private String email;
    private TipoVO tipoProfessor;

    @JsonCreator
    public ProfessorVO() {
        this.tipoProfessor = new TipoVO();
    }

    public TipoVO getTipoProfessor() {
        return tipoProfessor;
    }

    public void setTipoProfessor(TipoVO tipoProfessor) {
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
