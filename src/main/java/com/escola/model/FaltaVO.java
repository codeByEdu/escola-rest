package com.escola.model;

import java.util.Date;
import java.util.List;

public class FaltaVO {

    private List<AlunoVO> alunos;
    private Integer codHorario;

    public Integer getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(Integer codHorario) {
        this.codHorario = codHorario;
    }

    public List<AlunoVO> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoVO> alunos) {
        this.alunos = alunos;
    }

}
