package br.com.jps.cervejariaapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ClientException extends RuntimeException{

    public static final long serialVersionUUID = 1L;
    public ClientException(String message) {
        super(message);
    }

    @Getter
    @AllArgsConstructor
    public enum Message{
        CLIENT_PROFILE_DISABLED("Cliente não pode ser atualizado, perfil está desabilitado."),
        CLIENT_DOCUMENT_EMPTY("Não temos cliente com esse documento.");

        private String message;
    }

}
