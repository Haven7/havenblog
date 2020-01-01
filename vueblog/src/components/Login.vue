<template>
  <div>
    <el-form
      :rules="rules"
      class="login-container"
      label-position="left"
      label-width="0px"
      v-loading="loading"
    >
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="account">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
      </el-form-item>
      <el-button type="primary" @click="regDialog = true" style="width: 100%">注册</el-button>
    </el-form>
    <div class="regAddDialog">
      <el-dialog :title="'注册新用户'" :visible.sync="regDialog" width="40%">
        <el-form>
          <el-form-item label="用户名">
            <el-input v-model="newUser.username"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="newUser.nickname"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="newUser.password"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="regDialog = false">取 消</el-button>
          <template>
            <el-button type="primary" @click="reg(newUser)">添加</el-button>
          </template>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { postRequest } from "../utils/api";
import { putRequest } from "../utils/api";
export default {
  data() {
    return {
      newUser: {
        username: null,
        password: null,
        nickname: null
      },
      regDialog: false,
      rules: {
        account: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        checkPass: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true,
      loginForm: {
        username: "sang",
        password: "123"
      },
      loading: false
    };
  },
  methods: {
    submitClick: function() {
      var _this = this;
      this.loading = true;
      postRequest("/login", {
        username: this.loginForm.username,
        password: this.loginForm.password
      }).then(
        resp => {
          _this.loading = false;
          if (resp.status == 200) {
            //成功
            var json = resp.data;
            if (json.status == "success") {
              _this.$router.replace({ path: "/home" });
            } else {
              _this.$alert("登录失败!", "失败!");
            }
          } else {
            //失败
            _this.$alert("登录失败!", "失败!");
          }
        },
        resp => {
          _this.loading = false;
          _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
        }
      );
    },
    reg: function(newUser) {
      console.log(newUser);
      var _this = this;
_this.loading = true;
      postRequest("/reg", {
        username: this.newUser.username,
        nickname: this.newUser.nickname,
        password: this.newUser.password
      }).then(
        resp => {
          _this.loading = false;
          _this.regDialog=false;
          if (resp.status == 200) {
            //成功
            var json = resp.data;
            if (json.status == "success") {
              // _this.$router.replace({ path: "/home" });
              _this.$alert("注册成功!", "成功!");
            } else {
              _this.$alert("注册失败!", "失败!");
            }
          } else {
            //失败
            _this.$alert("注册失败!", "失败!");
          }
        },
        resp => {
          _this.loading = false;
          _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
        }
        // regAPI({
        //   stockJson: JSON.stringify(newUser)
        //   //  User:newUser
        // }).then(res => {
        // if (res.status === 1) {
        //   this.$message.success(res.data);
        //   this.getBasicData();
        // } else {
        //   this.$message.error(res.data);
        // }
      );
    }
  }
};
</script>
<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
