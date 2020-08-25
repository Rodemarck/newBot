package br.rodemarck.core.comandos.generico;

import jdk.jfr.Event;
import net.dv8tion.jda.api.entities.TextChannel;

public class Ping {
    public void executa (TextChannel channel, long ping){
        channel.sendMessage("** o ping é de mais de **" + ping + "**!!").queue();
    }
}
