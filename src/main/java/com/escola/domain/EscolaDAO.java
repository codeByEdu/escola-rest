package com.escola.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.escola.model.AlunoVO;
import com.escola.model.FaltaVO;
import com.escola.model.ProfessorVO;

public class EscolaDAO {

    /**
     * @param id
     * @param connection 
     * @return
     * @throws SQLException
     */
    public List<AlunoVO> getAlunosPorTurma(Integer id, Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<AlunoVO> alunos = new ArrayList<>();
        sql.append("select * from alunos where CD_TURMA = ?");
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

    public boolean validaProfessorParaTurma(Integer idProfessor, Integer idTurma, Connection con) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
       
        sql.append("select 1  from turmas");
        sql.append(" where CD_TURMA = ? AND CD_PROF = ?");
        Boolean professorValido = false;
        try{
             pst = con.prepareStatement(sql.toString());
            pst.setInt(1, idTurma);
            pst.setInt(2, idProfessor);


            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                professorValido = true;
            }

            

            
            return professorValido;
        }finally{
            con.close();
            pst.close();
        }
    }

    public List<ProfessorVO> getProfessores(Connection connection) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<ProfessorVO> professores = new ArrayList<>();
        sql.append("select * from professores");
        try{
             pst = connection.prepareStatement(sql.toString());


            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ProfessorVO professor = new ProfessorVO();
                professor.setEmail(rs.getString("TX_EMAIL"));
                professor.setNome(rs.getString("TX_NOME"));
                professor.setId(rs.getInt("CD_PROFESSOR"));
                professores.add(professor);
            }

            return professores;
        }finally{
            connection.close();
            pst.close();
        }
    }

    public AlunoVO getAlunoPorId(Connection con, Integer codAluno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        sql.append("select * from alunos where cd_aluno = ?");
        
        AlunoVO aluno = new AlunoVO();
        try{
             pst = con.prepareStatement(sql.toString());
             pst.setInt(1, codAluno);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               
                aluno.setEmailResponsavel(rs.getString("TX_EMAIL"));
                aluno.setNome(rs.getString("TX_NOME"));
                aluno.setId(rs.getInt("CD_ALUNO"));
           
            }

            return aluno;
        }finally{
            con.close();
            pst.close();
        }
    }

    public ProfessorVO getProfessorPorId(Connection con, Integer codProf) throws SQLException {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        ProfessorVO professor = new ProfessorVO();
        sql.append("select * from professores where cd_professor = ?");
        try{
             pst = con.prepareStatement(sql.toString());
            pst.setInt(1, codProf);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               
                professor.setEmail(rs.getString("TX_EMAIL"));
                professor.setNome(rs.getString("TX_NOME"));
                professor.setId(rs.getInt("CD_PROFESSOR"));
                
            }

            return professor;
        }finally{
            con.close();
            pst.close();
        }
    }

    public void registraFalta(Connection con, FaltaVO falta) throws SQLException {

        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
      
       sql.append(" insert into  faltas" );
       sql.append(" (CD_ALUNO, CD_PROF, TX_JUSTIFICATIVA, DT_FALTA) " );
       sql.append(" VALUES" );
       sql.append(" (?,?,?, current_date())" );
        try{
             pst = con.prepareStatement(sql.toString());
            pst.setInt(1, falta.getCodAluno());
            pst.setInt(2, falta.getCodProf());
            pst.setString(3, falta.getJustificativa());
           
            pst.executeUpdate();
            

            
        }finally{
            con.close();
            pst.close();
        }
    }

    
    
}
