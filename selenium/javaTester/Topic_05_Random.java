package javaTester;

import java.util.Random;

public class Topic_05_Random {
	public static void main(String[] args) {
		Random rand = new Random();
		String emailAddress = "Anhat" + rand.nextInt(9999)+ "gmail.com";
		System.out.println(emailAddress);
		
		String firstName = "Nhat";
		String lastName = "Nguyen";
		String fullName = firstName + " " + lastName;
		System.out.println(fullName);
		
	}

}
