package test;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Plotframe extends JPanel{
	
	double [][] x;
	double [][] xminmax;
	String[] namex = {};
	String[] namey = {};
	double minx =0, miny = 0, maxx = 0, maxy =0;
	int wd = 800;
	int ht = 550;
	int m = 40;
	int r = 5;
	int ptsize = 4 ;
	int n; int p; int ht1; int wd1;
	double [][] A;
	public Plotframe(double [][]x,  int n , int p, String[] namex, String[] namey){
		this.x = x;this.n = n ; this.p =p; this.namex = namex; this.namey = namey;//p�� ���������� �ǹ�
		//ù° ���� ���� ������ �ڷᰪ , 2~p °�� ���� �������� ������, ���� ������ŭ �������� �׷��ְ� �� �ȿ� �������� ������ŭ ���� ����ֱ� ���� �Ű������� ����
		xminmax = new double [3][p]; 
		A = new double [n][p];
		
		for(int j=0; j<p;j++){
			for(int i =0 ; i<n; i++){
				A[i][j] = x[i][j];
			}
		}


		for(int k=0; k<p;k++){
			for(int i=0;i<n;i++){
				for(int j=i+1;j<n; j++){
					double test = A[i][k];
					if(A[j][k]<test){
						A[i][k] = A[j][k];
						A[j][k] = test;
					}
				}
			}
		}
		
		for(int j=0; j<p ; j++){
				xminmax[0][j] = A[0][j];
				xminmax[1][j] = A[n-1][j];

		}
		setPreferredSize(new Dimension(wd,ht));
		
	}

	public void paint(Graphics g){
		super.paint(g);
		int xpxl, ypxl;
		wd = this.getWidth();
		ht = this.getHeight();
		g.setColor(Color.BLACK); 
		Font font = new Font("Serif",Font.BOLD,16);g.setFont(font);
		Font font2 = new Font("Serif",Font.BOLD,13);
		g.drawString("������ ���", (wd-2*m)/100*46, m/2);
		Font font1 = new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,13);
		g.setFont(font1);
		
		wd1 = (wd-2*m-(p-1)*r)/(p); // ������ �ϳ��� ������ �ʺ�� ����
		ht1 = (ht-2*m-(p-1)*r)/(p);
		
		for(int j =0 ; j<p; j++){ // 
			for(int i =0 ; i< p; i++){
				g.drawRect(m+(wd1+r)*i, m+(ht1+r)*j, wd1, ht1);
			}
		}
		
		g.drawString(namey[0],m+wd1/4, m+ht1/2);
		for(int i =0 ; i< p-1; i++){
			g.drawString(namex[i],(int) (m+wd1/5+(r+(double)wd1/100*98)*(i+1)), (int) (m+wd1/2+(r+(double)ht1/100*94)*(i+1)));
		}
		
		
		for(int k =0; k< p-1; k++){//ù ��° ����� p-1��° ����� ���� �����
			for(int j =k+1 ; j< p; j++){
				for(int i=0; i<n ; i++){
					xpxl = m +(ptsize/2)+ (wd1 + r )*j  +(int)((wd-2*m-(p-1+3)*r-(p-1)*wd1)*(x[i][j]-xminmax[0][j])/(xminmax[1][j]-xminmax[0][j]));
					ypxl = m+ (ht1 + r )*(k)+ r +(int)((ht-2*m-(p-1+2)*r-(p-1)*ht1)*(xminmax[1][k]-x[i][k])/(xminmax[1][k]-xminmax[0][k]));
					g.fillOval(xpxl, ypxl, ptsize, ptsize);
				}
			}
		}
		
	

		for(int k =p-1; k>0; k--){//p��° ����� 2��° ����� ���� �����
			for(int j =k-1 ; j>=0; j--){
				for(int i=0; i<n ; i++){
					xpxl = m +(ptsize/2)+ (wd1 + r )*j  +(int)((wd-2*m-(p-1+3)*r-(p-1)*wd1)*(x[i][j]-xminmax[0][j])/(xminmax[1][j]-xminmax[0][j]));
					ypxl = m + (ht1 + r)*(k)+ r +(int)((ht-2*m-(p-1+2)*r-(p-1)*ht1)*(xminmax[1][k]-x[i][k])/(xminmax[1][k]-xminmax[0][k]));
					g.fillOval(xpxl, ypxl, ptsize, ptsize);
				}
			}
		}
		
		g.setFont(font2);
		for( int j =0 ; j<p ; j++){
			for(int i =0 ; i < 2 ; i++){
				g.drawLine(m-8, m+(ht1+r)*j+ ht1*i, m-2,  m+(ht1+r)*j+ht1*i);
				g.drawLine(m+3+wd1*p+r*(p-1), m+(ht1+r)*j+ ht1*i, m+wd1*p+r*(p-1)+9,  m+(ht1+r)*j+ht1*i);
			}
		}	
	
		for( int j =0 ; j<p ; j++){
			for(int i =0 ; i < 2 ; i++){
				g.drawLine( m+(wd1+r)*j+wd1*i,ht-m+2,  m+(wd1+r)*j+wd1*i, ht-m+6);
			
			}
		}	
		
		for( int i =0 ; i<p ; i+=2){//���ʿ� y�ִ� �ּ� ǥ��
				g.drawString(String.format("%.0f",xminmax[1][i]), m/10*3,  m+ht1/10+(ht1+r)*i);
				g.drawString(String.format("%.0f",xminmax[0][i]), m/10*3,  m+ht1+(ht1+r)*i);
		}	
		for( int i =1 ; i<p ; i+=2){//�����ʿ� y�ִ� �ּ� ǥ��
			g.drawString(String.format("%.0f",xminmax[1][i]), m+wd1*p+r*(p-1),  m+ht1/10+(ht1+r)*i);
			g.drawString(String.format("%.0f",xminmax[0][i]), m+wd1*p+r*(p-1),  m+ht1+(ht1+r)*i);
		}	
	
		for( int j =0 ; j<p ; j++){//x, y�� ���� ǥ��
			for(int i =0 ; i < 2 ; i++){
				g.drawLine(m + wd1*i +(r+wd1)*j, m-8, m + wd1*i+(r+wd1)*j,  m-2);
				g.drawLine( m+(wd1+r)*j+wd1*i,ht-m+2,  m+(wd1+r)*j+wd1*i, ht-m+10);
			}
		}	
	
		for( int i =1 ; i<p ; i+=2){//���ʿ� x�ִ� �ּ� ǥ��
			g.drawString(String.format("%.0f",xminmax[0][i]), m+(wd1+r)*i, m);
			g.drawString(String.format("%.0f",xminmax[1][i]), m+wd1/20*17+(wd1+r)*i, m);
		}	
		for( int i =0 ; i<p ; i+=2){//�Ʒ��ʿ� x�ִ� �ּ� ǥ��
			g.drawString(String.format("%.0f",xminmax[0][i]), m+(wd1+r/5*8)*i, m+r*(p-1)+p*ht1+r*2);
			g.drawString(String.format("%.0f",xminmax[1][i]), m+wd1/20*17+(wd1+r/5*8)*i, m+r*(p-1)+p*ht1+r*2);
		}	

	
		
	
	}
	

	
}