package br.com.sales.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sales.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.sales.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.sales.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {
    private final AuthCandidateUseCase authCandidateUseCase;

    AuthCandidateController(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            AuthCandidateResponseDTO token = authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário/senha não encontrado.");
        }
    }
}
