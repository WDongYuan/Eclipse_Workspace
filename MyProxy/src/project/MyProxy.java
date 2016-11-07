package project;

import java.io.*;
import java.net.*;
import java.util.List;
import java.applet.Applet;

public class MyProxy extends Thread {
	public int bufsize;
	public int conntimes;
	public int timeout;
	public int pause;
	public Socket cli_soc;
	public Socket ser_soc;

	public MyProxy(Socket socket) {
		bufsize = 1024;
		conntimes = 5;
		timeout = 50;
		pause = 5;
		cli_soc = socket;
		start();
		//Run();
	}

	public void run() {
		System.out.println("Here?");
		try {
			String buffer = "";
			String URL = "";
			String host = "";
			int port = 80;
			InputStream cinput=null;
			OutputStream coutput = null;
			try {
				cli_soc.setSoTimeout(timeout);
				try {
					cinput =cli_soc.getInputStream();
					coutput = cli_soc.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String str="";
			 int c;
			 try {
				 c=cinput.read();
			while(c!='\n')
			{
				str=str+(char)c;
				c=cinput.read();
				System.out.print((char)c);
			}
			//System.out.println("ffffffffffffffffffuuuuuuuuuuuuuuccccccccccccccccckkkkkkkkkkkkkkkkkkkk");
			 str = str+"\n";
			//System.out.println("ffffffffffffffffffuuuuuuuuuuuuuuccccccccccccccccckkkkkkkkkkkkkkkkkkkk");
			 host=host+urlHandler(str);
			//System.out.println("ffffffffffffffffffuuuuuuuuuuuuuuccccccccccccccccckkkkkkkkkkkkkkkkkkkk");
			 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 ser_soc=new Socket(host,port);
			 while(ser_soc==null)
			 {
			 if(conntimes==0)
			 {
			 System.out.println("shit");
			 break;
			 }
			 ser_soc=new Socket(host,port);
			 conntimes--;
			 Thread.sleep(pause);
			 }
			 InputStream sinput=ser_soc.getInputStream();
			 OutputStream soutput=ser_soc.getOutputStream();
			 
			 
			 

			 soutput.write(str.getBytes());
			 int ir;
			 byte[] bytes = new byte[bufsize];

			 System.out.println("Begin here.");
			 
			 
			 while (true) {
					try {
						if ((ir = cinput.read(bytes)) > 0) {
							soutput.write(bytes, 0, ir);
							 //System.out.print (new String(bytes).trim ());
						} else if (ir < 0) {
							break;
						}
					} catch (InterruptedIOException e) {
					}
					try {
						if ((ir = sinput.read(bytes)) > 0) {
							coutput.write(bytes, 0, ir);
							 //System.out.print (new String(bytes).trim ());
						} else if (ir < 0) {
							break;
						}
					} catch (InterruptedIOException e) {
					}
			 }
//			 while(str!=null)
//			 {
//			 System.out.println(str);
//			 soutput.write((str+"\n").getBytes());
//			 str=cinput.readLine();
//			 }
//			 System.out.println();
//			 System.out.println("----------------------------------------------------------");
//			 System.out.println("server answer:");
//			 
////			 str=sinput.readLine();
////			 coutput.write((str+"\n").getBytes());
//			 ir=-1;
//			 bytes = new byte[bufsize];
//			 while (true) {
//					try {
//						if ((ir = sinput.read(bytes)) > 0) {
//							coutput.write(bytes, 0, ir);
//							 System.out.print (new String(bytes).trim ());
//						} else if (ir < 0) {
//							break;
//						}
//					} catch (InterruptedIOException e) {
//					}
//				}
////			 while(str!=null)
////			 {
////			 System.out.println("-----"+str);
////			 int c=sinput.read();
////			 coutput.write((str+"\n").getBytes());
////			 }
			 System.out.println("End");

		} catch (Exception e) {

			System.out.println("Error:" + e);
		}
		finally {
			try {
				cli_soc.close();
			} catch (Exception e1) {
			}
			try {
				ser_soc.close();
			} catch (Exception e2) {
			}
		}

	}

	public String urlHandler(String str) {
		String tmpstr=str;
		System.out.println("hahahahahahh"+str);
		String[] tmp1;
		String tmp2;
		tmp1 = tmpstr.split(" ");
		tmpstr=tmp1[1];
		if(tmpstr.indexOf("http://")!=-1)
		{
			tmp1 = tmpstr.split("http://");
			//tmp2 = tmp1[1];
			tmpstr=tmp1[1];
		}
		if(tmpstr.indexOf("/")!=-1)
		{
			tmp1 = tmpstr.split("/");
			tmpstr=tmp1[0];
		}
		return tmpstr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(10240);
			while (true) {
				Socket socket = serverSocket.accept();
				new MyProxy(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void run1() {
		System.out.println("run?");
		String line;
		String host;
		int port = 80; // 默认
		Socket outbound = null;
		try {
			cli_soc.setSoTimeout(timeout);
			InputStream is = cli_soc.getInputStream();
			OutputStream os = null;
			// 获取请求行的内容
			line = "";
			host = "";
			int state = 0;
			boolean space;
			while (true) {
				int c = is.read();
				if (c == -1) {
					break;
				}
				space = Character.isWhitespace((char) c);
				switch (state) {
				case 0:
					if (space) {
						continue;
					}
					state = 1;
				case 1:
					if (space) {
						state = 2;
						continue;
					}
					line = line + (char) c;
					break;
				case 2:
					if (space) {
						continue; // 跳过多个空白字符
					}
					state = 3;
				case 3: // ----------------------------------------------------------
					if (space) {
						state = 4;
						// 只取出主机名称部分
						String host0 = host;
						int n;
						n = host.indexOf("//");
						if (n != -1) {
							host = host.substring(n + 2);
						}
						n = host.indexOf('/');
						if (n != -1) {
							host = host.substring(0, n);
						}
						// 分析可能存在的端口号
						n = host.indexOf(":");
						if (n != -1) {
							port = Integer.parseInt(host.substring(n + 1));
							host = host.substring(0, n);
						}
						// 如果存在上级代理，则不用进行以上操作，直接和代理通讯
						int retry = conntimes;
						// 尝试连接远程目标，CONNECT_RETRIES次
						while (retry-- != 0) {
							try {
								outbound = new Socket(host, port);
								break;
							} catch (Exception e) {
								// System.out.println ("重试...");
							}
							// 等待
							Thread.sleep(pause);
						}
						if (outbound == null) {
							break;
						}
						outbound.setSoTimeout(timeout);
						os = outbound.getOutputStream(); // 向远程服务器写入请求
						os.write(line.getBytes());
						System.out.print(line+" ");
						os.write(' ');
						os.write(host0.getBytes());
						System.out.print(host+" ");
						os.write(' ');
						pipe(is, outbound.getInputStream(), os,
								cli_soc.getOutputStream());
						break; // 跳出case 3
					}
					host = host + (char) c;
					break;
				// -------------------------------------------------------------
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				cli_soc.close();
			} catch (Exception e1) {
			}
			try {
				outbound.close();
			} catch (Exception e2) {
			}
		}
	}

	void pipe(InputStream is0, InputStream is1, OutputStream os0,
			OutputStream os1) {
		try {
			int ir;
			byte[] bytes = new byte[bufsize];
			while (true) {
				try {
					if ((ir = is0.read(bytes)) > 0) {
						os0.write(bytes, 0, ir);
						 System.out.print (new String(bytes).trim ());
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
				try {
					if ((ir = is1.read(bytes)) > 0) {
						os1.write(bytes, 0, ir);
						 System.out.print (new String(bytes).trim ());
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
			}
		} catch (Exception e0) {
			// System.out.println ("Pipe异常: " + e0);
		}
	}
}
