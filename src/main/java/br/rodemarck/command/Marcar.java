package br.rodemarck.command;

import br.rodemarck.Repositorio;
import br.rodemarck.model.Campo;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Marcar extends Command {
    public Marcar() {
        super.name = "marcar";
    }

    @Override
    protected void execute(CommandEvent event) {
        String menber;
        if (event.getArgs().contains("public"))
            menber = "";
        else
            menber = event.getMember().getId();

        String guild = event.getGuild().getId();
        String s = event.getArgs();
        try {
            Campo campo = Repositorio.get(guild,menber);
            campo.marca(s);
            String texto = campo.print();
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
        }
    }
}
