# fftl-VoteBoard <투표 게시판 만들기>

### 개요

투표를 할 수 있는 게시판을 만들어 보았습니다. 투표 기능 자체에 집중을 하기 때문에 아이디만을 가지게 됩니다. 프로젝트는 Vue.js를 이용한 간단한 클라이언트와 SpringBoot를 이용한 REST API서버로 실행됩니다.

### 사용기술

-   Java, Spring Boot, H2, JPA, gradle, git

### 구현기능

투표 생성할 수 있는 API입니다. 아래의 기능을 가진 투표 API를 만들어 보았습니다.

-   투표는 하나의 유저와, 하나의 게시글에 종속됩니다. 즉 유저가 만든 게시글에 추가되는 기능의 느낌입니다.
-   투표 제목과 투표 설명, 그리고 데드라인이 존재합니다.
-   데드라인은 종료 시간으로 직접 설정하지 않는다면 24시간으로 설정됩니다.
-   데드라인이 종료되면 투표는 종료되며 더 이상 누구도 투표에 참여할 수 없습니다.
-   작성자 본인도 투표에 참여할 수 있으며, 한번 참여한 투표에는 다시 참여할 수 없습니다.

### DB설계

이번 프로젝트에서 가장 고민한 부분 입니다. 투표가 이루어지는 부분에 대해서 어떻게 구상해야하나 고민을 해봤습니다. 테이블은 **User, Vote(투표), VoteItem(투표항목), VoteUser(투표한 유저)** 이렇게 네 가지로 구성됩니다. Vote가 존재하며 Vote안에 2개 이상의 VoteItem이 존재하게 됩니다. 한 명의 유저는 하나의 투표에 한번만 참여할 수 있기 때문에 투표 참여 여부를 판단하기 위해 VoteUser를 사용합니다.

```java
User

String userId;
```

```java
Vote

String voteId;
String title;
String description;
String deadLine;
String userId;
Long boardId;

```

```java
VoteItem

Long voteItemId;
String content;
Long cnt;
String voteId;

```

```java
VoteUser

Long voteUserId;
String voteId;
String userId;

```

### 프로젝트 후기

일단 해당 프로젝트는 과거에 진행했던 과제 프로젝트를 정리하고, 테스트 코드를 작성해 본 프로젝트입니다. 해당 과제의 요구사항을 따라 DB에 foreign key를 작성하였고 이로 인해 연관관계 맵핑은 따로 사용하지 않았습니다.

### **_테스트코드 사용_**

테스트 코드는 최근에 추가하였습니다. 이전 프로젝트에 처음으로 테스트 코드를 작성할 때에는 무조건 단위 테스트가 좋은 것 인줄 알고 Controller 테스트지만 단위테스트로 진행했고 Service의 기능들을 willReturn을 이용하여 진행했습니다. 하지만 이번에는 @SpringBootTest를 이용하였고, data.sql을 이용해 테스트용 데이터를 셋팅해 놓는 등의 방법을 사용해 조금 더 좋은 테스트를 작성할 수 있었습니다.
