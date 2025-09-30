package com.auriel.auriel_financas.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalException extends RuntimeException {
    private GlobalException(String message) {
        super(message);
    }

    public static GlobalException usuarioNaoEncontrado() {
        return new GlobalException("Usuário não encontrado");
    }

    public static GlobalException emailNaoEncontrado() {
        return new GlobalException("Email não encontrado");
    }

    public static GlobalException emailJaCadastrado() {
        return new GlobalException("Email já cadastrado");
    }

    public static GlobalException senhaInvalida() {
        return new GlobalException("Senha inválida. A senha deve ter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais.");
    }

    public static GlobalException ativoNaoEncontrado() {
        return new GlobalException("Ativo não encontrado");
    }
}
