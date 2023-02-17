package com.escola.controller; 

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.escola.business.EscolaFacade;
import com.escola.model.AlunoVO;




@RestController
public class EscolaController {
  @GetMapping("/all")
  public List<AlunoVO> getAlunosPorAula(@RequestParam String id) {
    EscolaFacade facade = new EscolaFacade();
    
    List<AlunoVO> alunos = facade.getAlunosPorAula(id);

    return alunos;
  }


}
