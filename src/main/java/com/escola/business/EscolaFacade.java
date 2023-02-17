package com.escola.business;

import java.sql.Connection;
import java.util.Collections;
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
    public List<AlunoVO> getAlunosPorTurma(Integer idTurma, Integer idProfessor) throws ProfessorInvalidoException{
        try{
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            
           
            
            if(validaProfessorParaTurma(idProfessor,idTurma)){
                throw new ProfessorInvalidoException();
            }

            return escolaDAO.getAlunosPorTurma(idTurma, con);
        }catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        


        return null;
    }
    private boolean validaProfessorParaTurma(Integer idProfessor, Integer idTurma) {

        try{
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            return escolaDAO.validaProfessorParaTurma(idProfessor, idTurma, con);
        }
        catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        
        return false;
    }
    
}
