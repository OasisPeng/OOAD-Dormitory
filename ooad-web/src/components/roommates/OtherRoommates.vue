<template>
  <div>
    <el-button @click="getAllTeamsavailable">筛选未满组队</el-button>

    <el-table
        :data="allTeams"
        >
      <el-table-column
          prop="headId"
          label="队长学号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="id"
          label="组队编号"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="组队名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="current"
          label="当前人数"
          width="180"
          sortable>
      </el-table-column>
      <el-table-column
          prop="capacity"
          label="容量"
          width="180"
          sortable>
      </el-table-column>

      <el-table-column label="操作" >

        <template slot-scope="scope">
          <el-button type="text" @click="apply(scope.row)">申请加入</el-button>
        </template>

      </el-table-column>


    </el-table>

<!--    <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="currentPage"-->
<!--        :page-sizes="[10, 20, 30, 40]"-->
<!--        :page-size="pageSize"-->
<!--        :total="totalTeams"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--    ></el-pagination>-->


    <el-button type="primary" @click="showInputDialog">创建组队</el-button>

    <el-dialog
        title="创建组队"
        :visible.sync="dialogVisible"
        width="30%">
      <el-input v-model="input" placeholder="组队人数"></el-input>
      <el-input v-model="inputname" placeholder="队名"></el-input>


      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="createTeam">确 定</el-button>
      </span>
    </el-dialog>


  </div>


</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: null,
      userId:null,
      allTeams: [],
      capacity: 3,
      dialogVisible : false,
      input:null,
      inputname:null,
      userData:null

    };
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem('CurUser'))
    this.userData = JSON.parse(sessionStorage.getItem('UserData'))

    this.getAllTeams();
    // this.userId = this.user.id;
  },
  mounted() {

  },
  methods: {
    showDialog() {
      this.dialogVisible = true; // 显示窗口
    },
    closeDialog() {
      this.dialogVisible = false; // 隐藏窗口
    },
    showInputDialog() {
      this.dialogVisible = true;
    },
    async apply(rowData){
      try {
        const resopnse = await axios.post(this.$httpUrl + '/application',{
          teamId:rowData.id,
          userId:this.user.id,
          type:0
        },{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        console.log(resopnse.data)
      }
      catch (error){console.error('验证出错', error);
      }



    },

    async getAllTeams(){
      try {
        const resopnse = await axios.get(this.$httpUrl + '/teams',{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        if (resopnse.data.code == 2010){
          this.allTeams = resopnse.data.data;
          console.log('查询全部组队成功')
          console.log(this.allTeams)
        }
        else
          if (resopnse.data.code == 2011){
          console.error('查询全部组队失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }


    },
    // getTeamMembers() {  //通过队伍id获得成员信息
    //   // console.log('getTeamMembers调用：' + this.curTeamId)
    //   this.$axios.get(this.$httpUrl+'/users',
    //       {
    //         withCredentials: true,
    //         headers:{
    //           'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
    //         }}
    //   ).then(res=>{
    //     if (res.data.code===2010) {
    //       // 过滤队友
    //       console.log(res.data.data)
    //       this.teamMembers = res.data.data.filter(member => member.teamId === this.curTeamId);
    //
    //       console.log("组队成员信息：")
    //       console.log('curTeamId: ' + String(this.curTeamId) )
    //       console.log(this.teamMembers)
    //     } else {
    //       console.log(res.data.msg)
    //     }
    //   })
    // },
    async getAllTeamsAlt(){
      try {
        const resopnse = await axios.get(this.$httpUrl + '/teams/getUnFull/' + this.user.sex,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        if (resopnse.data.code == 2010){
          this.allTeams = resopnse.data.data;
          console.log('查询全部组队成功')
          console.log(this.allTeams)
        }
        else
        if (resopnse.data.code == 2011){
          console.error('查询全部组队失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }
    },
    async checkSex(targetId){
      try {
        console.log('target: ' + targetId)

        const resopnse = await axios.get(this.$httpUrl + '/user/' + targetId,{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        console.log('checking sex: ' + resopnse.data)
        if (resopnse.data.code == 2010){
          console.log('target sex:')
          console.log(resopnse.data.data.sex + this.userData.sex)

          if (resopnse.data.data.sex == this.userData.sex)
          {console.log('true')
            return true}
          else
          {console.log('false')
            return false}
        }
        else
        if (resopnse.data.code == 2011){
          console.error('查询全部组队失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }
    },


    async getAllTeamsavailable(){
      try {
        const resopnse = await axios.get(this.$httpUrl + '/teams',{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        if (resopnse.data.code == 2010){
          this.allTeams = resopnse.data.data.filter(member => ((member.capacity - member.current > 0) && this.checkSex(member.headId)));
          console.log('筛选全部组队成功')
          console.log(this.allTeams)
        }
        else
        if (resopnse.data.code == 2011){
          console.error('筛选全部组队失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }
    },

    async createTeam(){
      try {
        const resopnse = await axios.post(this.$httpUrl + '/team',{
          name:this.inputname,
          headId: this.user.id,
          capacity: this.input
        },{
          withCredentials: true,
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }
        });
        if (resopnse.code == 2040){
          console.log('成功添加寝室')
          await this.$router.push('/MyRoommates')
        }
        else if (resopnse.code == 2041){
          console.error('增加失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }
      this.$axios.get(this.$httpUrl+'/user/'+JSON.parse(sessionStorage.getItem('CurUser')).id, {
        withCredentials: true,
        headers:{
          'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
        }
      }).then(res=>{
        if (res.data.code===2010) {
          sessionStorage.setItem("UserData", JSON.stringify(res.data.data));
          console.log(JSON.parse(sessionStorage.getItem('UserData')));
          this.$router.push({ name:'index' });
        } else {
          console.log(res.data.msg)
          // 登录失败，可以显示错误消息
        }
      })
      this.dialogVisible = false
    }
    ,

    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.currentPage = 1;
      this.getTeams();
    },
    handleCurrentChange(newPage) {
      this.currentPage = newPage;
      this.getTeams();
    },


    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date');
    },

    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    formatter(row, column) {
      return row.currentRoomSelection;
    },
    filterTag(value, row) {
      return row.region === value;
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    }

  },
};
</script>
<style>
el-descriptions{
  border-radius: 30px;
  margin-bottom: 100px;
}
div {
  margin-bottom: 10px; /* 例如，添加10像素的下间距 */
}

span {
  margin-top: 10px; /* 例如，添加10像素的上间距 */
}
</style>
