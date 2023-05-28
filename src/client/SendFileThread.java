package client;
import java.io.*;
import java.net.*;

public class SendFileThread extends Thread{
	private Socket 				socket;
	private String 				file;
	private String 				sender;
	private String				receiver;
	private int 				ID;
	private Object				lock;
	private DataOutputStream	output;
	
	public SendFileThread(Socket socket, String filePath, String sender, String receiver) {
		this.socket 	= socket;
		this.file 		= filePath;
		this.sender 	= sender;
		this.receiver 	= receiver;
	}
	
	public void run() {
		try {
			
			File	filePath		= 	new File(file);
			String 	fileName		=	filePath.getName();
			output.writeUTF(fileName + " from " + sender + " to " + receiver );
			
			FileInputStream	input	=	new FileInputStream(fileName);
			output						= 	new DataOutputStream(socket.getOutputStream());
			BufferedInputStream bis	=	new BufferedInputStream(input);
			int 	count;
			byte[]	buffer			=	new byte[8000];
			
			while ((count = bis.read(buffer)) > 0) {
				synchronized (lock) {
					if(!socket.isClosed()) {
						output.writeInt(ID);
						output.writeInt(count);
						output.write(buffer, 0, count);
					}
				}
			}
			output.write(ID);
			output.write(-1);
			input.close();
			output.flush();
			System.out.println("File has been sent");
			
		} catch (IOException e) {
			System.out.println("[sendfile] "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	

}
