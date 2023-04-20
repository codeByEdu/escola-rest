package com.escola.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.business.ProfessorFacade;
import com.escola.model.ProfessorVO;
import com.escola.model.TipoVO;

@CrossOrigin(origins = "*")
@RequestMapping("/professor")
@RestController
public class ProfessorController {
  @GetMapping("/all")
  public ResponseEntity getProfessorres() {

    try {
      ProfessorFacade facade = new ProfessorFacade();
      List<ProfessorVO> professores = facade.getProfessores();
      return ResponseEntity.ok().body(professores);
    } catch (Exception e) {
      return null;
    }
  }

  @GetMapping("/tipos")
  public ResponseEntity getTiposProfessor() {
    ProfessorFacade facade = new ProfessorFacade();

    List<TipoVO> tipos = facade.getTipoProfessor();
    return ResponseEntity.ok().body(tipos);

  }

  @PostMapping("/add")
  public ResponseEntity addProfessor(@RequestBody ProfessorVO prof) {

    try {
      ProfessorFacade facade = new ProfessorFacade();
      facade.addProfessor(prof);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  @DeleteMapping("/{idProf}")
  public ResponseEntity removeProfessor(@PathVariable Long idProf) {

    try {
      ProfessorFacade facade = new ProfessorFacade();
      facade.removeProfessor(idProf);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  // update professor
  @PatchMapping("/patch")
  public ResponseEntity updateProfessor(@RequestBody ProfessorVO prof) {

    try {
      ProfessorFacade facade = new ProfessorFacade();
      facade.updateProfessor(prof);

      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }
}
