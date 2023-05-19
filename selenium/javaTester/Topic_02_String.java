package javaTester;

import org.testng.annotations.Test;

public class Topic_02_String {

	public static void main1(String[] args) {
		// TODO Auto-generated method stub
boolean a, b;
// And
a= true;
b= true;
System.out.println(a&&b);

a= true;
b= false;
System.out.println(a&&b);

a= false;
b= false;
System.out.println(a&&b);

a= false;
b= true;
System.out.println(a&&b);

//or
a= true;
b= true;
System.out.println(a || b);

a= true;
b= false;
System.out.println(a || b);

a= false;
b= false;
System.out.println(a || b);

a= false;
b= true;
System.out.println(a || b);

	}
@Test
	public static void main2(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String[] newUrl = url.split("//");
		//http:
		//the-internet.herokuapp.com/basic_auth
		url = newUrl[0] + "//"+"admin"+":"+"@"+newUrl[1];
		System.out.println(url);
	}	
}
