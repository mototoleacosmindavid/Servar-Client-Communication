import java.io.*;
import java.net.Socket;

public class GameClient
{
    static Socket clientSocket;
    static BufferedReader fromKeyboard;
    static InputStream fromServer;
    static OutputStream toServer;
    static BufferedReader bufferedReader;
    static PrintWriter printWriter;

    public static String GetCommand() throws IOException
    {
        bufferedReader = new BufferedReader(new InputStreamReader(fromServer));
        return bufferedReader.readLine();
    }

    public static void GiveCommand(String x) throws  IOException
    {
        printWriter = new PrintWriter(toServer, true);
        printWriter.println(x);
        printWriter.flush();
    }

    public static void main(String[] args) throws IOException
    {
        clientSocket = new Socket("localhost", 9000);
        fromKeyboard = new BufferedReader(new InputStreamReader(System.in));

        String respone;
        String request;

        while (!clientSocket.isClosed())
        {
            respone = GetCommand();
            System.out.println("Server response " + respone);
            request = fromKeyboard.readLine();
            GiveCommand(request);
            if (request.equals("Exit"))
            {
                System.out.println("Client exit");
                clientSocket.close();
                break;
            }
        }
    }

}
