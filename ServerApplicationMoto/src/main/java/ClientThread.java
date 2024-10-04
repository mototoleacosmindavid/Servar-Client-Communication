import java.io.*;
import java.net.Socket;

public class ClientThread
{
    Socket clientSocket;
    InputStream fromServer;
    OutputStream toServer;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    public ClientThread(Socket socket) throws IOException
    {
        clientSocket = socket;
        fromServer = clientSocket.getInputStream();
        toServer = clientSocket.getOutputStream();
    }

    public String GetCommand() throws IOException
    {
        bufferedReader = new BufferedReader(new InputStreamReader(fromServer));
        return bufferedReader.readLine();
    }

    public void GiveCommand(String x) throws IOException
    {
        printWriter = new PrintWriter(toServer, true);
        printWriter.println(x);
        printWriter.flush();
    }
}
