package com.escola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorFacade;
import com.escola.config.Constants;
import com.escola.model.AlunoVO;
import com.escola.model.DisciplinaVO;
import com.escola.model.FaltaVO;
import com.escola.model.ProfessorVO;

@CrossOrigin(origins = "*")
@RequestMapping("/escola")
@RestController
public class EscolaController {

  @PostMapping("/falta")
  public ResponseEntity postFalta(@RequestBody FaltaVO falta) {
    try {
      if (falta == null || falta.getCodAluno() == null || falta.getCodProf() == null) {
        return ResponseEntity.badRequest().build();
      } else {
        EscolaFacade facade = new EscolaFacade();
        ProfessorFacade professorFacade = new ProfessorFacade();
        AlunoVO aluno = facade.obtemAlunoPorId(falta.getCodAluno());
        ProfessorVO professor = professorFacade.obtemProfessorPorId(falta.getCodProf());
        if (professor == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.PROFESSOR_NAO_ENCONTRADO_TEXTO);
        } else if (aluno == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ALUNO_NAO_ENCONTRADO_TEXTO);
        } else {
          facade.registraFalta(falta);
          return ResponseEntity.status(HttpStatus.CREATED).body("Falta registrada");

        }
      }
    } catch (Exception e) {
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
