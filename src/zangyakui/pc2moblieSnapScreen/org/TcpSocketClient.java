package zangyakui.pc2moblieSnapScreen.org;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class TcpSocketClient {
	

	private Socket mSocket;
	private String mHost;
	private OutputStream oStream;
	private InputStream iStream;
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
				iStream=mSocket.getInputStream();
				
				oStream.write("I am client and please reply".getBytes());
				oStream.write("eof".getBytes());
				oStream.flush();
				System.out.println("client send successfully");
				
//				System.out.println("Client: I am client and please reply");
				
				int length=0;
				int index=0;
				byte[] b =new byte[1024];
				
				StringBuffer sb=new StringBuffer();
				while((length=iStream.read(b))!=-1){
					String tmp=new String(b,0,length);
					if((index=tmp.indexOf("eof"))!=-1){
						sb.append(tmp,0,index);
						break;
					}
					sb.append(tmp,0,length);
				}
				System.out.println("receive from server: "+sb);
				
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
