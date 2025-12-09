package com.ponto.eletronico.user;

import org.apache.catalina.User;

public record RegisterDTO(String login, String password, UserRole role) {
}
