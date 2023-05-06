package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;
import com.escola.model.TurmaVO;

public class EscolaDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void registraFalta(Connection con, FaltaVO falta) throws SQLException {

        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  faltas");
        sql.append(" (CD_ALUNO, CD_PROF, TX_JUSTIFICATIVA, DT_FALTA) ");
        sql.append(" VALUES");
        sql.append(" (?,?,?, current_date())");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, falta.getCodAluno());
            pst.setInt(2, falta.getCodProf());
            pst.setString(3, falta.getJustificativa());

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

}
