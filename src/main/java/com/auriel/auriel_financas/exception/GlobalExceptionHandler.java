// package com.auriel.auriel_financas.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import java.util.HashMap;
// import java.util.Map;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
//         Map<String, String> response = new HashMap<>();
//         response.put("error", ex.getMessage());
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
//         Map<String, String> response = new HashMap<>();
//         response.put("error", "Ocorreu um erro inesperado");
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//     }
// }
