package br.com.sales.gestao_vagas.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sales.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {
    private final JWTCandidateProvider jwtCandidateProvider;

    SecurityCandidateFilter(JWTCandidateProvider jwtCandidateProvider) {
        this.jwtCandidateProvider = jwtCandidateProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println(!request.getServletPath().startsWith("/candidate"));
        return !request.getServletPath().startsWith("/candidate");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");
        if (header != null) {
            var token = this.jwtCandidateProvider.validateToken(header);
            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            request.setAttribute("candidate_id", token.getSubject());
        }

        filterChain.doFilter(request, response);
    }

}
