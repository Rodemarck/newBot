package br.rodemarck.controller;

import br.rodemarck.core.Executador;
import br.rodemarck.utilitarios.Constantes;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Controlador extends ListenerAdapter {
    private boolean pre(Message message){
        return message.getContentRaw().startsWith(Constantes.PREFIXO);
    }
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().getTextChannelById(568241769456599044L).sendMessage("estou on Caralho").queue();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && pre(event.getMessage()))
            Executador.interpreta(event);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && pre(event.getMessage()))
            Executador.interpreta(event);
    }
}
