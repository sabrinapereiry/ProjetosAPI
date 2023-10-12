package com.api.academia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.academia.entities.Turma;
import com.api.academia.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	@Autowired
    TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas(){
        return new 
                ResponseEntity<>(turmaService.listarTurmas(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarId(@PathVariable Integer id) {
        Turma turma = turmaService.buscarIdTurma(id);

        if(turma == null)
            return new 
                ResponseEntity<>(turma,HttpStatus.NOT_FOUND);
        else
            return new 
                    ResponseEntity<>(turma,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Turma> salvar(@RequestBody Turma turma) {
        return new
                ResponseEntity<>(turmaService.salvarTurma(turma),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Turma> atualizar(@RequestBody Turma turma) {
        return new
                ResponseEntity<>(turmaService.atualizarTurma(turma), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarTurma(@RequestBody Turma turma) {
        if(turmaService.deletarTurma(turma))
            return new
                    ResponseEntity<>("{\"msg\":\"\"Deletado com Sucesso\"}", HttpStatus.OK);
            else
                return new
                        ResponseEntity<>("{\"msg\":\"\"Não foi possível deletar\"}", HttpStatus.BAD_REQUEST);
    }
}
