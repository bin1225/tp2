# tp2
2022 건국대 전과 포트제출용 웹 페이지 쇼핑몰 Toy Project

인프런 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발 강의에서 진행했던 프로젝트를 참고하여, 스스로 재구성하고 기능을 확장한 프로젝트 입니다.

### 기존 기능 
 - 회원 등록, 조회
 - 상품 등록, 수정 조회
 - 주문 등록, 조회

### 추가 기능
 - 로그인 및 검증
 - 장바구니
 - 카테고리별 아이템 분류
 - 관리자 전용 페이지를 통한 관리 기능
 - bootstrap 오픈 소스, thymeleaf를 이용한 실제 view 구성

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
## 프로젝트 구조
### 패키지 
![image](https://user-images.githubusercontent.com/89298874/149379187-9f0205c8-409b-424a-8e41-3f088084a0cc.png)

### 도메인
![image](https://user-images.githubusercontent.com/89298874/149442537-d48c9e62-ffb3-40f4-9631-e367a37b9836.png)
- 회원은 주문시 order 객체를 생성한다.
- 주문과 상품과의 다대다 관계에 대한 매핑 테이블로 orderItem 을 이용한다.
- orderItem 객체에 item, 수량, 가격 정보를 입력한다. 

# 주요기능
## 메인화면
![image](https://user-images.githubusercontent.com/89298874/149173226-7f3a1e3b-67a3-4c2b-a82b-5f6a3dfd3823.png)
* #### 로그인, 마이페이지 ABOUT 및 카테고리별 상품목록으로 이동할 수 있는 네비게이션 바
* #### 로그인 상태를 쿠키로 구별하여 로그인 상태 유지, 로그아웃 버튼 활성화/ 장바구니 기능 사용 가능 여부 판단
* #### 아이템 별로 카테고리 속성을 설정하여 Top, Trouser, Shoe 해당 카테고리에 속한 아이템만 화면에 출력하는 기능
* #### 장바구니 버튼을 통해 아이템을 장바구니에 저장
## 로그인 화면
![image](https://user-images.githubusercontent.com/89298874/149172043-754be3b7-425c-4337-9db2-e414f83547e1.png)
* #### 쿠키 생성을 통한 로그인 기능
* #### thymeleaf의 validation을 통해 로그인 검증 및 실패 시 아이디, 비밀번호에 대해 오류 출력

## 회원가입
![image](https://user-images.githubusercontent.com/89298874/149177218-6118e911-f044-4b29-bc6d-fd51a8e654e9.png)
* #### 로그인과 마찬가지로 thymeleaf를 이용한 오류 출력
* #### 중복 아이디 검증, 비밀번호 자릿수 조건 검증
* #### 제출 버튼을 통해 db에 정보 저장
## 상품 화면
![image](https://user-images.githubusercontent.com/89298874/149172550-9b858bcc-fcf4-4e56-adb5-ea1222bc9e56.png)
* #### 수량을 지정하여 장바구니에 상품 추가 및 상품 정보 전달
## 장바구니
![image](https://user-images.githubusercontent.com/89298874/149172276-120adb9a-d3fd-4842-8b95-43f4cd526627.png)
* #### 장바구니에 추가된 아이템들 중 check 박스로 선택하여 주문을 진행
* #### 선택되어 주문된 아이템들은 장바구니에서 제거

## 관리자 페이지
![image](https://user-images.githubusercontent.com/89298874/149176030-85b6577f-be01-45c2-98ce-a6bc28a389d3.png)
* #### 회원 목록 확인
### 주문 확인
* #### 주문 목록
 ![image](https://user-images.githubusercontent.com/89298874/149178112-831c1a91-e715-420e-9ea9-7a42bb140071.png)
 * #### 주문 상세
 ![image](https://user-images.githubusercontent.com/89298874/149389225-33aa60ac-bb93-486c-8382-7abf28f9a781.png)

### 상품 관리
* #### 상품 등록
![image](https://user-images.githubusercontent.com/89298874/149178253-2e3ebf0e-cb9b-4490-9cf2-f4c3a1c92f62.png)
* #### 상품 목록( 수정, 삭제 )
![image](https://user-images.githubusercontent.com/89298874/149388996-531c1063-e814-4db1-ab1a-616ff253bfe9.png)




