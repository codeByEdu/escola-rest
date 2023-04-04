package com.escola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.model.ProfessorVO;
import com.escola.model.TipoVO;

@CrossOrigin(origins = "*")
@RequestMapping("/professor")
@RestController
public class ProfessorController {
  @GetMapping("/all")
  public ResponseEntity getProfessorres() {

    try {
      EscolaFacade facade = new EscolaFacade();
      List<ProfessorVO> professores = facade.getProfessores();
      return ResponseEntity.ok().body(professores);
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/tipos")
  public ResponseEntity getTiposProfessor() {
    EscolaFacade facade = new EscolaFacade();

    List<TipoVO> tipos = facade.getTipoProfessor();
    return ResponseEntity.ok().body(tipos);

  }
}
