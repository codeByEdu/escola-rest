package com.escola.business;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.EscolaDAO;
import com.escola.model.AlunoVO;

public class EscolaFacade {

    public EscolaFacade EscolaFacade(){
        return this;
    }

    static final Logger logger = LoggerFactory.getLogger(EscolaFacade.class);
    public List<AlunoVO> getAlunosPorTurma(Integer idTurma, Integer idProfessor) throws ProfessorInvalidoException {
        try{
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            validaProfessorParaTurma(idProfessor,idTurma);
            return escolaDAO.getAlunosPorTurma(idTurma, con);
        }catch(ProfessorInvalidoException e){
            logger.info("Professor: "+ idProfessor +" não tem acesso a turma: "+idTurma);
            throw new ProfessorInvalidoException();
        }
        catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        return null;
    }
    private void validaProfessorParaTurma(Integer idProfessor, Integer idTurma) throws ProfessorInvalidoException{

        try{
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            if(!escolaDAO.validaProfessorParaTurma(idProfessor, idTurma, con)){
                throw new ProfessorInvalidoException();
            }
        }
        catch(ProfessorInvalidoException e ){
            throw new ProfessorInvalidoException();
        }
        catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }

    
    }
    
}
