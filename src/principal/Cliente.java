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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
   public JList<String> lista;
   public DefaultListModel<String> modeloLista;
   public JButton botaoConcluirAcao;
   public String statusAtual;
   public Evento eventoSelecionado;
    
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
               
               modeloLista = new DefaultListModel<>();
               
               lista = new JList<>();
               lista.setModel(modeloLista);
               lista.setBounds(80, 270, 630, 200);
               lista.setVisible(false);
               
               botaoConcluirAcao = new JButton("Concluir");
               botaoConcluirAcao.setBounds(340, 500, 100, 30);
               botaoConcluirAcao.setVisible(false);
               
               statusAtual = "";
               eventoSelecionado = null;
               
               frame.add(labelTitulo);
               frame.add(botaoAdicionarProduto);
               frame.add(botaoAdicionarEvento);
               frame.add(botaoAdicionarProdutoEvento);
               frame.add(botaoGerarListaProdutos);
               frame.add(botaoGerarListaCompraProdutos);
               frame.add(lista);
               frame.add(botaoConcluirAcao);
               
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
                     statusAtual = "";
                     modeloLista.removeAllElements();
                     lista.setVisible(false);
                     botaoConcluirAcao.setVisible(false);
                     ProdutoDiretor pd = new ProdutoDiretor(new ProdutoPerecivelBuilder());
                     String nomeProduto = JOptionPane.showInputDialog(paramFrame, "Digite o nome do Produto:");
                     double precoProduto = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite o preço do Produto:"));
                     String dateString = JOptionPane.showInputDialog(paramFrame, "Digite a validade do Produto: (Formato yyyy-MM-dd)");
                     DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date date = sdf.parse(dateString);
                     double gastoDiario = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite o seu gasto diário do Produto:"));
                     double quantidadeProduto = Double.parseDouble(JOptionPane.showInputDialog(paramFrame, "Digite a quantidade desse Produto a inserir:"));
                     int numeroConsumidoresProduto = Integer.parseInt(JOptionPane.showInputDialog(paramFrame, "Digite o número de consumidores desse Produto:"));
                     pd.buildProduto(nomeProduto, date, precoProduto, gastoDiario, quantidadeProduto, numeroConsumidoresProduto, logado.getId());
                     ip.inserir(pd.getProduto());
                     JOptionPane.showMessageDialog(paramFrame, "Produto Inserido Com Sucesso!");
                  }catch(Exception error){
                     JOptionPane.showMessageDialog(paramFrame, "Ocorreu um erro! Valor inserido provavelmente inválido. Tente outra vez");
                     error.printStackTrace();
                  }
               }
            });
            
         botaoAdicionarEvento.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  try{
                     statusAtual = "";
                     modeloLista.removeAllElements();
                     lista.setVisible(false); 
                     botaoConcluirAcao.setVisible(false);
                     String nomeEvento = JOptionPane.showInputDialog(paramFrame, "Digite o nome do Evento:");
                     String dateString = JOptionPane.showInputDialog(paramFrame, "Digite a data do Evento: (Formato yyyy-MM-dd)");
                     DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date date = sdf.parse(dateString);
                     Evento evento = new Evento(nomeEvento, date);
                     ie.inserir(evento);
                     JOptionPane.showMessageDialog(paramFrame, "Evento Inserido Com Sucesso!");
                  }catch(Exception error){
                     JOptionPane.showMessageDialog(paramFrame, "Ocorreu um erro! Valor inserido provavelmente inválido. Tente outra vez");
                     error.printStackTrace();
                  }
               }
            });
            
         botaoAdicionarProdutoEvento.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                  statusAtual = "";
                  modeloLista.removeAllElements();
                  lista.setVisible(false);
                  botaoConcluirAcao.setVisible(false);
                  modeloLista.removeAllElements();
                  ArrayList<Evento> listaEventos = ie.buscarEventos();
                  listaEventos.forEach(
                     (evento) -> {
                        modeloLista.addElement(evento.getId() + " - " + evento.getNome() + " - " + evento.getDataEvento());
                     });
                  lista.setVisible(true);
                  JOptionPane.showMessageDialog(paramFrame, "Selecione um Evento da lista abaixo:");
                  statusAtual = "SelecionarEvento";
               }
            });
            
         botaoGerarListaProdutos.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  statusAtual = "";
                  modeloLista.removeAllElements();
                  modeloLista.addElement("ID - Nome - Preço - Qntd - Gasto Diário - Num de Consumidores - Validade - "
                          + "Previsão de Falta");
                  modeloLista.addElement("\n");
                  ArrayList<Produto> listaProdutos = ip.buscarProdutos();
                  listaProdutos.forEach(
                     (produto) -> {
                        modeloLista.addElement(produto.getId() + " - " + produto.getNome() + " - " + produto.getPreco() + " - " + 
                              produto.getQuantidade() + " - " + produto.getGastoDiario() + " - " + 
                              produto.getNumeroConsumidores() + " - " + produto.getValidade() + " - " + 
                              produto.getPrevisaoFalta());
                     });
                  lista.setVisible(true);
                  botaoConcluirAcao.setVisible(true);
               }
            });
            
         botaoGerarListaCompraProdutos.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  statusAtual = "";
                  modeloLista.removeAllElements();
                  lista.setVisible(false);
                  botaoConcluirAcao.setVisible(false);
                  ArrayList<Produto> listaCompra = new ArrayList<>(0);
                  ArrayList<Produto> vencidos = ip.vencimento();
                  ArrayList<Produto> falta = ip.falta();
                  ArrayList<Evento> eventosDentre3Dias = ie.buscarEvento();
                  ArrayList<Produto> itensEventos = new ArrayList<>();
                  eventosDentre3Dias.forEach(
                     (evento) -> {
                        itensEventos.addAll(ip.buscarItensEvento(evento));
                     });
                  listaCompra.addAll(vencidos);
                  listaCompra.addAll(falta);
                  listaCompra.addAll(itensEventos);
                  modeloLista.addElement("ID - Nome - Preço - Qntd - Gasto Diário - Num de Consumidores - Validade - "
                          + "Previsão de Falta");
                  modeloLista.addElement("\n");
                  listaCompra.forEach(
                     (produto) -> {
                        modeloLista.addElement(produto.getId() + " - " + produto.getNome() + " - " + produto.getPreco() + " - " + 
                              produto.getQuantidade() + " - " + produto.getGastoDiario() + " - " + 
                              produto.getNumeroConsumidores() + " - " + produto.getValidade() + " - " + 
                              produto.getPrevisaoFalta());
                     });
                  lista.setVisible(true);
                  botaoConcluirAcao.setVisible(true);
               }
            });
         
         botaoConcluirAcao.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                  modeloLista.removeAllElements();
                  lista.setVisible(false);
                  botaoConcluirAcao.setVisible(false);
                  JOptionPane.showMessageDialog(paramFrame, "Feito!");
               }
            });
         
         lista.addMouseListener(
            new MouseListener(){
               @Override
               public void mouseClicked(MouseEvent e) {
                  if(statusAtual.equals("SelecionarEvento")){
                     int opcaoDialog = JOptionPane.showConfirmDialog(paramFrame, "Tem certeza que deseja selecionar esse evento?");
                     if(opcaoDialog == JOptionPane.YES_OPTION){
                        try{
                           int clicado = lista.getSelectedIndex();
                           String selecionado = modeloLista.getElementAt(clicado);
                           int id = Integer.parseInt(selecionado.split(" - ")[0]);
                           eventoSelecionado = ie.buscarEvento(id);
                           lista.setVisible(false);
                           JOptionPane.showMessageDialog(paramFrame, "Evento Selecionado! " + eventoSelecionado.getNome() + " - " + eventoSelecionado.getDataEvento());
                           statusAtual = "SelecionarProdutoEvento";
                           modeloLista.removeAllElements();
                           ArrayList<Produto> listaProdutos = ip.buscarProdutos();
                           listaProdutos.forEach(
                              (produto) -> {
                                 modeloLista.addElement(produto.getId() + " - " + produto.getNome());
                              });
                           lista.setVisible(true);
                           botaoConcluirAcao.setVisible(true);
                           JOptionPane.showMessageDialog(paramFrame, "Adicione Produto(s) da lista abaixo:");
                        }catch(Exception error){
                           JOptionPane.showMessageDialog(paramFrame, "Ocorreu um erro!");
                        }
                     }
                  }
                  else{
                     if(statusAtual.equals("SelecionarProdutoEvento")){
                        int opcaoEvento = JOptionPane.showConfirmDialog(paramFrame, "Tem certeza que deseja adicionar esse Produto?");
                        if(opcaoEvento == JOptionPane.YES_OPTION){
                           try{
                              String selecionado = modeloLista.getElementAt(lista.getSelectedIndex());
                              int id = Integer.parseInt(selecionado.split(" - ")[0]);
                              ie.inserirItem(eventoSelecionado, ip.busca(id));
                              JOptionPane.showMessageDialog(paramFrame, "Produto Inserido com sucesso! Pode inserir mais."); 
                           }catch(Exception error){
                              JOptionPane.showMessageDialog(paramFrame, "Ocorreu um erro!");
                           }
                        }
                     }
                  } 
               }
            
               @Override
               public void mousePressed(MouseEvent e) {
                 
               }
            
               @Override
               public void mouseReleased(MouseEvent e) {
                 
               }
            
               @Override
               public void mouseEntered(MouseEvent e) {
                 
               }
            
               @Override
               public void mouseExited(MouseEvent e) {
                 
               }
            
            });
         
       
      }
            
      @Override
        public Dimension getPreferredSize() {
         return new Dimension(800, 600);
      }
   }
}
