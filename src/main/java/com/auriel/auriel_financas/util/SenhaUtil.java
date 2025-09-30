package com.auriel.auriel_financas.util;

public class SenhaUtil {

    public static boolean validarCaracteresSenha(String senha) {     
        
        if (senha == null || senha.isEmpty()) {
            return false;            
        }
        
        return senha.length() >= 8 //8 caracteres
            && senha.matches(".*[A-Z].*") // pelo menos uma maiúscula
            && senha.matches(".*[^a-zA-Z0-9].*"); // pelo menos um caractere especial
    }

}
