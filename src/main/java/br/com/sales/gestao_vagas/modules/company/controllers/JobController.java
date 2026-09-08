package br.com.sales.gestao_vagas.modules.company.controllers;

import br.com.sales.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sales.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.sales.gestao_vagas.modules.company.entities.JobEntity;
import br.com.sales.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobRepository jobRepository;
    @Autowired
    CreateJobUseCase createJobUseCase;

    JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        try {
            UUID companyId = UUID.fromString(request.getAttribute("company_id").toString());
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.benefits())
                    .companyId(companyId)
                    .description(createJobDTO.description())
                    .level(createJobDTO.level())
                    .build();

            return ResponseEntity.ok(this.createJobUseCase.execute(jobEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
