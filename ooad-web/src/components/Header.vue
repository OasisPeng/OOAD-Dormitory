<script>
  export default {
      name:"Header",
      data(){
          return{
              //user:JSON.parse(sessionStorage.getItem('CurUser'))
              user: JSON.parse(sessionStorage.getItem('UserData')),
              avatarDialogVisible: false ,// 控制头像查看对话框的显示/隐藏,
          }
      },
      methods:{
          handleCommand(command) {
              switch (command) {
                  case 'myMessage':
                      this.$router.push('/MyMessage');
                      break;

                  case 'like':
                      this.$router.push('/Like');
                      break;

                  case 'comment':
                      this.$router.push('/Comment');
                      break;

                  case 'broadcast':
                      this.$router.push('/BroadcastMessages');
                      break;

                  case 'invite':
                      this.$router.push('/TeamInvitation');
                      break;

                  case 'offer':
                      this.$router.push('/TeamOffer');
                      break;

                  default:
                      // 如果没有匹配的命令，执行默认操作或者抛出错误
                      console.warn('Unknown command:', command);
              }
          },
          toUser(){
              console.log('to_user')
              this.$router.push('/Home')
          },
          logout(){
              //退出确认
              this.$confirm(this.$t('您确定要退出登录吗？'), this.$t('提示'), {
                  confirmButtonText: this.$t('确定'),
                  cancelButtonText: this.$t('取消'),
                  type: 'warning',
                  center: true
              }).then(() => {
                  this.$message({
                      type: 'success',
                      message: this.$t('退出登录成功')
                  });
                  this.$router.push('/'); //路由
                  //调一下后端的logout
                  sessionStorage.clear();
              }).catch(() => {
                  this.$message({
                      type: 'info',
                      message: this.$t('已取消退出登录')
                  });
              });
          },
          collapse(){
              this.$emit('doCollapse')
          },
          // handleUploadSuccess(response,file) {
          //     // 处理上传成功后的操作
          //     this.avatar = URL.createObjectURL(file.raw); // 更新头像URL
          //     console.log(this.avatar)
          // },
          // showAvatarDialog() {
          //     // 点击头像时显示头像查看对话框
          //     this.avatarDialogVisible = true;
          // },
          jumpToMessage(){
              this.$router.push("/MyMessage")
          },
          switchLanguage(lang) {
              this.$i18n.locale = lang;
          },

      },
      props:{
          icon:String
      },
      created() {
          this.$router.push("/Home")
      },
      mounted() {
      }
  };
</script>

<template>
  <div style="display: flex;line-height: 60px;">
      <div>
          <i :class="icon" style="font-size: 20px;line-height: 60px;cursor: pointer;" @click="collapse"></i>
      </div>
      <div>
          <el-button style="margin-left: 10px" type="text" @click="switchLanguage('en')">English</el-button>
          <el-button type="text" @click="switchLanguage('zh')">中文</el-button>
      </div>
      <div style="flex: 1; font-size: 30px; text-align: center; margin-left:110px;font-weight: bold; color: #282626; font-family: 'Arial', sans-serif;">
          <span>{{ $t('宿舍选择系统') }}</span>
      </div>
      <div >
          <el-dropdown trigger="hover" @command="handleCommand">
              <el-badge is-dot  style="line-height: 45px;">
                  <i class="el-icon-chat-dot-round" style="font-size: 25px; line-height: 30px; margin-right: 10px;" @click= "jumpToMessage"></i>
              </el-badge>

              <el-dropdown-menu slot="dropdown" >
                  <el-dropdown-item class="clearfix" command="myMessage">
                     {{$t('我的消息')}}
<!--                      <el-badge class="mark" :value="unreadMyMessage" />-->
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix" command="like">
                      {{$t('点赞')}}
<!--                      <el-badge class="mark" :value="unreadLike" />-->
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix" command="comment">
                      {{$t('评论')}}
<!--                      <el-badge class="mark" :value="unreadComment" />-->
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix" command="broadcast">
                      {{$t('广播通知')}}
<!--                      <el-badge class="mark" :value="unreadBroadcast" />-->
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix" command="invite">
                      {{$t('组队邀请')}}
<!--                      <el-badge class="mark" :value="unreadInvitation" />-->
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix" command="offer">
                      {{$t('组队申请')}}
<!--                      <el-badge class="mark" :value="unreadOffer" />-->
                  </el-dropdown-item>
              </el-dropdown-menu>
          </el-dropdown>
      </div>
      <el-avatar v-if="user.avatar" :src="user.avatar" :size="32" style="margin-left: 10px;margin-top: 10px;margin-right: 3px" ></el-avatar>
      <el-dropdown>
          <span>
              {{ user.name }}
          </span>
          <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
            <el-dropdown-menu slot="dropdown">
<!--                <el-upload-->
<!--                    action="https://jsonplaceholder.typicode.com/posts/"-->
<!--                    :show-file-list="false"-->
<!--                    :on-success="handleUploadSuccess"-->
<!--                >-->
<!--                    <el-dropdown-item slot="trigger">上传头像</el-dropdown-item>-->
<!--                </el-upload>-->
                <el-dropdown-item @click.native="toUser">{{ $t('个人中心') }}</el-dropdown-item>
                <el-dropdown-item @click.native="logout">{{ $t('退出登录') }}</el-dropdown-item>
            </el-dropdown-menu>
      </el-dropdown>
<!--      <el-dialog-->
<!--          title=""-->
<!--          :visible.sync="avatarDialogVisible"-->
<!--          width="300px"-->
<!--          style="text-align: center"-->
<!--      >-->
<!--          <img v-if="user.avatar" :src="user.avatar" alt="User Avatar" style="max-width: 100%;height: 100%">-->
<!--          <el-upload-->
<!--              action="https://jsonplaceholder.typicode.com/posts/"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleUploadSuccess"-->
<!--          >-->
<!--              <el-button size="small" type="primary" plain>上传头像</el-button>-->
<!--          </el-upload>-->
<!--      </el-dialog>-->
  </div>
</template>

<style scoped>

</style>