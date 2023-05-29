package com.escola.domain;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escola.model.HorarioVO;
import com.escola.model.TurmaRequestVO;
import com.escola.model.TurmaVO;

public class TurmaDAO {

    public List<TurmaVO> getTurmas(Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<TurmaVO> turmas = new ArrayList<>();
        sql.append("SELECT P.TX_NOME, T.ANO, T.CD_TURMA, P.CD_TIPO, P.CD_PROFESSOR");
        sql.append("    FROM PROFESSOR P ");
        sql.append("    INNER JOIN TURMA_PROFESSOR TP ON(P.CD_PROFESSOR = TP.CD_PROF) ");
        sql.append(" INNER JOIN TURMA T ON(T.CD_TURMA = TP.CD_TURMA)");
        sql.append(" WHERE CD_TIPO = 1");

        try {
            pst = con.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TurmaVO turma = new TurmaVO();
                turma.setAno(rs.getString("ANO"));
                turma.setCodigo(rs.getInt("CD_TURMA"));
                turma.getProfessorResponsavel().setNome(rs.getString("TX_NOME"));
                turma.getProfessorResponsavel().setId(rs.getInt("CD_PROFESSOR"));
                turmas.add(turma);
            }

            return turmas;
        } finally {
            con.close();
            pst.close();
        }
    }

    public Integer addTurma(TurmaRequestVO turma, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        // idTurma = id criado no banco
        Integer idTurma = 0;

        sql.append(" insert into  TURMA");
        sql.append(" (ANO ) ");
        sql.append(" VALUES");
        sql.append(" (?)");
        try {
            pst = con.prepareStatement(sql.toString(), 1);
            pst.setString(1, turma.getAno());

            pst.executeUpdate();

            // atribui idTurma ao idCriado no banco agora
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                idTurma = (int) rs.getLong(1);
            }
            rs.close();

        } finally {
            con.close();
            pst.close();
        }
        return idTurma;
    }

    // add TurmaxProfessor

    public void vincularProfessorTurma(Integer prof, Integer turma, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  TURMA_PROFESSOR");
        sql.append(" (CD_TURMA, CD_PROF) ");
        sql.append(" VALUES");
        sql.append(" (?,?)");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, turma);
            pst.setInt(2, prof);

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }

    public HorarioVO getHorario(Integer idTurma, Integer diaSemana, Integer ordemAula, Connection con)
            throws SQLException {

        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        HorarioVO horario = new HorarioVO();
        sql.append("select * from HORARIO");
        sql.append(" inner join DISCIPLINA on (HORARIO.CD_DISCIPLINA = DISCIPLINA.CD_DISCIPLINA)");
        sql.append(" where CD_TURMA = ? and NM_DIA_SEMANA = ? and NM_ORDEM_AULA = ?");

        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, idTurma);
            pst.setInt(2, diaSemana);
            pst.setInt(3, ordemAula);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                horario.setIdHorario(rs.getInt("CD_HORARIO"));
                horario.setIdTurma(rs.getInt("CD_TURMA"));
                horario.setIdDisciplina(rs.getInt("CD_DISCIPLINA"));
                // horario.setIdProfessor(rs.getInt("CD_PROFESSOR"));
                horario.setDiaSemana(rs.getInt("NM_DIA_SEMANA"));
                horario.setOrdemAula(rs.getInt("NM_ORDEM_AULA"));
                horario.setNomeDisciplina(rs.getString("TX_DESCRICAO"));
            }

            return horario;
        } finally {
            con.close();
            pst.close();
        }

    }

}
