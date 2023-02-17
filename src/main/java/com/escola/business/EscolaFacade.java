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

    final static Logger logger = LoggerFactory.getLogger(EscolaFacade.class);
    public List<AlunoVO> getAlunosPorAula(Integer id) {
        try{
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            return escolaDAO.getAlunosPorAula(id, con);
            // if(id!= null){
            //     return null;
            // }
        }catch(Exception e ){
            logger.error("erro ao buscar alunos dessa sala", e);
        }
        


        return null;
    }
    
}
