package br.rodemarck;

import br.rodemarck.controller.Controlador;
import br.rodemarck.core.Executador;
import br.rodemarck.core.MensagemGuildRecebida;
import br.rodemarck.core.comandos.guild.campominado.JogarCampo;
import br.rodemarck.core.comandos.guild.campominado.MarcarCampo;
import br.rodemarck.core.comandos.guild.campominado.MostrarCampo;
import br.rodemarck.core.comandos.guild.PingGuild;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
       try{

            final JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken("NzQ1NTQ5NjIyOTk4NDY2NTkw.XzzZMQ.V3-v8AvazqJ3cYSXsQ0aFUTMacc")
                    .setActivity(Activity.playing("nem eu sei"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
            inicializaComandos();
            jda.addEventListener(new Controlador());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void inicializaComandos(){
        multiplosComandosMensagemGuild(new PingGuild(),"ping","pong","p");
        multiplosComandosMensagemGuild(new MostrarCampo(), "campo","c");
        multiplosComandosMensagemGuild(new JogarCampo(), null,"j","jogar","joga");
        multiplosComandosMensagemGuild(new MarcarCampo(),"marcar","m","mark");
    }
    public static void multiplosComandosMensagemGuild(MensagemGuildRecebida comando, String ... keys){
        for(String s:keys)
            Executador.COMANDOS_MENSAGEM_RECEBIDA_GUILD.put(s,comando);
    }
}
