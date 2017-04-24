#include "link.h"

#include <iostream>
#include <string>
using namespace std;
    Link::Link(string* s, Link* link): value(s), next(link){}

    Link::~Link(){
    	delete value;
    }
    
    Link* Link::getNext(){
    	return next;
    }
    
    string* Link::getString(){
    	return value;
    }

    void Link::printAll(){
    	cout<<*value<<endl;
    	if(next!=NULL)
    		next->printAll();

    }