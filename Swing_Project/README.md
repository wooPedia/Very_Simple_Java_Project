# SWING을 이용한 분석화면 구현 (학생부 정렬)

SWING 컴포넌트를 이용하여 분석 메뉴 화면 구성 및 이벤트 처리 로직을 구현해봅니다.   

1. 데이터 선정 및 데이터 분석 시나리오
2. 텍스트 또는 CSV 파일을 선택하여 읽어 들여, 특정 컬럼에 대한 오름차순/내림차순 정렬을 수행합니다.   
=> 정렬 종류는 사용자가 사용할 수 있게끔 구현합니다.
3. 정렬 결과를 파일에 Write하여 저장합니다.

## 사용 방법

윈도우 기준 cmd에서 src 폴더로 이동 후 java Main 입력   
=> 파일 경로에 한글이 있으면 안됨

<br>

## 데이터 선정 및 데이터 분석 시나리오

- 데이터는 학번, 학년, 이름, 성적 총 4개의 필드를 가진 학생 정보가 담긴
csv 파일을 처리하는 것으로 구상하였습니다.

- 해당 입력으로 사용될 csv 파일은 학생 정보를 무작위로 생성하는 소스를 작성하였습니다. GUI에 버튼을 추가하여 사용자가 입력한 학생 수 만큼의 데이터가 담긴 csv 파일을 생성하도록 하여 쉽게 입력파일로 사용할 수 있도록 하였습니다.

- 데이터 분석 종류는 데이터를 정렬하는 것으로 하였으며, 오름차순과 내림차순을 할 수 있습니다. 정렬 기준은 학번과 학년 두 가지의 기준이 있습니다.
정렬의 종류나 정렬 기준은 사용자가 선택할 수 있습니다.

- 따라서 학번 기준의 오름차순과 내림차순 정렬, 학년

<br>

## 사용자 시나리오

