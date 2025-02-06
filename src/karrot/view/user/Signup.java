package karrot.view.user;

import karrot.model.dto.MemberDTO;
import karrot.model.userAPI.Signup_DB;
import karrot.view.util.Input;

public class Signup {
	public static void signupService() {

		MemberDTO memberDTO = MemberDTO.getInstance();

		System.out.print("아이디 : ");
		String input_id = Input.inputStrig();
		System.out.print("비밀번호 : ");
		String input_pw = Input.inputStrig();
		System.out.print("비밀번호 재확인 : ");
		String input_pw_check = Input.inputStrig();
		System.out.print("이름 : ");
		String input_name = Input.inputStrig(); 
		
		boolean pw_check = checkPw(input_pw, input_pw_check);
		
		 
		if(pw_check) {
			//비밀번호 일치
			memberDTO.Signup(input_id, input_pw, input_name);
			boolean result = Signup_DB.signupDB();
			if(result) {
				//회원가입에 성공하면
				System.out.println("회원가입에 성공했습니다!");
			}
		}
		else {
			System.out.println("비밀번호가 서로 일치하지 않습니다.");
		}   
	}
	
	private static boolean checkPw(String pw1, String pw2) {
		boolean result = false;
		try {
			if(pw1.equals(pw2)) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("회원가입 과젱에서 에러 발생");
		}
		return result;
	}
}
