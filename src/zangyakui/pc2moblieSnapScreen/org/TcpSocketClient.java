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
				
				oStream.write("I am client and please reply".getBytes("gbk"));
				System.out.println("client send successfully");
				oStream.close();
//				System.out.println("Client: I am client and please reply");
				
				byte[] b =new byte[1024];
				StringBuilder sb=new StringBuilder();
				while(iStream.read(b,0,20)!=-1){
					sb.append(b.toString());
				}
				System.out.println("reply from server: "+sb.toString());
				iStream.close();
				mSocket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
}
