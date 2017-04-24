#include "link.h"

#include <iostream>
#include <string>

int main(){
	Link* curr = NULL;
	Link* head;
	string* input = NULL;
	for(int i = 0; i < 10; i++){
		getline(cin, *input);
		head = new Link(input, curr);
		curr = head;
	}
	head->printAll();
        Link* temp = head;
	for(int i = 0; i < 10; i++){
            head = head->getNext();
            delete temp;
            temp = head;
	}
	return 42;
}
