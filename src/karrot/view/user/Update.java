package karrot.view.user;

import karrot.model.userAPI.Auth_API;
import karrot.model.dto.MemberDTO;
import karrot.model.dto.SecureUserInfo;
import karrot.model.userAPI.Update_API;
import karrot.view.util.Input;

public class Update {
public static void updateService() {
	MemberDTO memberDTO = MemberDTO.getInstance();
	SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
	// 사용자 정보 검증
	System.out.println("사용자 ID : "+ secureUserInfo.getSecureUserId());
	System.out.println("사용자 이름 : "+ secureUserInfo.getSecureUserName());
	System.out.println("========== 본인확인 절차 ==========");
	System.out.print("pw : ");
	String input_pw = Input.inputStrig();
	memberDTO.setPw(input_pw);

	boolean authCheck = Auth_API.authAPI();

	if (authCheck) {
		System.out.println("--정보 변경--");
		System.out.print("pw : ");
		String new_pw1 = Input.inputStrig();
		System.out.print("pw check : ");
		String new_pw2 = Input.inputStrig();
		System.out.print("name : ");
		String new_name = Input.inputStrig();

		if(new_pw1.equals(new_pw2)) {
			System.out.print("정보를 수정하시겠습니까? 예 : 'Y' 아니요 : AnyKey >>> ");
			String user_update_check = Input.inputStrig();
			if(user_update_check.equalsIgnoreCase("Y")) {
				memberDTO.setPw(new_pw1);
				memberDTO.setName(new_name);
				boolean update_check = Update_API.updateDB();
				if (update_check) {
					secureUserInfo.setAllSecureUser(new_name, new_pw1);
					System.out.println("변경에 성공했습니다!");
				} else {
					System.out.println("변경에 실패했습니다!");
				}
			}
			else {
				System.out.println("취소했습니다.");
			}

		}

		else {
			System.out.println("비밀번호가 서로 일치하지 않습니다.");
		}

	} else {
		System.out.println("본인 확인에 실패했습니다.");
	}
	memberDTO.setAllNull();
		
	}
}

