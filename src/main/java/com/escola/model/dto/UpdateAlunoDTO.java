package com.escola.model.dto;

public class UpdateAlunoDTO {
    private String nome;
    private String emailResponsavel;
    private Integer cdTurma;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public Integer getCdTurma() {
        return cdTurma;
    }

    public void setCdTurma(Integer cdTurma) {
        this.cdTurma = cdTurma;
    }

}
