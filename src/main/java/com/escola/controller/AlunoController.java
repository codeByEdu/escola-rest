package com.escola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.AlunoFacade;
import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorInvalidoException;
import com.escola.model.AlunoVO;

@CrossOrigin(origins = "*")
@RequestMapping("/aluno")
@RestController
public class AlunoController {
  @GetMapping("/class")
  public ResponseEntity getAlunosPorTurma(@RequestParam Integer idTurma, @RequestParam Integer idProf) {

    try {
      AlunoFacade facade = new AlunoFacade();
      List<AlunoVO> alunos = facade.getAlunosPorTurma(idTurma, idProf);
      return ResponseEntity.ok().body(alunos);
    } catch (ProfessorInvalidoException e) {
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("Professor não tem acesso para esta turma");
    }
  }

  @GetMapping("/class/all")
  public ResponseEntity getAlunosPorTurma(@RequestParam Integer idTurma) {

    try {
      AlunoFacade facade = new AlunoFacade();
      List<AlunoVO> alunos = facade.getAlunosPorTurma(idTurma);
      return ResponseEntity.ok().body(alunos);
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Erro ao buscar alunos dessa turma");
    }
  }

  @GetMapping("/name")
  public ResponseEntity getAlunoPorNome(@RequestParam String nome) {
    AlunoFacade facade = new AlunoFacade();
    AlunoVO alunos = facade.getAlunoPorNome(nome);
    return ResponseEntity.ok().body(alunos);

  }

}
