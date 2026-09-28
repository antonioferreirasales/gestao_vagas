package br.com.sales.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sales.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.sales.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.sales.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        CandidateEntity candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found.");
        });
        ProfileCandidateResponseDTO candidateProfileDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .build();
        return candidateProfileDTO;
    }
}
