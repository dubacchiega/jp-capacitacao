package br.com.indra.eduardo_bacchiega.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String name, String email) {
}
