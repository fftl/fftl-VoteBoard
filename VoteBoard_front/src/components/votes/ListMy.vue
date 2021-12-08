<template>
  <div v-if="noneCheck">
    <table>
      <tr>
        <th>게시글 id</th>
        <th>투표 제목</th>
        <th>deadline</th>
        <th>작성자 id</th>
      </tr>
      <tr v-for="v in voteArray" :key="v.boardId">
        <td>{{ v.boardId }}</td>
        <td>
          <a
            href="javascript:;"
            @click="goDetail(`${v.voteId}, ${v.boardId}`)"
            >{{ v.title }}</a
          >
        </td>
        <td>{{ v.deadLine }}</td>
        <td>{{ v.userId }}</td>
      </tr>
    </table>
  </div>
  <div v-else>
    <h2>해당 사용자가 등록한 투표가 존재하지 않습니다.</h2>
  </div>
</template>
<script>
export default {
  data() {
    return {
      voteArray: [],
      noneCheck: false,
    };
  },
  mounted() {
    this.getAllVotes();
  },
  methods: {
    getAllVotes() {
      this.$http
        .get("http://localhost:8080/vote/myVote", {
          headers: {
            "x-user-id": this.$store.state.userId,
          },
        })
        .then((res) => {
          if (res.data.success) {
            this.noneCheck = res.data.success;
            this.voteArray = res.data.data;
          } else {
            console.log(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goDetail(getIds) {
      console.log(getIds);
      let ids = getIds.split(",");
      this.$router.push({
        path: "/detail",
        query: { voteId: ids[0], boardId: ids[1].trim() },
      });
    },
  },
};
</script>