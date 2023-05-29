package com.escola.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.escola.config.ConnectionFactory;
import com.escola.domain.AlunoDAO;
import com.escola.model.AlunoVO;
import com.escola.model.dto.AlunoDTO;

@Service
public class AlunoFacade {

    static final Logger logger = LoggerFactory.getLogger(AlunoFacade.class);

    public AlunoVO getAlunoPorNome(String nome) {
        try {
            Connection con = ConnectionFactory.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO();
            return alunoDAO.getAlunoPorNome(con, nome);
        } catch (Exception e) {
            logger.error("Erro ao buscar aluno", e);
        }
        return null;
    }

    public List<AlunoVO> getAlunosPorTurma(Integer idTurma, Integer idProfessor) throws ProfessorInvalidoException {
        try {
            ProfessorFacade professorFacade = new ProfessorFacade();
            Connection con = ConnectionFactory.getConnection();
            professorFacade.validaProfessorParaTurma(idProfessor, idTurma);
            AlunoDAO alunoDAO = new AlunoDAO();
            return alunoDAO.getAlunosPorTurma(idTurma, con);
        } catch (ProfessorInvalidoException e) {
            logger.info("Professor: " + idProfessor + " não tem acesso a turma: " + idTurma);
            throw new ProfessorInvalidoException();
        } catch (Exception e) {
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        return null;
    }

    public List<AlunoVO> getAlunosPorTurma(Integer idTurma) {
        try {
            Connection con = ConnectionFactory.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO();
            return alunoDAO.getAlunosPorTurma(idTurma, con);
        } catch (Exception e) {
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        return null;
    }

    public void updateAluno(Integer idAluno, AlunoDTO updateAluno) throws SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.updateAluno(con, idAluno, updateAluno);
    }

    public void addAluno(AlunoDTO aluno) {
        try {
            Connection con = ConnectionFactory.getConnection();
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.addAluno(con, aluno);
        } catch (Exception e) {
            logger.error("Erro ao adicionar aluno", e);
        }
    }
}
