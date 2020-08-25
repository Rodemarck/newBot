package br.rodemarck.command;

import br.rodemarck.Repositorio;
import br.rodemarck.model.Campo;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.IOException;

public class Jogar extends Command {
    public Jogar() {
        super.name = "null";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("sexo");
        /*
        String menber = event.getMember().getId();
        String guild = event.getGuild().getId();
        String s = event.getArgs();
        try {
            Campo campo = Repositorio.get(guild,menber);
            String texto = campo.joga(s);
            event.reply(texto);
            if (campo.isAcabado()) {
                event.reply("morreu cara...");
                Repositorio.delete(guild, menber);
            }
            else {
                Repositorio.set(guild, menber, campo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.reply(e.getMessage());
        }*/
    }
}
