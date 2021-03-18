package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    Socket socket = null;
    InputStreamReader is = null;
    DataOutputStream dos = null;

    @FXML //Аннотация, помечающая класс или член как доступные для разметки.
    private Button ConnectionButton; //создаем текстовые объекты

    @FXML
    private TextField TextField1;

    @FXML
    private TextField TextField2;

    @FXML
    private TextField TextField3;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button SendButton;

    @FXML
    void initialize() {
        ConnectionButton.setOnAction(event -> { //лямбда-выражение, кошда надимаем на кнопку, происходит ивент
            try {
                socket = new Socket(InetAddress.getByName(TextField1.getText()), Integer.parseInt(TextField2.getText())); //для локалхост и 8080 поля
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SendButton.setOnAction(event -> {
            try {
                doGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void doGUI() throws IOException {
        if (socket == null) {
            return;
        }
        try {
            dos = new DataOutputStream(socket.getOutputStream()); //объект для вывода байтовый
            String text = TextField3.getText(); //забираем тект из филда
            dos.writeUTF(text); //прием текста
            is = new InputStreamReader(socket.getInputStream()); //объект для ввода
            BufferedReader bf = new BufferedReader(is);
            String str = bf.readLine(); //объект для вывода результата после сортировки
            TextArea.appendText(str); //добавляем текст
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            is.close(); //закрытие входного потока
            dos.close(); //закрытие входного потока
            socket.close(); //закрытие сокета, выделенного для работы с подключившимся клиентом
        }
    }
}