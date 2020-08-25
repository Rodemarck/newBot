package br.rodemarck;

import br.rodemarck.model.Campo;

import java.io.*;
import java.util.concurrent.Semaphore;

public class Repositorio {

    public static Campo get(String guild, String menbro) throws IOException, ClassNotFoundException {
        try{
            File f = new File("banco/campoMinado/"+guild+"/"+menbro+".bin");
            if(!f.exists()){
                f = new File("banco/campoMinado/"+guild+"/public.bin");
                if(!f.exists()){
                    Campo c = new Campo(10,10);
                    set(guild,menbro,c);
                    return c;
                }
            }
            try(FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);){
                return (Campo) ois.readObject();
            }
        }catch (IOException| ClassNotFoundException e){
            throw e;
        }
    }

    public static void set(String guild, String menbro, Campo campo) throws IOException {
        File f = new File("banco/campoMinado/"+guild);
        if(!f.exists())
            f.mkdirs();
        f = new File("banco/campoMinado/"+guild+"/"+menbro+".bin");
        f.createNewFile();
        try(FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(campo);
        } catch (IOException e) {
            throw e;
        }

    }

    public static void delete(String guild, String menbro)  {
        File f = new File("banco/campoMinado/"+guild+"/"+menbro+".bin");
        if(f.exists())
            f.delete();
    }
}
