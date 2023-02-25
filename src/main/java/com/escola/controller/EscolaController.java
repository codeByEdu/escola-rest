package com.escola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorInvalidoException;
import com.escola.config.Constants;
import com.escola.model.AlunoVO;
import com.escola.model.FaltaVO;
import com.escola.model.ProfessorVO;

import ch.qos.logback.classic.Logger;

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
  public ResponseEntity getProfessorres() {
    
    try {
      EscolaFacade facade = new EscolaFacade();
      List<ProfessorVO> professores = facade.getProfessores();
      return ResponseEntity.ok().body(professores);
    } 
    catch(Exception e ){
      return null;
    }
   

  }

  @PostMapping("/falta")
  public ResponseEntity postFalta(@RequestBody FaltaVO falta){
   try {
    if(falta == null || falta.getCodAluno() == null || falta.getCodProf() == null ){
      return ResponseEntity.badRequest().build();
    }else{
      EscolaFacade facade = new EscolaFacade();
      AlunoVO aluno = facade.obtemAlunoPorId(falta.getCodAluno());
      ProfessorVO professor = facade.obtemProfessorPorId(falta.getCodProf());
      if(professor == null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.PROFESSOR_NAO_ENCONTRADO_TEXTO);
      }
      else if(aluno == null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.ALUNO_NAO_ENCONTRADO_TEXTO);
      }else{
          facade.registraFalta(falta);
          return ResponseEntity.status(HttpStatus.CREATED).body("Falta registrada");

      }
    }




   } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
   }
    
  }



}
