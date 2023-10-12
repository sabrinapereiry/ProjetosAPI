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

import com.api.academia.entities.Telefone;
import com.api.academia.services.TelefoneService;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
	@Autowired
    TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<Telefone>> listarTelefones(){
        return new 
                ResponseEntity<>(telefoneService.listarTelefones(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> buscarId(@PathVariable Integer id) {
        Telefone telefone = telefoneService.buscarIdTelefone(id);

        if(telefone == null)
            return new 
                ResponseEntity<>(telefone,HttpStatus.NOT_FOUND);
        else
            return new 
                    ResponseEntity<>(telefone,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Telefone> salvar(@RequestBody Telefone telefone) {
        return new
                ResponseEntity<>(telefoneService.salvarTelefone(telefone),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Telefone> atualizar(@RequestBody Telefone telefone) {
        return new
                ResponseEntity<>(telefoneService.atualizarTelefone(telefone), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarTelefone(@RequestBody Telefone telefone) {
        if(telefoneService.deletarTelefone(telefone))
            return new
                    ResponseEntity<>("{\"msg\":\"\"Deletado com Sucesso\"}", HttpStatus.OK);
            else
                return new
                        ResponseEntity<>("{\"msg\":\"\"Não foi possível deletar\"}", HttpStatus.BAD_REQUEST);
    }
}
