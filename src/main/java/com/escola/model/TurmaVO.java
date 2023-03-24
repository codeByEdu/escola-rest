package com.escola.model;

public class TurmaVO {
    private Integer codigo;
    private String ano;
    private ProfessorVO professorResponsavel;
    private ProfessorVO professorIngles;

    public TurmaVO() {
        professorIngles = new ProfessorVO();
        professorResponsavel = new ProfessorVO();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public ProfessorVO getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(ProfessorVO professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public ProfessorVO getProfessorIngles() {
        return professorIngles;
    }

    public void setProfessorIngles(ProfessorVO professorIngles) {
        this.professorIngles = professorIngles;
    }

}
