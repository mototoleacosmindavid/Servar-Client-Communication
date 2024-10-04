import java.io.IOException;
import java.net.ServerSocket;

public class GameServer extends IOException
{
    private static ServerSocket serverSocket;
    private static ClientThread clientThread;

    public static void main(String args[]) throws IOException
    {
        serverSocket = new ServerSocket(9000);
        clientThread = new ClientThread(serverSocket.accept());
        System.out.println("Server on");
        String request;

        while(!serverSocket.isClosed())
        {
            request = clientThread.GetCommand();
            if (request.equals("Stop"))
            {
                clientThread.GiveCommand("Server stopped");
                System.out.println("Server stopping");
                clientThread.clientSocket.close();
                serverSocket.close();
                break;
            }
            else
            {
                System.out.println("Server received the request : " + request);
            }
        }
    }
}
