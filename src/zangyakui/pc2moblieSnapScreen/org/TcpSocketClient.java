package zangyakui.pc2moblieSnapScreen.org;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.BitSet;

import javax.imageio.ImageIO;


public class TcpSocketClient {
	

	private Socket mSocket;
	private String mHost;
	private OutputStream oStream;
	private DataInputStream iStream;
	private int mPort;
	
	public TcpSocketClient(){
		
		mHost=TcpSocketConstant.SERVER_ADDRESS;
		mPort=TcpSocketConstant.PORT;
		
		try {
			mSocket= new Socket(mHost,mPort);
			System.out.println("socket created successfully");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void TcpSocketClient(String host,int port){
		mHost=host;
		mPort= port;
		
		try {
			mSocket= new Socket(mHost,mPort);
			System.out.println("socket created successfully");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void  doTransaction(){
		System.out.println("doTransaction");
		if(mSocket!=null){
			try {
				oStream=mSocket.getOutputStream();
				iStream=new DataInputStream(mSocket.getInputStream());
				File f=new File("e:/receive/receive.png");
				DataOutputStream fos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
				
				int bufferSize=8192;
				byte[] buffer=new byte[bufferSize];
				int len=0;
				if(f!=null){
					while((len=iStream.read(buffer, 0, bufferSize))!=-1){
						oStream.write(buffer,0,len);
//						oStream.flush();
					}			
				}
				oStream.write("eof".getBytes());
				oStream.flush();
				System.out.println("client send successfully");
				
//				System.out.println("Client: I am client and please reply");
//				
//				int length=0;
//				int index=0;
//				byte[] b =new byte[1024];
//				
//				StringBuffer sb=new StringBuffer();
//				while((length=iStream.read(b))!=-1){
//					String tmp=new String(b,0,length);
//					if((index=tmp.indexOf("eof"))!=-1){
//						sb.append(tmp,0,index);
//						break;
//					}
//					sb.append(tmp,0,length);
//				}
//				System.out.println("receive from server: "+sb);
				
				iStream.close();
				oStream.close();
				mSocket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
}
