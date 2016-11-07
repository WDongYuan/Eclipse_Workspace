package lab1;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class lab {
	public static int[][] flag[]=new  int[4][14][14];//Ĭ�ϳ�ʼ��ֵΪ0,������õ�
	public static int[] best=new int[14]; //���ŷ���
	public static float[][] data=new float[14][3];
	public static float req[][] = new float[5][2];  //������

	public static void main(String[] args){
		float rp[][][]= new float[14][500][2];       //��R��pֵ
		int p[][]=new int[4][14];      //���ÿ������������Щ�,Ĭ�ϳ�ʼΪ0
		long time1=System.currentTimeMillis();
		System.out.println(time1);
		rp=readService();
		p=readProcess();
		req=readReq();
		Cal(rp);
		CalData(p,rp);
		output();
		long time2=System.currentTimeMillis();
		System.out.println(time2);
		System.out.println("����ʱ�䣺"+(time2-time1)+"ms");
	}
	
	//��Service�ļ�
	public static float[][][] readService(){
		String s = null;
		String data[][][] = new String[14][500][5];
		float rp[][][] = new float[14][500][2];
		int i = 0;
		try{
			BufferedReader st = new BufferedReader(new FileReader("F:\\SERVICE.txt"));
			while((s=st.readLine())!=null){
				StringTokenizer ss = new StringTokenizer(s);
				while(ss.hasMoreTokens()){
					data[i/2500][(i/5)%500][i%5]=ss.nextToken();
					i++;   }
				}
			st.close();}
		catch(FileNotFoundException ee)  {ee.printStackTrace();}
		catch(IOException e)  {e.printStackTrace();}
		for(int m=0;m<14;m++)
			for(int j=0;j<500;j++){
				rp[m][j][0]=Float.parseFloat(data[m][j][2]);
				rp[m][j][1]=Float.parseFloat(data[m][j][4]);
			}
		return rp;
		
	}
	
	//��Process�ļ�
	public static int[][] readProcess(){
		int[][] p = new int[4][14];
		String s = null;
	    int line=0;
		for(int i=0;i<4;i++) Arrays.fill(p[i], 0);
		try{
			BufferedReader st = new BufferedReader(new FileReader("F:\\PROCESS.txt"));
			while((s=st.readLine())!=null){
				StringTokenizer ss = new StringTokenizer(s,"(");
				while(ss.hasMoreTokens()){
				String temp=ss.nextToken(); //�洢ÿһ������
				p[line][temp.charAt(0)-'A']=1;
				p[line][temp.charAt(2)-'A']=1;
				flag[line][temp.charAt(0)-'A'][temp.charAt(2)-'A']=1; //���4�����̷ֱ�����Щ��
				}
				line++; }
			st.close();}
		catch(FileNotFoundException ee)  {ee.printStackTrace();}
		catch(IOException e)  {e.printStackTrace();}
		return p;
	}
	
	//��Req�ļ�
	public static float[][] readReq(){
		float req[][] = new float[5][2];
		int line=0;
		String s = null;
		try{
			BufferedReader st = new BufferedReader(new FileReader("F:\\REQ.txt"));
			while((s=st.readLine())!=null&&s.length()!=0){ // s.length()!=0���������鷶Χ����
				req[line][0]=Float.parseFloat(s.substring(1, 4));
				req[line][1]=Float.parseFloat(s.substring(5, 7));
				line++;
				}
			st.close();}
		catch(FileNotFoundException ee)  {ee.printStackTrace();}
		catch(IOException e)  {e.printStackTrace();}
		return req;
	}
	
	//дRESULT�ļ�
   public static void output(){
	   File file=new File("F:\\RESULT.txt");
	   FileWriter fw=null;
	   BufferedWriter bw=null;
	   try{
	   		fw=new FileWriter(file);
	   		bw=new BufferedWriter(fw);
	   		for(int k=0;k<4;k++){
	   			for(int j=0;j<14;j++)
	   			  for(int i=0;i<14;i++) {
	   				if(flag[k][i][j]!=0)
	   					bw.write("("+(char)('A'+i)+"-"+best[i]+","+(char)('A'+j)+"-"+best[j]+"),");
	   		    }   bw.newLine();
	   			    if(data[k][0]>=req[k][0]&&data[k][1]<req[k][1])   //�����Ƿ�����Ҫ��
	   			    	bw.write("Reliability="+(float)Math.round((float)(data[k][0]*10000))/10000+", Cost="+(float)Math.round((float)(data[k][1]*100))/100+", Q="+(float)Math.round((float)(data[k][2]*10000))/10000);
	   		        bw.newLine(); }
	   		bw.close();
	   		fw.close();
	   		System.out.println("д��ɹ���");
	   }
	   catch (IOException e){
		   System.out.println("д��ʧ�ܣ�");
		   e.printStackTrace();
	   }
    }
   
  //���������������Ѻ�ѡ�
   public static void Cal(float[][][] rp){ 
	   double temp=0;
	   for(int i=0;i<14;i++){
	       temp=0.85*(rp[i][0][0]-1)-rp[i][0][1]/100;
	       best[i]=0;  //��ʼ�����ź�ѡΪ��һ������
		   for(int j=0;j<500;j++)
		     if(temp<(0.85*(rp[i][j][0]-1)-rp[i][j][1]/100)) {
		    	 best[i]=j;   //�������ŷ���
		    	 temp=0.85*(rp[i][j][0]-1)-rp[i][j][1]/100;//����tempֵ
		     }
	   }	   	   
   }
   
   //����ÿ�����̵�����������洢��data[14][3]
   public static void CalData(int p[][],float[][][] rp){ 
	   for(int i=0;i<4;i++){
		   data[i][0]=1;
		   data[i][1]=0;
		   for(int j=0;j<14;j++)
			   if(p[i][j]!=0){
				   data[i][0]*=rp[j][best[j]][0];
				   data[i][1]+=rp[j][best[j]][1];
			   }
		  data[i][2]=data[i][0]-data[i][1]/100;   
	   }
   }
   
}
	