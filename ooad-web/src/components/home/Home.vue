<script>
  export default {
      name: "Home",
      data(){
          return{
              user: {},
              userSID:'',
              isMissingBaseInfo: false, // 控制提示框显示
              isMissingMoreInfo: false,
              editingBase: false, // 添加编辑状态变量
              editingMore: false,
              isExplanationVisible: false,
              currentRoute: '/Home', // 设置默认选中的路由
          }
      },
      computed:{
      },
      methods:{
          handleSelect(index) {
              this.currentRoute = index; // 更新选中的路由
              this.$router.push(this.currentRoute)
          },
          getUser(){
              this.$axios.get(this.$httpUrl+'/user/'+this.userSID).then(res=>{
                  if (res.data.code===2010) {
                      this.user = res.data.data
                      this.UserMoreInfo()
                      console.log(this.user)
                  } else {
                      console.log(res.data.msg)
                      // 登录失败，可以显示错误消息
                  }
              })
          },
          init(){
              this.userSID=JSON.parse(sessionStorage.getItem('CurUser')).id
              console.log(this.userSID)
              this.getUser()
              console.log(this.user)
          },
          UserMoreInfo() {
              // 判断用户信息是否完整，如果不完整则显示提示框
              this.isMissingMoreInfo = !this.user.timetable1|| !this.user.timetable2 ||!this.user.nap
                  || !this.user.smoke || !this.user.sleepHabit || !this.user.clean || !this.user.characters
                  || !this.user.isQuiet || !this.user.temperature;
                  // || !this.user.hobbies ;
          },
          editUserMoreInfo() {
              this.editingMore = true; // 进入编辑状态
          },
          submitUserMoreInfo() {
              // 在这里执行提交到后台的逻辑，例如使用axios或者Vue Resource发送HTTP请求
              this.$axios.put(this.$httpUrl+'/user/updateAUser',this.user).then(res=>{
                  if (res.data.code===2020) {
                      console.log( res.data.msg)
                      this.getUser()
                      console.log(this.user)
                  } else {
                      console.log( res.data.msg)
                      // 登录失败，可以显示错误消息
                  }
              }),
              this.UserMoreInfo()
              // 提交成功后，离开编辑状态
              this.editingMore = false;
          },
          showExplanation() {
              this.isExplanationVisible = true; // 显示说明弹窗
          }
      },
      created() {
          this.init()
      }
  }
</script>

