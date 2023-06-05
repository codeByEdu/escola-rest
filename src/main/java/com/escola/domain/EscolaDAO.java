package com.escola.domain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.escola.config.ConnectionFactory;
import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;
import com.escola.model.TurmaVO;

public class EscolaDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void registraFalta(Integer codAluno, Integer codHorario, Date date) throws SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  falta ");
        sql.append(" (CD_ALUNO, CD_HORARIO, DT_APLICACAO) ");
        sql.append(" VALUES ");
        sql.append(" (?, ?, ?)");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codAluno);
            pst.setInt(2, codHorario);
            pst.setDate(3, new java.sql.Date(date.getTime()));

            // pst.setInt(2, falta.getCodProf());
            // pst.setString(3, falta.getJustificativa());

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }

    public List<DisciplinaVO> getDisciplinas(Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<DisciplinaVO> disciplinas = new ArrayList<>();
        sql.append(" select * from DISCIPLINA");

        try {
            pst = con.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DisciplinaVO disciplinaVO = new DisciplinaVO();
                disciplinaVO.setId(rs.getInt("CD_DISCIPLINA"));
                disciplinaVO.setNome(rs.getString("TX_DESCRICAO"));
                disciplinas.add(disciplinaVO);
            }

            return disciplinas;
        } finally {
            con.close();
            pst.close();
        }
    }

    public void vincularDisciplinaTurma(DisciplinaVO disciplina, TurmaVO turma, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  TURMA_DISCIPLINA");
        sql.append(" (CD_TURMA, CD_DISCIPLINA) ");
        sql.append(" VALUES");
        sql.append(" (?,?)");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, turma.getCodigo());
            pst.setInt(2, disciplina.getId());

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }

    public void registraAula(Connection con, Integer codHorario, Date date) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  AULA ");
        sql.append(" (CD_HORARIO, DT_REALIZACAO) ");
        sql.append(" VALUES ");
        sql.append(" (?, ?)");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codHorario);
            pst.setDate(2, new java.sql.Date(date.getTime()));

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validaAula(Integer codHorario, Date dataFalta) throws IOException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" select count(*) as qtd ");
        sql.append(" from  AULA ");
        sql.append(" where CD_HORARIO = ? ");
        sql.append(" and DT_REALIZACAO = ? ");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codHorario);
            pst.setDate(2, new java.sql.Date(dataFalta.getTime()));

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt("qtd") > 0) {
                    return true;
                }
            }

        } finally {
            con.close();
            pst.close();
        }
        return false;

    }

}
