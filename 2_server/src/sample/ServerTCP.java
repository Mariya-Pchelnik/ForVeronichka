package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerTCP {
    public static void main(String[] args) throws IOException {

        DataInputStream dis = null;
        PrintWriter pr = null;
        ServerSocket sock = null;

        try {
            sock = new ServerSocket(8082);
            Socket socket = sock.accept();
            System.out.println("Congrats, client is connected!");
            dis = new DataInputStream(socket.getInputStream()); //куда будем записывать поток байтов
            String s = dis.readUTF(); // this operation are read from the contained input stream
            char[] chars = s.toCharArray(); //конветируем в массив чар
            Arrays.sort(chars); // сортировка (встроенная)
            pr = new PrintWriter(socket.getOutputStream()); //объект для записи в поток
            pr.write(new String(chars)); //записываем в поток
            pr.flush(); //очищаем поток
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            dis.close(); //закрытие входного потока
            pr.close(); //закрытие входного потока
            sock.close(); //закрытие сокета, выделенного для работы с подключившимся клиентом
            System.out.println("Client is disconnected :*");
        }
    }
}