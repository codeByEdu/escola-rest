package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escola.model.AlunoVO;
import com.escola.model.dto.UpdateAlunoDTO;

@Repository
public class AlunoDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<AlunoVO> getAlunosPorTurma(Integer id, Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<AlunoVO> alunos = new ArrayList<>();
        sql.append("select * from ALUNO where CD_TURMA = ?");
        try {
            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AlunoVO aluno = new AlunoVO();
                aluno.setId(rs.getInt("CD_ALUNO"));
                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));
                alunos.add(aluno);
            }

            return alunos;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public AlunoVO getAlunoPorNome(Connection con, String nome) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        AlunoVO aluno = new AlunoVO();

        sql.append("select * from ALUNO where TX_NOME like ? ");
        try {
            pst = con.prepareStatement(sql.toString());
            pst.setString(1, '%' + nome + '%');

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                aluno.setId(rs.getInt("CD_ALUNO"));
                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));

            }

            return aluno;
        } finally {
            con.close();
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

    public void updateAluno(Connection con, Integer idAluno, UpdateAlunoDTO updateAluno) throws SQLException {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append(" UPDATE ALUNO ");
        sql.append(" SET TX_NOME = ? ,");
        sql.append(" TX_EMAIL = ? ,");
        sql.append(" CD_TURMA = ? ");
        sql.append(" WHERE CD_ALUNO = ? ");

        pst = con.prepareStatement(sql.toString());
        pst.setString(1, updateAluno.getNome());
        pst.setString(2, updateAluno.getEmailResponsavel());
        pst.setInt(3, updateAluno.getCdTurma());
        pst.setInt(4, idAluno);

        pst.executeUpdate();
    }

}
