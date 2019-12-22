
package br.com.ia.search;

import br.com.ia.model.No;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */
//***********Código do calculo de Dijkstra************
/** Algoritmo
    * Enquanto houver vértice aberto: (1º)
        * Escolha o nó(u) cuja a estimativa seja a menor entre os abertos (2º)
        * Feche o nó escolhido (3º)
        * Para todo nó(v) adjacente ao no(u): (4º)
            * Analise o custo de [u,v] (5º)
*/
//*****************************************************
public class Dijkstra {
    //Atributos
    public List<No> caminho = new LinkedList<No>(); //Pode ser uma Queue
    
    //Construtores
    public Dijkstra(BuscaLargura service){
        heuristica(service);
    }
    
    private void copy(List<No> src, List<No> dest){
        for (No no : src) {
            dest.add(no);
        }
    }
    
    //Metodos
    private final void heuristica(BuscaLargura service){
        
        List<No> listaControle = service.getFilaDeMapeamento();
        List<No> listaVisitado = new ArrayList<No>();
        //Copiando de uma lista para outra
        
        copy(listaControle, listaVisitado);
        int tamC = listaControle.size();
        int menori = 0;
        //1º
        while(listaVisitado.size() > 0){
            int tamV = listaVisitado.size();
            int custocon = 10000001;
            //2º
            for(int i = 0; i < tamV; i++){
                if(listaVisitado.get(i).getCustoCaminho()<custocon){
                    custocon = listaVisitado.get(i).getCustoCaminho();
                    menori = i;
                }
            }
            No noAnalisado = listaVisitado.get(menori);
            //3º
            listaVisitado.get(menori).setVisitado(true);
            noAnalisado.setVisitado(true);
            listaVisitado.remove(menori);
            //4º & 5º
            String param[] = noAnalisado.getPosicao().split(":");
            int linha = Integer.parseInt(param[0]);
            int coluna = Integer.parseInt(param[1]);
            custocon=noAnalisado.getCustoCaminho();
            tamV = listaVisitado.size();
            for (int i = 0; i < tamV; i++) {

                //Analista filho Norte
                String concat = "0:0";
                if(linha-1 != -1){
                    concat = (linha-1) +":"+ coluna;
                    if(listaVisitado.get(i).getPosicao().equals(concat)){
                        int custoanalisado=custocon+listaVisitado.get(i).getCusto();
                        if(custoanalisado <= listaVisitado.get(i).getCustoCaminho()){
                            listaVisitado.get(i).setCustoCaminho(custoanalisado);
                            listaVisitado.get(i).setPai(noAnalisado);
                            listaVisitado.get(i).setPosicaoAnalisada(concat+";"+custoanalisado);
                        }
                    }
                }
                //Analista filho Esquerda
                if(coluna-1 != -1){
                    concat = linha +":"+ (coluna-1);
                    if(listaVisitado.get(i).getPosicao().equals(concat)){
                        int custoanalisado=custocon+listaVisitado.get(i).getCusto();
                        if(custoanalisado <= listaVisitado.get(i).getCustoCaminho()){
                            listaVisitado.get(i).setCustoCaminho(custoanalisado);
                            listaVisitado.get(i).setPai(noAnalisado);
                            listaVisitado.get(i).setPosicaoAnalisada(concat+";"+custoanalisado);
                        }
                    }
                }
                //Analista filho Direita
                concat = linha +":"+ (coluna+1);
                if(listaVisitado.get(i).getPosicao().equals(concat)){
                    int custoanalisado=custocon+listaVisitado.get(i).getCusto();
                    if(custoanalisado <= listaVisitado.get(i).getCustoCaminho()){
                        listaVisitado.get(i).setCustoCaminho(custoanalisado);
                        listaVisitado.get(i).setPai(noAnalisado);
                        listaVisitado.get(i).setPosicaoAnalisada(concat+";"+custoanalisado);
                    }
                }
                //Analista filho Sul
                concat = (linha+1) +":"+ coluna;
                if(listaVisitado.get(i).getPosicao().equals(concat)){
                    int custoanalisado=custocon+listaVisitado.get(i).getCusto();
                    if(custoanalisado <= listaVisitado.get(i).getCustoCaminho()){
                        listaVisitado.get(i).setCustoCaminho(custoanalisado);
                        listaVisitado.get(i).setPai(noAnalisado);
                        listaVisitado.get(i).setPosicaoAnalisada(concat+";"+custoanalisado);
                    }
                }

            }
        }
        //
        int posicao = 0;
        for (int i = 0; i < tamC; i++) {
            System.out.println(listaControle.get(i).getCustoCaminho());
            if(listaControle.get(i).isEstatdoSesnor() == true){
               posicao = i;
            }
        }        
        No retorno = listaControle.get(posicao);
        while(retorno.getPai() != null){
            System.out.println(retorno.getPosicaoAnalisada());
            this.caminho.add(retorno);
            retorno = retorno.getPai();
        }
    }
}


/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */