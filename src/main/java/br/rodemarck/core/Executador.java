package br.rodemarck.core;

import br.rodemarck.utilitarios.Constantes;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Executador {
    public static HashMap<String, MensagemGuildRecebida> COMANDOS_MENSAGEM_RECEBIDA_GUILD = new HashMap<>();
    public static HashMap<String, MensagemPrivadaRecebida> COMANDOS_MENSAGEM_RECEBIDA_PRIVADA = new HashMap<>();

    public static void interpreta(PrivateMessageReceivedEvent e){
        LinkedList<String> args = traduz(e.getMessage().getContentRaw());
        String comando = args.size() == 0 ? null : args.getFirst();
        MensagemPrivadaRecebida mpr = COMANDOS_MENSAGEM_RECEBIDA_PRIVADA.get(comando);
        if (mpr == null)
            mpr = COMANDOS_MENSAGEM_RECEBIDA_PRIVADA.get(null);
        if(mpr!=null && mpr.livre(args, e))
            mpr.executa(args, e);
    }
    public static void interpreta(GuildMessageReceivedEvent e){
        LinkedList<String> args = traduz(e.getMessage().getContentRaw());
        String comando = args.size() == 0 ? "" : args.getFirst();
        MensagemGuildRecebida mgr = COMANDOS_MENSAGEM_RECEBIDA_GUILD.get(comando);
        if (mgr == null)
            mgr = COMANDOS_MENSAGEM_RECEBIDA_GUILD.get(null);
        if (mgr != null && mgr.livre(args, e))
            mgr.executa(args, e);
    }

    private static LinkedList<String>  traduz(String raw){
        String texto = raw.replaceFirst(Constantes.PREFIXO,"");
        StringTokenizer tokens = new StringTokenizer(texto);
        LinkedList<String> palavras = new LinkedList<>();
        while (tokens.hasMoreTokens())
            palavras.add(tokens.nextToken());
        return palavras;
    }
}
