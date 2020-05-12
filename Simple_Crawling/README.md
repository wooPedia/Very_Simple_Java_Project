# 스레드를 이용한 크롤링

크롤링이 가능한 사이트에서 스레드 기반의 크롤러 구현하기   
- 결과물은 파일로 저장하기

<br>

## 사용 방법

윈도우 기준 cmd에서 src 폴더로 이동 후 java Main 입력   
=> 파일 경로에 한글이 있으면 안됨


<br>

##  대상 사이트 선정

- 구인구직 사이트인 ‘인크루트’라는 사이트 선정 ( https://www.incruit.com/ )

- 로봇 배제 표준 ( https://www.incruit.com/robots.txt )은 disallow이지만         학습목적이라 그냥 사용

- 해당 웹 사이트에서 .net을 검색한 결과를 크롤링하여 해당 결과의 모든 페이지를    가져옴 

- 아래 페이지의 채용 정보의 테두리 부분을 저장할 예정

![image](https://user-images.githubusercontent.com/46551002/81673944-64271480-9487-11ea-9c75-f71ed78bba89.png)

<br>

## 문제 해결

- 크롤링은 Jsoup과 같은 라이브러리를 이용하지 않고 URL 클래스를 이용 

- 해당 페이지의 hmtl 소스 코드를 참고하여 문자열 클래스의 split() 함수를 이용하여
  객체 별로 나누어 구함

  ![image](https://user-images.githubusercontent.com/46551002/81674009-815be300-9487-11ea-8471-44f3a0bbd847.png)

<br>

## 스레드 설계

- 해당 사이트로부터 가져올 정보는 회사 이름, 제목, 채용 일정, 기타 사항으로 총 4가지 

- 각 정보마다 스레드를 적용하여 각자 읽어오며 스레드를 적용하지 않았을 때에 비해 
  약 4초 단축   
( 1page 당 4~5s -> 1~1.5s )

- 결과 파일에 Buffer를 이용해 write 하는 스레드

![image](https://user-images.githubusercontent.com/46551002/81674071-96387680-9487-11ea-9223-7d527f57eb03.png)


<br>

## 클래스 설계

- 핵심 클래스: Bring class

- 실질적으로 웹으로부터 데이터를 읽어와 저장하는 static 메서드들을 가짐

![image](https://user-images.githubusercontent.com/46551002/81674119-a94b4680-9487-11ea-95f7-a165e54684b8.png)

<br>

- 읽어올 정보에 따라 문자열을 자르는 기준이 다름

- 아래와 같이 회사 이름은 해당 기준으로 잘라 가져옴

![image](https://user-images.githubusercontent.com/46551002/81674163-b9632600-9487-11ea-9769-95bb8d64e952.png)

<br>

## 컴파일 및 실행 결과

![image](https://user-images.githubusercontent.com/46551002/81674206-c7b14200-9487-11ea-9807-a13b63a08620.png)

![image](https://user-images.githubusercontent.com/46551002/81674235-d3046d80-9487-11ea-9826-00e21fa50e6a.png)

![image](https://user-images.githubusercontent.com/46551002/81674262-da2b7b80-9487-11ea-8cba-63ff1f6362c3.png)


- 페이지 규칙 (끝 숫자 20씩 증가)