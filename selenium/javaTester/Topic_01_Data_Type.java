package javaTester;

import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {
public static void main(String[] args) {
	// 1. Khai báo trước, khởi tạo dữ liệu sau
	
	String fullName; // Kiểu dữ liệu - tên biến
	fullName = "AutomationFC"; // gán dữ liệu =  giá trị phù hợp
	// 2. Khai báo + khởi tạo dữ liệu cùng lúc
	String addressName = "135 hanoi";
	
	// Kiểu dữ liệu nguyên thuỷ: Primitive type. Gồm 8 loại chính
	// Ký tự: Char
	char  c = 'A'; // duy nhất 1 ký tự, dùng dấu ''
	
	// Số nguyên: byte short int long, độ rộng của khoảng giá trị. Không dấu, không thập phân
	byte bNumber = 127; // max value
	short sNumber = 32767;// max value
	int iNumber = 152346;
	long lNumber = 52986438;		
	// Số thực: float double; kiểu dữ liệu có dấu, có thập phân
	float fNumber= 15.8f;
	double dNumber = 153.78d;
	// Logic: boolean
	boolean status = true;
	status = false; // cập nhật giá trị mới --> true ko còn, status chuyển thành false
	// Kiểu dữ liệu tham chiếu: Reference type
	// Chuỗi ký tự: String , dùng dấu ""
	String cityName="Ho Chi Minh city";
	System.out.println(cityName);
	
	System.out.println(cityName);
	
	 cityName="Ha noi";
	System.out.println(cityName);
	// Class
	FirefoxDriver fDriver;
	
	// Interface
	WebDriver driver;
	// Collection: set list queue
	HashSet hashSet = new HashSet<>();
	ArrayList arrayList = new ArrayList();
	// Object
	Object number;
	
}
}
