<template>


<!--  redis-server.exe redis.windows.conf-->
<!--  D:\OneDrive - 南方科技大学\课程\创新实践-->
  <div>

    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">简介</el-menu-item>
      <el-menu-item index="2">评分</el-menu-item>
      <el-menu-item index="3">查看可选宿舍</el-menu-item>
      <el-menu-item index="4">查看房型</el-menu-item>
    </el-menu>

    <div class="line"></div>
    <div v-if="showHupan4Image" class="image-container">
    <img src="/hupan4.png" alt="湖畔4栋的图片" class="center-top">
      <div class="button-container">
        <el-button type="primary" plain @click="goBack">返回</el-button>
      </div>
  </div>
    <div class="line"></div>
    <div v-if="showHupan5Image" class="image-container">
      <img src="/hupan5.png" alt="湖畔5栋的图片" class="center-top">
      <div class="button-container">
        <el-button type="primary" plain @click="goBack">返回</el-button>
      </div>
    </div>
    <div class="line"></div>
    <div v-if="showHupan6Image" class="image-container">
      <img src="/hupan6.png" alt="湖畔6栋的图片" class="center-top">
      <div class="button-container">
        <el-button type="primary" plain @click="goBack">返回</el-button>
      </div>
    </div>


      <div v-show="quanju&& !tupian">
        <div class="credit"></div>
        <!-- 全景容器 -->
        <div v-show="activeIndex === '1'" ref="panoramaContainer" style="width: 100%; height: 85vh;"></div>
      </div>







    <div v-if="activeIndex === '2'&& !tupian">

        <div class="title-container">
          <h2  class="conference-title" >总分</h2>
          <el-rate
              v-model="value"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}">

          </el-rate>
        </div>
      <div class="content-above" ref="tableContainer" >
        <el-table :header-cell-style="{ background: '#f2f5fc', color: '#555555', fontSize: '20px', fontWeight: 'bold', height: '50px', lineHeight: '50px' }"
                  :data="displayedFlights"
                  stripe
                  border
                  style="width: 100%;;margin: 0 auto;">

          <el-table-column prop="grade"  label="grade" :width="500" :min-width="100" >
            <template slot-scope="scope">
              <el-rate :value="scope.row.grade" disabled show-score text-color="#ff9900" score-template="{value}" style="height: 40px;">
              </el-rate>
            </template>
          </el-table-column>
          <el-table-column prop="content" label="content" :min-width="100"  style="height: 40px;"/>
          <!-- 其他列 -->

        </el-table>
      </div>
      <div class="pagination-and-button">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[10,20, 30, 40]"
            :page-size="pageSize"
            :total="flights.length"
            layout="total, sizes, prev, pager, next, jumper"
        >
        </el-pagination>
        <div class="add-room-button">
          <el-button type="primary" plain @click="createComment">Add comment</el-button>
        </div>
      </div>



      <div class="bottom-bar">
        <!-- 这里可以添加底部内容，例如版权信息或其他信息 -->
      </div>



      <el-dialog
          :visible.sync="dialogVisible"
          title="添加打分贴"
          width="600px"
          center
      >
        <!-- 在 el-form 内部的元素添加样式 -->
        <!-- 增加一个隐藏字段用于存储选定行的索引 -->
        <el-form-item label="Edit Index" prop="EditIndex" style="display: none;">
          <el-input v-model="comment.EditIndex" :readonly="true" />
        </el-form-item>


        <el-form
            ref="Room-name"
            :model="comment"
            :rules="rules"
            label-width="auto"
            label-position="right"
            size="default"
            style="font-size: 20px;"
        >


          <el-form-item label="打分" prop="RoomName">
            <el-rate
                v-model="comment.grade"
                :colors="colors">
            </el-rate>
          </el-form-item>
          <el-form-item label="评价" prop="Department">
            <el-input v-model="comment.content"/>
          </el-form-item>

          <el-form-item>
            <div class="button-group2">
              <el-button type="primary" @click="AddRoom('Room-name')" class="submit-button"> 上传</el-button>
              <el-button @click="cancel('Room-name')" class="cancel-button">取消</el-button>
            </div>
          </el-form-item>


        </el-form>
      </el-dialog>



    </div>



    <div v-if="activeIndex === '3'&& !tupian">
      <div>

          <div>
            <div class="search-header">
              <el-button
                  type="primary"
                  style="width: 100%;;margin: 20px auto;"
                  @click="toggleSearch">
                {{ isSearchExpanded ? '收起查询' : '展开查询' }}</el-button>
            </div>


            <div class="search-row" v-show="isSearchExpanded" >
              <el-input
                  v-model="searchParams['distribution']"
                  placeholder="distribution"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['building']"
                  placeholder="building"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['room']"
                  placeholder="room"
                  clearable
              ></el-input>
              <!-- 添加其他查询输入框 -->
              <el-input
                  v-model="searchParams['floor']"
                  placeholder="floor"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['floorSex']"
                  placeholder="floorSex"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['available']"
                  placeholder="available"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['detail']"
                  placeholder="detail"
                  clearable
              ></el-input>
              <el-input
                  v-model="searchParams['size']"
                  placeholder="size"
                  clearable
              ></el-input>
            </div>
          </div>

        <div class="content-above" ref="tableContainer" :style="{ 'max-height': isSearchExpanded ? 'calc(100vh - 400px)' : 'calc(100vh - 200px)' }">
          <el-table :header-cell-style="{ background:'#f2f5fc', color: '#555555',fontSize: '20px', fontWeight: 'bold', height: '50px', lineHeight: '50px'}"
                    :data="displayedFlights1"

                    stripe
                    border
                    style="width: 100%;;margin: 0 auto;">

            <el-table-column prop="distribution" label="distribution" :min-width="100" />
            <el-table-column prop="building" label="building" :min-width="100" />
            <!-- 其他列 -->
            <el-table-column prop="room" label="room"/>
            <el-table-column prop="floor" label="floor"/>
            <el-table-column prop="floorSex" label="floorSex"/>
            <el-table-column prop="available" label="available"/>
            <el-table-column prop="size" label="size"/>
            <el-table-column prop="detail" label="detail"/>
            <el-table-column label="Operations">
              <template #default="scope">
                <div class="button-group">
                  <el-button type="primary" @click="editRoom(scope.row)">收藏</el-button>

                  <el-button type="danger" @click="deleteRoom(scope.row.id)">取消收藏</el-button>

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

        </div>
        <div>

          <div class="bottom-bar">
          </div>
        </div>
      </div>
      <div class="bottom-bar">
        <!-- 这里可以添加底部内容，例如版权信息或其他信息 -->
      </div>

    </div>
    <div v-if="activeIndex === '4'&& !tupian">
      <el-row>
        <el-col :span="8">
          <div class="room-container">
            <div class="room-title">双人间矮床</div>
            <img v-for="(image, index) in imageList" :src="image" :key="index" alt="照片" class="room-image" />
          </div>
        </el-col>

        <el-col :span="8">
          <div class="room-container">
            <div class="room-title">双人间高床</div>
            <img v-for="(image, index) in imageList2" :src="image" :key="index" alt="照片" class="room-image" />
          </div>
        </el-col>

        <el-col :span="8">
          <div class="room-container">
            <div class="room-title">三人间</div>
            <img :src="imageList3[0]" alt="照片" class="room-image" />
          </div>
        </el-col>
      </el-row>



    </div>

  </div>







