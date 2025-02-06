package karrot.view.user;

import karrot.model.userAPI.Login_API;
import karrot.model.dto.MemberDTO;
import karrot.model.dto.SecureUserInfo;
import karrot.view.util.Input;

public class Login {
	public static void loginService(){
		MemberDTO memberDTO = MemberDTO.getInstance();
		SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();

		// 로그인 서비스 로직
		System.out.println(" ----- 로그인 서비스 ----- ");
		System.out.print("id : ");
		String input_id = Input.inputStrig();
		System.out.print("pw : ");
		String input_pw = Input.inputStrig(); 
		// 사용자 정보 검증

		memberDTO.login(input_id, input_pw);
		Login_API.loginAPI();

		if (secureUserInfo.getLogin()) {
			System.out.print("로그인에 성공했습니다!");
		}

		System.out.println();
		memberDTO.setAllNull();
		//보안을 위해 pw값 null로 초기화
	}
}
