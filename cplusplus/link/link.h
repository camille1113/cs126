#ifndef _LINK_H_
#define _LINK_H_
#include <string>
using namespace std;
class Link
{

  private:
	string* value;
	Link* next;

  public:

    Link(string* s, Link* link);//constructor

    ~Link();//destructor
    
    Link* getNext();//returns a pointer to the next link
    
    string* getString();//returns a pointer to the next string
    
    void printAll();//prints this string, anew line and also recursively 
    						//calls printAll on the next link if the next field is not null 

};

#endif
