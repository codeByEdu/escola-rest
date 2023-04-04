package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.escola.model.AlunoVO;
import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;
import com.escola.model.ProfessorVO;
import com.escola.model.TipoVO;
import com.escola.model.TurmaVO;

public class EscolaDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public boolean validaProfessorParaTurma(Integer idProfessor, Integer idTurma, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("select 1  from TURMA_PROFESSOR");
        sql.append(" where CD_TURMA = ? AND CD_PROF = ?");
        Boolean professorValido = false;
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, idTurma);
            pst.setInt(2, idProfessor);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                professorValido = true;
            }

            return professorValido;
        } finally {
            con.close();
            pst.close();
        }
    }

    public List<ProfessorVO> getProfessores(Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<ProfessorVO> professores = new ArrayList<>();
        sql.append("SELECT P.TX_NOME,P.TX_EMAIL,P.CD_PROFESSOR, TP.TX_DESCRICAO");
        sql.append("    FROM PROFESSOR P ");
        sql.append("   INNER JOIN TIPO_PROFESSOR TP ON(P.CD_TIPO = TP.CD_TIPO) ");
        try {
            pst = connection.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProfessorVO professor = new ProfessorVO();
                professor.setEmail(rs.getString("TX_EMAIL"));
                professor.setNome(rs.getString("TX_NOME"));
                professor.setId(rs.getInt("CD_PROFESSOR"));
                professor.setTipoProfessor(rs.getString("TX_DESCRICAO"));
                professores.add(professor);
            }

            return professores;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public AlunoVO getAlunoPorId(Connection con, Integer codAluno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        sql.append("select * from alunos where cd_aluno = ?");

        AlunoVO aluno = new AlunoVO();
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codAluno);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));
                aluno.setId(rs.getInt("CD_ALUNO"));

            }

            return aluno;
        } finally {
            con.close();
            pst.close();
        }
    }

    public ProfessorVO getProfessorPorId(Connection con, Integer codProf) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        ProfessorVO professor = new ProfessorVO();
        sql.append("select * from professores where cd_professor = ?");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codProf);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                professor.setEmail(rs.getString("TX_EMAIL"));
                professor.setNome(rs.getString("TX_NOME"));
                professor.setId(rs.getInt("CD_PROFESSOR"));

            }

            return professor;
        } finally {
            con.close();
            pst.close();
        }
    }

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

    public List<TipoVO> getTiposProfessor(Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<TipoVO> tipos = new ArrayList<>();
        sql.append(" select * from TIPO_PROFESSOR");

        try {
            pst = con.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TipoVO tipoVO = new TipoVO();
                tipoVO.setId(rs.getInt("CD_TIPO"));
                tipoVO.setDescricao(rs.getString("TX_DESCRICAO"));
                tipos.add(tipoVO);
            }

            return tipos;
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

}
