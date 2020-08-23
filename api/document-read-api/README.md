# Document Read API

## 개요


## 디렉토리 구조

```bash  
src/main/java
  ⎪
  ├─ controller
  ⎪ └─ advice
  ⎪
  ├─ service
  ⎪
  ├─ repository
  ⎪
  ├─ model
  ⎪
  ├─ dto
  ⎪ ├─ mapper
  ⎪ └─ assembler
  ⎪
  ├─ exception
  ⎪
  ├─ util
  ⎪
  └─ Applicatoin.java

```

### 디렉토리 구조 설명

#### controller

- 컨트롤러의 역할만.
- 즉 직접적으로 요청을 받아 어떤 일을 수행할지 제어하는 계층

#### controller.advice

- exception에 대해 에러메세지 출력을 담당

#### service

- 컨트롤러에게 할 일을 지시받아 repository를 통해 DB를 접근하는 계층

#### repository

- 직접적으로 데이터베이스를 제어하는 계층

#### model

- 직접적으로 DB와 통신하기 위한 객체

#### dto

- 유저 인터페이스와 공유해야하는 데이터만 담당
- db에 저장되고 다른 서브 객체와 결합한 형태의 전체 모델을 다루지는 않음

#### dto.mapper

- dto가 모델과 거의 유사하다면 ModelMapper 유틸리티를 이용
- 이러한 경우는 현실에서 찾아보기 힘드므로 현 디렉토리에서 커스텀 매퍼를 활용

#### dto.assembler

- 유저에게 전달될 데이터에 대해 hateoas를 위한 링크를 매핑

#### exception

- 여러가지 custom exception 관리

