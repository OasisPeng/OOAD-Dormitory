<template>


  <div>

    <el-descriptions  title="组队信息" :border = true  :column=1 size="large" >
      <el-descriptions-item label="组队编号">{{ this.curTeamId }}</el-descriptions-item>
      <el-descriptions-item label="队名">{{ this.curTeam.name }}</el-descriptions-item>
      <el-descriptions-item label="队长ID">{{ this.curTeam.headId }}</el-descriptions-item>
      <el-descriptions-item label="已选宿舍号">{{ selection }}</el-descriptions-item>
      <el-descriptions-item label="状态" size="small">{{ status }}</el-descriptions-item>
      <el-descriptions-item label="人数">{{ this.curTeam.current +'/'+ this.curTeam.capacity  }}</el-descriptions-item>
    </el-descriptions>

    <el-table :data="this.teamMembers" style="width: 100%" :border="true" >
      <el-table-column prop="id" label="SID"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">
          <el-button type="danger" @click="seeStudentDet(scope.row)">查看</el-button>
          <el-button type="danger" v-if="isLeader" @click="kick(scope.row)">踢出</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" style="margin-left:30px" @click="exitTeam">退出组队</el-button>
    <el-button type="primary" v-if="isLeader" style="margin-left:30px" @click="showDialog">邀请成员</el-button>

    <el-dialog
        title="邀请成员"
        :visible.sync="inviteMemberVis"
        width="30%">
      <el-input v-model="input" placeholder="目标学号"></el-input>

      <span slot="footer" class="dialog-footer">
        <el-button @click="inviteMemberVis= false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="学生详情" :visible.sync="studentDetailsVis" width="50%">
      <el-descriptions :border = true  :column=1 size="large" >
        <el-descriptions-item label="用户学号">{{ this.targetStudent.id }}</el-descriptions-item>
        <el-descriptions-item label="姓名">{{ this.targetStudent.name }}</el-descriptions-item>
        <el-descriptions-item label="书院">{{ this.targetStudent.college }}</el-descriptions-item>
        <el-descriptions-item label="性格">{{ this.targetStudent.characters }}</el-descriptions-item>
        <el-descriptions-item label="午睡">{{ this.targetStudent.nap }}</el-descriptions-item>

        <el-descriptions-item label="是否抽烟">{{ this.targetStudent.smoke }}</el-descriptions-item>
        <el-descriptions-item label="性别">{{ this.targetStudent.sex }}</el-descriptions-item>
        <el-descriptions-item label="打呼噜">{{ this.targetStudent.sleepHabit }}</el-descriptions-item>
        <el-descriptions-item label="学历">{{ this.targetStudent.studentType }}</el-descriptions-item>
        <el-descriptions-item label="是否在意卫生清洁">{{ this.targetStudent.clean }}</el-descriptions-item>

        <el-descriptions-item label="年纪">{{ this.targetStudent.grade }}</el-descriptions-item>
        <el-descriptions-item label="喜欢安静还是热闹">{{ this.targetStudent.isQuiet}}</el-descriptions-item>
        <el-descriptions-item label="起床时间">{{ this.targetStudent.timetable1 }}</el-descriptions-item>
        <el-descriptions-item label="睡觉时间">{{ this.targetStudent.timetable2 }}</el-descriptions-item>

      </el-descriptions>
    </el-dialog>




  </div>


</template>


