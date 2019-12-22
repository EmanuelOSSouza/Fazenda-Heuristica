
package br.com.ia.model;

/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */
public class No{
    
    public static final String MOVER_CIMA = "C";
    public static final String MOVER_BAIXO = "B";
    public static final String MOVER_ESQUERDA = "E";
    public static final String MOVER_DIREITA = "D";
    
    private String posicao;//"19:20"
    private No pai;
    private String acao;
    private int custo;//10
    private boolean visitado = false;
    private int custoCaminho = 10000000;//10
    private String posicaoAnalisada;//"20:20;10"
    private boolean estatdoSesnor = false;

    public boolean isEstatdoSesnor() {
        return estatdoSesnor;
    }

    public void setEstatdoSesnor(boolean estatdoSesnor) {
        this.estatdoSesnor = estatdoSesnor;
    }

    public String getPosicaoAnalisada() {
        return posicaoAnalisada;
    }

    public void setPosicaoAnalisada(String posicaoAnalisada) {
        this.posicaoAnalisada = posicaoAnalisada;
    }

    public int getCustoCaminho() {
        return custoCaminho;
    }

    public void setCustoCaminho(int custoCaminho) {
        this.custoCaminho = custoCaminho;
    }

    
    public String getPosicao() {
        return posicao;
    }

    
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    
    public No getPai() {
        return pai;
    }

    
    public void setPai(No pai) {
        this.pai = pai;
    }

    
    public String getAcao() {
        return acao;
    }

    
    public void setAcao(String acao) {
        this.acao = acao;
    }
    
    
    public int getCusto() {
        return custo;
    }

    
    public void setCusto(int custo) {
        this.custo = custo;
    } 

    
    public boolean isVisitado() {
        return visitado;
    }

    
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }   
    
}
