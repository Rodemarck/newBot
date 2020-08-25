package br.rodemarck.core;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.LinkedList;

public interface MensagemPrivadaRecebida {
    boolean livre (LinkedList<String> args, PrivateMessageReceivedEvent event);
    void executa(LinkedList<String> args, PrivateMessageReceivedEvent event);
}
