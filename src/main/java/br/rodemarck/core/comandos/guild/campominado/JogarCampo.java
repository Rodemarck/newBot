package br.rodemarck.core.comandos.guild.campominado;

import br.rodemarck.core.MensagemGuildRecebida;
import br.rodemarck.model.Campo;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.LinkedList;

public class JogarCampo implements MensagemGuildRecebida {
    @Override
    public boolean livre(LinkedList<String> args, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void executa(LinkedList<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        try{
            Campo campo = CampoMinadoHelper.mostra(event);

            String jogada = args.getFirst().length()==2? args.getFirst():args.get(1);
            String texto = campo.joga(jogada);

            channel.editMessageById(campo.getLastMessage(),texto).queue();
            if (campo.isAcabado()) {
                channel.sendMessage("vc perdeu cara, só aceita").queue();
                CampoMinadoHelper.deleteCampo(event);
            }
            else {
                CampoMinadoHelper.setCampo(event, campo);
            }
        }catch (Exception e){
            channel.sendMessage(e.getMessage()).queue();
        }

    }
}
