package br.com.indra.eduardo_bacchiega.user.dto;

import lombok.Builder;

@Builder
public record UserCreatedResponseDto(String name, String email) {
}
