package com.escola.business;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.ProfessorDAO;
import com.escola.domain.ProfessorDAO;
import com.escola.model.ProfessorVO;
import com.escola.model.TipoVO;

public class ProfessorFacade {

    static final Logger logger = LoggerFactory.getLogger(ProfessorFacade.class);

    public List<TipoVO> getTipoProfessor() {
        try {
            ProfessorDAO ProfessorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            return ProfessorDAO.getTiposProfessor(con);
        } catch (Exception e) {
            logger.error("Erro ao buscar tipos de professor", e);
        }
        return null;
    }

    public ProfessorVO obtemProfessorPorId(Integer codProf) {
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            return professorDAO.getProfessorPorId(con, codProf);
        } catch (Exception e) {
            logger.error("Erro ao buscar professor", e);
        }
        return null;
    }

    public void validaProfessorParaTurma(Integer idProfessor, Integer idTurma) throws ProfessorInvalidoException {

        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();
            if (!professorDAO.validaProfessorParaTurma(idProfessor, idTurma, con)) {
                throw new ProfessorInvalidoException();
            }
        } catch (ProfessorInvalidoException e) {
            throw new ProfessorInvalidoException();
        } catch (Exception e) {
            logger.error("erro ao buscar alunos dessa sala", e);
        }

    }

    public List<ProfessorVO> getProfessores() {
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            return professorDAO.getProfessores(con);
        } catch (Exception e) {
            logger.error("Erro ao buscar professores", e);
        }
        return null;
    }

    public void addProfessor(ProfessorVO prof) throws Exception {

        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            professorDAO.addProfessor(prof, con);
        } catch (Exception e) {
            logger.error("Erro ao add professor", e);
            throw new Exception(e);
        }
    }

    public void removeProfessor(Long idProf) throws Exception {

        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            professorDAO.deleteProfessor(idProf, con);
        } catch (Exception e) {
            logger.error("Erro ao remover professor", e);
            throw new Exception(e);
        }
    }

    public void updateProfessor(ProfessorVO prof) {
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            Connection con = ConnectionFactory.getConnection();

            professorDAO.updateProfessor(prof, con);
        } catch (Exception e) {
            logger.error("Erro ao atualizar professor", e);
        }
    }

}
