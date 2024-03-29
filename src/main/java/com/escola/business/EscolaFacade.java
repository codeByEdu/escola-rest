package com.escola.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.escola.config.ConnectionFactory;
import com.escola.domain.AlunoDAO;
import com.escola.domain.EscolaDAO;
import com.escola.model.AlunoVO;
import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;
import com.escola.model.RelatorioFaltaVO;

public class EscolaFacade {

    static final Logger logger = LoggerFactory.getLogger(EscolaFacade.class);

    public AlunoVO obtemAlunoPorId(Integer codAluno) {
        try {
            AlunoDAO alunoDAO = new AlunoDAO();
            Connection con = ConnectionFactory.getConnection();

            return alunoDAO.getAlunoPorId(con, codAluno);
        } catch (Exception e) {
            logger.error("Erro ao buscar aluno", e);
        }
        return null;
    }

    public List<RelatorioFaltaVO> resgataFaltas(Integer idAluno) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            List<RelatorioFaltaVO> relatorios = escolaDAO.resgataFalta(idAluno);
            return relatorios;
        } catch (Exception e) {
            logger.error("Erro ao registrar falta", e);
        }
        return new ArrayList<>();
    }

    public void registraFalta(FaltaVO falta, Date date) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();

            for (AlunoVO aluno : falta.getAlunos()) {
                escolaDAO.registraFalta(aluno.getId(), falta.getCodHorario(), date);
            }

        } catch (Exception e) {
            logger.error("Erro ao registrar falta", e);
        }

    }

    public List<DisciplinaVO> getDisciplinas() {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();

            return escolaDAO.getDisciplinas(con);
        } catch (Exception e) {
            logger.error("Erro ao buscar disciplinas", e);
        }
        return null;
    }

    public void registraAula(Integer codHorario, Date date) throws AulaJaRealizadaException {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            Connection con = ConnectionFactory.getConnection();
            if (escolaDAO.validaAula(codHorario, date)) {
                throw new AulaJaRealizadaException("Aula já realizada");
            }

            escolaDAO.registraAula(con, codHorario, date);
        } catch (AulaJaRealizadaException e) {
            throw new AulaJaRealizadaException("Aula Ja Realizada");
        }

        catch (Exception e) {
            logger.error("Erro ao registrar aula", e);
        }
    }

    public List<RelatorioFaltaVO> resgataPorcentagemFaltas(Integer idAluno) {
        try {
            EscolaDAO escolaDAO = new EscolaDAO();
            List<RelatorioFaltaVO> relatorios = escolaDAO.resgataPorcentagemFalta(idAluno);
            return relatorios;
        } catch (Exception e) {
            logger.error("Erro ao registrar falta", e);
        }
        return new ArrayList<>();
    }

}
