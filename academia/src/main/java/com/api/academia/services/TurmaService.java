package com.api.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.academia.entities.Turma;
import com.api.academia.repositories.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
    TurmaRepository turmaRepo;

    public List<Turma> listarTurmas() {
        return turmaRepo.findAll();
    }

    public Turma buscarIdTurma(Integer id) {
        return turmaRepo.findById(id).orElse(null);
    }

    public Turma salvarTurma(Turma turma) {
        return turmaRepo.save(turma);
    }

    public Turma atualizarTurma(Turma turma) {
        return turmaRepo.save(turma);
    }

    public Boolean deletarTurma(Turma turma) {
        if (turma == null)
            return false;

        Turma turmaExistente = buscarIdTurma(turma.getIdTurma());

        if (turmaExistente == null)
            return false;

        turmaRepo.delete(turma);

        Turma turmaExiste = buscarIdTurma(turma.getIdTurma());

        if (turmaExiste == null)
            return true;

        return false;

    }

}
