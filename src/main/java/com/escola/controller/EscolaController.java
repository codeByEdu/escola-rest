package com.escola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorInvalidoException;
import com.escola.model.AlunoVO;

@RestController
public class EscolaController {
  @GetMapping("/all")
  public ResponseEntity<List<AlunoVO>> getAlunosPorTurma(@RequestParam Integer idTurma, @RequestParam Integer idProf) {
    EscolaFacade facade = new EscolaFacade();
    try {
     
      return ResponseEntity.ok().body(facade.getAlunosPorTurma(idTurma, idProf));
    } catch (ProfessorInvalidoException e) {
      return ResponseEntity.notFound().build();
    }

  }

}
