package br.rodemarck.core;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.LinkedList;

public interface MensagemGuildRecebida {
    boolean livre (LinkedList<String> args, GuildMessageReceivedEvent event);
    void executa(LinkedList<String> args, GuildMessageReceivedEvent event);
}
