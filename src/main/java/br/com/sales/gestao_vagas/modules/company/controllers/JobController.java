package br.com.sales.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sales.gestao_vagas.modules.company.entities.JobEntity;
import br.com.sales.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    CreateJobUseCase createJobUseCase;

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
        try {
            UUID companyId = UUID.fromString(request.getAttribute("company_id").toString());
            jobEntity.setCompanyId(companyId);
            var result = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
