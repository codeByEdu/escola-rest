package com.escola.business;

import java.util.List;

import com.escola.domain.EscolaDAO;
import com.escola.model.AlunoVO;

public class EscolaFacade {

    public List<AlunoVO> getAlunosPorAula(String id) {
        try{
            EscolaDAO escolaDAO = new EscolaDAO();

            if(id!= null){
                return escolaDAO.getAlunosPorAula(id);
            }
        }finally{

        }
        


        return null;
    }
    
}
