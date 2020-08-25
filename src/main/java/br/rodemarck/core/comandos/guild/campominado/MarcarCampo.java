package br.rodemarck.core.comandos.guild.campominado;

import br.rodemarck.command.CampoMinado;
import br.rodemarck.core.MensagemGuildRecebida;
import br.rodemarck.model.Campo;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.LinkedList;

public class MarcarCampo implements MensagemGuildRecebida {
    @Override
    public boolean livre(LinkedList<String> args, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void executa(LinkedList<String> args, GuildMessageReceivedEvent event) {
        System.out.println("marca\n?\n?\n?\n?\n?");
        TextChannel channel = event.getChannel();
        try{
            Campo campo = CampoMinadoHelper.mostra(event);
            String jogada = args.getFirst().length()==2? args.getFirst():args.get(1);
            campo.marca(jogada);
            CampoMinadoHelper.setCampo(event,campo);
            channel.editMessageById(campo.getLastMessage(), campo.print()).queue();
        }catch (Exception e){
            e.printStackTrace();
            channel.sendMessage(e.getMessage()).queue();
        }
    }
}
