package br.com.jps.cervejariaapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ErrorMessageException extends RuntimeException{

    public static final long serialVersionUUID = 1L;
    public ErrorMessageException(String message) {
        super(message);
    }

    @Getter
    @AllArgsConstructor
    public enum Message{
        CLIENT_PROFILE_DISABLED("Cliente não pode ser atualizado, perfil está desabilitado."),
        CLIENT_DOCUMENT_EMPTY("Não temos cliente com esse documento."),
        CLIENT_NOT_FOUND("Cliente não encontrado: "),
        ADDRESS_EXCEEDED_LIMIT("Endereço não pode ser cadastrado, limite excedido. "),
        ADDRESS_NOT_UPDATE("Endereço não pode ser atualizado, houve um erro de processamento."),
        ADDRESS_NOT_FOUND("Endereço não encontrado."),
        ADDRESS_NOT_DELETED("Endereço não apagado, verifique as informações e tente novamente."),
        CONTENT_NOT_FOUND("Conteúdo não encontrado."),
        PRODUCT_NOT_FOUND("Produto não encontrado: "),
        CATEGORY_NOT_DELETED("Categoria não pode ser apagada."),
        CATEGORY_NOT_FOUND("Categoria não encontrada, verifique as informações e tente novamente."),
        STOCK_NOT_FOUND ("Produto não encontrado em nosso estoque."),
        STOCK_NOT_DELETED("Produto não pode ser deletado pois ainda tem unidades disponíveis"),
        ORDER_NOT_FOUND("Pedido não pode ser encontrado.");

        private String message;
    }

}
