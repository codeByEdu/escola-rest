package com.escola.business;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.AlunoDAO;
import com.escola.domain.EscolaDAO;
import com.escola.model.AlunoVO;

public class AlunoFacade {

    static final Logger logger = LoggerFactory.getLogger(AlunoFacade.class);
    public AlunoVO getAlunoPorNome(String nome) {
        try {
            AlunoDAO alunoDAO = new AlunoDAO();
            Connection con = ConnectionFactory.getConnection();

            return alunoDAO.getAlunoPorNome(con, nome);
        } catch (Exception e) {
            logger.error("Erro ao buscar aluno", e);
        }
        return null;
    }
    public List<AlunoVO> getAlunosPorTurma(Integer idTurma, Integer idProfessor) throws ProfessorInvalidoException {
        try{
            EscolaFacade escolaFacade = new EscolaFacade();
            AlunoDAO alunoDAO = new AlunoDAO();
            Connection con = ConnectionFactory.getConnection();
            escolaFacade.validaProfessorParaTurma(idProfessor,idTurma);
            return alunoDAO.getAlunosPorTurma(idTurma, con);
        }catch(ProfessorInvalidoException e){
            logger.info("Professor: "+ idProfessor +" não tem acesso a turma: "+idTurma);
            throw new ProfessorInvalidoException();
        }
        catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        return null;
    }
}
