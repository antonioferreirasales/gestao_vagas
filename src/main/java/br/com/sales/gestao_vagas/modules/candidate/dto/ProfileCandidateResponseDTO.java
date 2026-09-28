package br.com.sales.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record ProfileCandidateResponseDTO(String username, String email, String description, UUID id, String name) {
}