![image](https://user-images.githubusercontent.com/46551002/81669727-92a1f100-9481-11ea-91ec-47afbb8bbec0.png)   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;프로그램 초기화면

<br>

1. 정렬 대상인 csv 파일을 생성하기 위해 텍스트 필드에 학생 수를 입력 후 
Make csv 버튼을 클릭합니다.
2. Choose a File 버튼을 클릭하여 생성한 csv 파일을 불러옵니다.
3. Choose a File 버튼 하단의 텍스트 필드에 선택한 파일의 경로가 입력됩니다.
4. 정렬 기준과 정렬 방법을 선택 후 Sorting 버튼을 클릭합니다.
5. 정상적으로 정렬이 완료되어 정렬된 csv 파일이 생성되면 성공 메시지 창이 뜨며,
   텍스트 필드에 성공 메시지가 입력됩니다.
6. Open Folder 버튼을 클릭해 폴더를 열어 생성된 csv 파일을 확인합니다.

<br>

- 예외 처리
1. 학생 수 입력 칸에 1000이상의 수를 입력할 시
2. 학생 수 입력 칸에 문자를 입력할 시
3. 파일을 선택하지 않고 Sorting 버튼을 클릭할 시
4. 정렬된 결과 파일을 열어놓은 상태에서 Sorting 버튼을 클릭할 시

<br>

## 클래스 관계

![image](https://user-images.githubusercontent.com/46551002/81670143-27a4ea00-9482-11ea-87f6-7a024232ebf4.png)

- Main 클래스의 Main() 메소드에서 MyFrame 객체를 생성
- awt, 스윙 컴포넌트들이 선언되어 있는 MyFrame 클래스
- 각 버튼의 이벤트 처리를 위한 내부 클래스들이 MyFrame 클래스에 속해있다.

<br><br>

![image](https://user-images.githubusercontent.com/46551002/81670192-39868d00-9482-11ea-81f5-d31383a6bbf6.png)



![image](https://user-images.githubusercontent.com/46551002/81670206-3f7c6e00-9482-11ea-9788-e7500f4d205d.png)

- Sorting 버튼의 이벤트를 처리하기 위한 클래스와 메소드들
- 아래 클래스들은 Sort 클래스에 정의되어 있으며 Comparator 인터페이스로부터
   상속받아 재정의 된 메소드를 포함한다.
- 정렬 기준, 정렬 종류 별로 구현된 클래스

<br>

## 주요 코드 설명

### 학생 데이터가 무작위로 저장된 csv 파일 생성 로직
1. 파일에 데이터를 쓰기 위해 먼저 학생 정보 4가지를 랜덤으로 생성하여 row 생성
-> StuTestResult 클래스에서 getRow() 메소드를 통해 1 row를 반환

2. getRow()를 통해 얻은 값을 파일에 write 한다.
-> MakeCsvFile 클래스에서 makeCSV(int) 메소드를 통해 입력파일 생성

### csv 파일을 읽어 정렬하기 위한 로직
1. Token 클래스의 createToken() 메소드에서 csv파일을 읽는다.

2. Scanner 클래스의 useDelimiter() 메소드를 이용하여 각 row를 필드별로 분리하여 값을 필드별 벡터에 저장한다.

3. Token 클래스의 getStuVector() 메소드를 이용하여 Student 객체를 저장하는 vector에 위에서 구한 필드별 값을 저장한다.

4. Sort 클래스에서 각 row를 비교하여 정렬하기 위해 row가 저장된 Student형 vector를 ascSort() or dscSort() 메소드를 이용해 정렬하고 정렬된 배열을 반환   
-> 정렬은 Arrays 클래스의 sort(T[] a, Comparator<? super T> c)) 
메소드를 사용하여 정렬    
∴ row가 저장된 vector를 Student[]형 배열에 배열 형태로 반환하여 저장한다.         (toArray() 메소드 사용)

<br>

sort() 메소드의 매개변수인 c는 Comparator 인터페이스를 상속받은 클래스의 
인스턴스이다. c에 구현된 compare() 메소드에 따라 정렬을 수행한다.
따라서 정렬 기준, 정렬 종류를 조합한 4가지 정렬이 필요하므로 4개의 클래스를 작성한다.

![image](https://user-images.githubusercontent.com/46551002/81670706-f842ad00-9482-11ea-8c03-e8d1d4b4d34c.png)   
비교 예시

<br>

![image](https://user-images.githubusercontent.com/46551002/81670750-05f83280-9483-11ea-80b3-56ebfcb800d8.png)

위의 TypeAscStuID 클래스의 인스턴스를 생성하여 
sort() 메소드의 인자로 전달

<br>

5. 정렬된 Student[]형 배열을 반환하여 MakeCsvFile 클래스의 
   makeCSV(Student[], String) 메소드에 인자로 전달한다.

6. Student[]형 배열을 받아 새 파일에 write 한다.
-> 빠른 속도를 위해 BufferedWriter 클래스를 사용하여 Buffer를 통한 write

<br>

### GUI 및 이벤트 처리 (패널 배치관리자 null, 모든 컴포넌트 절대 위치)


![image](https://user-images.githubusercontent.com/46551002/81671254-c4b45280-9483-11ea-9a94-2d49c94ded3a.png)

<br>

Make csv 버튼 이벤트 처리   
![image](https://user-images.githubusercontent.com/46551002/81671449-f9280e80-9483-11ea-9ded-d3cc1eb9102a.png)

<br>

전달받은 학생 수 만큼의 csv 파일을 생성한다. // makeCSV()
![image](https://user-images.githubusercontent.com/46551002/81671546-0c3ade80-9484-11ea-8111-6d9586926f2a.png)

<br>

### Choose a File 버튼 이벤트 처리

![image](https://user-images.githubusercontent.com/46551002/81671710-3b515000-9484-11ea-957b-e540e3fd2351.png)

![image](https://user-images.githubusercontent.com/46551002/81671723-3ee4d700-9484-11ea-847b-2bd9c18beace.png)

- 입력 파일을 저장한 경로를 default 위치로 파일 선택 창을 연다.
- filter에 .csv 파일 추가
- 파일 선택 후 열기 버튼을 클릭하면 TextField를 파일 경로로 수정한다.

<br>

### 정렬 기준, 정렬 종류 선택 버튼 이벤트 처리

![image](https://user-images.githubusercontent.com/46551002/81671897-663ba400-9484-11ea-9790-d88e64f65bf7.png)

익명 클래스로 해당 메소드를 정의한다.
해당 버튼 선택 시 해당 버튼에 해당하는 변수의 값을 1로
나머지 버튼의 값을 0으로 변경한다.

<br>

### Sorting 버튼 이벤트 처리

![image](https://user-images.githubusercontent.com/46551002/81671980-7c496480-9484-11ea-9154-3162d87df836.png)

![image](https://user-images.githubusercontent.com/46551002/81671992-7f445500-9484-11ea-8d3e-0172fae42275.png)

- 정렬된 파일은 위에서 입력 파일로 사용한 경로에 생성한다.
- 위에서 선택된 정렬 기준, 정렬 종류 버튼의 값에 따라 알맞은 정렬 메소드를 호출한다.

<br>

### Open Folder 버튼 이벤트 처리

![image](https://user-images.githubusercontent.com/46551002/81672016-8703f980-9484-11ea-966c-ec4474d3e38e.png)   
입력 파일, 결과 파일이 저장되는 경로의 폴더를 연다.

<br>

## 테스트 및 결과

초기 화면 (학생 수 default 값:40)   
![image](https://user-images.githubusercontent.com/46551002/81672267-e06c2880-9484-11ea-900e-9eaa237e6d5d.png)

<br>

33으로 설정 후 Make csv -> 성공 메시지 확인
![image](https://user-images.githubusercontent.com/46551002/81672274-e2ce8280-9484-11ea-95a5-d05675c2adda.png)

<br>

Open Folder 버튼을 이용해 폴더를 연 후 생성된 파일 확인
![image](https://user-images.githubusercontent.com/46551002/81672365-05609b80-9485-11ea-9f11-426767c43ae7.png)

<br>

무작위로 생성된 33명 학생 정보 확인
(학번, 학년, 이름, 점수)

![image](https://user-images.githubusercontent.com/46551002/81672426-19a49880-9485-11ea-9372-e39461e61d31.png)

<br>

Choose a File 버튼 클릭 후 생성한 파일 선택
![image](https://user-images.githubusercontent.com/46551002/81672493-2a550e80-9485-11ea-8968-88ce1293a94a.png)

<br>

TextField 변경 확인   
![image](https://user-images.githubusercontent.com/46551002/81672555-422c9280-9485-11ea-970b-6ace43a2ec13.png)

<br>

학년, 오름차순 선택 후 Sorting 버튼 클릭 -> 성공 메세지
![image](https://user-images.githubusercontent.com/46551002/81672634-612b2480-9485-11ea-8065-a0e94dad5ca0.png)

<br>

정상 결과 텍스트 필드 변경 확인   
![image](https://user-images.githubusercontent.com/46551002/81672657-69835f80-9485-11ea-8ec3-bb3b99c147f7.png)


<br>

생성된 결과 파일 확인   
![image](https://user-images.githubusercontent.com/46551002/81672704-7e5ff300-9485-11ea-9e27-b93d5479e3fb.png)

<br>

정렬된 결과 확인 // 똑같은 파일로 학년 오름차순, 학번 내림차순 결과
![image](https://user-images.githubusercontent.com/46551002/81672745-8d46a580-9485-11ea-9359-556229f834d3.png)

6. 특이 사항

- 학생 정보 랜덤생성 시 학번은 중복제외 처리 하지않음

7. 파일 실행

- 윈도우 기준
cmd에서 src 폴더로 이동 후 java Main 입력