<template>
  <div>
    <el-button @click="clearFilter">清除所有过滤器</el-button>

    <el-table
        ref="filterTable"
        :data="teams"
        style="width: 100%">
      <el-table-column
          prop="name"
          label="组队名"
          sortable
          width="180">
      </el-table-column>
      <el-table-column
          prop="leaderName"
          label="队长名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="currentMember"
          label="当前人数"
          width="180">
      </el-table-column>
      <el-table-column
          prop="capacity"
          label="容量"
          width="180">
      </el-table-column>
      <el-table-column
          prop="currentRoomSelection"
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


    <el-button type="primary" @click="dialogVisible = true">创建组队</el-button>

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确定</el-button>
      </span>
    </el-dialog>

  </div>


</template>

<script>
export default {
  data() {
    return {
      dialogVisible : false,
      // teams: [],
      teams: [{
        name: 'test',
        leaderName: 'leader1',
        currentMember: 1,
        capacity: 5,
        currentRoomSelection: '1009',
        region: '湖畔'
      }, {
        name: 'test',
        leaderName: 'leader2',
        currentMember: 2,
        capacity: 5,
        currentRoomSelection: '1009',
        region: '二期'
      }, {
        name: 'test',
        leaderName: 'leader3',
        currentMember: 3,
        capacity: 5,
        currentRoomSelection: '1009',
        region: '湖畔'
      }, {
        name: 'test',
        leaderName: 'leader4',
        currentMember: 4,
        capacity: 5,
        currentRoomSelection: '1009',
        region: '二期'
      }]
    };
  },
  mounted() {
    this.getTeams();
  },
  methods: {
    showDialog() {
      this.dialogVisible = true; // 显示窗口
    },
    closeDialog() {
      this.dialogVisible = false; // 隐藏窗口
    },
    getTeams() {
      // 发送请求获取数据，假设使用axios库
      axios.get('/teams')
          .then(response => {
            const data = response.data;
            if (data.code === 2010) {
              this.teams = data.data;
            } else if (data.code === 2011) {
              console.error(data.message);
            }
          })
          .catch(error => {
            console.error(error);
          });
    },
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
