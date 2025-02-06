package karrot.view.util;

import java.util.Scanner;

public class Input {  
	 
	public static String inputStrig() { 
		Scanner scan = ScannerUtil.getScanner();
		String result;	
		while (true) {
			try {
				result = scan.nextLine().trim();
				if (!result.isEmpty()) {
					break;
				}
				System.out.print("문자열 재입력 : ");
			} catch (Exception e) {
				System.out.print("문자열 재입력 : ");
			}
		}  
		return result;
	}
	
	public static int inputNumber() {
		Scanner scan = ScannerUtil.getScanner();
		int result;
		while (true) {
			try {
				String input = scan.nextLine().trim();
				if (input.isEmpty()) {
					System.out.print("숫자 재입력 : ");
					continue;
				}
				result = Integer.parseInt(input);
				if (result >= 0) {					
					break;
				}
				System.out.print("'0' 이상을 입력 : ");
			} catch (Exception e) { 
				System.out.print("정수만 입력해 주세요: ");
			} 
		}
		return result;
	}
}
