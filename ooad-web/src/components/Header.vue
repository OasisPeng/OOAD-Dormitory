<script>
  export default {
      name:"Header",
      data(){
          return{
              //user:JSON.parse(sessionStorage.getItem('CurUser'))
              user: JSON.parse(sessionStorage.getItem('CurUser')),
              avatarDialogVisible: false // 控制头像查看对话框的显示/隐藏
          }
      },
      methods:{
          toUser(){
              console.log('to_user')
              this.$router.push('/Home')
          },
          logout(){
              //退出确认
              this.$confirm('您确定要退出登录吗？','提示',{
                  confirmButtonText:'确定',
                  type:'warning',
                  center:true
              }).then(()=>{
                  this.$message({
                      type:'success',
                      message:'退出登录成功'
                  })
                  this.$router.push('/');//路由
                  sessionStorage.clear()
              })
                  .catch(()=>{
                      this.$message({
                          type:'info',
                          message:'已取消退出登录'
                      })
                  })
          },
          collapse(){
              this.$emit('doCollapse')
          },
          handleUploadSuccess(response,file) {
              // 处理上传成功后的操作
              this.avatar = URL.createObjectURL(file.raw); // 更新头像URL
              console.log(this.avatar)
          },
          showAvatarDialog() {
              // 点击头像时显示头像查看对话框
              this.avatarDialogVisible = true;
          }
      },
      props:{
          icon:String
      },
      created() {
          this.$router.push("/Home")
      }
  };
</script>

<template>
  <div style="display: flex;line-height: 60px;">
      <div>
          <i :class="icon" style="font-size: 20px;line-height: 60px;cursor: pointer;" @click="collapse"></i>
      </div>
      <div style="flex: 1; font-size: 30px; text-align: center; margin-left:110px;font-weight: bold; color: #282626; font-family: 'Arial', sans-serif;">
          <span>Dormitory Selection System</span>
      </div>
      <div >
          <el-dropdown trigger="hover">
              <a href="/Message" target="_blank">
                  <el-badge is-dot class="item" style="line-height: 45px;">
                      <i class="el-icon-chat-dot-round" style="font-size: 25px; line-height: 30px; margin-right: 10px;"></i>
                  </el-badge>

              </a>
              <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item class="clearfix">
                      我的消息
                      <el-badge class="mark" :value="10" />
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix">
                      点赞
                      <el-badge class="mark" :value="3" />
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix">
                      评论
                      <el-badge class="mark" :value="3" />
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix">
                      广播通知
                      <el-badge class="mark" :value="12" />
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix">
                      组队邀请
                      <el-badge class="mark" :value="1" />
                  </el-dropdown-item>
                  <el-dropdown-item class="clearfix">
                      组队申请
<!--                      <el-badge class="mark" :value="0" />-->
                  </el-dropdown-item>
              </el-dropdown-menu>
          </el-dropdown>
      </div>
      <el-avatar v-if="user.avatar" :src="user.avatar" :size="32" style="margin-left: 10px;margin-top: 10px;margin-right: 3px" @click.native="showAvatarDialog"></el-avatar>
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
              <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
      </el-dropdown>
      <el-dialog
          title=""
          :visible.sync="avatarDialogVisible"
          width="300px"
          style="text-align: center"
      >
          <img v-if="user.avatar" :src="user.avatar" alt="User Avatar" style="max-width: 100%;height: 100%">
          <el-upload
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleUploadSuccess"
          >
              <el-button size="small" type="primary" plain>上传头像</el-button>
          </el-upload>
      </el-dialog>
  </div>
</template>

<style scoped>

</style>