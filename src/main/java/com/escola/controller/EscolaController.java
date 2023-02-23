package com.escola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorInvalidoException;
import com.escola.model.AlunoVO;
import com.escola.model.ProfessorVO;

@RestController
public class EscolaController {
  @GetMapping("/alunos")
  public ResponseEntity getAlunosPorTurma(@RequestParam Integer idTurma, @RequestParam Integer idProf) {
    
    try {
      EscolaFacade facade = new EscolaFacade();
      List<AlunoVO> alunos = facade.getAlunosPorTurma(idTurma, idProf);
      return ResponseEntity.ok().body(alunos);
    } catch (ProfessorInvalidoException e) {
      return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body("Professor não tem acesso para esta turma");
    }

  }

  @GetMapping("/professores")
  public ResponseEntity getProfessores() {
    
    try {
      EscolaFacade facade = new EscolaFacade();
      List<ProfessorVO> professores = facade.getProfessores();
      return ResponseEntity.ok().body(professores);
    } 
    catch(Exception e ){
      return null;
    }
   

  }

}
