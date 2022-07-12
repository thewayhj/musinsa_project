# musinsa_project
  * 온라인 쇼핑몰의 상품 카테고리를 구현하세요.
  ## 개발 시 반드시 포함되어야 하는 항목
    * RDBMS
    * 과제 작성시 Embedded DB 사용 권장 (ex. Java의 경우 H2 DB)
    * REST API
   ## 개발 시 반드시 구현되어야 하는 내용
    * 카테고리 등록/수정/삭제 API
    * 카테고리를 등록 / 수정 / 삭제 할 수 있어야 합니다.
    * 카테고리 조회 API
    * 상위 카테고리를 이용해, 해당 카테고리 하위의 모든 카테고리를 조회 가능해야 합니다.
    * 상위 카테고리를 지정하지 않을 시, 전체 카테고리를 반환해야 합니다.

# 구성
  * 버전
    * com.musinsa.homework-0.0.1-SNAPSHOT
  * 개발 환경
    * mac OS Big Sur 11.6
    * IntelliJ IDEA 2021.3.2 (Ultimate Edition)
    * java version 1.8.0_171
    * h2-1.4.199
    * gradle
  * 라이브러리
    * spring boot 2.7.2-SNAPSHOT
    * lombok 1.18.24
    * commons-lang3 3.0.1
    * swagger2 2.9.2
    * modelmapper 2.4.5

## Getting Started
 ## adoptOpenJDK 1.8.0 설치
  * java 설치 확인
    ``` 
      java -version 
    ```
  * 설치된 jdk 없을 시 아래 방법에서 설치
    * https://adoptopenjdk.net/index.html 에서 다운로드 후 설치 (OpenJDK 8)
    * yum list java*jdk-devel 에서 설치 가능한 java 확인 후 설치
  
 ## Spring Boot jar 생성
  * IntelliJ IDEA Gradle 탭에서 Task > build > bootJar 실행
  * build > libs 하위에 생성된 jar 파일을 Linux 서버에 옮김
  
 ## jar 실행
  * jar 파일이 있는 위치에서 아래 명력어 실행.
    ``` 
      java -jar ${fileName}.jar
    ```
  * background 실행
    ``` 
      nohup java -jar ${fileName}.jar &
    ```
