package br.rodemarck.core.comandos.guild.campominado;

import br.rodemarck.Repositorio;
import br.rodemarck.core.MensagemGuildRecebida;
import br.rodemarck.model.Campo;
import lombok.extern.java.Log;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;
import java.util.LinkedList;

@Log
public class MostrarCampo implements MensagemGuildRecebida {
    @Override
    public boolean livre(LinkedList<String> args, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void executa(LinkedList<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        try{
            Campo campo = CampoMinadoHelper.mostra(event);
            channel.sendMessage(campo.print()).queue(m->{
                campo.setLastMessage(m.getIdLong());
                try {
                    CampoMinadoHelper.setCampo(event,campo);
                } catch (IOException e) {
                    channel.sendMessage("erro no sistema");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            channel.sendMessage(e.getMessage()).queue();
        }
    }
}
