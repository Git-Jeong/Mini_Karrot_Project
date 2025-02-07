package karrot.view;
 
import karrot.model.dto.SecureUserInfo;
import karrot.view.product.ProductList;
import karrot.view.product.ProductSale;
import karrot.view.user.Login;
import karrot.view.user.Mypage;
import karrot.view.user.Signup;
import karrot.view.user.Update;
import karrot.view.util.Input;
import karrot.view.util.ScannerUtil;

public class Main {

	final static int EXIT_RETURN_CODE = 999;

	final static int SIGNUP_CODE = 1;
	final static int LOGIN_CODE = 2;

	final static int SEARCH_PRODUCT_LIST_CODE = 1;
	final static int SALE_PRODUCT_CODE = 2;
	final static int MY_PAGE_CODE = 3;
	final static int EDIT_CODE = 4;
	final static int LOGOUT_CODE = 5;

	static SecureUserInfo secureUserInfo = SecureUserInfo.getInstance();
	
	public static void main(String[] args) {
		while(true) {
			if(!secureUserInfo.getLogin()) {
				//로그인이 되지 않았다면
				int serviceOption = nonLoginService();
				if (serviceOption == EXIT_RETURN_CODE) {
					break;
				}
			}
			else{
				//로그인이 되어 있다면 
				System.out.println();
				loginService();
			}
		}
		
		System.out.println("시스템이 종료되었습니다.");
		ScannerUtil.closeScanner();
	}
	
	private static int nonLoginService() {
		while (true) {
			System.out.println("========== 당근마켓 ==========");
			System.out.printf("회원가입 : '%d',\t 로그인 : '%d',\t 종료:AnyKey \n", SIGNUP_CODE, LOGIN_CODE);
			System.out.print("원하는 옵션을 선택  >>> ");
			String inputOption = Input.inputStrig();

			if (inputOption.equals(String.valueOf(SIGNUP_CODE))) {
				// 회원가입 코드 실행
				System.out.println("회원가입 시작");
				Signup.signupService();
			} else if (inputOption.equals(String.valueOf(LOGIN_CODE))) {
				// 로그인 코드 실행
				Login.loginService();
				return 0;
			} else {
				// 시스템 종료 코드
				System.out.print("시스템을 종료하겠습니까??   Yes : 'Y' >>>> ");
				String inputExit = Input.inputStrig();
				if (inputExit.equalsIgnoreCase("Y")) {
					return EXIT_RETURN_CODE;
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	

	private static void loginService() {
		if(secureUserInfo.getLogin()) {
			System.out.printf("[%d]상품 조회, [%d]상품 판매, [%d]마이페이지, [%d]정보수정, [%d]로그아웃\n",
					SEARCH_PRODUCT_LIST_CODE,
					SALE_PRODUCT_CODE,
					MY_PAGE_CODE,
					EDIT_CODE,
					LOGOUT_CODE
			);
			System.out.print("숫자를 입력 : ");
			String inputOption = Input.inputStrig();
			if (inputOption.equals(String.valueOf(MY_PAGE_CODE))) {
				// 내가 판매중인 상품의 정보, 내가 구매한 상품의 정보
				Mypage.showMypage();
			} else if (inputOption.equals(String.valueOf(SEARCH_PRODUCT_LIST_CODE))) {
				//  남들이 판매하는 상품의 목록들을 불러오는 코드
				ProductList.productList();
			} else if (inputOption.equals(String.valueOf(SALE_PRODUCT_CODE))) {
				//  내 물건을 판매하는 코드
				ProductSale.productSale();
			} else if (inputOption.equals(String.valueOf(EDIT_CODE))) {
				//  유저 정보를 수정하는 코드
				Update.updateService();
			} else if (inputOption.equals(String.valueOf(LOGOUT_CODE))) {
				// 로그아웃 코드 실행
				secureUserInfo.setAllSecureUserNull();
				}
			}
		}
}
