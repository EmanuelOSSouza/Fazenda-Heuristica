package br.com.ia.search;

import br.com.ia.model.No;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Iarley
 */
public class BuscaLargura {
    private Queue<No> fila = new LinkedList<No>();
    private List<No> filaDeMapeamento = new ArrayList<No>();
    public static ArrayList<No> retorno = new ArrayList<No>();
    private List<String> lista = new LinkedList<String>();//Lista para controle de nos visitados
    private No raiz = new No();
    public static int linha;
    public static int coluna;
    private int[] inicio = {linha, coluna};

    public int[] getInicio() {
        return inicio;
    }

    public void setInicio(int[] inicio) {
        this.inicio = inicio;
    }
    
    public static final int M = 42, N = 42;
    public static int[][] MAPA = {         /*0*/{120,10,10,10,10,120,10,100,10,10,10,10,10,10,10,10,120,120,120,120,120,120,120,10,10,10,120,120,120,120,120,10,10,10,10,120,120,120,10,10,10,10},
                                    /*1*/{120,10,120,120,120,120,10,100,10,120,120,120,120,10,10,120,120,120,120,120,120,120,120,120,10,10,120,10,10,10,120,10,10,120,120,120,120,120,120,120,10,10},
                                    /*2*/{120,10,10,10,10,120,10,100,10,120,10,10,120,10,120,120,120,120,120,120,120,120,120,120,120,10,120,120,10,120,120,10,120,120,120,120,200,120,120,120,120,10},
                                    /*3*/{120,10,120,120,120,120,10,100,10,120,10,10,120,10,120,120,120,120,120,120,120,120,120,120,120,10,10,120,10,120,10,120,120,120,120,200,200,200,120,120,120,120},
                                    /*4*/{120,10,10,10,10,120,10,100,10,10,10,10,10,10,10,120,120,120,120,120,120,120,120,120,10,10,10,120,10,120,10,120,120,120,200,200,200,200,200,120,120,120},
                                    /*5*/{120,120,120,120,10,120,10,100,100,100,100,100,10,10,10,10,10,120,120,120,120,120,10,10,10,120,10,120,10,120,10,120,120,120,120,200,200,200,120,120,120,120},
                                    /*6*/{10,120,10,10,10,10,10,10,10,10,10,100,10,120,120,10,10,10,10,10,10,10,10,10,10,120,10,120,10,120,10,10,120,120,120,120,200,120,120,120,120,10},
                                    /*7*/{10,120,10,10,10,10,120,120,120,120,10,100,10,120,120,10,10,10,10,120,10,10,120,10,10,10,10,10,10,10,10,10,10,120,120,120,120,120,120,120,10,10},
                                    /*8*/{10,10,10,10,120,10,10,10,10,10,10,10,10,10,10,10,10,10,120,120,120,10,10,10,120,120,120,120,120,120,120,10,10,10,10,120,120,120,10,10,10,10},
                                    /*9*/{10,10,120,10,10,10,10,10,120,120,10,100,10,120,120,10,120,10,10,120,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,120,10},
                                    /*10*/{10,200,120,10,120,120,120,10,120,120,10,100,10,120,120,10,120,10,10,10,10,10,10,10,120,120,120,10,10,10,120,120,120,10,120,120,120,120,10,10,120,10},
                                    /*11*/{10,10,120,10,10,10,10,10,10,10,10,100,10,10,10,10,120,120,120,120,120,10,10,120,120,120,120,120,10,10,120,120,120,10,10,10,10,10,10,10,10,10},
                                    /*12*/{10,10,120,10,10,10,10,10,10,10,10,100,100,100,100,10,120,10,10,120,10,10,120,120,120,120,120,120,120,10,10,10,10,10,120,10,10,120,120,120,120,10},
                                    /*13*/{10,10,10,10,10,10,10,10,120,10,10,10,10,10,100,10,120,10,10,120,10,10,120,120,120,100,120,120,120,10,10,10,10,10,120,10,10,10,10,10,10,10},
                                    /*14*/{120,120,120,120,10,120,10,120,120,120,10,10,10,10,100,10,10,10,10,120,10,10,10,120,120,100,120,120,10,10,10,10,120,120,120,120,120,120,120,10,10,10},
                                    /*15*/{10,10,10,10,10,120,10,120,120,120,10,10,100,100,100,100,100,10,10,120,10,10,10,10,10,100,10,10,10,10,10,10,10,10,10,120,10,10,10,10,10,10},
                                    /*16*/{10,10,120,10,10,120,10,10,120,10,10,100,100,100,100,100,100,100,10,10,10,120,120,120,10,100,10,10,10,120,10,10,120,10,10,120,10,10,10,10,10,10},
                                    /*17*/{120,10,120,10,10,120,10,10,10,10,100,100,100,100,100,100,100,100,100,10,10,10,10,10,10,100,10,120,120,120,120,10,120,10,10,120,10,10,10,10,120,120},
                                    /*18*/{120,10,120,10,10,120,10,10,10,10,100,100,100,10,10,10,100,100,100,10,10,120,10,10,10,100,10,10,10,120,10,10,120,10,10,10,10,120,120,120,120,120},
                                    /*19*/{120,10,120,10,120,120,120,10,10,10,100,100,100,10,10,10,100,100,100,10,120,120,120,10,10,100,10,10,10,10,10,10,120,120,120,10,120,120,120,120,120,120},
                                    /*20*/{120,10,10,10,10,10,10,10,10,10,100,100,100,10,10,10,100,100,100,10,120,200,120,10,10,100,100,100,100,100,100,10,10,10,10,10,10,10,10,10,120,120},
                                    /*21*/{120,120,120,120,120,10,10,10,10,10,100,100,100,100,100,100,100,100,100,10,120,120,120,10,10,10,10,10,10,10,100,10,120,120,120,10,120,120,120,120,120,120},
                                    /*22*/{10,10,10,10,120,10,120,10,10,10,10,100,100,100,100,100,100,100,10,10,10,120,10,10,10,120,120,120,10,10,100,10,120,10,10,10,10,120,120,120,120,120},
                                    /*23*/{10,10,10,10,120,10,120,10,10,10,10,10,100,100,100,100,100,10,10,10,10,10,10,10,10,120,120,120,10,10,100,10,120,10,120,120,10,10,10,10,120,120},
                                    /*24*/{10,10,120,10,10,10,120,10,120,120,10,10,10,10,100,10,10,10,10,10,10,120,120,120,10,10,10,10,10,10,100,10,120,10,120,10,10,10,10,10,10,10},
                                    /*25*/{10,120,120,120,10,10,120,10,120,120,120,120,10,10,100,10,10,10,120,120,10,10,10,10,10,10,120,10,10,10,100,10,10,10,120,120,120,120,10,10,10,10},
                                    /*26*/{10,120,120,120,10,120,120,10,10,10,10,10,10,10,100,10,10,120,120,120,120,10,10,10,120,10,120,10,10,100,100,100,10,10,10,120,120,120,10,10,120,10},
                                    /*27*/{10,120,120,120,10,10,10,10,120,120,120,10,10,10,100,10,120,120,120,120,120,120,10,10,120,10,10,10,100,100,100,100,100,10,10,10,10,10,10,10,120,10},
                                    /*28*/{10,10,120,10,10,10,10,10,10,10,10,10,10,10,100,10,120,120,120,120,120,120,10,10,120,10,10,100,100,100,100,100,100,100,10,10,120,120,120,120,120,10},
                                    /*29*/{120,10,10,10,120,10,10,120,100,100,100,100,100,100,100,10,10,120,120,120,120,10,10,10,120,10,10,10,100,100,100,100,100,10,10,10,120,10,10,10,120,10},
                                    /*30*/{120,120,10,120,120,120,10,10,100,10,10,10,10,10,120,10,10,10,120,120,10,10,120,120,120,120,10,10,10,100,100,100,10,10,10,10,10,10,10,10,10,10},
                                    /*31*/{120,120,10,120,120,120,10,10,100,10,10,10,10,10,120,10,120,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,120,120,120,120,10,120,120,120,10},
                                    /*32*/{120,10,10,10,120,10,10,10,100,10,120,120,120,120,120,10,120,120,120,120,10,120,120,120,120,120,120,10,10,10,10,120,10,10,10,120,10,10,10,120,10,10},
                                    /*33*/{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,120,10,10,120,120,120,120,120,10,120,10,120,10,120,10,120},
                                    /*34*/{10,120,10,120,120,120,120,10,100,10,120,120,120,10,120,120,120,120,120,120,120,120,120,120,10,120,120,10,10,10,10,10,10,10,10,10,10,10,10,120,10,120},
                                    /*35*/{10,120,10,10,10,10,120,10,100,10,10,120,10,10,120,10,10,10,10,10,10,10,10,120,10,120,10,10,120,120,120,120,10,120,120,120,120,10,10,120,10,120},
                                    /*36*/{10,120,120,120,10,10,120,10,100,10,10,120,10,10,120,10,120,120,120,120,120,120,10,120,10,10,10,120,120,120,200,200,10,200,200,120,120,120,10,10,10,120},
                                    /*37*/{10,120,10,10,10,10,120,10,100,10,10,10,10,10,120,10,120,10,10,10,10,120,10,120,10,10,120,120,200,200,200,200,10,200,200,200,200,120,120,10,10,120},
                                    /*38*/{120,120,10,120,120,120,120,10,100,100,100,100,10,10,120,10,10,10,10,10,10,120,10,120,10,10,120,120,200,200,200,200,10,200,200,200,200,120,120,10,10,10},
                                    /*39*/{120,10,10,10,10,10,120,10,10,10,10,100,10,10,120,120,120,120,120,120,120,120,10,120,10,120,120,200,200,200,200,200,10,200,200,200,200,200,120,120,10,10},
                                    /*40*/{120,10,120,120,120,10,120,10,10,10,10,100,10,10,10,10,10,10,10,10,10,10,10,10,10,120,120,200,200,200,200,200,10,200,200,200,200,200,120,120,10,10},
                                    /*41*/{120,10,10,10,10,10,120,10,10,10,10,100,10,10,120,120,120,120,120,120,120,120,120,120,10,120,120,200,200,200,200,200,200,200,200,200,200,200,120,120,10,10}                                     
                            };

