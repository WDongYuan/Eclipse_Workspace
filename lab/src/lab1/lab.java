package lab1;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class lab {
	public static int[][] flag[]=new  int[4][14][14];//默认初始化值为0,输出是用到
	public static int[] best=new int[14]; //最优服务
	public static float[][] data=new float[14][3];
	public static float req[][] = new float[5][2];  //存需求

	public static void main(String[] args){
		float rp[][][]= new float[14][500][2];       //存R与p值
		int p[][]=new int[4][14];      //标记每个流程中有哪些活动,默认初始为0
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
		System.out.println("运行时间："+(time2-time1)+"ms");
	}
	
	//读Service文件
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
	
	//读Process文件
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
				String temp=ss.nextToken(); //存储每一个单词
				p[line][temp.charAt(0)-'A']=1;
				p[line][temp.charAt(2)-'A']=1;
				flag[line][temp.charAt(0)-'A'][temp.charAt(2)-'A']=1; //标记4个流程分别有哪些边
				}
				line++; }
			st.close();}
		catch(FileNotFoundException ee)  {ee.printStackTrace();}
		catch(IOException e)  {e.printStackTrace();}
		return p;
	}
	
	//读Req文件
	public static float[][] readReq(){
		float req[][] = new float[5][2];
		int line=0;
		String s = null;
		try{
			BufferedReader st = new BufferedReader(new FileReader("F:\\REQ.txt"));
			while((s=st.readLine())!=null&&s.length()!=0){ // s.length()!=0处理超出数组范围问题
				req[line][0]=Float.parseFloat(s.substring(1, 4));
				req[line][1]=Float.parseFloat(s.substring(5, 7));
				line++;
				}
			st.close();}
		catch(FileNotFoundException ee)  {ee.printStackTrace();}
		catch(IOException e)  {e.printStackTrace();}
		return req;
	}
	
	//写RESULT文件
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
	   			    if(data[k][0]>=req[k][0]&&data[k][1]<req[k][1])   //检验是否满足要求
	   			    	bw.write("Reliability="+(float)Math.round((float)(data[k][0]*10000))/10000+", Cost="+(float)Math.round((float)(data[k][1]*100))/100+", Q="+(float)Math.round((float)(data[k][2]*10000))/10000);
	   		        bw.newLine(); }
	   		bw.close();
	   		fw.close();
	   		System.out.println("写入成功！");
	   }
	   catch (IOException e){
		   System.out.println("写入失败！");
		   e.printStackTrace();
	   }
    }
   
  //计算各个服务活动的最佳候选活动
   public static void Cal(float[][][] rp){ 
	   double temp=0;
	   for(int i=0;i<14;i++){
	       temp=0.85*(rp[i][0][0]-1)-rp[i][0][1]/100;
	       best[i]=0;  //初始化最优候选为第一个服务
		   for(int j=0;j<500;j++)
		     if(temp<(0.85*(rp[i][j][0]-1)-rp[i][j][1]/100)) {
		    	 best[i]=j;   //更新最优服务
		    	 temp=0.85*(rp[i][j][0]-1)-rp[i][j][1]/100;//更新temp值
		     }
	   }	   	   
   }
   
   //计算每个流程的三项参数，存储到data[14][3]
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
	