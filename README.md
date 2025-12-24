# ReBAC Practice – Hands-on Lab

이 저장소는 **Relationship-Based Access Control(ReBAC)** 개념을  
CRUD 단위로 직접 실습하며 이해하기 위한 예제 프로젝트입니다.

실습자는 **branch 단위로 기능을 따라가며**,  
관계 기반 권한 체크가 어떻게 확장되는지를 단계적으로 학습하게 됩니다.

---

## 1. 프로젝트 클론 및 실행 방법

### 1.1 Git Clone

```bash
git clone https://github.com/ksngh/rebac_practice.git
cd rebac_practice
```

---

### 1.2 실행 환경

- Java 21 이상
- Gradle
- IntelliJ IDEA
- Docker compose

> 별도의 외부 서비스 없이 **로컬 실행 기준**으로 구성되어 있습니다.

---

## 2. 브랜치 구조

이 저장소는 **CRUD 단위로 브랜치를 분리**하여 실습을 진행합니다.

```text
create
read
refactor
```

각 브랜치는 **이전 단계의 구현을 포함한 상태**에서 확장됩니다.

| 브랜치 | 목적 |
|-------|------|
| create | 관계(tuple) 생성 및 권한 정의 |
| read | 관계 기반 조회 권한 판단 |
| refactor | RBAC -> ReBAC으로 권한 검증 전환 |

---

## 3. Step 1 – Create (관계 생성)

### 브랜치 이동

```bash
git checkout create
```

---

### 학습 목표

- ReBAC에서 **권한은 역할(role)이 아니라 관계(relation)** 로 결정됨을 이해
- 사용자 간, 사용자-리소스 간 관계를 **tuple 형태로 저장**
- 기본적인 권한 모델 정의

---

### 주요 내용

- 관계 생성 API
- Relation Tuple 구조
- `user → resource` 관계 정의
- 단순한 `canView` 권한 계산

---

## 4. Step 2 – Read (조회 권한)

### 브랜치 이동

```bash
git checkout read
```

---

### 학습 목표

- **누가 이 리소스를 볼 수 있는가?** 를 관계 기반으로 판단
- 조회 시점의 권한 체크 흐름 정리

---

### 주요 내용

- READ 권한 체크 로직
- 팔로우(follow) 관계 고려

---

## 5. Step 3 – Refactor (리팩터링)

### 브랜치 이동

```bash
git checkout refactor
```

---

### 학습 목표

- 기존 **RBAC 기반 권한 판단 로직이 왜 복잡해지는지** 직접 확인
- 역할(Role) 중심 모델에서 관계(Relation) 중심 모델(ReBAC)로 전환하는 과정을 실습
- 권한 판단 로직이  
  **비즈니스 규칙 → 관계 모델 → 권한 체크**로 단순화되는 흐름을 체감

---

### 주요 내용

- RBAC 기반 권한 구조 제거
  - Role, UserRole, PostRole, RolePermission 중심 로직 제거
- 관계 기반 모델(ReBAC)로 리팩터링
- Service 레벨 권한 판단 로직 단순화
  - 역할 해석 로직 제거
  - 관계 존재 여부만으로 READ / WRITE 판단
- 동일한 요구사항을
  - RBAC 방식 vs ReBAC 방식으로 비교하여 복잡도 차이 확인

---


