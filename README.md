# tp2
2022 건국대 전과 포트제출용 웹 페이지 쇼핑몰 Toy Project 


# 프로젝트 구성 
*  개발환경 : Intellij
# 사용 기술
## 백엔드
### Spring boot
* JAVA 8
* Spring MVC
* Spring Data JPA


### Build tool
* Gradle
### Database
* H2

### 프론트엔드
* Thymeleaf
* BootStrap



## 주요기능
### 메인화면
![image](https://user-images.githubusercontent.com/89298874/149173226-7f3a1e3b-67a3-4c2b-a82b-5f6a3dfd3823.png)
* 로그인, 마이페이지 ABOUT 및 카테고리별 상품목록으로 이동할 수 있는 네비게이션 바
* 로그인 상태를 쿠키로 구별하여 로그인 상태 유지, 로그아웃 버튼 활성화/ 장바구니 기능 사용 가능 여부 판단
* 아이템 별로 카테고리 속성을 설정하여 Top, Trouser, Shoe 해당 카테고리에 속한 아이템만 화면에 출력하는 기능
### 로그인 화면
![image](https://user-images.githubusercontent.com/89298874/149172043-754be3b7-425c-4337-9db2-e414f83547e1.png)
* 쿠키 생성을 통한 로그인 기능
* thymeleaf, object error를 통해 로그인에 실패하면 아이디, 비밀번호에 대해 오류 출력

### 회원가입
![image](https://user-images.githubusercontent.com/89298874/149177218-6118e911-f044-4b29-bc6d-fd51a8e654e9.png)
* 로그인과 마찬가지로 thymeleaf,object error, field error 를 이용해 오류 출력
* 중복 아이디 검증, 비밀번호 자릿수 조건 검증
* 제출 버튼을 통해 db에 정보 저장
### 상품 화면
![image](https://user-images.githubusercontent.com/89298874/149172550-9b858bcc-fcf4-4e56-adb5-ea1222bc9e56.png)
* 수량을 지정하여 장바구니에 상품 추가 및 상품 정보 전달
### 장바구니
![image](https://user-images.githubusercontent.com/89298874/149172276-120adb9a-d3fd-4842-8b95-43f4cd526627.png)
* 장바구니에 추가된 아이템들 중 check 박스로 선택하여 주문을 진행
* 선택되어 주문된 아이템들은 장바구니에서 제거
### ABOUT
![image](https://user-images.githubusercontent.com/89298874/149172886-6680fbe5-4be1-4982-8cea-afb48b592af8.png)

### 관리자 페이지
![image](https://user-images.githubusercontent.com/89298874/149176030-85b6577f-be01-45c2-98ce-a6bc28a389d3.png)
* 주문 확인
  * ![image](https://user-images.githubusercontent.com/89298874/149178112-831c1a91-e715-420e-9ea9-7a42bb140071.png)
* 회원 목록 확인
* 상품 등록 및 목록 확인
![image](https://user-images.githubusercontent.com/89298874/149178253-2e3ebf0e-cb9b-4490-9cf2-f4c3a1c92f62.png)



