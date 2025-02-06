package karrot.model.dto;

public class SecureUserInfo {
    MemberDTO memberDTO = MemberDTO.getInstance(); 
    
    private static SecureUserInfo instance;
    private String username;
    private String id;
    private boolean check_Login;

    private SecureUserInfo() {
        this.check_Login = false;
    }

    public static SecureUserInfo getInstance() {
        if (instance == null) {
            instance = new SecureUserInfo();
        }
        return instance;
    }

    public void setAllSecureUser(String username, String id) {
        this.username = username;
        this.id = id;
        this.check_Login = true;
    }

    public void setAllSecureUserNull() {
        this.username = null;
        this.id = null;
        this.check_Login = false;
        memberDTO.setAllNull();
    }

    public String getSecureUserName() {
        return username;
    }

    public String getSecureUserId() {
        return id;
    }

    public boolean getLogin(){
        return check_Login;
    }
}
