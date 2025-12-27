# ReBAC Practice – Hands-on Lab

이 저장소는 **Relationship-Based Access Control(ReBAC)** 개념을  
CRUD 단위로 직접 실습하며 이해하기 위한 예제 프로젝트입니다.

---
# 환경 설정 문서: https://buly.kr/9MRjJbB

# 행사장 WIFI 접속: https://buly.kr/GvoJUyY
**오프라인 참가자만 신청해 주세요.**
**구성원 ID: robot.hoony@woowahan.com**

---

# 0. Docker 설치
Docker가 설치 되어 있다면, #1으로 이동해 주세요.

### Mac
```
brew install docker docker-compose colima
colima start

mkdir -p ~/.docker/cli-plugins
ln -sfn $(brew --prefix)/opt/docker-compose/bin/docker-compose ~/.docker/cli-plugins/docker-compose
```

### Window
```
# power shell에서
wsl --install
```
```
# wsl 내에서
sudo apt-get update
sudo apt-get install docker.io docker-compose-v2
```

---

# 1. 프로젝트 클론 및 실행 방법

### 1.1 Git Clone

```bash
git clone https://github.com/ksngh/rebac_practice.git
cd rebac_practice
```

---

### 1.2 실행 환경

- Java 21 이상
- Gradle 8.14.3
- IntelliJ IDEA
- Docker compose

---

### 1.2.1 Docker compose
Docker Compose 사용 가능 여부를 확인합니다.

```bash
docker compose version
```

정상적으로 설치되어 있다면 다음과 같이 버전 정보가 출력됩니다.

```text
Docker Compose version v2.x.x
```
---

### 1.2.2 Docker Image

아래 명령어를 실행하면 docker-compose 실행 전에 필요한 이미지들을 미리 다운로드할 수 있습니다.

```bash
docker pull mariadb:11.4.8
docker pull authzed/spicedb:latest
```

---

## 2. 브랜치 구조

이 저장소는 **CREATE / READ 단위로 브랜치를 분리**하여 실습을 진행합니다.

```text
create
read
```

각 브랜치는 **이전 단계의 구현을 포함한 상태**에서 확장됩니다.

| 브랜치 | 목적 |
|-------|------|
| create | 관계(tuple) 생성 및 권한 정의 |
| read | 관계 기반 조회 권한 판단 |

---








