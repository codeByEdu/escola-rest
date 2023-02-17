package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escola.model.AlunoVO;

public class EscolaDAO {

    /**
     * @param id
     * @param connection TODO
     * @return
     * @throws SQLException
     */
    public List<AlunoVO> getAlunosPorAula(Integer id, Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst= null;
        List<AlunoVO> alunos = new ArrayList<>();
        sql.append("select * from alunos where CD_SALA = ?");
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
        }
    }
    
}
