<script>
export default {
  name:"DormitoryPropose",
  methods:{
    loadDateData() {
      // 发起后端请求以加载日期数据
      this.$axios
          .post(this.$httpUrl+'/openSystemDate/loadDate', this.isGraduateOrUnder) // 请将路径替换为您的实际后端接口路径
          .then( (res => {
            const data = res.data.data; // 假设后端返回的数据包含日期信息
            if (res.data.code === 2000) {
              // 解析日期并设置到组件的数据属性
              this.submitStartDate = new Date(data.submitStartDate);
              this.submitEndDate = new Date(data.submitEndDate);
              this.grabStartDate = new Date(data.grabStartDate);
              this.grabEndDate = new Date(data.grabEndDate);

              // 获取当前日期
              const currentDate = new Date();

              // 根据日期比较来设置active的值
              if (currentDate >= this.submitStartDate && currentDate <= this.submitEndDate) {
                this.active = 0; // 在提交宿舍日期范围内
              } else if (currentDate >= this.grabStartDate && currentDate <= this.grabEndDate) {
                this.active = 1; // 在抢宿舍日期范围内
              } else {
                this.active = 2; // 其他情况
              }
            }else {
              alert('获取数据失败')
            }
          }))
          .catch((error) => {
            console.error('Failed to load date data:', error);
          });
    },
    getAvailableDormitory(){

    },
    getPreferredDormitory(){

    },
    getOptionalDormitories(){

    },
    submit(){
      //退出确认
      this.$confirm('您确定要所选组队和寝室信息吗？','提示',{
        confirmButtonText:'确定',
        type:'warning',
        center:true
      }).then(()=>{
        this.$message({
          type:'success',
          message:'提交成功'
        })
        // this.$router.push('/');//路由
        // sessionStorage.clear()
      })
          .catch(()=>{
            this.$message({
              type:'info',
              message:'已取消提交'
            })
          })
    },
    grab(){
      // 抢、
      this.$axios.get(this.$httpUrl + '/', {}, {
        withCredentials: true, // 允许跨域请求中的Cookie
        "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
      }).then(res=>{
        if(res && res.code == '2040'){
          this.$message({
            type: 'success',
            message: '已抢到该宿舍'
          })
        }else {
          this.$message({
            type: 'info',
            message: res.msg
          })
        }
      })


    },
    formatDateRange(startDate, endDate) {
      // 格式化开始日期
      const formattedStartDate = this.formatDate(startDate);
      // 格式化结束日期
      const formattedEndDate = this.formatDate(endDate);

      return `${formattedStartDate} - ${formattedEndDate}`;
    },
    formatDate(date) {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');

      return `${year}/${month}/${day} ${hours}:${minutes}`;
    },
    handleChange(value) {
      console.log(value);
    }
  },
  // beforeMount() {
  //     this.loadDateData(); // 加载日期数据
  //     this.getAvailableDormitory();
  // },
  data() {
    return {
      options: [{
        value: '湖畔',
        label: '湖畔',
        children: [{
          value: '1栋',
          label: '1栋',
          children: [{value: '212', label: '212'}, {value: '320', label: '320'}]
        }, {
          value: '2栋',
          label: '2栋',
          children: [{value: '221', label: '221'}, {value: '443', label: '443'}]
        }, {
          value: '3栋',
          label: '3栋',
          children: [{value: '232', label: '232'}, {value: '325', label: '325'}]
        }]
      }, {
        value: '学生宿舍',
        label: '学生宿舍',
        children: [{
          value: '11栋',
          label: '11栋',
          children: [{value: '1716', label: '1716'}, {value: '1311', label: '1311'}]
        }]
      }, {
        value: '荔园',
        label: '荔园',
        children: [{
          value: '8栋',
          label: '8栋',
          children: [{value: '1716', label: '1716'}, {value: '1311', label: '1311'}]
        }]
      },{
        value: '欣园',
        label: '欣园',
        children: [{
          value: '4栋',
          label: '4栋',
          children: [{value: '1716', label: '1716'}, {value: '1311', label: '1311'
          }]
        }]
      }],
      selectedTeam:"123",
      selectedDormitory:"湖畔1栋-3楼-333",
      submittedDormitory:"",
      centerDialogVisible: false,
      active: 2,
      isGraduateOrUnder: 0, // 0是本科生，1是研究生
      activeName:'1',
      submitStartDate: new Date('2023/07/01 00:00:00'), // 提交宿舍开始日期
      submitEndDate: new Date('2023/08/15 23:59:59'), // 提交宿舍结束日期
      grabStartDate: new Date('2023/08/17 00:00:00'), // 抢宿舍开始日期
      grabEndDate: new Date('2023/08/30 23:59:59'), // 抢宿舍结束日期
    }
  },
  computed: {
    // 提交宿舍步骤的描述
    submitTitle() {
      return `提交宿舍 (${this.formatDateRange(this.submitStartDate, this.submitEndDate)})`;
    },
    // 抢宿舍步骤的描述
    grabTitle() {
      return `抢宿舍 (${this.formatDateRange(this.grabStartDate, this.grabEndDate)})`;
    },
  },
}
</script>

