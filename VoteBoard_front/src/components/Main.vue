<template>
  <div>
    <ul>
      <li>
        <p>
          아이디 직접 입력하여 로그인 :
          <input type="text" v-model="inputUserId" ref="inputUserId" />
          <button type="button" @click="loginUserId">로그인</button>
        </p>
      </li>
      <li>
        <button type="button" @click="createUserId">
          랜덤 아이디로 로그인
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: "",
      newUserId: "",
      inputUserId: "",
    };
  },
  methods: {
    goList() {
      this.$router.push({ path: "/list" });
    },

    createUserId() {
      this.$http
        .get("http://localhost:8080/user")
        .then((res) => {
          this.$store.commit("setUserId", res.data.data);
          this.goList();
        })
        .catch((err) => {
          alert(err);
        });
    },

    loginUserId() {
      //공백포함 네글자와, 공백 등을 제외하고 로그인이 가능합니다.
      if (this.checkInputId(this.inputUserId)) {
        console.log("문자테스트!");
        this.userId = this.inputUserId;
        this.$store.commit("setUserId", this.inputUserId);
        this.goList();
      }
    },

    checkInputId(str) {
      var check_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
      var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
      if (str.length != 4) {
        alert("영어와 숫자만을 포함한 네자리 문자를 입력해주세요!");
        return false;
      }

      for (let i = 0; i < str.length; i++) {
        if (
          check_kor.test(str.charAt(i)) ||
          check_spc.test(str.charAt(i)) ||
          str.charAt(i) == " "
        ) {
          alert("영어와 숫자만을 포함한 네자리 문자를 입력해주세요!");
          return false;
        }
      }

      return true;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
