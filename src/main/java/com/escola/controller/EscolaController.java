package com.escola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.AulaJaRealizadaException;
import com.escola.business.EscolaFacade;
import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;

import ch.qos.logback.classic.Logger;

@CrossOrigin(origins = "*")
@RequestMapping("/escola")
@RestController
public class EscolaController {
  Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(EscolaController.class);

  @GetMapping("/falta")
  public ResponseEntity<?> resgataFaltas(@RequestParam Integer idAluno) {
    try {
      EscolaFacade facade = new EscolaFacade();
      return ResponseEntity.status(200).body(facade.resgataFaltas(idAluno));
    } catch (Exception e) {
      logger.info("Erro ao resgatar faltas");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao resgatar falta");
    }
  }

  @PostMapping("/falta")
  public ResponseEntity postFalta(@RequestBody FaltaVO falta) {
    try {
      if (falta == null || falta.getAlunos() == null || falta.getAlunos().isEmpty()
          || falta.getCodHorario() == null) {
        return ResponseEntity.badRequest().body("Dados enviados inválidos");
      } else {
        EscolaFacade facade = new EscolaFacade();
        facade.registraAula(falta.getCodHorario(), falta.getDataFalta());
        // ProfessorVO professor =
        // professorFacade.obtemProfessorPorId(falta.getCodProf());
        facade.registraFalta(falta, falta.getDataFalta());
        return ResponseEntity.status(HttpStatus.CREATED).body("Falta registrada");
      }
    } catch (AulaJaRealizadaException e) {
      logger.info("Aula já realizada");
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Aula já realizada");
    }

    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/disciplinas")
  public ResponseEntity getDisciplinas() {
    EscolaFacade facade = new EscolaFacade();

    List<DisciplinaVO> disciplinas = facade.getDisciplinas();
    return ResponseEntity.ok().body(disciplinas);

  }

}
