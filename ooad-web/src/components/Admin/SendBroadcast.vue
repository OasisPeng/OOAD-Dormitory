<template>

  <div>

    <div>
      <div class="search-header">
        <el-button
            type="primary"
            style="width: 100%;;margin: 0 auto;"
            @click="toggleSearch">
          {{ isSearchExpanded ? '收起查询' : '展开查询' }}</el-button>
      </div>
      <div class="search-row" v-show="isSearchExpanded" >

        <el-input
            v-model="searchParams['content']"
            placeholder="content"
            style="width: 100%"

        ></el-input>

        <el-input
            type="datetime"
            v-model="searchParams['adminId']"
            placeholder=" adminId"
            style="width: 100%"

        ></el-input>
        <!-- 添加其他查询输入框 -->
      </div>
    </div>

    <div class="content-above" ref="tableContainer" :style="{ 'max-height': isSearchExpanded ? 'calc(100vh - 400px)' : 'calc(100vh - 200px)' }">
      <el-table :header-cell-style="{ background:'#f2f5fc', color: '#555555',fontSize: '20px', fontWeight: 'bold', height: '30px', lineHeight: '10px'}"
                :data="displayedFlights1"

                stripe
                border
                style="width: 100%;;margin: 0 auto;">

        <el-table-column prop="adminId" label="adminId" :min-width="100" />
        <!-- 其他列 -->
        <el-table-column prop="content" label="content"/>

        <el-table-column label="Operations">
          <template #default="scope">
            <div class="button-group">
              <el-button type="primary" @click="editRoom(scope.row.id)">Edit</el-button>

              <el-button type="danger" @click="deleteRoom(scope.row.id)">Delete</el-button>

            </div>
          </template>
        </el-table-column>

      </el-table>
    </div>
    <div class="pagination-and-button">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[10,20, 30, 40]"
          :page-size="pageSize"
          :total="filteredFlights.length"
          layout="total, sizes, prev, pager, next, jumper"
      >
      </el-pagination>
      <div class="add-room-button">
        <el-button type="primary" plain @click="createbroadcast">Add Broadcast</el-button>
      </div>
    </div>

    <el-dialog
        :visible.sync="dialogVisible"
        title="Add Broadcast"
        width="600px"
        center
    >
      <!-- 在 el-form 内部的元素添加样式 -->
      <!-- 增加一个隐藏字段用于存储选定行的索引 -->
      <el-form-item label="Edit Index" prop="EditIndex" style="display: none;">
        <el-input v-model="broadcast.EditIndex" :readonly="true" />
      </el-form-item>

      <el-form-item>
        <div class="button-group2">
          <el-button type="primary" @click="AddRoom('Room-name')">{{ editMode ? 'Update' : 'Submit' }}</el-button>
          <el-button @click="cancel('Room-name')">Cancel</el-button>
        </div>
      </el-form-item>


      <el-form
          ref="Room-name"
          :model="broadcast"
          :rules="rules"
          label-width="auto"
          label-position="right"
          size="default"
          style="font-size: 20px;"
      >

        <el-form-item label="adminId" prop="adminId">
          <el-input v-model="broadcast.adminId"/>
        </el-form-item>

        <el-form-item label="Content" prop="content">
          <el-input v-model="broadcast.content"/>
        </el-form-item>
        <el-form-item>
          <div class="button-group2">
            <el-button type="primary" @click="AddRoom('Room-name')" class="submit-button">{{ editMode ? 'Update' : 'Submit' }}</el-button>
            <el-button @click="cancel('Room-name')" class="cancel-button">Cancel</el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>



    <div>

      <div class="bottom-bar">
      </div>
    </div>
  </div>

</template>

<script>


