package principal;

import DAO.Factory;
import DAO.InterfaceEvento;
import DAO.InterfaceProduto;
import DAO.InterfaceUsuario;
import builder.ProdutoDiretor;
import builder.ProdutoPerecivelBuilder;
import factory_method.AlertaFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import models.AlertaAbstrato;
import models.Evento;
import models.Produto;
import models.Usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.imageio.ImageIO;

import javax.swing.*;

public class Cliente {
   static Usuario logado;
   InterfaceUsuario iu;
   InterfaceProduto ip;
   InterfaceEvento ie;
  
   public JButton botaoAdicionarProduto;
   public JButton botaoAdicionarEvento;
   public JButton botaoAdicionarProdutoEvento;
   public JButton botaoGerarListaProdutos;
   public JButton botaoGerarListaCompraProdutos;
   public JLabel labelTitulo;
    
   public static void main(String[] args) throws ParseException {
      new Cliente();
   }
   
   public Cliente(){
      SwingUtilities.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               JFrame frame = new JFrame();
               
               iu = Factory.criarUsuarioDAO();
               ip = Factory.criarProdutoDAO();
               ie = Factory.criarEventoDAO();
            
               logado = iu.busca("teste@teste.com");
            
            // teste BD Usuario
               Usuario user = new Usuario();
               user.setNome("Teste");
               user.setIdade(12);
               user.setEmail("teste@teste.com");
               user.setTelefone("(31) 132456");
               user.setAfiliacao("NULL");
            
            //iu.inserir(user);
               
               Font font = new Font("Courier", Font.BOLD, 24);
               
               labelTitulo = new JLabel("Engenharia de Software 2 - Despensa");
               labelTitulo.setBounds(150, 30, 600, 30);
               labelTitulo.setForeground(Color.BLACK);
               labelTitulo.setFont(font);
               
               botaoAdicionarProduto = new JButton("Adicionar Produto");
               botaoAdicionarProduto.setBounds(80, 90, 300, 30);
                              
               botaoAdicionarEvento = new JButton("Adicionar Evento");
               botaoAdicionarEvento.setBounds(410, 90, 300, 30);
               
               botaoAdicionarProdutoEvento = new JButton("Adicionar Produto em Evento");
               botaoAdicionarProdutoEvento.setBounds(80, 150, 300, 30);
               
               botaoGerarListaProdutos = new JButton("Gerar Lista de Produto");
               botaoGerarListaProdutos.setBounds(410, 150, 300, 30);
               
               botaoGerarListaCompraProdutos = new JButton("Gerar Lista de Compra");
               botaoGerarListaCompraProdutos.setBounds(80, 210, 300, 30);
               
               frame.add(labelTitulo);
               frame.add(botaoAdicionarProduto);
               frame.add(botaoAdicionarEvento);
               frame.add(botaoAdicionarProdutoEvento);
               frame.add(botaoGerarListaProdutos);
               frame.add(botaoGerarListaCompraProdutos);
               
               frame.add(new TestPane(frame));
               
               frame.pack();
               frame.setLocationRelativeTo(null);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setDefaultLookAndFeelDecorated(true);
               frame.setLayout(null);
               frame.setVisible(true);
            }
         });
   }
   
   public class TestPane extends JPanel {
   
      public TestPane(JFrame paramFrame) {
         
         botaoAdicionarProduto.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  try{
                     ProdutoDiretor pd = new ProdutoDiretor(new ProdutoPerecivelBuilder());
                     DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     String dateString = "2019-11-25";
                     Date date = sdf.parse(dateString);
                     String nomeProduto = JOptionPane.showInputDialog(paramFrame, "Digite o nome do Produto:");
                     double precoProduto = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite o preço do Produto:"));
                     double gastoDiario = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite o seu gasto diário do Produto:"));
                     double quantidadeProduto = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite a quantidade desse Produto a inserir:"));
                     int numeroConsumidoresProduto = Integer.parseInt(JOptionPane.showInputDialog(paramFrame, "Digite o número de consumidores desse Produto:"));
                     pd.buildProduto(nomeProduto, date, precoProduto, gastoDiario, quantidadeProduto, numeroConsumidoresProduto, logado.getId());
                     ip.inserir(pd.getProduto());
                  }catch(Exception error){
                     error.printStackTrace();
                  }
               }
            });
            
         botaoAdicionarEvento.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  System.out.println("Hu3");
               }
            });
            
         botaoAdicionarProdutoEvento.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  System.out.println("Hu3");
               }
            });
            
         botaoGerarListaProdutos.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  System.out.println("Hu3");
               }
            });
            
         botaoGerarListaCompraProdutos.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  System.out.println("Hu3");
               }
            });
      }
            
      @Override
        public Dimension getPreferredSize() {
         return new Dimension(800, 600);
      }
   }
}
