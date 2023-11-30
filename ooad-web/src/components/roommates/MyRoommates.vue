<template>


  <div>

    <ul>
      <li v-for="term in terms" :key="term.id">{{ term.name }}</li>
    </ul>

    <el-descriptions  title="组队信息" border = "True" column="1" size="large">
      <el-descriptions-item label="组队编号">{{ list.num }}</el-descriptions-item>
      <el-descriptions-item label="已选宿舍号">{{ list.currentRoomSelection }}</el-descriptions-item>
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

    <el-button type="primary" style="margin-left:30px" @click="">退出组队</el-button>
  </div>


</template>


<script>
import axios from 'axios';
export default {
  data() {
    return {
      isLoading: false,
      error: '',
      userTeamId: null,

      teamData:[{
        name: 'xxx',
        SID : '12110876'
      }],

      list: {
        num: 111,
        state: 'full',
        building: '1',
        currentRoomSelection:'1107',
        region: '湖畔',
        capacity: 5,
        currentMembers: 3,
      },


    };
  },
  created() {
    this.fetchTerms();
  },
  methods: {
    async fetchTerms(){
      // try {
      //   const response = await axios.get()
      // }
    }
  },

  computed: {
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