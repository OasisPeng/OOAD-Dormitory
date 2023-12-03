<template>
  <div>
    <el-button @click="clearFilter">清除所有过滤器</el-button>

    <el-table
        ref="filterTable"
        :data="teams"
        style="width: 100%">
<!--      <el-table-column-->
<!--          prop="name"-->
<!--          label="组队名"-->
<!--          sortable-->
<!--          width="180">-->
<!--      </el-table-column>-->
      <el-table-column
          prop="leaderName"
          label="队长名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="current"
          label="当前人数"
          width="180">
      </el-table-column>
      <el-table-column
          prop="capacity"
          label="容量"
          width="180">
      </el-table-column>
      <el-table-column
          prop="dorm"
          label="当前宿舍选择"
          :formatter="formatter">
      </el-table-column>
      <el-table-column
          prop="region"
          label="地区"
          width="100"

          :filters="[{ text: '湖畔', value: '湖畔' }, { text: '二期', value: '二期' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">

        <template slot-scope="scope">
          <el-tag
              :type="scope.row.region === '二期' ? 'primary' : 'success'"
              disable-transitions>
            {{ scope.row.region }}
          </el-tag>
        </template>


      </el-table-column>
      <el-table-column>
        <el-button type="primary" @click="apply">申请加入</el-button>
      </el-table-column>

    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        :total="totalTeams"
        layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>


    <el-button type="primary" @click="showInputDialog">创建组队</el-button>

    <el-dialog
        title="创建组队"
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
import axios from "axios";

export default {
  data() {
    return {
      user: null,
      userId:null,
      allTeams: [],
      capacity: 3,
      dialogVisible : false,

      teams: [{
        leaderName: 'leader1',
        current: 1,
        capacity: 5,
        dorm: '1009',
        region: '湖畔'
      }, {
        leaderName: 'leader2',
        current: 2,
        capacity: 5,
        dorm: '1010',
        region: '二期'
      }, {
        leaderName: 'leader3',
        current: 3,
        capacity: 5,
        dorm: '1011',
        region: '湖畔'
      }, {
        leaderName: 'leader4',
        current: 4,
        capacity: 5,
        dorm: '1019',
        region: '二期'
      }]
    };
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem('CurUser'));
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
    async apply(){

    },

    async getAllTeams(){
      try {
        const resopnse = await axios.get(this.$httpUrl + '/teams');
        // if (resopnse.code == 2010){
          this.allTeams = resopnse.data;
          console.log('查询全部组队成功')
        // }
        // else
          if (resopnse.code == 2011){
          console.error('查询全部组队失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }
    },

    async createTeam(){
      try {
        const resopnse = await axios.post(this.$httpUrl + '/team',{
          headId: this.user.id,
          capacity: this.capacity
        });
        if (resopnse.code == 2040){
          console.log('成功添加寝室')
        }
        else if (resopnse.code == 2041){
          console.error('增加失败，请重试')
        }
      }
      catch (error){console.error('验证出错', error);
      }

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
