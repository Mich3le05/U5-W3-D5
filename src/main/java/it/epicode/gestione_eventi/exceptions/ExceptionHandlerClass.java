package it.epicode.gestione_eventi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    public static class NonAutorizzatoException extends RuntimeException {
        public NonAutorizzatoException(String message) {
            super(message);
        }
    }

    public static class UtenteNonTrovatoException extends RuntimeException {
        public UtenteNonTrovatoException(String message) {
            super(message);
        }
    }

    public static class EventoNonTrovatoException extends RuntimeException {
        public EventoNonTrovatoException(String message) {
            super(message);
        }
    }

    public static class EventoPienoException extends RuntimeException {
        public EventoPienoException(String message) {
            super(message);
        }
    }

    public static class EventoPresenteException extends RuntimeException {
        public EventoPresenteException(String message) {
            super(message);
        }
    }

    public static class PartecipazionePresenteException extends RuntimeException {
        public PartecipazionePresenteException(String message) {
            super(message);
        }
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestHandler(BadRequestException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> ExceptionHandler(Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonAutorizzatoException.class)
    public ResponseEntity<Object> NonAutorizzatoHandler(NonAutorizzatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UtenteNonTrovatoException.class)
    public ResponseEntity<Object> UtenteNonTrovatoHandler(UtenteNonTrovatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoNonTrovatoException.class)
    public ResponseEntity<Object> EventoNonTrovatoHandler(EventoNonTrovatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoPienoException.class)
    public ResponseEntity<Object> EventoPienoHandler(EventoPienoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventoPresenteException.class)
    public ResponseEntity<Object> EventoPresenteHandler(EventoPresenteException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartecipazionePresenteException.class)
    public ResponseEntity<Object> PartecipazionePresenteHandler(PartecipazionePresenteException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
