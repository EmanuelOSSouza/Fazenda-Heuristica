/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import br.com.ia.search.BuscaLargura;
import br.com.ia.search.Dijkstra;

/**
 *
 * @authors Iarley Moraes, Emanuel Souza, Caio Barreto
 */
@SuppressWarnings("serial")
public class Map extends JPanel implements Runnable {
    BuscaLargura service = null;
    private JTable table = null;
    private JButton btn = null;
    private int custo = 0;
    public static int custoTotal;
    
    //Construtor
    public Map() {
        service = new BuscaLargura();
        setDoubleBuffered(true);
        setFocusable(true);
        load();
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }

    @Override
    public void run() {
        
        while (true) {
            update();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void load() {
        table = new JTable(BuscaLargura.M, BuscaLargura.N) {
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                switch (service.MAPA[rowIndex][vColIndex]) {
                    case 120:
                        c.setBackground(new Color(235,199,158));
                        break;
                    case 100:
                        c.setBackground(new Color(50,153,204));
                        break;
                    case 200:
                        c.setBackground(new Color(255,255,255));
                        break;
                    case 0:
                        c.setBackground(new Color(255,0,0));
                        break;
                    case 1:
                        c.setBackground(new Color(255,255,0));
                        break;
                    case 201:
                        c.setBackground(new Color(190,190,190));
                        break;
                    default:
                        c.setBackground(new Color(153,204,50));
                        break;
                }
                return c;
            }
        };
       
        for (int c = 0; c < BuscaLargura.M; c++) {
            getTable().getColumnModel().getColumn(c).setHeaderValue("");
        }

        JScrollPane scrollPane = new JScrollPane(getTable());
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
        btn = new JButton("Custo: 0");
        add(btn, BorderLayout.SOUTH);

    }
    
    private void update() {
        boolean estatdoBusca = service.busca();
        
        if (estatdoBusca) {
            int linha , coluna, custo = 0;
            String param[];
            Dijkstra djk = new Dijkstra(service);
            //Adaptar esse "for" para o algoritmo de Dijkstra
            System.out.println("Tamanho da lista:"+djk.caminho.size());
            if(djk.caminho.size()==0){
                int []posicaoInicial;
                posicaoInicial =service.getInicio();
                BuscaLargura.linha = posicaoInicial[0];
                BuscaLargura.coluna = posicaoInicial[1];
                JOptionPane.showMessageDialog(null, "Custo do caminho: 0");
                new MainFrameBase().setVisible(true); 
            } else{
                for(int i=0; i < djk.caminho.size(); i++) {
                    djk.caminho.get(i);
                    param = djk.caminho.get(i).getPosicao().split(":");
                    linha = Integer.parseInt(param[0]);
                    coluna = Integer.parseInt(param[1]);
                    custo += djk.caminho.get(i).getCusto();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    table.setValueAt("#", linha, coluna);
                    table.repaint();
                }
                JOptionPane.showMessageDialog(null, "Custo do caminho: " + custo);
                custoTotal = custoTotal + custo;
                System.out.println(djk.caminho.get(0).getPosicao());
                param = djk.caminho.get(0).getPosicao().split(":"); //A partir daqui
                linha = Integer.parseInt(param[0]);
                coluna = Integer.parseInt(param[1]);
                BuscaLargura.linha = linha;
                BuscaLargura.coluna = coluna;
                System.out.println("["+BuscaLargura.linha+":"+BuscaLargura.coluna+"]");
                System.out.println("["+linha+":"+coluna+"]");
                if(service.contenVaca()){
                    //add(new Map());//Tem alguma coisa aqui ou tem que mexer no método run()
                    new MainFrameBase().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "A fazenda foi mapeada com sucesso.\nCusto total: "+custoTotal);
                    System.out.println();
                    System.exit(0);
                }
            }
        } else {
            String param[] = BuscaLargura.retorno.get(0).getPosicao().split(":");
            int linha = Integer.parseInt(param[0]);
            int coluna = Integer.parseInt(param[1]);
            table.setValueAt("@", linha, coluna);
            //custo+=estatdoBusca.get(0).getCusto();
            btn.setText("Custo total: "+custo);
            BuscaLargura.retorno.remove(0);
        }
        btn.repaint();
        table.repaint();
    }

    
    public static void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    /**
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

}
