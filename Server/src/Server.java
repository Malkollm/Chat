import java.net.*;
import java.io.*;
public class Server {
    public static void main(String[] ar)    {
        int port = 6666; // порт
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера с портом
            System.out.println("Ожидаем клиента");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Клиент подключен");
            System.out.println("Ожидаем сообщение от клиента");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while(true) { // бесконечный цикл
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("Сообщение от клиента: " + line);
                System.out.println("Отсылаем сообщение клиенту...");
                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Ожидаем сообщение от клиента");
                System.out.println();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}