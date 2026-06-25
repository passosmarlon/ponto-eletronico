package com.ponto.eletronico.user;

public record RegisterDTO(String login, String password, UserRole role, Long employeeId) {
}