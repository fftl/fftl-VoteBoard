<template>
  <form>
    <p class="voteDetail">
      게시글 id :
      <input
        type="text"
        v-model="boardId"
        ref="boardId"
        placeholder="숫자를 입력해주세요."
      />
    </p>
    <p class="voteDetail">
      투표 제목 :
      <input
        type="text"
        v-model="title"
        ref="title"
        placeholder="제목을 입력해주세요."
      />
    </p>
    <p class="voteDetail">
      투표 설명:
      <textarea
        v-model="description"
        ref="description"
        placeholder="(선택)투표에 대한 설명을 입력해주세요."
      />
    </p>
    <p id="msg">( DeadLine 입력하지 않으면 24시간으로 자동 설정 됩니다. )</p>
    <p class="voteDetail">
      DeadLine:
      <input type="date" v-model="deadDate" ref="deadDate" />
      <input type="time" v-model="deadTime" ref="deadTime" step="1" />
    </p>
    <button type="button" @Click="addItem">추가하기</button>
    <div v-for="(i, index) in this.voteItems" :key="i">
      <span
        >투표 항목 : <input type="text" v-model="i.content" /><button
          type="button"
          @click="removeVote(index)"
        >
          X
        </button></span
      >
    </div>
    <div id="buttons">
      <button type="button" @click="goList">뒤로가기</button>
      <button type="button" @click="saveVote">완료하기</button>
    </div>
  </form>
</template>
<script>
export default {
  data() {
    return {
      userId: this.$store.state.userId,
      boardId: "",
      title: "",
      description: "",
      deadDate: "",
      deadTime: "",
      voteItems: [
        {
          content: "",
        },
      ],

      //request
      saveVoteRequest: [],
    };
  },
  methods: {
    addItem() {
      this.voteItems.push({
        content: "",
      });
    },
    saveVote() {
      if (isNaN(this.boardId)) {
        alert("게시글 id는 숫자만 입력해주세요.");
      } else {
        this.saveVoteRequest = {
          boardId: this.boardId,
          title: this.title,
          description: this.description,
          voteItemRequest: this.voteItems,
          deadLine: this.deadDate + " " + this.deadTime,
        };
        this.$http
          .post("http://localhost:8080/vote", this.saveVoteRequest, {
            headers: {
              "x-user-id": this.$store.state.userId,
            },
          })
          .then((res) => {
            if (res.data.success) {
              alert("등록을 완료하였습니다.");
              this.goList();
            } else {
              alert(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    removeVote(index) {
      console.log(index);
      this.voteItems.splice(index, 1);
    },
    goList() {
      this.$router.push({
        path: "/list",
      });
    },
  },
};
</script>
<style scoped>
#buttons {
  margin-top: 30px;
}
#msg {
  color: #808080;
  font-size: 10px;
}
</style>