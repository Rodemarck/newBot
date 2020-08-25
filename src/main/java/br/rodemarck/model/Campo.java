package br.rodemarck.model;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class Campo implements Serializable {
    public final static List<Character> alfabeto = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V');
    private final int [][] campo;
    private final int [][] campoVisivel;
    private boolean acabado;
    private long lastMessage;

    public long getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(long lastMessage) {
        this.lastMessage = lastMessage;
    }

    //predicate.test(this.campo[Y][X])
    public Campo(int tamanho, int numero){
        this.campo = new int[tamanho][tamanho];
        this.campoVisivel = new int[tamanho][tamanho];
        acabado = false;
        int x,y;
        Random rng = new Random();
        for(int i=0;i<numero;i++){
            do{
                x = rng.nextInt(tamanho);
                y = rng.nextInt(tamanho);
            }while (this.campo[y][x] != 0);
            this.campo[y][x] = -1;
            procura(new Ponto(y,x), p->this.campo[p.y][p.x] != -1, p-> ++this.campo[p.y][p.x]);

        }
    }
    public String print(){
        StringBuilder builder = new StringBuilder();
        builder.append("```c\n  ");
        for(int y=0; y<campo.length;y++)
            builder.append("   " +alfabeto.get(y));
        builder.append("\n");
        for(int y=0; y<campo.length;y++){
            builder.append(y+" ");
            for(int x=0; x<campo.length;x++){
                builder.append("   ");
                builder.append(switch (campoVisivel[y][x]){
                    case 2 -> switch (campo[y][x]){
                        case -1 -> "X";
                        case 0 -> " ";
                        default -> ""+campo[y][x];
                    };
                    case 1 -> "?";
                    default -> "*";
                });
            }
            builder.append("\n\n");
        }
        builder.append("```");
        return builder.toString();
    }
    public void procura(Ponto p, Predicate<Ponto> predicate, FuncaPonto f){
        Object o;
        for(int X = (p.x-1); X<=(p.x+1);X++){
            if(X<0 || X>= campo.length) continue;
            for(int Y = (p.y-1); Y<=(p.y+1);Y++){
                if(Y<0 || Y>= campo.length) continue;
                Ponto ponto = new Ponto(Y,X);
                if(predicate.test(ponto))
                    f.aply(ponto);
            }
        }
    }
    public boolean joga(int y, int x){
        if(campoVisivel[y][x] == 2)
            return false;
        campoVisivel[y][x] = 2;
        if(campo[y][x] == 0){
            LinkedList<Ponto> aux = new LinkedList<>();
            LinkedList<Ponto> pontos = new LinkedList<>();
            LinkedList<Ponto> todosPontos = new LinkedList<>();
            Ponto p = new Ponto(y,x);
            pontos.add(p);
            while(!pontos.isEmpty()){
                System.out.println("*");
                aux.clear();
                p = pontos.pop();
                todosPontos.add(p);
                procura(p, i->campo[i.y][i.x]==-1, aux::add);
                if(aux.size() == 0)
                    procura(p, i->true, ponto->{
                        System.out.println(ponto);
                        if(!todosPontos.contains(ponto) && campoVisivel[ponto.y][ponto.x] == 0)
                            pontos.add(ponto);
                        campoVisivel[ponto.y][ponto.x] = 2;
                    });
            }
        }
        return campo[y][x] == -1;
    }
    public void marca(String jogada) throws Exception {
        Ponto p = interpreta(jogada);
        campoVisivel[p.y][p.x] = 1;
    }

    private Ponto interpreta(String jogada) throws Exception {
        int x,y;
        try{
            if(Character.isDigit(jogada.charAt(0)) && Character.isAlphabetic(jogada.charAt(1))){
                y = Integer.parseInt(""+jogada.charAt(0));
                x = alfabeto.indexOf(Character.toUpperCase(jogada.charAt(1)));
            }else if (Character.isDigit(jogada.charAt(1)) && Character.isAlphabetic(jogada.charAt(0))){
                y = Integer.parseInt(""+jogada.charAt(1));
                x = alfabeto.indexOf(Character.toUpperCase(jogada.charAt(0)));
            }else{
                throw new Exception();
            }
            return new Ponto(y,x);
        }catch (Exception e){
            throw new Exception("argumentos invalidos");
        }
    }
    public String joga(String jogada) throws Exception{
        Ponto p = interpreta(jogada);
        joga(p.y, p.x);
        return print();
    }

    private interface FuncaPonto{
        void aply(Ponto p);
    }
    private class Ponto{
        int y,x;

        public Ponto(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ponto ponto = (Ponto) o;
            return y == ponto.y &&
                    x == ponto.x;
        }

        @Override
        public String toString() {
            return String.format("{%d,%d}",y,x);
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public boolean isAcabado() {
        return acabado;
    }
}