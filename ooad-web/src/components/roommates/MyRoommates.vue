<template>


  <div>

<!--    <ul>-->
<!--      <li v-for="term in terms" :key="term.id">{{ term.name }}</li>-->
<!--    </ul>-->

    <el-descriptions  title="组队信息" border = "True" column="1" size="large" >
      <el-descriptions-item label="组队编号">{{ list.num }}</el-descriptions-item>
      <el-descriptions-item label="已选宿舍号">{{ list.dorm }}</el-descriptions-item>
      <el-descriptions-item label="楼号">{{ list.building }}</el-descriptions-item>
      <el-descriptions-item label="状态">
        <el-tag size="small">{{ status }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="地区">{{ list.region }}</el-descriptions-item>
      <el-descriptions-item label="人数">{{ list.currentMembers +'/'+ list.capacity  }}</el-descriptions-item>
    </el-descriptions>

    <el-table :data="teamData" style="width: 100%" border="True" >
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="SID" label="SID"></el-table-column>
      <el-table-column label="操作">

        <template slot-scope="scope">

          <el-button type="danger" @click="deleteRow(scope.$index)">查看</el-button>
          <el-button type="danger" @click="deleteRow(scope.$index)">踢出</el-button>

        </template>

      </el-table-column>
    </el-table>

    <el-button type="primary" style="margin-left:30px" @click="exitTeam">退出组队</el-button>
    <el-button type="primary" style="margin-left:30px" @click="showDialog">邀请成员</el-button>

    <el-dialog
        title="邀请成员"
        :visible.sync="dialogVisible"
        width="30%">
      <el-input v-model="input" placeholder="组队人数"></el-input>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>


</template>


<script>
import axios from 'axios';
export default {
  data() {
    return {
      user: null,
      team: [],
      isLoading: false,
      error: '',
      userTeamId: null,
      dialogVisible: false,

      teamData:[{
        name: 'x同学',
        SID : '12110876'
      }],

      list: {
        num: 1,
        dorm: 1101,
        state: 'full',
        building: '1',
        currentRoomSelection:'1107',
        region: '湖畔',
        capacity: 5,
        currentMembers: 3,
      },

      input:''
    };
  },

  created() {
    this.user = JSON.parse(sessionStorage.getItem('CurUser'));
    this.getUserTeam()

  },

  methods: {
    showDialog() {
      this.dialogVisible = true
    },
    async inviteMember(){

    }
    ,
    // 查询当前用户所在组队的信息
    async getUserTeam() {
      try {
        const response = await axios.get(this.$httpUrl + '/user/team/'+ String(this.user.id));
            // {user: this.user} );
        // 处理成功返回的数据
        if (response.code == 2010) {

          console.log('查询成功，组队ID为：', response.data);
          this.team = response.data.data;
          console.log(this.team)

        } else {
          console.error('查询失败：', response.msg);
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
    },

    async exitTeam(){
      try {
        const response = await axios.delete(this.$httpUrl +'/' + this.team['team_id']+ '/'+ String(this.user.id));
            // {user: this.user} );
        // 处理成功返回的数据
        if (response.code === 2030) {
          console.log('删除成功');
        } else {
          console.error('删除失败，请重试');
        }
      } catch (error) {
        console.error('请求失败：', error);
      }
    },
      handleConfirm(){

      }
  },

  // computed内容回自动计算并动态变化
  computed: {
    // 此处定义了一个status变量
    status() {
      // 根据目前的member数量和capacity判断是否满员
      if (this.list.capacity === this.list.currentMembers) {
        return 'full';
      } else if (this.list.capacity > this.list.currentMembers) {
        return 'unfull';
      } else {
        return 'unknown';
      }
    },
  },

};
</script>

<style>
.el-descriptions{
  border-radius: 30px;
  margin-bottom: 100px;
}
</style>