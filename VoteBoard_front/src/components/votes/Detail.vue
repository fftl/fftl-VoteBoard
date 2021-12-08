<template>
  <!-- ### 투표시간이 끝나지 않았다면? ### -->
  <div v-if="timeCheck">
    <p class="voteDetail">
      투표 제목 :
      <span>{{ this.title }}</span>
    </p>
    <p class="voteDetail">
      투표 설명:
      <span>{{ this.description }}</span>
    </p>
    <p class="voteDetail">
      DeadLine:
      <span>{{ this.deadLine }}</span>
    </p>
    <!-- 아직 투표에 참여하지 않았다면? -->
    <div v-if="meCheck">
      <tr v-for="i in this.voteItems" :key="i.voteItemId">
        <td>{{ i.content }}</td>
        <td>
          <input type="radio" v-model="voteCheck" :value="i.voteItemId" />
        </td>
      </tr>
      <button type="button" @click="goList">뒤로가기</button>
      <button type="button" @click="goVote">투표하기</button>
    </div>
    <!-- 투표시간이 끝나지 않았지만, 투표에 참여하였다면? -->
    <div v-else>
      <div>투표에 이미 참여하였습니다.</div>
      <tr v-for="c in this.voteCnts" :key="c.voteCnts">
        <td>{{ c.content }} :</td>
        <td>
          {{ c.cnt }}
        </td>
      </tr>
      <button type="button" @click="goList">뒤로가기</button>
    </div>
  </div>
  <!-- ### 투표 시간이 종료 되었다면? ###-->
  <div v-else>
    <p class="voteDetail">
      투표 제목 :
      <span>{{ this.title }}</span>
    </p>
    <p class="voteDetail">
      투표 설명:
      <span>{{ this.description }}</span>
    </p>
    <p class="voteDetail">
      DeadLine:
      <span>{{ this.deadLine }}</span>
    </p>
    <div>투표가 종료 되었습니다.</div>
    <tr v-for="c in this.voteCnts" :key="c.voteCnts">
      <td>{{ c.content }} :</td>
      <td>
        {{ c.cnt }}
      </td>
    </tr>
    <button type="button" @click="goList">뒤로가기</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      voteId: this.$route.query.voteId,
      boardId: this.$route.query.boardId,

      //response
      title: "",
      userId: "",
      description: "",
      voteItems: [],
      voteCnts: [],
      voteCheck: "", //내가 시행한 radio
      meCheck: true, //내가 참여한 투표인지 체크(true - 아직 참여 안함, false - 이미 참여함)
      timeCheck: true, //데드라인을 넘겼는지(true - 데드라인을 안지남, false - 데드라인을 지남)
      deadLine: "",

      //request
      goVoteRequest: [],
    };
  },
  mounted() {
    this.checkVote();
    this.getVoteDetail();
  },
  methods: {
    checkVote() {
      this.$http
        .get("http://localhost:8080/vote/" + this.voteId, {
          headers: {
            "x-user-id": this.$store.state.userId,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.meCheck = res.data.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVoteDetail() {
      this.$http
        .get("http://localhost:8080/vote/" + this.boardId + "/" + this.voteId, {
          headers: {
            "x-user-id": this.$store.state.userId,
          },
        })
        .then((res) => {
          if (res.data.success) {
            let voteDetails = res.data.data;
            this.title = voteDetails.title;
            this.userId = voteDetails.userId;
            this.description = voteDetails.description;
            this.voteItems = voteDetails.voteItems;
            this.voteCnts = voteDetails.voteCnts;
            this.deadLine = voteDetails.deadLine;
            this.timeCheck = this.deadLineCheck(this.deadLine);
            console.log(this.timeCheck);
          } else {
            alert(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goVote() {
      if (this.voteCheck === "" || this.voteCheck === null) {
        alert("투표항목을 선택해주세요!");
      } else {
        this.goVoteRequest = {
          boardId: this.boardId,
          voteId: this.voteId,
          voteItemId: this.voteCheck,
        };
        this.$http
          .patch("http://localhost:8080/vote", this.goVoteRequest, {
            headers: {
              "x-user-id": this.$store.state.userId,
            },
          })
          .then((res) => {
            if (res.data.success) {
              alert("투표를 완료하였습니다.");
              this.$router.go();
            } else {
              alert(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    deadLineCheck(deadLine) {
      let today = new Date();
      let dtime = new Date(deadLine);
      if (today > dtime) {
        return false;
      }

      return true;
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
.voteDetail {
  text-align: left;
}
</style>