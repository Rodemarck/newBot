package br.rodemarck.core.comandos.guild.campominado;

import br.rodemarck.Repositorio;
import br.rodemarck.model.Campo;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

public class CampoMinadoHelper {
    public static Campo mostra(GuildMessageReceivedEvent event) throws IOException, ClassNotFoundException {
        String menber = event.getMember().getId();
        String guild = event.getGuild().getId();
        Campo campo = Repositorio.get(guild,menber);
        event.getChannel().deleteMessageById(event.getMessageIdLong()).queue();
        return campo;
    }
    public static void setCampo(GuildMessageReceivedEvent event, Campo campo) throws IOException {
        String menber = event.getMember().getId();
        String guild = event.getGuild().getId();
        Repositorio.set(guild,menber, campo);
    }
    public static void deleteCampo(GuildMessageReceivedEvent event){
        String menber = event.getMember().getId();
        String guild = event.getGuild().getId();
        Repositorio.delete(guild,menber);
    }
}
