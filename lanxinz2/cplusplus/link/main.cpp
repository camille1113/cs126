#include "link.h"

#include <iostream>
#include <string>
using namespace std;
int main(){
	Link* curr = NULL;
	Link* head;
	string input;
	cout<<"put some string:"<<endl;
	while(true){
		getline(cin, input);
		if(input == "end") break;
		string* s = new string(input);
		head = new Link(s, curr);
		curr = head;
	}
	head->printAll();
    Link* temp = head;
	while(temp){
            head = head->getNext();
            delete temp;
            temp = head;
	}
	return 42;
}
