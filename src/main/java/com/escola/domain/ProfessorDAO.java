package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escola.model.ProfessorVO;
import com.escola.model.TipoVO;

public class ProfessorDAO {

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
                professor.getTipoProfessor().setDescricao(rs.getString("TX_DESCRICAO"));
                professores.add(professor);
            }

            return professores;
        } finally {
            connection.close();
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

    public void addProfessor(ProfessorVO prof, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" insert into  PROFESSOR");
        sql.append(" (TX_EMAIL, TX_NOME, CD_TIPO) ");
        sql.append(" VALUES");
        sql.append(" (?,?,?)");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setString(1, prof.getEmail());
            pst.setString(2, prof.getNome());
            pst.setInt(3, prof.getTipoProfessor().getId());

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }
    // update professor

    public void updateProfessor(ProfessorVO prof, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" update  PROFESSOR");
        sql.append(" set TX_EMAIL = ?, TX_NOME = ?, CD_TIPO = ? ");
        sql.append(" where CD_PROFESSOR = ?");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setString(1, prof.getEmail());
            pst.setString(2, prof.getNome());
            pst.setInt(3, prof.getTipoProfessor().getId());
            pst.setInt(4, prof.getId());

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }
    // delete professor

    public void deleteProfessor(Long idProf, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append(" delete from PROFESSOR");
        sql.append(" where CD_PROFESSOR = ?");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setLong(1, idProf);

            pst.executeUpdate();

        } finally {
            con.close();
            pst.close();
        }
    }

}
