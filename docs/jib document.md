빌드 스크립트 설명
1. 먼저 플러그인을 추가한다.
plugins {
...
   id 'com.google.cloud.tools.jib' version '3.0.0'
}
2. 도커 이미지를 구분하기 위해 확장자에 빌드 버전을 추가해야 한다. 
ext {
   BUILD_VERSION = new Date().format("yyyyMMddHHmmSS")
}
간략하게 날짜로 설정하였지만 필요에 따라 수정이 가능하다.
3. 베이스가 될 이미지와 업로드할 이미지 저장소를 선택하고, 어떤 앱을 도커라이징할지 선택한다.
jib {
   from {
       //베이스 이미지
       image = 'openjdk:8-alpine'
   }
   to {
       //업로드할 이미지 이름
       image = "arsgsg/classvar_score-service"
       //이미지의 태그
       tags = ["${project.version}".toString(), "${BUILD_VERSION}".toString()]
   }
   container {
       //실행될 메인 클래스의 경로
       mainClass = "com.classvar.scoreservice.ScoreServiceApplication"
       //expose시킬 포트 번호
       ports = ["8080"]
   }
}
위의 예제에서는 베이스 이미지를 openjdk:8-alphine으로 선택했지만, jdk 버전에 따라 취사선택할 수 있다.
업로드할 이미지의 이름은 /를 기준으로 앞 부분에는 업로드할 이미지 저장소 계정의 아이디를 적어야 하며, 뒷 부분에는 이미지의 이름을 적어주면 된다.
이미지에는 태그를 붙일 수 있으며, 위에서 설정한 빌드 버전으로 태그를 붙였다.
컨테이너 옵션에서 실행될 메인 클래스의 경로를 적어주면 된다.

실행 방법
1. 도커 레지스트리에 빌드하여 업로드하기
Windows : ./gradlew jib
Linux or MAC : ./gradle jib
2. 도커 데몬에 빌드하여 업로드하기
Windows : ./gradlew jibDockerBuild
Linux or MAC : ./gradle jibDockerBuild

