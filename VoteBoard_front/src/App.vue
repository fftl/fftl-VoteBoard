<template>
  <div id="wrapper">
    <Header />
    <div id="container">
      <router-view />
    </div>
    <Footer />
  </div>
</template>

<script>
import Header from "./components/common/Header";
import Footer from "./components/common/Footer";

export default {
  name: "App",
  components: {
    Header,
    Footer,
  },
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

#app {
  width: 100%;
  height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  position: static;
}
h1 {
  color: #43b984;
}
table {
  width: 100%;
  border-collapse: collapse;
}
.wrapper {
  min-height: 100%;
  position: relative;
  padding-bottom: 0px;
  /* footer height */
}
#container {
  position: relative;

  width: 800px;
  margin: 0px auto;
  padding: 20px;
  height: 100%;
}
a {
  text-decoration: none;
}
</style>
