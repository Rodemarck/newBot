package br.rodemarck.command;

import br.rodemarck.Repositorio;
import br.rodemarck.model.Campo;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.extern.java.Log;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.File;
import java.io.IOException;

public class CampoMinado extends Command {
    public CampoMinado() {
        super.name = "campo";
    }

    @Override
    protected void execute(CommandEvent event) {
        String menber = event.getMember().getId();
        String guild = event.getGuild().getId();
        try{
            Campo campo = Repositorio.get(guild,menber);
            if(campo == null) {
                event.reply("tabuleiro está sendo criado");
                campo = new Campo(10,10);
                Repositorio.set(guild,menber,campo);
            }
            event.reply(campo.print());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
