package com.escola.model;

public class RelatorioFaltaVO {
    private String aluno;
    private String disciplina;
    private Integer totalFaltas;
    private Integer porcentagemFalta;
    private Integer totalAulas;

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Integer getTotalFaltas() {
        return totalFaltas;
    }

    public void setTotalFaltas(Integer totalFaltas) {
        this.totalFaltas = totalFaltas;
    }

    public Integer getPorcentagemFalta() {
        return porcentagemFalta;
    }

    public void setPorcentagemFalta(Integer porcentagemFalta) {
        this.porcentagemFalta = porcentagemFalta;
    }

    public Integer getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(Integer totalAulas) {
        this.totalAulas = totalAulas;
    }

}
