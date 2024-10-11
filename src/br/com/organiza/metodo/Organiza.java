/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.organiza.metodo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fe_li
 *
 * Programa organiza pasta de musica, ele cria uma pasta com o nome da banda que
 * contenha mais de uma musica e as que contem somente uma ele move para a pasta
 * com a inicial do nome da banda.
 *
 * O diretorio principal já tem que ter as pastas com as letas do alfabeto
 *
 * Os arquivos de musica precisam estar nomeados no padrão "Autor - Musica -
 * Album . extensão" o principal é o nome do altor/banda estar no inicio e
 * separados por "-"
 */
public class Organiza {

    String diretorio = "D:\\Music";
    Map<String, List<String>> ListaArtista = new HashMap<>();
    String[] Alfabeto = {"A",}; //"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    public void lista_arquivos() {

        File file = new File(diretorio);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            if (arquivos.getName().contains(".") && !arquivos.getName().contains(".jpg")) {
                String[] arrNome = arquivos.getName().split("-");
                
                
                 if (!ListaArtista.containsKey(arrNome[0])) {
            ListaArtista.put(arrNome[0], new ArrayList<>());
        }
        ListaArtista.get(arrNome[0]).add(arquivos.getName());
            }
        }    
    }

    public void Arruma() {

        for (String bandas : ListaArtista.keySet()) {
            List<String> listaMusicas = ListaArtista.get(bandas);
            
            if (listaMusicas.size() > 1) {                
                String strPath = diretorio + "\\" + bandas;
                File path = new File(strPath);
                if (!path.exists()) {
                    path.mkdirs();
                }                
                for(String musica : listaMusicas ){                    
//                File arquivo = new File(diretorio + "\\" + musica); 
//                File Destino = new File(diretorio + "\\" + bandas);
//                arquivo.renameTo(new File(Destino, arquivo.toString()));
//                
                File arquivo = new File(diretorio + "\\" + musica); 
                File Destino = new File(strPath.trim());
                arquivo.renameTo(new File(Destino, arquivo.getName()));
                }
                
            }else{
              File arquivo = new File(diretorio + "\\" + listaMusicas.get(0)); 
              File Destino = new File(diretorio + "\\" + bandas.toUpperCase().charAt(0));
              arquivo.renameTo(new File(Destino, arquivo.getName()));
            }
        }
    }
    

    public static void main(String[] main) {
        Organiza o = new Organiza();
        o.lista_arquivos();
        o.Arruma();
    }

}