<script>
import axios from 'axios';
export default {
  data() {
    return {

      user: null,
      curTeamId: null,
      isLoading: false,
      error: '',
      userTeamId: null,
      dialogVisible: false,
      curTeam: null,
      teamMembers:[],
      isLeader:null,
      inviteMemberVis:false,
      input:null,
      studentDetailsVis:false,
      targetStudent:[]
    };
  },

  created() {

    this.user = JSON.parse(sessionStorage.getItem('CurUser'));
    // console.log(this.user)
    this.init()

  },

  methods: {
    async kick(rowData){
      try {
        var path = this.$httpUrl + '/team/' + String(this.curTeamId+'/'+rowData.id)
        console.log(path)
        const response = await axios.delete(path,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        // 处理成功返回的数据
        if (response.data.code === 2030) {
          console.log('删除成功', response.data);

        } else {
          console.error('删除失败：', response.msg);
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
      this.init()
    }
    ,
    async seeStudentDet(rowData){
      this.studentDetailsVis = true
      try {
        var path = this.$httpUrl + `/user/${rowData.id}`
        console.log(path)
        const response = await axios.get(path,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        // 处理成功返回的数据
        if (response.data.code === 2010) {
          console.log('查询成功：', response.data);
          this.targetStudent = response.data.data
        } else {
          console.error('查询失败：', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败：', error);
      }

    },
    showDialog() {
      this.inviteMemberVis = true
    },

    async init() {
      try {
        var path = this.$httpUrl + '/user/team/' + String(this.user.id)
        console.log(path)
        const response = await axios.get(path,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });

        // 处理成功返回的数据
        if (response.data.code === 2010) {
          console.log('查询成功，组队ID为：', response.data);
          this.curTeamId = response.data.data;
        } else {
          console.error('查询失败：', response.msg);
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
      this.getUserTeamData()
      this.getTeamMembers()

    },
    async isTeamLeader(){

      console.log('当前用户id: ' + this.user.id + ' 队长id: ' + this.curTeam.headId)
      if (this.user.id == this.curTeam.headId) this.isLeader = true
      else this.isLeader = false
    },
    getTeamMembers() {  //通过队伍id获得成员信息
      // console.log('getTeamMembers调用：' + this.curTeamId)
      this.$axios.get(this.$httpUrl+'/users',
          {
            withCredentials: true,
            headers:{
              'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
            }}
      ).then(res=>{
        if (res.data.code===2010) {
          // 过滤队友
          console.log(res.data.data)
          this.teamMembers = res.data.data.filter(member => member.teamId === this.curTeamId);
          console.log("组队成员信息：")
          console.log(this.teamMembers)
        } else {
          console.log(res.data.msg)
        }
      })
    },
    // 取得当前用户组队的详细信息
    async getUserTeamData() {
      try {
        var path = this.$httpUrl + '/team/' + String(this.curTeamId)
        console.log(path)
        const response = await axios.get(path,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        // 处理成功返回的数据
        if (response.data.code === 2010) {
          console.log('查询成功，组队为', response.data);
          this.curTeam = response.data.data;
        } else {
          console.error('查询失败：', response.msg);
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
      this.isTeamLeader()
    },

    // 查询当前用户所在组队的信息


    async exitTeam(){
      try {
        var path = this.$httpUrl +'/team/' + this.team + '/'+ String(this.user.id);
        console.log(path)
        const response = await axios.delete(path,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        // 处理成功返回的数据
        if (response.data.code === 2030) {
          console.log('删除成功');
          console.log(response.data)
        } else {
          console.error('删除失败，请重试');
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
    },
      async handleConfirm(){
        try {
          const resopnse = await axios.post(this.$httpUrl + '/application',{
            teamId:this.curTeamId,
            userId:this.input,
            type:1
          },{
            withCredentials: true,
            headers:{
              'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
            }
          });
          console.log(resopnse.data)
          if (resopnse.code == 2040){
            console.log('成功邀请')
          }
          else if (resopnse.code == 2041){
            console.error('邀请失败')
          }
        }
        catch (error){console.error('验证出错', error);
        }
        this.inviteMemberVis = false
      }
  },

  // computed内容回自动计算并动态变化
  computed: {
    // 此处定义了一个status变量
    status() {
      // 根据目前的member数量和capacity判断是否满员
      if (this.curTeam.capacity == this.curTeam.current) {
        return 'Full';
      } else if (this.curTeam.capacity > this.curTeam.current) {
        return 'unfull';
      } else {
        return 'unknown';
      }
    },
    selection(){
      if (this.curTeam.dorm !== null) return this.curTeam.dorm
      else return 'Not yet selected'
    }
  },

};
</script>

<style>
.el-descriptions{
  border-radius: 30px;
  margin-bottom: 100px;
}
</style>