export default {
  data() {
    return {
      searchParams: {}, // 存储查询条件
      isSearchExpanded: true, // 控制查询框展开与隐藏
      rules: {


      },
      dialogVisible: false,
      editMode: false,
      currentIndex: 0,
      currentPage4: 1, // 当前页数
      pageSize: 10,   // 初始每页显示的条数
      filteredFlights:[],
      rowid:" ",
      Room: [



      ],
      broadcast: {
        content:"",
        adminId:"",
        id:""
      },
      value: 0
    }
  },
  created() {
    // 在组件创建时计算初始平均值并设置给 value
    this.updateTableHeight();
    this.$axios.get(this.$httpUrl+'/broadCasts', {
      withCredentials: true,
      headers: {
        'Authorization': "Bearer" + " " + JSON.parse(sessionStorage.getItem('admin')).token
      },

    }).then(res => {
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log(res)
      console.log(data)
      // 检查 data 是否为数组
      if (Array.isArray(data)) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        const flights = data
            .map(roomData => {
              return {
                content:  String(roomData.content || ""),
                adminId:  String(roomData.adminId || ""),
                id:  String(roomData.id || ""),
              };
            });

        // 将转换后的数据添加到 Room 数组
        this.Room = flights;

        // 打印转换后的数据
        console.log(this.Room);
      } else {
        console.error("The response data is not an array.");
      }
    });



  },

  methods: {
    handleFilterChange(filteredData) {
      // 当筛选条件发生变化时，更新筛选后的全部数据
      this.filteredFlights = filteredData;
      console.log(this.filteredFlights)
      // 更新每页显示的条数
      this.pageSize = 10; // 重置 pageSize 为默认值
      // 更新当前页数
      this.currentPage4 = 1; // 重置 currentPage4 为第一页

      // 计算当前页的起始索引
      const startIndex = (this.currentPage4 - 1) * this.pageSize;
      // 使用数组的 slice 方法获取当前页的数据
      this.displayedFlights = this.filteredFlights.slice(startIndex, startIndex + this.pageSize);
    },
    handleCurrentChange(val) {
      // 更新当前页数
      this.currentPage4 = val;
      // 计算当前页的起始索引
      const startIndex = (this.currentPage4 - 1) * this.pageSize;
      // 使用数组的 slice 方法获取当前页的数据
      this.displayedFlights = this.filteredFlights.slice(startIndex, startIndex + this.pageSize);
    },
    updateTableHeight() {
      this.$nextTick(() => {
        const tableContainer = this.$refs.tableContainer; // 表格容器的引用，需要在模板中设置ref="tableContainer"
        if (tableContainer) {
          const maxHeight = this.isSearchExpanded ? 'calc(100vh - 400px)' : 'calc(100vh - 300px)';
          tableContainer.style.maxHeight = maxHeight;
        }
      });
    },
    toggleSearch() {
      this.isSearchExpanded = !this.isSearchExpanded;
      // 根据查询框的展开状态动态设置表格容器的高度
      this.$nextTick(() => {
        this.updateTableHeight();
      });
    },

    handleSelect(key) {
      this.activeIndex = key;
    },

    handleSizeChange(val) {
      // 更新每页显示的条数
      this.pageSize = val;
      // 计算当前页的起始索引
      const startIndex = (this.currentPage4 - 1) * this.pageSize;
      // 使用数组的 slice 方法获取当前页的数据
      this.displayedFlights = this.filteredFlights.slice(startIndex, startIndex + this.pageSize);
    },

    createbroadcast() {
      this.dialogVisible = true;
      this.editMode = false; // 进入添加模式
    },
    editRoom(row) {
      this.rowid=row;
      this.editMode = true; // 进入添加模式
      this.dialogVisible = true;

    },

    AddRoom(FormName) {
      console.log("1",this.editMode)
      if (this.editMode === true){
        this.$refs[FormName].validate((valid) => {
          if (valid) {
            this.$axios.put(this.$httpUrl+'/broadCast',{
            content: this.broadcast.content,
              adminId: this.broadcast.adminId,
              id:this.rowid

            },{
              withCredentials: true,
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },


            }).then(res=>{
              console.log(res.data)


            })

            this.dialogVisible = false;
            this.$message.success( 'comment added successfully');
            // 清空表单
            this.broadcast = {
              adminId: ' ',
              content: '',
              id: ''
            };
            this.$forceUpdate();

          } else {
            this.$message.error('Add comment Failed');
          }
        });
      }else {
        this.$refs[FormName].validate((valid) => {
          if (valid) {


            this.$axios.post(this.$httpUrl+'/broadCast',{

              content: this.broadcast.content,
              adminId: this.broadcast.adminId,

            },{
              withCredentials: true,
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },


            }).then(res=>{
              console.log(res.data)


            })






            this.dialogVisible = false;
            this.editMode = false;
            this.$message.success( 'comment added successfully');
            // 清空表单
            this.broadcast = {
              adminId: ' ',
              content: '',
              id: ''
            };


          } else {
            this.$message.error('Add comment Failed');
          }
        });
      }

    },

    deleteRoom(index) {
      this.$confirm('Are you sure you want to delete this broadcast?', 'Tips', {
        confirmButtonText: 'Submit',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {


            this.$axios.delete(this.$httpUrl+`/broadCast/${index}`,{
              withCredentials: true,
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
            }).then(res=>{
              console.log(res.data)
              this.dialogVisible = false;
              this.$message.success( 'comment delete successfully');
              // 清空表单
              this.comment = {
                adminId: "",
                content: " ",
                id:" ",
                EditIndex: -1, // 重置 EditIndex
              }
            })


      }).catch(() => {
        this.$message.info('Deletion canceled');
      });
    },






    cancel(){
      this.dialogVisible = false;
      this.$message.success('Cancel  operation');

      // 清空表单
      this.broadcast = {
        adminId: "",
        content: " ",
        id: " "

      }
    }
  },
  computed: {


    displayedFlights1() {
      this.filteredFlights = this.Room.filter((flight) => {
        if (
            (!this.searchParams.content || (flight.content && flight.content.toLowerCase().includes(this.searchParams.content.toLowerCase()))) &&
            (!this.searchParams.adminId || (flight.adminId && flight.adminId.toLowerCase().includes(this.searchParams.adminId.toLowerCase())))

        ) {

          return true;
        }

        return false;
      });


      // 根据当前页数和每页显示的条数进行分页
      const startIndex = (this.currentPage4 - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      this.handleFilterChange(this.filteredFlights)
      return this.filteredFlights.slice(startIndex, endIndex);
    }
  }

};
</script>

<style scoped>




.image img {
  width: 100%;
  height: auto;
  object-fit: cover;
  cursor: pointer;
}

.content-above {
  position: relative;
  z-index: 1; /* 确保表格在分页按钮上面显示 */
  max-height: calc(100vh - 350px); /* 设置表格的最大高度，确保不会超过屏幕 */
  overflow-y: auto; /* 如果表格高度超过最大高度，启用垂直滚动条 */

}

.submit-button, .cancel-button {
  flex: 1;
  margin: 0 5px; /* 可以根据需要调整按钮之间的间距 */
}
.bottom-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgb(255, 255, 255);
  color: #070707;
  content-align: center;
  font-size: 20px;
  font-weight: bold;
  padding: 10px;
  z-index: 1; /* 确保底部栏显示在其它内容之上 */
}

.search-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.search-row {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

.pagination-and-button {
  position: fixed;
  bottom: 100px;
  left: 200px;
  right: 0;
  display: flex;
  justify-content: flex-start; /* Shift the content to the right */
  align-items: center;
  z-index: 1;
}

.button-group {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}
.button-group .el-button {
  flex: 0 0 50px;
}


</style>