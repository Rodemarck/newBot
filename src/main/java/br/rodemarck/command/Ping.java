package br.rodemarck.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {
    public Ping() {
        super.name = "ping";
        super.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("ping é de mais de **" + event.getJDA().getGatewayPing() + "**!!!");
    }
}
