package com.escola.business;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.TurmaDAO;
import com.escola.model.TurmaRequestVO;
import com.escola.model.TurmaVO;

public class TurmaFacade {
    static final Logger logger = LoggerFactory.getLogger(TurmaFacade.class);

    public List<TurmaVO> getTurmas() {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            Connection con = ConnectionFactory.getConnection();

            return turmaDAO.getTurmas(con);
        } catch (Exception e) {
            logger.error("Erro ao buscar turmas", e);
        }
        return null;
    }

    public Integer addTurma(TurmaRequestVO turma) throws Exception {

        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            Connection con = ConnectionFactory.getConnection();

            return turmaDAO.addTurma(turma, con);
        } catch (Exception e) {
            logger.error("Erro ao add turma", e);
            throw new Exception(e);
        }
    }

    // vincular professor a turma
    public void vinculaProfessorTurma(Integer idProfessor, Integer idTurma) throws Exception {

        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            Connection con = ConnectionFactory.getConnection();

            turmaDAO.vincularProfessorTurma(idProfessor, idTurma, con);
        } catch (Exception e) {
            logger.error("Erro ao vincular professor a turma", e);
            throw new Exception(e);
        }
    }
}