    public BuscaLargura() {
        initComponents();
    }

    public List<No> getFilaDeMapeamento() {
        return filaDeMapeamento;
    }

    public void setFilaDeMapeamento(List<No> filaDeMapeamento) {
        this.filaDeMapeamento = filaDeMapeamento;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
    
    private void initComponents() {
        
        MAPA[inicio[0]][inicio[1]] = 0;
                
        raiz.setPosicao(inicio[0]+":"+inicio[1]);
        raiz.setCusto(0);
        raiz.setCustoCaminho(0);
        raiz.setPosicaoAnalisada(inicio[0]+":"+inicio[1]+";"+0);
        
        lista.add(inicio[0]+":"+inicio[1]);
        fila.add(raiz);
        filaDeMapeamento.add(raiz);
    }

    public boolean busca() {
        boolean bool = false;
        String param[] = new String[2]; //Vetor de Strings para ser tratado para ser passado como parametro
        int linha;
        int coluna;
        
        if (!fila.isEmpty()) {
            No no = fila.remove();
            param = no.getPosicao().split(":");//Tratando a string para a conseguir os valores de linha e coluna
            linha = Integer.parseInt(param[0]);//Passando a linha do nó criado
            coluna = Integer.parseInt(param[1]);//Passando a linha do nó criado
            retorno.add(no);
            //Precisa ser alterado para incorporar o sensor
            if (sensor(linha, coluna) || MAPA[linha][coluna]==200) {
                ArrayList<String> caminho = new ArrayList<String>(); //A variável caminho não está sendo utilizada
                no.setEstatdoSesnor(true);
                caminho.add(no.getPosicao());
                System.out.println("goal");
                fila.clear();
                bool = true;
                return bool;
            //Enquanto estiver passando por esse else a função ira retornar false
            } else {

                // Analisando o filho ao Note(CIMA)
                if ((linha - 1) >= 0 && (linha - 1) < M && MAPA[linha - 1][coluna] != 201) {
                    //Este if faz o controle de qais posições ja foram lidas e nós criados.
                    if (!lista.contains((linha - 1) + ":" + coluna)) {
                        No cima = new No();
                        cima.setAcao(No.MOVER_CIMA);
                        //Alterar esse custo, pois o calculo está errado
                        //O custo do nó tem que ser dele mesmo não a soma dos custos do pai
                        cima.setCusto(MAPA[linha - 1][coluna]);
                        cima.setPosicao((linha - 1) + ":" + coluna);
                        //***********************************************
                        cima.setPai(no);
                        fila.add(cima);//Adicionando o filho do nó a cima para a fila
                        lista.add(cima.getPosicao());//Adicionando as posições do nó filho na lista 
                        filaDeMapeamento.add(cima);//
                    }
                }

                // Analisando o filho ao Oeste(ESQUERDA)
                if ((coluna - 1) >= 0 && (coluna - 1) < N && MAPA[linha][coluna - 1] != 201) {
                    //Este if faz o controle de qais posições ja foram lidas e nós criados.
                    if (!lista.contains(linha + ":" + (coluna - 1))) {
                        No esquerda = new No();
                        esquerda.setAcao(No.MOVER_ESQUERDA);
                        //Alterar esse custo, pois o calculo está errado
                        //O custo do nó tem que ser dele mesmo não a soma dos custos do pai
                        esquerda.setCusto(MAPA[linha][coluna-1]);
                        esquerda.setPosicao(linha + ":" + (coluna - 1));
                        //***********************************************
                        esquerda.setPai(no);
                        fila.add(esquerda);
                        lista.add(esquerda.getPosicao());
                        filaDeMapeamento.add(esquerda);
                    }
                }

                // Analisando o filho ao Leste(DIREITA)
                if ((coluna + 1) < N && MAPA[linha][coluna + 1] != 201) {
                    //Este if faz o controle de qais posições ja foram lidas e nós criados.
                    if (!lista.contains(linha + ":" + (coluna + 1))) {
                        No direita = new No();
                        direita.setAcao(No.MOVER_DIREITA);
                        //Alterar esse custo, pois o calculo está errado
                        //O custo do nó tem que ser dele mesmo não a soma dos custos do pai
                        direita.setCusto(MAPA[linha][coluna+1]);
                        direita.setPosicao(linha + ":" + (coluna + 1));
                        //***********************************************
                        direita.setPai(no);
                        fila.add(direita);
                        lista.add(direita.getPosicao());
                        filaDeMapeamento.add(direita);
                    }
                }

                // Analisando o filho ao Sul(BAIXO)
                if ((linha + 1 ) < M && MAPA[linha + 1][coluna] != 201) {
                    //Este if faz o controle de qais posições ja foram lidas e nós criados.
                    if (!lista.contains((linha + 1) + ":" + coluna)) {
                        No baixo = new No();
                        baixo.setAcao(No.MOVER_BAIXO);
                        //Alterar esse custo, pois o calculo está errado
                        //O custo do nó tem que ser dele mesmo não a soma dos custos do pai
                        baixo.setCusto(MAPA[linha + 1][coluna]);
                        baixo.setPosicao((linha + 1) + ":" + coluna);
                        //***********************************************
                        baixo.setPai(no);
                        fila.add(baixo);
                        lista.add(baixo.getPosicao());
                        filaDeMapeamento.add(baixo);
                    }
                }
            }
        }
        return bool;
    }
    private boolean sensor(int linha, int coluna){
	for (int i=-3; i<=3; i++){
            if((linha+i>=0)&&(linha+i<=41)){ //ESTA TRATANDO AQUI
                for(int j=-3;j<=3;j++){
                    if(coluna+j>=0 && coluna+j<=41){  //ESTA TRATANDO AQUI
                        if(MAPA[linha+i][coluna+j] == 200){
                            migue(linha + i, coluna + j);
                            return true;
                        }
                    }
                }
            }
	}
        return false;
    }
    
    private void migue(int linha, int coluna){
    	for (int i=-3; i<=3; i++){
            if((linha+i>=0)&&(linha+i<=41)){ //ESTA TRATANDO AQUI
                for(int j=-3;j<=3;j++){
                    if(coluna+j>=0 && coluna+j<=41){  //ESTA TRATANDO AQUI
                        if(MAPA[linha+i][coluna+j] == 200){

                            MAPA[linha + i][coluna + j] = 201;
                        }
                    }
                }
            }
	}
    }
    
    public boolean contenVaca(){
        for (int i = 0; i < 41; i++) {
            for (int j = 0; j < 41; j++) {
                if(MAPA[i][j] == 200)
                    return true;
            }
        }
        return false;
    }
}


/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */
