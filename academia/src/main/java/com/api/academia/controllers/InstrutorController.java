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

import com.api.academia.entities.Instrutor;
import com.api.academia.services.InstrutorService;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
	@Autowired
    InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<Instrutor>> listarInstrutores(){
        return new 
                ResponseEntity<>(instrutorService.listarInstrutores(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> buscarId(@PathVariable Integer id) {
        Instrutor instrutor = instrutorService.buscarIdInstrutor(id);

        if(instrutor == null)
            return new 
                ResponseEntity<>(instrutor,HttpStatus.NOT_FOUND);
        else
            return new 
                    ResponseEntity<>(instrutor,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Instrutor> salvar(@RequestBody Instrutor instrutor) {
        return new
                ResponseEntity<>(instrutorService.salvarInstrutor(instrutor),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Instrutor> atualizar(@RequestBody Instrutor instrutor) {
        return new
                ResponseEntity<>(instrutorService.atualizarInstrutor(instrutor), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarInstrutor(@RequestBody Instrutor instrutor) {
        if(instrutorService.deletarInstrutor(instrutor))
            return new
                    ResponseEntity<>("{\"msg\":\"\"Deletado com Sucesso\"}", HttpStatus.OK);
            else
                return new
                        ResponseEntity<>("{\"msg\":\"\"Não foi possível deletar\"}", HttpStatus.BAD_REQUEST);
    }
}
