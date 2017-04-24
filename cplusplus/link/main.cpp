#include "link.cpp"

#include <iostream>
#include <string>

int main(){
	Link* tail = NULL;
	Link* head;
	string input;
	for(int i = 0; i < 10; i++){
		getline(cin, input);
		head = new Link(&input, tail);
		tail = head;
	}
	head->printAll();
	for(int i = 0; i < 10; i++){
		head->~Link();
		head = head->getNext();
	}
	return 42;
}
