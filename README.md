# fftl-VoteBoard <투표 게시판 만들기>

### 개요

Vue.js를 이용한 간단한 클라이언트와 java, spring boot를 이용한 API서버를 이용해 만든 투표 게시판 만들기 프로젝트 입니다. Vue.js는 서버를 테스트 해보기 위한 정도로 간단하게 만들어 보았고, 주 목적인 서버는 spring boot와 jpa를 이용해서 만들어 보았습니다.

### 사용기술

-   Java, Spring Boot, JPA, gradle, git

### 구현기능

투표 생성할 수 있는 API입니다. 아래의 기능을 가진 투표 API를 만들어 보았습니다.

-   투표는 하나의 유저와, 하나의 게시글에 종속됩니다. 즉 유저가 만든 게시글에 추가되는 기능의 느낌입니다.
-   투표 제목과 투표 설명, 그리고 데드라인이 존재합니다.
-   데드라인은 종료 시간으로 직접 설정하지 않는다면 24시간으로 설정됩니다.
-   데드라인이 종료되면 투표는 종료되며 더 이상 누구도 투표에 참여할 수 없습니다.
-   작성자 본인도 투표에 참여할 수 있으며, 한번 참여한 투표에는 다시 참여할 수 없습니다.

### DB설계

이번 프로젝트에서 가장 고민한 부분 입니다. 투표가 이루어지는 부분에 대해서 어떻게 구상해야하나 고민을 해봤습니다. 일단 지금 상태에서 생각할 수 있는 좋은 설계를 해보았습니다.

```java
User

//pk
String userId;
```

```java
Vote

//pk
String voteId;

//content
String title;
String description;
String deadLine;

//fk
String userId;
Long boardId;
```

```java
VoteItem

//pk
Long voteItemId;

//content
String content;
Long cnt;

//fk
String voteId;
```

```java
VoteUser

//pk
Long voteUserId;

//fk
String voteId;
String userId;
```
