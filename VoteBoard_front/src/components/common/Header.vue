<template>
  <header>
    <h1 v-if="userId === ''">
      <a href="javascript:;" @click="logout">Daangn Vote System</a>
    </h1>
    <h1 v-else>
      <router-link to="/list">Daangn Vote System</router-link>
    </h1>
    <div v-if="userId === ''"></div>
    <div v-else id="myId">
      <span id="myPage"
        >현재 로그인 아이디 :
        <a href="javascript:;" @click="goListMy">{{ this.userId }}</a></span
      >
      <span id="myLogout"
        ><router-link to="/" @click="logout">로그아웃</router-link></span
      >
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      userId: "",
    };
  },
  mounted() {
    //새로고침 하더라도, localStorage에 저장되어 있는 userId 값을 가져와 다시 store와 this.userId에 할당해주어 유지줍니다.
    if (this.userId == "") {
      let arr = localStorage.getItem("vuex").split('"');
      this.$store.commit("setUserId", arr[3]);
      this.userId = arr[3];
    }
  },
  //this.$store.state.userId; (store에 있는 userId 값을 체크하여 userId 값이 변경될 때마다 해당 값으로 변경시켜줍니다.)
  computed: {
    check_userId() {
      return this.$store.state.userId;
    },
  },
  watch: {
    check_userId(val) {
      this.userId = val;
    },
  },
  methods: {
    goListMy() {
      this.$router.push({ path: "/listMy" });
    },
    goHome() {
      this.$roter.push({ path: "/main" });
    },
    goList() {
      this.$roter.push({ path: "/list" });
    },
    logout() {
      this.$store.commit("resetUserId");
    },
  },
};
</script>

<style scoped>
header {
  width: 100%;
  text-align: center;
  position: relative;
  margin-bottom: 40px;
}
#myId {
  width: 100%;
}
#myLogout {
  float: right;
  text-align: right;
  margin-right: 20px;
}
#myPage {
  float: left;
  text-align: left;
  margin-left: 20px;
}
</style>