<template>
  <div>
    <div style="margin: 10px 30px 10px 150px; font-family: arial, sans-serif">
      <el-steps :active="active" finish-status="success" >
        <el-step :title="submitTitle" description="由队长提交心仪的宿舍，可以从收藏中选择，组队信息在抢宿舍阶段不可变更"></el-step>
        <el-step :title="grabTitle" description="手速拼起来~`"></el-step>
      </el-steps>
    </div>
    <el-divider></el-divider>
    <div>
      <div v-if="active === 0">
        <div style="display: flex; flex-direction: column; justify-content:center; align-items: flex-start; height: 500px; margin-left: 310px; font-family: arial, sans-serif;font-size: 15px">
          <el-card class="box-card">
            <div style="font-size: 7px; color: crimson">
              <span>您已提交湖畔1栋-3楼-333寝室，该寝室当前已被4人选择，时间截止前可随时更改组队和寝室信息</span>
            </div>
            <div style="margin-top: 30px">
              您是：
              <el-radio-group v-model="isGraduateOrUnder">
                <!-- 如果 isGraduateOrUnder 的初始值为 0，则选择 "本科生" 并禁用 "研究生" -->
                <el-radio :label="0"  :disabled="isGraduateOrUnder === 1||isGraduateOrUnder === 2">本科生</el-radio>
                <!-- 如果 isGraduateOrUnder 的初始值为 1，则选择 "研究生" 并禁用 "本科生" -->
                <el-radio :label="1"  :disabled="isGraduateOrUnder === 0||isGraduateOrUnder === 2">研究生</el-radio>
                <el-radio :label="2"  :disabled="isGraduateOrUnder === 1||isGraduateOrUnder === 0">博士生</el-radio>
              </el-radio-group>
            </div>
            <div style="margin-top: 23px">
              选择您的组队：
              <el-input v-model="selectedTeam"></el-input>
            </div>
            <div style="margin-top: 23px">
              选择寝室：
              <el-cascader
                  v-model="selectedDormitory"
                  :options="options"
                  :props="{ expandTrigger: 'hover' }"
                  @change="handleChange">

              </el-cascader>
              <el-button type="text" class="button" style="margin-left: 7px" @click="centerDialogVisible = true">从收藏中选择</el-button>
            </div>
            <div style="margin-top: 20px">
              <el-button slot="reference" type="primary" @click="submit" round>提交</el-button>
              <el-dialog
                  title="我的收藏"
                  :visible.sync="centerDialogVisible"
                  width="30%"
                  center
                  style="margin-left: 210px">
                <el-radio-group v-model="selectedDormitory">
                  <div>
                    <el-radio :label="3">湖畔3栋-2楼-222</el-radio>
                  </div>
                  <div style="margin-top: 5px">
                    <el-radio :label="6">湖畔1栋-3楼-333</el-radio>
                  </div>
                </el-radio-group>
                <span slot="footer" class="dialog-footer">
                                    <el-button @click="centerDialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
                                </span>
              </el-dialog>
            </div>
          </el-card>
        </div>
      </div>

      <div v-if="active=== 1">
        <div style="display: flex; flex-direction: column; justify-content:center; align-items: flex-start; height: 500px; margin-left: 310px; font-family: arial, sans-serif;font-size: 15px">
          <el-card class="box-card">
            <div style="margin-top: 20px">
              <el-row>
                <span>您的组队信息：</span>
                <div style="margin-top: 10px;">
                  <el-collapse v-model="activeName" accordion>
                    <el-collapse-item name="1">
                      <el-template slot="title">
                        <div style="color: #817f7f">队名：123</div>
                      </el-template>
                      <div style="color: #817f7f">队长：12111110 杨四</div>
                      <div style="color: #817f7f">成员：12111111 李一，12111112 王二， 12111113 张三</div>
                    </el-collapse-item>
                  </el-collapse>
                </div>
              </el-row>
            </div>
            <div style="margin-top: 10px;display: flex;align-items: flex-start">
              <span style="margin-right: 10px;">您的所选寝室：</span>
              <div>
                <el-card :body-style="{ padding: '0px' }" style="height: 200px; width: 180px">
                  <img src="../../assets/img1.jpg" class="image" style="height: 160px;">
                  <div style="padding: 8px;">
                    <span>湖畔3栋-2楼-222</span>>
                  </div>
                </el-card>
                <el-button type="text" class="button" style="margin-left: 7px" @click="">更换其他可选寝室</el-button>
              </div>
            </div>
            <div style="margin-top: 20px; margin-left: 270px">
              <el-button slot="reference" type="success" style="font-size: 17px" @click="grab">抢</el-button>
            </div>
          </el-card>
        </div>

      </div>

      <div v-if="active=== 2">
        <!--                <span>寝室申请已经结束</span>-->
        <el-empty description="寝室申请已经结束"></el-empty>
      </div>
    </div>
  </div>
</template>


<style scoped>

.box-card {
  width: 620px;
}
</style>