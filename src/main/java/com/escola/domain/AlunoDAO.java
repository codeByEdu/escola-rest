package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escola.model.AlunoVO;

public class AlunoDAO {
    public List<AlunoVO> getAlunosPorTurma(Integer id, Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<AlunoVO> alunos = new ArrayList<>();
        sql.append("select * from ALUNO where CD_TURMA = ?");
        try{
             pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, id);


            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                AlunoVO aluno = new AlunoVO();
                aluno.setId(rs.getInt("CD_ALUNO"));
                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));
                alunos.add(aluno);
            }
          
            return alunos;
        }finally{
            connection.close();
            pst.close();
        }
    }

	public AlunoVO getAlunoPorNome(Connection con, String nome) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        AlunoVO aluno = new AlunoVO();
        
        sql.append("select * from ALUNO where TX_NOME like ? ");
        try{
             pst = con.prepareStatement(sql.toString());
            pst.setString(1, '%'+ nome + '%') ;


            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                
                aluno.setId(rs.getInt("CD_ALUNO"));
                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));
                
            }
          
            return aluno;
        }finally{
            con.close();
            pst.close();
        }
	}

}
