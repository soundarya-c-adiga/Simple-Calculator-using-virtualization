// Java program to illustrate Client Side Programming 
// for Simple Calculator using TCP 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  
public class Calc_Client 
{ 
    public static void main(String[] args) throws IOException 
    { 
        InetAddress ip = InetAddress.getLocalHost(); 
        int port = 4444; 
        Scanner sc = new Scanner(System.in); 
        int ip=”192.168.232.132”                  // IP Address of other version of ubuntu which has Server program
        // Step 1: Open the socket connection. 
        Socket s = new Socket(ip, port); 
  
        // Step 2: Communication-get the input and output stream 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
  
        while (true) 
        { 
            // Enter the equation in the form- 
            // "operand1 operation operand2" 
            System.out.print("Enter the equation in the form: "); 
            System.out.println("'operand operator operand'"); 
  
            String inp = sc.nextLine(); 
    
            // send the equation to server 
            dos.writeUTF(inp); 
           if (inp.equals("bye")) 
                break; 

  
           // wait till request is processed and sent back to client 
            String ans = dis.readUTF(); 
            System.out.println("Answer=" + ans); 
        } 
    } 
} 
