package br.rodemarck.core.comandos.guild;

import br.rodemarck.core.MensagemGuildRecebida;
import br.rodemarck.core.comandos.generico.Ping;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.LinkedList;

public class PingGuild extends Ping implements MensagemGuildRecebida {

    @Override
    public boolean livre(LinkedList<String> args, GuildMessageReceivedEvent event) {
        return true;
    }

    @Override
    public void executa(LinkedList<String> args, GuildMessageReceivedEvent event) {
        super.executa(event.getChannel(), event.getJDA().getGatewayPing());
    }
}
