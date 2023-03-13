package com.escola.business;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.EscolaDAO;
import com.escola.model.AlunoVO;
import com.escola.model.FaltaVO;
import com.escola.model.ProfessorVO;

public class EscolaFacade {


    static final Logger logger = LoggerFactory.getLogger(EscolaFacade.class);
    
    public void validaProfessorParaTurma(Integer idProfessor, Integer idTurma) throws ProfessorInvalidoException{

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
    public List<ProfessorVO> getProfessores() {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();

            return escolaDAO.getProfessores(con);
        } catch (Exception e) {
            logger.error("Erro ao buscar professores", e);
        }
        return null;
    }
    public AlunoVO obtemAlunoPorId(Integer codAluno) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();

            return escolaDAO.getAlunoPorId(con, codAluno);
        } catch (Exception e) {
            logger.error("Erro ao buscar aluno", e);
        }
        return null;
    }
    public ProfessorVO obtemProfessorPorId(Integer codProf) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();

            return escolaDAO.getProfessorPorId(con, codProf);
        } catch (Exception e) {
            logger.error("Erro ao buscar professor", e);
        }
        return null;
    }
    public void registraFalta(FaltaVO falta) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();

            escolaDAO.registraFalta(con, falta);
        } catch (Exception e) {
            logger.error("Erro ao registrar falta", e);
        }
     
    }
    
}
