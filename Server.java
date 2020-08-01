import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 
  
public class Server 
{ 
    public static void main(String args[]) throws IOException 
    { 
  
        // Step 1: Establish the socket connection. 
        ServerSocket ss = new ServerSocket(40022); 
        Socket s = ss.accept(); 
  
        // Step 2: Processing the request. 
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
        
        while (true) 
        { 
            // wait for input 
            String input = dis.readUTF(); 
  
            if(input.equals("bye")) 
                break; 
  
            System.out.println("Equation received:-" + input); 
            float result=0; 
  
            // Use StringTokenizer to break the equation into operand and 
            // operation 
            StringTokenizer st = new StringTokenizer(input); 
           try{ 
            float oprnd1 = Float.valueOf(st.nextToken()); 
         String operation = st.nextToken(); 
            float oprnd2 = Float.valueOf(st.nextToken());
             
                
           
  
            // perform the required operation. 
            if (operation.equals("+")) 
            { 
                result = oprnd1 + oprnd2; 
            } 
  
            else if (operation.equals("-")) 
            { 
                result = oprnd1 - oprnd2; 
            } 
            else if (operation.equals("*")) 
            { 
                result = oprnd1 * oprnd2; 
            } 
            else if(operation.equals("/"))
            { 
              if(oprnd2==0)
             {
              System.out.println("Sending the result...");
                  result=0;
                  dos.writeUTF("Divide by zero error...");
                  


               }else{

                result = oprnd1 / oprnd2;
                 
             }
            } 
           if(!operation.equals('/') && oprnd2!=0)
           {
            System.out.println("Sending the result..."); 
  
            // send the result back to the client. 
            dos.writeUTF(Float.toString(result));
           } 
     }     
          catch(NumberFormatException e){
               System.out.println("Sending the result...");
                  result=0;
                  dos.writeUTF("Invalid input...");
                  

        } 
    } 
}

}