<template>
  <div>

      <el-menu :default-active="this.currentRoute" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="/Home" style="font-family: arial, sans-serif">{{$t('个人信息')}}</el-menu-item>
          <el-menu-item index="/MyPrefer" style="font-family: arial, sans-serif">{{$t('我的收藏')}}</el-menu-item>
      </el-menu>
      <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin-top: 30px;">

      <el-descriptions class="el-descriptions" :title="$t('基本信息')"  :column="2" size="40"  border>
             <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-edit"></i>
                  {{ $t('姓名') }}
              </template>
              <span v-if="editingBase">
                  <el-input v-model="user.name"></el-input>
              </span>
              <span v-else>
                {{ user.name}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-s-custom"></i>
                  {{ $t('学号') }}
              </template>
              <span v-if="editingBase">
          <el-input v-model="user.no"></el-input>
        </span>
              <span v-else>
          {{ user.id }}
        </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  {{ $t('性别') }}
              </template>
              <span v-if="editingBase">
          <el-input v-model="user.sex"></el-input>
        </span>
              <span v-else>
          {{ user.sex == 1 ? $t('男') : $t('女') }}
        </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-tickets"></i>
                  {{ $t('书院') }}
              </template>
              <span v-if="editingBase">
          <el-input v-model="user.college"></el-input>
        </span>
              <span v-else>
          {{ user.college }}
        </span>
          </el-descriptions-item>
      </el-descriptions>

      <el-alert
          v-if="isMissingMoreInfo"
          :title="$t('为了更好地进行算法匹配，请完善您的匹配信息')"
          type="error"
          show-icon
          center
          closable
          @close="isMissingMoreInfo = false"
          style="margin-top: 5px"
      />
      <el-descriptions class="el-descriptions" :title="$t('匹配信息（生活习惯、爱好等）')" :column="3" size="40" border>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-alarm-clock"></i>
                  {{ $t('起床时间') }}
              </template>
              <span v-if="editingMore">
                  <el-select v-model="user.timetable1">
                      <el-option label="<6:00" value="<6:00"></el-option>
                      <el-option label="6:00-7:00" value="6:00-7:00"></el-option>
                      <el-option label="7:00-8:00" value="7:00-8:00"></el-option>
                      <el-option label="8:00-9:00" value="8:00-9:00"></el-option>
                      <el-option label="9:00-10:00" value="9:00-10:00"></el-option>
                      <el-option label="10:00-11:00" value="10:00-11:00"></el-option>
                      <el-option label=">11:00" value=">11:00"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.timetable1}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-moon-night"></i>
                  {{ $t('入睡时间') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.timetable2">
                       <el-option label="<21:00" value="<21:00"></el-option>
                      <el-option label="21:00-22:00" value="21:00-22:00"></el-option>
                      <el-option label="22:00-23:00" value="22:00-23:00"></el-option>
                      <el-option label="23:00-24:00" value="23:00-24:00"></el-option>
                      <el-option label=">24:00" value=">24:00"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.timetable2}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-table-lamp"></i>
                  {{ $t('是否午睡') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.nap">
                      <el-option label="经常" value="经常"></el-option>
                      <el-option label="偶尔" value="偶尔"></el-option>
                      <el-option label="从不" value="从不"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.nap}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-smoking"></i>
                  {{ $t('是否抽烟') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.smoke">
                      <el-option label="经常" value="经常"></el-option>
                      <el-option label="偶尔" value="偶尔"></el-option>
                      <el-option label="从不" value="从不"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.smoke}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-alarm-clock"></i>
                  {{ $t('睡觉时是否有磨牙、打呼噜等习惯') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.sleepHabit">
                      <el-option label="经常" value="经常"></el-option>
                      <el-option label="偶尔" value="偶尔"></el-option>
                      <el-option label="从不" value="从不"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.sleepHabit}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-brush"></i>
                  {{ $t('是否在意卫生清洁') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.clean">
                      <el-option label="特别在意" value="特别在意"></el-option>
                      <el-option label="较为在意" value="较为在意"></el-option>
                      <el-option label="不太在意" value="不太在意"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.clean}}
              </span>
          </el-descriptions-item>

          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-timer"></i>
                  {{ $t('空调温度偏好') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.temperature">
                      <el-option label="25度及以下" value="25度及以下"></el-option>
                      <el-option label="26度及以上" value="26度及以上"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.temperature}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-magic-stick"></i>
                  {{ $t('喜欢热闹还是喜欢安静') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.isQuiet">
                      <el-option label="热闹" value="热闹"></el-option>
                      <el-option label="安静" value="安静"></el-option>
                      <el-option label="都可以" value="都可以"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.isQuiet}}
              </span>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                  <i class="el-icon-magic-stick"></i>
                  {{ $t('性格倾向') }}
              </template>
              <span v-if="editingMore">
                   <el-select v-model="user.characters">
                      <el-option label="社牛型" value="社牛型"></el-option>
                      <el-option label="社恐型" value="社恐型"></el-option>
                  </el-select>
              </span>
              <span v-else>
                {{ user.characters}}
              </span>
          </el-descriptions-item>
<!--          <el-descriptions-item>-->
<!--              <template slot="label">-->
<!--                  <i class="el-icon-trophy"></i>-->
<!--                  兴趣爱好-->
<!--              </template>-->
<!--              <span v-if="editingMore">-->
<!--                   <el-select v-model="user.hobbies" clearable multiple placeholder="请选择" >-->
<!--                       <el-option label="跑步" value="跑步"></el-option>-->
<!--                       <el-option label="摄影" value="摄影"></el-option>-->
<!--                       <el-option label="Kpop" value="Kpop"></el-option>-->
<!--                       <el-option label="睡觉" value="睡觉"></el-option>-->
<!--                       <el-option label="游戏" value="游戏"></el-option>-->
<!--                       <el-option label="动漫" value="动漫"></el-option>-->
<!--                       <el-option label="原神" value="原神"></el-option>-->
<!--                       <el-option label="音乐" value="音乐"></el-option>-->
<!--                       <el-option label="二次元" value="二次元"></el-option>-->
<!--                       <el-option label="篮球" value="篮球"></el-option>-->
<!--                       <el-option label="购物" value="购物"></el-option>-->
<!--                   </el-select>-->
<!--              </span>-->
<!--              <span v-else>-->
<!--                  {{user.hobbies}}-->
<!--              </span>-->
<!--          </el-descriptions-item>-->

      </el-descriptions>
      <div style="display: flex;align-items: center">
          <div style="margin: 5px auto 5px 65px;">
              <el-button type="success"  @click="editUserMoreInfo" >{{ $t('编辑') }}</el-button>
              <el-button type="primary"  @click="submitUserMoreInfo" style="margin-left: 5px">{{ $t('提交') }}</el-button>
          </div>
      </div>
      <div style="display: flex;align-items: center">
          <div style="margin-top: 10px; margin-left: 65px; margin-bottom: 20px">
              <el-button type="info" round>
                  {{ $t('点击查看你的专属算法推荐室友') }}
              </el-button>
              <i class="el-icon-warning-outline" style="margin-left: 5px; cursor: pointer;color:#817f7f;" @click="showExplanation"></i>
          </div>
      </div>
<!--      <DateUtils></DateUtils>-->
      <el-dialog
          :title="$t('详情')"
          :visible="isExplanationVisible"
          @close="isExplanationVisible = false"
      >
      <!-- 在这里添加弹窗的说明内容 -->
      <!-- 例如： -->
      <p>这是一个基于K-means和决策树实现的室友分配算法。请先完善您的匹配信息，我们会尽可能地为您选出生活习惯、兴趣爱好等相似度高的室友人选，
          你可以与他们进行进一步的联系和沟通。
          另外请注意，算法推荐仅作为参考，为了未来更舒适的宿舍生活，还需彼此间多一份体谅与友善。</p>
      </el-dialog>
    </div>
  </div>
</template>

<style scoped>
.el-descriptions{
    width:90%;

    margin: 0 auto;
    text-align: center;
}

</style>