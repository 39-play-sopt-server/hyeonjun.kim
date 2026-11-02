# Docker 이미지 만들 때 사용할 이미지 지정
# jdk 아니고 jre인 이유: 빌드된 jar파일을 실행만 할꺼라서 (빌드는 github actions에서 함)
FROM eclipse-temurin:21-jre

# RUN : 명령어를 실행하는 지시어
# apt-get update : 운영체제의 패키지 목록을 최신 상태로 갱신
# apt-get install ... : curl 설치 -> curl http://.../health 요청 보내서 헬스체크 가능해짐
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

# 불필요한 패키지 목록 삭제해서 이미지 크기 줄이기
rm -rf /var/lib/apt/lists/*

# 작업 디렉토리 설정
# 이후 실행되는 COPY, RUN, ENTRYPOINT 등의 명령어에서 상대 경로를 사용할 때 기준이 됨
WORKDIR /app

# 로컬의 jar파일을 이미지 내부로 복사 ( COPY {원본경로} {대상경로} )
COPY hyeonjun.kim/build/libs/*SNAPSHOT.jar app.jar

# 컨테이너 내부에서 애플리케이션이 8080포트 사용한다고 명시
EXPOSE 8080

# 컨테이너가 시작될 때 기본적으로 실행할 명령어 지정 (java -jar app.jar 실행)
ENTRYPOINT ["java", "-jar", "app.jar"]
