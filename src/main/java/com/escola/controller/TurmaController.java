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

import com.escola.business.TurmaFacade;
import com.escola.model.TurmaRequestVO;
import com.escola.model.TurmaVO;

@CrossOrigin(origins = "*")
@RequestMapping("/turma")
@RestController
public class TurmaController {

    @GetMapping("/all")
    public ResponseEntity getTurmas() {
        TurmaFacade facade = new TurmaFacade();

        List<TurmaVO> turmas = facade.getTurmas();
        return ResponseEntity.ok().body(turmas);

    }

    // add turma
    @PostMapping("/add")
    public ResponseEntity addTurma(@RequestBody TurmaRequestVO turma) {
        try {
            if (turma == null || turma.getAno() == null || turma.getAno().isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                TurmaFacade facade = new TurmaFacade();
                Integer idTurma = facade.addTurma(turma);
                facade.vinculaProfessorTurma(turma.getIdProfessor(), idTurma);
                return ResponseEntity.status(HttpStatus.CREATED).body("Turma adicionada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