</template>

<script>
import pictur1 from  '@/picture/湖畔矮双人.jpg'
import pictur2 from   '@/picture/湖畔矮双人2.jpg'
import pictur3 from  '@/picture/湖畔高双人1.jpg'
import pictur4 from  '@/picture/湖畔高双人2.jpg'
import pictur5 from  '@/picture/湖畔三人.jpg'
import * as PANOLENS from 'panolens';
export default {

  mounted() {
    const img = new Image();
    img.src = '/lakeside.jpg';
    img.onload = () => {
      this.initPanorama(img.width, img.height);
    };
  },
  data() {
    return {
      usersex:" ",
      tupian:false,
      quanju:true,
      showFangXing:true,
      panoramaContainer: null,
      showHupan4Image: false,
      showHupan5Image: false,
      showHupan6Image: false,
      panorama: null,
      viewer: null,
      searchParams: {}, // 存储查询条件
      isSearchExpanded: true, // 控制查询框展开与隐藏
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      rules: {


      },
      // viewer: null,
      dialogVisible: false,
      editMode: false,
      activeIndex: '1',
      currentIndex: 0,
      imageList: [
        pictur1,
        pictur2,
      ],
      imageList2: [
        pictur3,
        pictur4,
      ],
      imageList3: [
        pictur5,
      ],
      currentPage4: 1, // 当前页数
      pageSize: 10,   // 初始每页显示的条数
      comment: {
        grade: " ",
        content: " ",
        distribution: " "
      },
      flights: [

      ],
      Room: [],
      filteredFlights:[],
      RoomForm: {
        distribution: "",
        building: "",
        room: "",
        detail: "",
        favourite:"",
        id:"",
        version:"",
        floor: " ",
        floorSex:" ",
        available:" ",
        size:""

      },
      value: 0
    }
  },

  created() {



    // 在组件创建时计算初始平均值并设置给 value
    this.$axios.get(this.$httpUrl+'/distributionGrade/湖畔',{
      withCredentials: true,
      headers:{
        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
      },


    }).then(res=>{
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      // 检查 data 是否为数组
      if (Array.isArray(data)) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        const flights = data
            .filter(evo => evo.distribution === "湖畔") // 过滤符合条件的数据
            .map(evo => {
              return {
                distribution: evo.distribution || "",
                grade: evo.grade || "",
                content: evo.msg || "",

              };
            });

        // 将转换后的数据添加到 Room 数组
        this.flights = flights;
        this.calculateInitialAverageValue();
        // 打印转换后的数据
        console.log( this.flights);
      } else {
        console.error("The response data is not an array.");
      }
    });


    console.log("token",sessionStorage.getItem('CurUser'))
    this.updateTableHeight();
    this.$axios.get(this.$httpUrl+'/dorms', {
      withCredentials: true,
      headers: {
        'Authorization': "Bearer" + " " + JSON.parse(sessionStorage.getItem('CurUser')).token
      },

    }).then(res => {
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log("1111",this.usersex)
      console.log("寝室",data)
      // 检查 data 是否为数组
      if (Array.isArray(data)) {

        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        const flights = data
            .filter(roomData => roomData.distribution === "湖畔") // 过滤符合条件的数据
            .filter(roomData => roomData.floorSex===JSON.parse(sessionStorage.getItem('UserData')).sex)
            .map(roomData => {
              return {
                distribution:  String(roomData.distribution || ""),
                building:  String(roomData.building || ""),
                room: String(roomData.room || ""),
                detail: String(roomData.detail || ""),
                favourite: String(roomData.favourite || ""),
                id: String(roomData.id || ""),
                version: String(roomData.version || ""),
                floor: String(roomData.floor || ""),
                floorSex: String(roomData.floorSex || ""),
                available: String(roomData.available|| ""),
                size: String(roomData.size|| ""),
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

    initPanorama(width, height) {
      var infospot, infospot5, infospot6, infospot7, panorama, viewer;

      infospot = new PANOLENS.Infospot();
      // ... (为 infospot2 到 infospot12 重复 Infospot 配置)




      infospot5 = new PANOLENS.Infospot();
      infospot5.position.set( -4436, -1186, -1875 );
      infospot5.addHoverText( '湖畔4栋' );
      infospot5.addEventListener('click', () => {
        // 点击湖畔4栋时切换到图片模式
        this.showHupan4Image = true;
        this.tupian = true;
        this.quanju=false;

      });

      infospot6 = new PANOLENS.Infospot();
      infospot6.position.set( -4916, -769, -440 );
      infospot6.addHoverText( '湖畔5栋' );
      infospot6.addEventListener('click', () => {
        // 点击湖畔4栋时切换到图片模式
        this.showHupan5Image = true;
        this.tupian = true;
        this.quanju=false;

      });
      infospot7 = new PANOLENS.Infospot();
      infospot7.position.set( -4827, -1102, 637 );
      infospot7.addHoverText( '湖畔6栋' );
      infospot7.addEventListener('click', () => {
        // 点击湖畔4栋时切换到图片模式
        this.showHupan6Image = true;
        this.tupian = true;
        this.quanju=false;

      });
      this.panorama = new PANOLENS.ImagePanorama('/lakeside.jpg');


      this.panorama.add(infospot5);
      this.panorama.add(infospot6);
      this.panorama.add(infospot7);

      this.viewer = new PANOLENS.Viewer({
        container: this.$refs.panoramaContainer,
      });

      this.viewer.add(this.panorama);
    },
    goBack() {
      // 返回到全景容器
      this.quanju=true;
      this.showHupan4Image = false;
      this.showHupan5Image = false;
      this.showHupan6Image = false;
      this.tupian = false;
      // 重新初始化全景容器
      // this.viewer.remove(this.panorama);
    //   const img = new Image();
    //   img.src = '/lakeside.jpg';
    //   img.onload = () => {
    //     this.initPanorama(img.width, img.height);
    // }
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
      console.log('toggleSearch called');
      this.isSearchExpanded = !this.isSearchExpanded;
      // 根据查询框的展开状态动态设置表格容器的高度
      this.$nextTick(() => {
        this.updateTableHeight();
      });
    },
    calculateInitialAverageValue() {
      // 计算初始平均值
      let total = 0;

      for (let i = 0; i < this.flights.length; i++) {
        total += parseFloat(this.flights[i].grade);

      }
      this.value = this.flights.length > 0 ? total / this.flights.length : 0;
      this.value=this.value.toFixed(2);
    },
    updateAverageValue() {
      // 计算 flights 里的平均值
      let total = 0;
      for (let i = 0; i < this.flights.length; i++) {
        total += parseFloat(this.flights[i].grade);
      }
      this.value = (total / this.flights.length).toFixed(2);
    },
    handleSelect(key) {
      this.$nextTick(() => {
        if (key !== '1') {
          this.viewer.visibility = 'hidden'
        }

        if (key === '1') {
          this.quanju = true;
        }

        // 在下一个事件循环中进行页面跳转
        setTimeout(() => {
          this.activeIndex = key;
        }, 100);
      });
      //  this.activeIndex = key;
    },





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
    handleSizeChange(val) {
      // 更新每页显示的条数
      this.pageSize = val;
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

    createComment() {
      this.dialogVisible = true;
      this.editMode = false; // 进入添加模式
    },
    editRoom(row) {
      this.$confirm('确定收藏？', 'Tips', {
        confirmButtonText: 'Submit',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // User clicked the OK button, execute delete operation
        console.log("check",JSON.parse(sessionStorage.getItem('UserData')))

        //去后台验证用户密码
        this.$axios.post(this.$httpUrl+'/favouriteDorm',{
          personId: JSON.parse(sessionStorage.getItem('UserData')).id,
          dormId:row.id,
          teamId:JSON.parse(sessionStorage.getItem('UserData')).teamId,
        },{
          headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          },
          withCredentials: true // 允许跨域请求中的Cookie
        }).then(res=>{

          console.log(res)
          if (res.data.code===2041){
            this.$message.warning(res.data.msg);
          }
          else {
            this.$message.success(res.data.msg);
          }

        })


      }).catch(() => {
        // User clicked the Cancel button, cancel the delete operation
        this.$message.info('Favorite canceled');
      });


    },

    AddRoom(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {

          this.$axios.post(this.$httpUrl+'/distributionGrade',{
            distribution:"湖畔",
            grade: this.comment.grade,
            msg: this.comment.content,
          },{
            withCredentials: true,
            headers:{
              'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
            },


          }).then(res=>{
            console.log(res.data)


          })

          this.flights.push({
            "distribution": "湖畔",
            "grade": this.comment.grade,
            "content": this.comment.content,
          });



          this.updateAverageValue();
          this.dialogVisible = false;
          this.$message.success( 'comment added successfully');
          // 清空表单
          this.comment = {
            grade: "",
            content: " ",
            EditIndex: -1, // 重置 EditIndex
          }
        } else {
          this.$message.error('Add comment Failed');
        }
      });
    },
    deleteRoom(roomId) {
      this.$confirm('确定取消收藏？', 'Tips', {
        confirmButtonText: 'Submit',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // User clicked the OK button, execute delete operation
        console.log("check", JSON.parse(sessionStorage.getItem('CurUser')));

        //去后台验证用户密码
        this.$axios.delete(this.$httpUrl + '/favouriteDorm', {
          data: {
            personId: JSON.parse(sessionStorage.getItem('UserData')).id,
            dormId:roomId,
            teamId:JSON.parse(sessionStorage.getItem('UserData')).teamId,
          },
          withCredentials: true,
              headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
          }



        }).then(res => {
          // console.log(this.loginUsername)
          // console.log(this.loginPassword)
          console.log(res);
          if (res.data.code === 2031) {
            this.$message.warning(res.data.msg);
          } else {
            this.$message.success(res.data.msg);
          }
        });
      }).catch(() => {
        // User clicked the Cancel button, cancel the delete operation
        this.$message.info('Deletion canceled');
      });
    },

    cancel(){
      this.dialogVisible = false;
      this.$message.success('Cancel  operation');

      // 清空表单
      this.comment = {
        grade: "",
        content: " ",

      }
    }
  },
  computed: {
    displayedFlights() {
      // 根据查询条件筛选数据
      const filteredFlights = this.flights

      // 根据当前页数和每页显示的条数进行分页
      const startIndex = (this.currentPage4 - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;

      return filteredFlights.slice(startIndex, endIndex);
    },

    displayedFlights1() {
      // 根据查询条件筛选数据

      this.filteredFlights = this.Room.filter((flight) => {
        if (
            (!this.searchParams.distribution || (flight.distribution && flight.distribution.toLowerCase().includes(this.searchParams.distribution.toLowerCase()))) &&
            (!this.searchParams.building || (flight.building && flight.building.toLowerCase().includes(this.searchParams.building.toLowerCase()))) &&
            (!this.searchParams.room || (flight.room && flight.room.toLowerCase().includes(this.searchParams.room.toLowerCase()))) &&
            (!this.searchParams.floor || (flight.floor && flight.floor.toLowerCase().includes(this.searchParams.floor.toLowerCase()))) &&
            (!this.searchParams.floorSex || (flight.floorSex && flight.floorSex.toLowerCase().includes(this.searchParams.floorSex.toLowerCase()))) &&
            (!this.searchParams.available || (flight.available && flight.available.toLowerCase().includes(this.searchParams.available.toLowerCase()))) &&
            (!this.searchParams.detail || (flight.detail && flight.detail.toLowerCase().includes(this.searchParams.detail.toLowerCase())))&&
            (!this.searchParams.size || (flight.size && flight.size.toLowerCase().includes(this.searchParams.size.toLowerCase())))
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

.room-container {
  border: 1px solid #dcf3d8;
  margin-bottom: 20px;
  padding: 10px;
  text-align: center;
  background: #dcf3d8;
  height: 40% ;
  overflow-y: auto; /* 添加垂直滚动条 */
}

.room-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.room-image {
  max-width: 70%;
  max-height: 70%; /* 新增 max-height 属性 */
  width: 100%; /* 调整宽度 */
  margin-bottom: 10px;
  object-fit: cover; /* 保持图片比例 */
}



.el-descriptions-item {
  font-size: 16px; /* 设置字体大小 */
  font-weight: bold; /* 设置字体加粗 */
}
.button-container {
  margin-top: 10px; /* 你可以根据需要调整间隔的大小 */
}
.image-container {
  width: 100%;
  height: 100%;
  position: relative;
}

.center-top {
  position: relative;
  top: 0;
  left: 50%;
  transform: translateX(-50%);
  max-width: 100%; /* 确保图片不超过容器宽度 */
  max-height: 100%; /* 确保图片不超过容器高度 */
  width: 70%;
  height: 70%;
  /* 可以根据需要添加其他样式，例如最大宽度、最大高度等 */
}

html,
body {
  margin: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background-color: #000;
}

a:link,
a:visited {
  color: #bdc7c6;
}

.credit {
  position: absolute;
  text-align: center;
  width: 100%;
  padding: 20px 0;
  color: #fff;
}
.content-container {
  font-size: 1.2em; /* 将文字大小放大两倍 */
}

.content {
  /* 你可以进一步调整文字的样式，如颜色等 */
  color: #333; /* 设置文本颜色 */
}

.bg-purple-dark {
  background: rgba(211, 220, 230, 0.5); /* 50%透明度的背景色 */
}


.grid-content {
  border-radius: 4px;
  min-height: 36px;

}

.image img {
  width: 100%;
  height: auto;
  object-fit: cover;
  cursor: pointer;
}

.current-image {
  width: 400px;
  height: auto;
  object-fit: cover;
}


.content-above {
  position: relative;
  z-index: 1; /* 确保表格在分页按钮上面显示 */
  max-height: calc(100vh - 350px); /* 设置表格的最大高度，确保不会超过屏幕 */
  overflow-y: auto; /* 如果表格高度超过最大高度，启用垂直滚动条 */

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
.select-type {
  width: 100%; /* 设置选择框宽度为100%，与Room Name选择框相同 */
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

.add-room-button {
  width: 150px; /* 设置按钮的宽度 */
  height: 40px; /* 设置按钮的高度 */
  font-size: 16px; /* 设置按钮的字体大小 */
  display: flex; /* 使用 flex 布局使按钮在容器内居中 */
  justify-content: flex-start; /* 在容器内左对齐 */
  align-items: center; /* 在容器内垂直居中 */
  margin-right: auto; /* 将按钮推到最左边 */

}



.title-container {
  display: flex; /* 使用 flex 布局 */
  justify-content: center; /* 水平居中对齐 */
  align-items: center; /* 垂直居中对齐 */
  background-color: rgb(255, 255, 255); /* 设置标题容器的背景颜色为白色 */
  text-align: center; /* 居中标题文本 */
  padding: 20px; /* 添加内边距以增加标题的可读性 */
  height: 50px; /* 设置容器的高度为 50 像素，您可以根据需要调整这个值 */
}

.conference-title {
  font-size: 40px !important;
  font-weight: bold;
}


/deep/ .el-rate__icon{
  font-size: 35px;
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
.button-group2 {
  display: flex;
  justify-content: center;
  gap: 10px;
  align-items: center;
}
.submit-button, .cancel-button {
  flex: 1;
  margin: 0 5px; /* 可以根据需要调整按钮之间的间距 */
}

.right {
  /* 样式和布局 */
}
</style>
