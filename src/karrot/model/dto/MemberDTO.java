package karrot.model.dto;

public class MemberDTO {

   // 유저 정보의 Class 정보가 들어갈 곳
   private static MemberDTO instance;  // 싱글톤 인스턴스
   private String id;
   private String pw;
   private String name;

   // 생성자를 private 로 선언하여 외부에서 인스턴스화를 막음
   private MemberDTO() {}

   // 싱글톤 인스턴스 반환
   public static MemberDTO getInstance() {
      if (instance == null) {
         instance = new MemberDTO();
      }
      return instance;
   }

   public void login(String id, String pw) {
      this.id = id;
      this.pw = pw;
   }

   public void Signup(String id, String pw, String name) {
      this.id = id;
      this.pw = pw;
      this.name = name;
   }

   // 3. 모든 필드의 getter, setter
   public String getId() {
      return id;
   }

   public String getPw() {
      return pw;
   }

   public String getName() {
      return name;
   }

   public void setPw(String pw) {
      this.pw = pw;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAllNull() {
      this.name = null;
      this.id = null;
      this.pw = null;
   }
}
