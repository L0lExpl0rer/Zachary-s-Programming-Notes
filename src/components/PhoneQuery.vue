<template>
  <div id="app">
    <el-container>
      <el-header>
        <h1>手机号码查询</h1>
      </el-header>
      <el-main>
        <div class="query-container">
          <el-input
              v-model="country"
              placeholder="输入国家码，中国: 86 ; 美国: 1"
              style="margin-top: 10px;"
          ></el-input>
          <el-input
              v-model="phone"
              placeholder="输入手机号"
              @input="onPhoneInput"
          ></el-input>
          <el-button
              v-for="button in buttons"
              :key="button.label"
              type="primary"
              style="margin: 5px;"
              @click="handleButtonClick(button.method)"
          >
            {{ button.label }}
          </el-button>
          <div v-if="result !== null" class="result-box">
            <p>结果：{{ result }}</p>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>


<style scoped>
#app {
  font-family: 'Arial', sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f4f9;
}

.el-container {
  width: 100%;
  max-width: 600px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.el-header {
  text-align: center;
  background-color: #409EFF;
  color: white;
  padding: 0;
  font-size: 18px;
  border-radius: 8px;
}

.query-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.el-input {
  width: 100%;
  max-width: 400px;
  margin: 10px 0;
}

.el-button {
  width: 100%;
  max-width: 150px;
  padding: 10px 20px;
}

.result-box {
  width: 100%;
  max-width: 400px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  text-align: center;
  margin-top: 20px;
  color: #333;
}

.el-button:hover {
  background-color: #66b1ff;
}

.el-input:focus {
  border-color: #409EFF;
}

.el-button:active {
  background-color: #409EFF;
}

</style>

<script>
import axios from "axios";

export default {
  data() {
    return {
      phone: "",
      country: "",
      result: null,
      buttons: [
        {label: "地理位置", method: "queryLocation"},
        {label: "运营商", method: "queryOperator"},
        {label: "详情", method: "queryDetails"},
        {label: "是否合法", method: "validatePhone"},
      ],
    };
  },
  methods: {
    async onPhoneInput() {
      if (this.phone.length === 3) {
        try {
          const response = await axios.get("/api/phone/operator/prefix", {
            params: {phonePrefix: this.phone},
          });
          this.result = `运营商：${response.data.data}`;
        } catch (error) {
          this.result = `错误：${error.response?.data?.message || error.message}`;
        }
      }
    },
    async handleButtonClick(method) {
      if (!this.phone || !this.country) {
        this.result = "请输入完整的手机号和国家码";
        return;
      }

      try {
        let response;
        switch (method) {
          case "queryLocation":
            response = await axios.get("/api/phone/location", {
              params: {phone: this.phone, country: this.country},
            });
            break;
          case "queryOperator":
            response = await axios.get(`/api/phone/operator/${this.phone}/${this.country}`);
            break;
          case "queryDetails":
            response = await axios.get("/api/phone/details", {
              params: {phone: this.phone, country: this.country},
            });
            break;
          case "validatePhone":
            response = await axios.get("/api/phone/valid", {
              params: {phone: this.phone, country: this.country},
            });
            break;
          default:
            throw new Error("未定义的操作");
        }
        this.result = response.data.data;
      } catch (error) {
        this.result = `错误：${error.response?.data?.message || error.message}`;
      }
    },
  },
};
</script>