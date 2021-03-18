#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#include <winsock2.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#pragma comment (lib,"ws2_32.lib")
using namespace std;
int main(){
	setlocale(LC_ALL, "Russian");
	WORD wVersionRequested; // максимальный номер версии WinSock
	WSADATA wsaData; // структура для WSAStartup
	wVersionRequested = MAKEWORD(2, 2); // инициализируем WinSock API
	WSAStartup(wVersionRequested, &wsaData);

	struct sockaddr_in peer;  // Создаем структуру данных соединения
	peer.sin_family = AF_INET; // Поле sin_family всегда имеет значение AF_INET
	peer.sin_port = htons(1280); // Обращаемся через порт 1280
	peer.sin_addr.s_addr = inet_addr("127.0.0.1");  // т.к. клиент и сервер на одном компьютере, пишем адрес 127.0.0.1
	SOCKET s = socket(AF_INET, SOCK_STREAM, 0); // Создаем клиентский сокет
	connect(s, (struct sockaddr*) & peer, sizeof(peer)); // Подключаемся к серверу
	char buf[255], b[255];
	cout << "Hello, I'm Client" << endl;
	cout << "Enter 2 numbers" << endl;
	cin.getline(buf, 100, '\n');
	send(s, buf, sizeof(buf), 0); // Отправляем строку на сервер для обработки
	if (recv(s, b, sizeof(b), 0) != 0) {  // Получаем ответ от сервера
		b[strlen(b)] = '\0'; //Удаление ненужных символов в конце строки 
		cout << b << endl;
		cin.get(); //ожидаем нажатия enter
	}
	closesocket(s); // Завершаем работу сокета
	WSACleanup(); // Завершаем использование WinSock DLL
	cout << "\nEnd of connection :)" << endl;
	_getch();
	return 0;
}
