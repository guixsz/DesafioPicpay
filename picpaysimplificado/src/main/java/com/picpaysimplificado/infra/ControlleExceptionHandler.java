package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExceptcionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlleExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptcionDTO exceptcionDTO = new ExceptcionDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptcionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity threat404(EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptcionDTO exceptcionDTO = new ExceptcionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptcionDTO);
    }


}
