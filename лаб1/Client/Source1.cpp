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
	WORD wVersionRequested; // ������������ ����� ������ WinSock
	WSADATA wsaData; // ��������� ��� WSAStartup
	wVersionRequested = MAKEWORD(2, 2); // �������������� WinSock API
	WSAStartup(wVersionRequested, &wsaData);

	struct sockaddr_in peer;  // ������� ��������� ������ ����������
	peer.sin_family = AF_INET; // ���� sin_family ������ ����� �������� AF_INET
	peer.sin_port = htons(1280); // ���������� ����� ���� 1280
	peer.sin_addr.s_addr = inet_addr("127.0.0.1");  // �.�. ������ � ������ �� ����� ����������, ����� ����� 127.0.0.1
	SOCKET s = socket(AF_INET, SOCK_STREAM, 0); // ������� ���������� �����
	connect(s, (struct sockaddr*) & peer, sizeof(peer)); // ������������ � �������
	char buf[255], b[255];
	cout << "Hello, I'm Client" << endl;
	cout << "Enter 2 numbers" << endl;
	cin.getline(buf, 100, '\n');
	send(s, buf, sizeof(buf), 0); // ���������� ������ �� ������ ��� ���������
	if (recv(s, b, sizeof(b), 0) != 0) {  // �������� ����� �� �������
		b[strlen(b)] = '\0'; //�������� �������� �������� � ����� ������ 
		cout << b << endl;
		cin.get(); //������� ������� enter
	}
	closesocket(s); // ��������� ������ ������
	WSACleanup(); // ��������� ������������� WinSock DLL
	cout << "\nEnd of connection :)" << endl;
	_getch();
	return 0;
}
