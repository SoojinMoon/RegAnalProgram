package test;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import test.ScatterPlot;

public class task {
	public static void main(String[] args){
		Fileopen one = new Fileopen();
		one.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Fileopen extends JFrame implements ActionListener, ItemListener{
	private Container con;
	private String[] scol;
	private String [][] scell;
	private DefaultTableModel model = new DefaultTableModel(scell,scol);
	private JTable jt= new JTable(model);
	private DefaultTableModel jt1 = new DefaultTableModel();
	private JScrollPane jsp=new JScrollPane(jt);
	private ImageIcon im = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/s2.jpg");
	private ImageIcon openima = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/����.jpg");
	private ImageIcon saveima = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/����.jpg");
	private ImageIcon closeima = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/����.jpg");
	private FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text files(.txt)","txt","TXT");
	private FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV Files(.csv)","csv","CSV");
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private JButton ok = new JButton("Ȯ��");
	private JButton no = new JButton("���");
	private JMenuBar menubar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu analysisMenu = new JMenu("�м�");
	private JMenuItem open = new JMenuItem("Open",openima);
	private JMenuItem close = new JMenuItem("Close",closeima);
	private JMenuItem plot = new JMenuItem("���������",openima);
	private JMenuItem anal = new JMenuItem("�л�м�ǥ",openima);
	private JMenu residualanal = new JMenu("�����м�");
	private JMenuItem anal1 = new JMenuItem("�����м�");
	private JMenuItem anal2 = new JMenuItem("������ �� ���� �׸�");
	private JFrame frame = new JFrame();
	private String [] newrow = new String [5];
	private String [] newcol = new String [5];
	private String[] str = new String [5];
	private JFrame Pf;
	private Label ylabel = new Label("��������",1);
	private Label xlabel = new Label("������",1);
	private Choice choice = new Choice();
	private JButton selbt1 = new JButton("=>");
	private JButton selbt2 = new JButton("<=");
	private JButton selok = new JButton("Ȯ��");
	private JButton selok1 = new JButton("Ȯ��");
	private JButton selok2= new JButton("Ȯ��");
	private JButton selok3 = new JButton("Ȯ��");
	private JButton selno = new JButton("���");
	private List list1 = new List();
	private List list2 = new List();
	
	
	public Fileopen(){ //����â
		super("Package");
		init();
		start();
		setSize(600,500);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();
		xpos = (int)(dimen.getWidth()/2-dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);
		setIconImage(im.getImage());
		setVisible(true);
	}
	
	public void init(){ //����â ����
		con = this.getContentPane();
		con.setLayout(new BorderLayout(5,5));

		fileMenu.add(open);  fileMenu.addSeparator(); fileMenu.add(close);
		analysisMenu.add(plot);analysisMenu.addSeparator();analysisMenu.add(anal);
		analysisMenu.addSeparator();analysisMenu.add(residualanal);
		residualanal.add(anal1); residualanal.add(anal2);
		menubar.add(fileMenu);
		menubar.add(analysisMenu);
		con.add("North", menubar);
		con.add("Center",jsp);
		
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(ok); jp.add(no);
		con.add("South",jp);
	}
	public void start(){ //����â �̺�Ʈ
		open.addActionListener(this);
		close.addActionListener(this);
		plot.addActionListener(this);
		anal.addActionListener(this);
		ok.addActionListener(this);
		no.addActionListener(this);
		anal1.addActionListener(this);
		anal2.addActionListener(this);
	}

	public Fileopen(DefaultTableModel model){//���� ����â - �л� �м�ǥ
		super("�л� �м�");
		jt1 = model;
		setselectframe();
		setselectframeevent();
		setSize(400,300);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();
		xpos = (int)(dimen.getWidth()/2-dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);
		setIconImage(im.getImage());
		setVisible(true);
	}
	
	public Fileopen(DefaultTableModel model,String title){//������ ��� ���� ����â
		setTitle(title);
		jt1 = model;
		setselectframe1();
		setselectframeevent();
		setSize(400,300);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();
		xpos = (int)(dimen.getWidth()/2-dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);
		setIconImage(im.getImage());
		setVisible(true);
	}
	
	public Fileopen(DefaultTableModel model,String[] title){ //�����м�  ��������â
		setTitle(title[0]);
		jt1 = model;
		setselectframe2();
		setselectframeevent();
		setSize(400,300);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();
		xpos = (int)(dimen.getWidth()/2-dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);
		setIconImage(im.getImage());
		setVisible(true);
	}
	
	public Fileopen(DefaultTableModel model, int a){ //�����м�  ��������â
		setTitle("������ �� �����м�");
		jt1 = model;
		setselectframe3();
		setselectframeevent();
		setSize(400,300);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();
		xpos = (int)(dimen.getWidth()/2-dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);
		setIconImage(im.getImage());
		setVisible(true);
	}
	
	public void setselectframe(){//��������â ����
		con = this.getContentPane();
		con.setLayout(new BorderLayout(5,5));

		for(int i =0 ; i< 5;i++){
			str[i] = jt1.getColumnName(i);
		}
		
		for(int i =0;i< str.length; i++){
			list1.add(str[i]);
			choice.add(str[i]);
		}
		
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1.add(ylabel); jp1.add(choice);
		con.add("North",jp1);
		
		JPanel jp4 = new JPanel(new BorderLayout(5,5));
		JPanel jp2 = new JPanel(new FlowLayout());
		JPanel jp3 = new JPanel(new GridLayout(2,1,10,10));
		jp3.add(selbt1); jp3.add(selbt2);
		jp2.add(list1); jp2.add(jp3); jp2.add(list2);
		jp4.add("North", xlabel);jp4.add("Center", jp2);
		con.add("Center", jp4);
		
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(selok); jp.add(selno);
		con.add("South",jp);
	}
	
	public void setselectframe1(){//��������â ����, ������ ���
		con = this.getContentPane();
		con.setLayout(new BorderLayout(5,5));

		for(int i =0 ; i< 5;i++){
			str[i] = jt1.getColumnName(i);
		}
		
		for(int i =0;i< str.length; i++){
			list1.add(str[i]);
			choice.add(str[i]);
		}
		
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1.add(ylabel); jp1.add(choice);
		con.add("North",jp1);
		
		JPanel jp4 = new JPanel(new BorderLayout(5,5));
		JPanel jp2 = new JPanel(new FlowLayout());
		JPanel jp3 = new JPanel(new GridLayout(2,1,10,10));
		jp3.add(selbt1); jp3.add(selbt2);
		jp2.add(list1); jp2.add(jp3); jp2.add(list2);
		jp4.add("North", xlabel);jp4.add("Center", jp2);
		con.add("Center", jp4);
		
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(selok1);
		con.add("South",jp);
	}
	
	public void setselectframe2(){//��������â ����, �����м�
		con = this.getContentPane();
		con.setLayout(new BorderLayout(5,5));
		for(int i =0 ; i< 5;i++){
			str[i] = jt1.getColumnName(i);
		}
		
		for(int i =0;i< str.length; i++){
			list1.add(str[i]);
			choice.add(str[i]);
		}
		
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1.add(ylabel); jp1.add(choice);
		con.add("North",jp1);
		
		JPanel jp4 = new JPanel(new BorderLayout(5,5));
		JPanel jp2 = new JPanel(new FlowLayout());
		JPanel jp3 = new JPanel(new GridLayout(2,1,10,10));
		jp3.add(selbt1); jp3.add(selbt2);
		jp2.add(list1); jp2.add(jp3); jp2.add(list2);
		jp4.add("North", xlabel);jp4.add("Center", jp2);
		con.add("Center", jp4);
		
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(selok2);
		con.add("South",jp);
	}
	
	public void setselectframe3(){//��������â ����, �����м��� ������
		con = this.getContentPane();
		con.setLayout(new BorderLayout(5,5));
		for(int i =0 ; i< 5;i++){
			str[i] = jt1.getColumnName(i);
		}
		
		for(int i =0;i< str.length; i++){
			list1.add(str[i]);
			choice.add(str[i]);
		}
		
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jp1.add(ylabel); jp1.add(choice);
		con.add("North",jp1);
		
		JPanel jp4 = new JPanel(new BorderLayout(5,5));
		JPanel jp2 = new JPanel(new FlowLayout());
		JPanel jp3 = new JPanel(new GridLayout(2,1,10,10));
		jp3.add(selbt1); jp3.add(selbt2);
		jp2.add(list1); jp2.add(jp3); jp2.add(list2);
		jp4.add("North", xlabel);jp4.add("Center", jp2);
		con.add("Center", jp4);
		
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp.add(selok3);
		con.add("South",jp);
	}
	
	public void setselectframeevent(){//���� ���� �̺�Ʈ
		selok.addActionListener(this);
		selok1.addActionListener(this);
		selok2.addActionListener(this);
		selok3.addActionListener(this);
		selno.addActionListener(this);
		selbt1.addActionListener(this);
		selbt2.addActionListener(this);
		choice.addItemListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		
		if(label.equals("Open")){//JTABLE �ҷ�����
			File dir = new File("/Users/munsujin/Downloads/workspace/workspace");
			
			final JFileChooser fc = new JFileChooser(dir);
			fc.addChoosableFileFilter(textFilter);
			fc.addChoosableFileFilter(csvFilter);
			fc.setFileFilter(textFilter);

			int val = fc.showDialog(frame, "����");
			if(val == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				try {
					Scanner in = new Scanner(file);
					for(int i =0; i<5 ; i++){newcol[i] = in.next();}
					model.setColumnIdentifiers(newcol);
					while(in.hasNext()){
						for(int i =0; i<5 ; i++){newrow[i] = in.next();}
						model.addRow(newrow); 
						}
					}
				catch (FileNotFoundException e1) {	
					e1.printStackTrace();
				}
			}
			
		}
		if(e.getSource()==close){
			System.exit(0);
		}
		if(e.getSource()==ok){
			System.exit(0);
		}
		if(e.getSource()==no){
			System.exit(0);
		}
		if(e.getSource()==plot){//������ ���� ������
			Fileopen one = new Fileopen(model,"������ ���");
			one.setVisible(true);
		}
		
		if(e.getSource()==anal){//�л�м� ������
			Fileopen one1 = new Fileopen(model);
			one1.setVisible(true);
		}
		if(e.getSource()==anal1){
			String [] title = {"���� �м�"};
			Fileopen one = new Fileopen(model,title);
			one.setVisible(true);
		}
		if(e.getSource()==anal2){
			Fileopen two = new Fileopen(model,1);
			two.setTitle("������ �� �����м�");
			two.setVisible(true);
		}
		
		
		if(e.getSource()==selok){//�л�м�ǥ ����,
			ImageIcon im = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/s2.jpg");
			
			
			
			int n = jt1.getRowCount();
			int p = list2.getItemCount()+1; 
			Object [][] objx = new Object [n][p];
			Object [] objy = new Object [n];
			double [][] x = new double [n][p];
			double [] y = new double [n];
			String [] namex = new String[p];
			String [] namey = new String[1];
			
			for(int j=0; j<jt1.getColumnCount();j++){
				for(int i=0 ;i< list2.getItemCount();i++){	
					if(jt1.getColumnName(j)==list2.getItem(i)){
						for(int k=0; k<n;k++){
							objx[k][0] = 1;
							objx[k][i+1] =  jt1.getValueAt(k, j);
						}
					}
					if(jt1.getColumnName(j)==choice.getSelectedItem()){
						for(int k=0; k<n;k++){
							objy[k] =  jt1.getValueAt(k, j);
						}
					}
				}		
			}
			
			namex[0] = "Intercept";
			for(int i=0 ;i< list2.getItemCount();i++){	
				namex[i+1] = list2.getItem(i);
			}
			namey[0] = choice.getSelectedItem();
			
			for(int j=0 ; j< p;j++){
				for(int i=0 ; i<n;i++){
					x[i][j] = Double.parseDouble(objx[i][j].toString());
					y[i]= Double.parseDouble(objy[i].toString());
				}			
			}
			
			
			Analysisofvariancetable wow = new Analysisofvariancetable(x,y,n,p,namex,namey);
			JFrame jframe = new JFrame("Anova table");
			JScrollPane sp = new JScrollPane(wow,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jframe.add(sp);
			jframe.pack();
			jframe.setIconImage(im.getImage());
			jframe.setLocationRelativeTo(null);
			jframe.setVisible(true);
		}
		
		if(e.getSource()==selok1){	//��������� ���� �̺�Ʈ
			ImageIcon im = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/s2.jpg");
			
			int n = jt1.getRowCount();
			int p = list2.getItemCount()+1; 
			Object [][] objx = new Object [n][p];
			
			double [][] x = new double [n][p+1];
			
			String [] namex = new String[p];
			String [] namey = new String[1];
			
			for(int j=0; j<jt1.getColumnCount();j++){
				for(int i=0 ;i< list2.getItemCount();i++){	
					if(jt1.getColumnName(j)==choice.getSelectedItem()){
						for(int k=0; k<n;k++){
							objx[k][0] =  jt1.getValueAt(k, j);
						}
					}
					if(jt1.getColumnName(j)==list2.getItem(i)){
						for(int k=0; k<n;k++){
							objx[k][i+1] =  jt1.getValueAt(k, j);
						}
					}
				}		
			}
			
			for(int i=0 ;i< list2.getItemCount();i++){	
				namex[i] = list2.getItem(i);
			}
			namey[0] = choice.getSelectedItem();
			
			for(int j=0 ; j< p;j++){
				for(int i=0 ; i<n;i++){
					x[i][j] = Double.parseDouble(objx[i][j].toString());

				}			
			}
			Plotframe wow = new Plotframe(x,n,p,namex,namey);
			Pf = new JFrame("������ ���");
			Pf.add(wow);
			Pf.pack();
			Pf.setIconImage(im.getImage());
			Pf.setLocationRelativeTo(null);
			Pf.setVisible(true);
		}
		
		if(e.getSource()==selok2){	//���� �м��� ���� �̺�Ʈ
			int n = jt1.getRowCount();
			int p = list2.getItemCount()+1; //p�� ������ �Ѱ���
			Object [][] objx = new Object [n][p];
			Object [] objy = new Object [n];
			double [][] x = new double [n][p];
			double [] y = new double [n];
			String [] namex = new String[p];
			String [] namey = new String[1];
			
			for(int j=0; j<jt1.getColumnCount();j++){
				for(int i=0 ;i< list2.getItemCount();i++){	
					if(jt1.getColumnName(j)==list2.getItem(i)){
						for(int k=0; k<n;k++){
							objx[k][0] = 1;
							objx[k][i+1] =  jt1.getValueAt(k, j);
						}
					}
					if(jt1.getColumnName(j)==choice.getSelectedItem()){
						for(int k=0; k<n;k++){
							objy[k] =  jt1.getValueAt(k, j);
						}
					}
				}		
			}
			
			namex[0] = "Intercept";
			for(int i=0 ;i< list2.getItemCount();i++){	
				namex[i+1] = list2.getItem(i);
			}
			namey[0] = choice.getSelectedItem();
			
			for(int j=0 ; j< p;j++){
				for(int i=0 ; i<n;i++){
					x[i][j] = Double.parseDouble(objx[i][j].toString());
					y[i]= Double.parseDouble(objy[i].toString());
				}			
			}
			Analysisofvariancetable wow = new Analysisofvariancetable(x,y,n,p,namex,namey);
			double [] prey =wow.prey;
			double [] residual =wow.residual;
			double [] stdres= wow.stdres;
			double [] cooksd =wow.cooksd;
			
			ResidualAnal res = new ResidualAnal(prey, residual, stdres,y, cooksd,namey,n);
			JFrame jframe1 = new JFrame("�����м�");
			jframe1.add(res);
			jframe1.pack();
			jframe1.setIconImage(im.getImage());
			jframe1.setLocationRelativeTo(null);
			jframe1.setVisible(true);
			
		}
		
		if(e.getSource()==selok3){
			int n = jt1.getRowCount();
			int p = list2.getItemCount()+1; //p�� ������ �Ѱ���
			Object [][] objx = new Object [n][p];
			Object [] objy = new Object [n];
			double [][] x = new double [n][p];
			double [] y = new double [n];
			String [] namex = new String[p];
			String [] namey = new String[1];
			
			for(int j=0; j<jt1.getColumnCount();j++){
				for(int i=0 ;i< list2.getItemCount();i++){	
					if(jt1.getColumnName(j)==list2.getItem(i)){
						for(int k=0; k<n;k++){
							objx[k][0] = 1;
							objx[k][i+1] =  jt1.getValueAt(k, j);
						}
					}
					if(jt1.getColumnName(j)==choice.getSelectedItem()){
						for(int k=0; k<n;k++){
							objy[k] =  jt1.getValueAt(k, j);
						}
					}
				}		
			}
			
			namex[0] = "Intercept";
			for(int i=0 ;i< list2.getItemCount();i++){	
				namex[i+1] = list2.getItem(i);
			}
			namey[0] = choice.getSelectedItem();
			
			for(int j=0 ; j< p;j++){
				for(int i=0 ; i<n;i++){
					x[i][j] = Double.parseDouble(objx[i][j].toString());
					y[i]= Double.parseDouble(objy[i].toString());
				}			
			}
			Analysisofvariancetable wow = new Analysisofvariancetable(x,y,n,p,namex,namey);
			double [] residual =wow.residual;
			ImageIcon im = new ImageIcon("/Users/munsujin/Downloads/workspace/workspace/Soojin/src/s2.jpg");
			
			for(int j=0; j<jt1.getColumnCount();j++){
				for(int i=0 ;i< list2.getItemCount();i++){	
					if(jt1.getColumnName(j)==choice.getSelectedItem()){
						for(int k=0; k<n;k++){
							objx[k][0] =  jt1.getValueAt(k, j);
						}
					}
					if(jt1.getColumnName(j)==list2.getItem(i)){
						for(int k=0; k<n;k++){
							objx[k][i+1] =  jt1.getValueAt(k, j);
						}
					}
				}		
			}
			
			for(int i=0 ;i< list2.getItemCount();i++){	
				namex[i] = list2.getItem(i);
			}
			namey[0] = choice.getSelectedItem();
			
			for(int j=0 ; j< p;j++){
				for(int i=0 ; i<n;i++){
					x[i][j] = Double.parseDouble(objx[i][j].toString());

				}			
			}
			
			for(int i =0 ; i< n ; i++){
				x[i][0] = residual[i];
			}
			ResidualAnal2 woow = new ResidualAnal2(x,n,p,namex,namey);
			
			JFrame jframe = new JFrame("������ �� ����");
			jframe.add(woow);
			jframe.pack();
			jframe.setIconImage(im.getImage());
			jframe.setLocationRelativeTo(null);
			jframe.setVisible(true);
			
		}
		if(e.getSource()==selno){	
			setVisible(false);
		}
		
		if(e.getSource()==selbt1){	
			String str = list1.getSelectedItem();
			list2.add(str);
			list1.remove(str);
		}
		if(e.getSource()==selbt2){	
			String str = list2.getSelectedItem();
			list1.add(str);
			list2.remove(str);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==choice){	
			String str = choice.getSelectedItem();
			list1.remove(str);
	}
		
	}
}		