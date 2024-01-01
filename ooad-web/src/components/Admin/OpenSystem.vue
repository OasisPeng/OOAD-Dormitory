<script>
export default {
  created() {
    this.$axios.get(this.$httpUrl+'/openTime/1',{
      headers:{
        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
      },
      withCredentials: true // 允许跨域请求中的Cookie
    }).then(res=>{
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log(res)
      console.log(data)
      // 检查 data 是否为数组
      if (res.data.code===2010) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        this.benkesheng.benke_opentime=data.openTime
        this.benkesheng.benke_endtime=data.closeTime
        // 将转换后的数据添加到 Room 数组
        console.log(this.benkesheng)
        this.benke= 2
        console.log(this.benke)
        // 打印转换后的数据

      } else {
        this.benke= 1
        console.log(this.benke)
        console.error("The response data is not an array.");
      }
    });

    this.$axios.get(this.$httpUrl+'/openTime/2',{
      headers:{
        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
      },
      withCredentials: true // 允许跨域请求中的Cookie
    }).then(res=>{
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log(res)
      console.log(data)
      // 检查 data 是否为数组
      if (res.data.code===2010) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        this.yanjiusheng.yanjiu_opentime=data.openTime
        this.yanjiusheng.yanjiu_endtime=data.closeTime
        // 将转换后的数据添加到 Room 数组
        console.log(this.yanjiusheng)
        this.yanjiu= 2
        console.log(this.yanjiu)
        // 打印转换后的数据

      } else {
        this.yanjiu= 1
        console.log(this.yanjiu)
        console.error("The response data is not an array.");
      }
    });

    this.$axios.get(this.$httpUrl+'/openTime/3',{
      headers:{
        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
      },
      withCredentials: true // 允许跨域请求中的Cookie
    }).then(res=>{
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log(res)
      console.log(data)
      // 检查 data 是否为数组
      if (res.data.code===2010) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        this.boshisheng.boshi_opentime=data.openTime
        this.boshisheng.boshi_endtime=data.closeTime
        // 将转换后的数据添加到 Room 数组
        console.log(this.boshisheng)
        this.bo= 2
        console.log(this.bo)
        // 打印转换后的数据

      } else {
        this.bo= 1
        console.log(this.bo)
        console.error("The response data is not an array.");
      }
    });

    this.$axios.get(this.$httpUrl+'/openTime/4',{
      headers:{
        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
      },
      withCredentials: true // 允许跨域请求中的Cookie
    }).then(res=>{
      // 假设 res.data 是您从后端获得的数据
      const data = res.data.data;
      console.log(res)
      console.log(data)
      // 检查 data 是否为数组
      if (res.data.code===2010) {
        // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
        this.houbusheng.houbu_opentime=data.openTime
        this.houbusheng.houbu_endtime=data.closeTime
        // 将转换后的数据添加到 Room 数组
        console.log(this.houbusheng)
        this.houbu= 2
        console.log(this.houbu)
        // 打印转换后的数据

      } else {
        this.houbu= 1
        console.log(this.houbu)
        console.error("The response data is not an array.");
      }
    });


  },
  filters: {
    formatDate(date) {
      if (!date) {
        return '';
      }

      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
      return new Date(date).toLocaleString('en-US', options);
    },
  },
  data() {

    return {
      benke:"",
      yanjiu:"",
      bo:"",
      houbu:"",

      benkesheng:{
        benke_opentime:null,
        benke_endtime:null,
      },
      yanjiusheng:{
        yanjiu_opentime:null,
        yanjiu_endtime:null,

      },
      boshisheng:{
        boshi_opentime:null,
        boshi_endtime:null,
      },
      houbusheng:{
        houbu_opentime:null,
        houbu_endtime:null,
      },

      houbu_edit: false,
      benke_edit: false,
      yanjiu_edit:false,
      boshi_edit:false,


      rules: {
        // benke_opentime: [
        //   { required: true, message: '请选择开始时间', trigger: 'change' },
        //   { validator: this.validateOpenTime, trigger: 'change' },
        // ],
        // benke_endtime: [
        //   { required: true, message: '请选择结束时间', trigger: 'change' },
        //   { validator: this.validateEndTime, trigger: 'change' },
        // ],
        // yanjiu_opentime: [
        //   { required: true, message: '请选择开始时间', trigger: 'change' },
        //   { validator: this.validateOpenTime, trigger: 'change' },
        // ],
        // yanjiu_endtime: [
        //   { required: true, message: '请选择结束时间', trigger: 'change' },
        //   { validator: this.validateEndTime2, trigger: 'change' },
        // ],
        // boshi_opentime: [
        //   { required: true, message: '请选择开始时间', trigger: 'change' },
        //   { validator: this.validateOpenTime, trigger: 'change' },
        // ],
        // boshi_endtime: [
        //   { required: true, message: '请选择结束时间', trigger: 'change' },
        //   { validator: this.validateEndTime3, trigger: 'change' },
        // ],
        // houbu_opentime: [
        //   { required: true, message: '请选择开始时间', trigger: 'change' },
        //   { validator: this.validateOpenTime, trigger: 'change' },
        // ],
        // houbu_endtime: [
        //   { required: true, message: '请选择结束时间', trigger: 'change' },
        //   { validator: this.validateEndTime4, trigger: 'change' },
        // ],
      },
    }
  },

  methods:{
    validateOpenTime(rule, value, callback) {
      const currentTime = new Date();
      if (value <= currentTime) {
        callback(new Error('开始时间必须大于当前时间'));
      } else {
        callback();
      }
    },
    validateEndTime(rule, value, callback) {
      if (value <= this.benkesheng.benke_opentime) {
        callback(new Error('结束时间必须大于开始时间'));
      } else {
        callback();
      }
    },
    validateEndTime2(rule, value, callback) {
      if (value <= this.yanjiusheng.yanjiu_opentime) {
        callback(new Error('结束时间必须大于开始时间'));
      } else {
        callback();
      }
    },
    validateEndTime3(rule, value, callback) {
      if (value <= this.boshisheng.boshi_opentime) {
        callback(new Error('结束时间必须大于开始时间'));
      } else {
        callback();
      }
    },
    validateEndTime4(rule, value, callback) {
      if (value <= this.houbusheng.houbu_opentime) {
        callback(new Error('结束时间必须大于开始时间'));
      } else {
        callback();
      }
    },
    loadPost(){
      this.benke_edit=true
    },

    resetParam(){
      this.yanjiu_edit=true
    },
    add(){
      this.boshi_edit=true
    },
    add2(){
      this.houbu_edit=true
    },
    AddRoom(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.benke_edit = false;

          if (this.benke===2){
           const fuck = JSON.parse(sessionStorage.getItem('admin')).token
            this.$axios.put(this.$httpUrl+'/openTime',{
              id: 1 ,
              openTime: this.benkesheng.benke_opentime,
              closeTime: this.benkesheng.benke_endtime,
            },{
              withCredentials: true, // 允许跨域请求中的Cookie
              headers:{
                'Authorization':"Bearer"+" "+fuck
              },
            }).then(res=>{
              console.log(res.data)
            })
          }
          else  if (this.benke===1){
            this.$axios.post(this.$httpUrl+'/openTime',{
              id: 1 ,
              openTime: this.benkesheng.benke_opentime,
              closeTime: this.benkesheng.benke_endtime,
            },{
              withCredentials: true, // 允许跨域请求中的Cookie
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
            }).then(res=>{
              console.log(res.data)
            })
            this.$message.success( 'Time set successfully');
          }else {
            console.log("wrong")
          }

        } else {
          // 表单验证不通过，不执行提交操作，显示错误提示
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom2(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.yanjiu_edit = false;
          console.log(this.yanjiu)
          if (this.yanjiu===2){
            this.$axios.put(this.$httpUrl+'/openTime',{
              id: 2 ,
              openTime: this.yanjiusheng.yanjiu_opentime,
              closeTime: this.yanjiusheng.yanjiu_endtime,
            },{
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
              withCredentials: true // 允许跨域请求中的Cookie
            }).then(res=>{
              console.log(res.data)
            })
          }
          else  if (this.yanjiu===1){
            this.$axios.post(this.$httpUrl+'/openTime',{
              id: 2 ,
              openTime: this.yanjiusheng.yanjiu_opentime,
              closeTime: this.yanjiusheng.yanjiu_endtime,
            },{
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
              withCredentials: true // 允许跨域请求中的Cookie
            }).then(res=>{
              console.log(res.data)
            })
            this.$message.success( 'Time set successfully');
          }else {
            console.log("wrong")
          }

        } else {
          // 表单验证不通过，不执行提交操作，显示错误提示
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom3(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.boshi_edit = false;
          console.log(this.bo)
          if (this.bo===2){
            this.$axios.put(this.$httpUrl+'/openTime',{
              id: 3 ,
              openTime: this.boshisheng.boshi_opentime,
              closeTime: this.boshisheng.boshi_endtime,
            },{
              withCredentials: true, // 允许跨域请求中的Cookie
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },

            }).then(res=>{
              console.log(res.data)
            })
          }
          else  if (this.bo===1){
            this.$axios.post(this.$httpUrl+'/openTime',{
              id: 3 ,
              openTime: this.boshisheng.boshi_opentime,
              closeTime: this.boshisheng.boshi_endtime,
            },{
              withCredentials: true, // 允许跨域请求中的Cookie
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
            }).then(res=>{
              console.log(res.data)
            })
            this.$message.success( 'Time set successfully');
          }else {
            console.log("wrong")
          }

        } else {
          // 表单验证不通过，不执行提交操作，显示错误提示
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom4(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.houbu_edit = false;
          console.log(this.houbu)
          if (this.houbu===2){
            this.$axios.put(this.$httpUrl+'/openTime',{
              id: 4 ,
              openTime: this.houbusheng.houbu_opentime,
              closeTime: this.houbusheng.houbu_endtime,
            },{
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
              withCredentials: true // 允许跨域请求中的Cookie
            }).then(res=>{
              console.log(res.data)
            })
          }
          else  if (this.houbu===1){
            this.$axios.post(this.$httpUrl+'/openTime',{
              id: 4 ,
              openTime: this.houbusheng.houbu_opentime,
              closeTime: this.houbusheng.houbu_endtime,
            },{
              headers:{
                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
              },
              withCredentials: true // 允许跨域请求中的Cookie
            }).then(res=>{
              console.log(res.data)
            })
            this.$message.success( 'Time set successfully');
          }else {
            console.log("wrong")
          }

        } else {
          // 表单验证不通过，不执行提交操作，显示错误提示
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    cancel(){
      this.benke_edit = false;
      this.$axios.get(this.$httpUrl+'/openTime/1',{
        headers:{
          'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
        },
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res=>{
        // 假设 res.data 是您从后端获得的数据
        const data = res.data.data;
        console.log(res)
        console.log(data)
        // 检查 data 是否为数组
        if (res.data.code===2010) {
          // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
          this.benkesheng.benke_opentime=data.openTime
          this.benkesheng.benke_endtime=data.closeTime
          // 将转换后的数据添加到 Room 数组
          console.log(this.benkesheng)
          this.benke= 2
          console.log(this.benke)
          // 打印转换后的数据

        } else {
          this.benke= 1
          this.benkesheng.benke_opentime=null
          this.benkesheng.benke_endtime=null
          console.log(this.benke)
          console.error("The response data is not an array.");
        }
      });
      this.$message.success('Cancel  operation');
      // 清空表单
    },
    cancel2(){
      this.yanjiu_edit = false;
      this.$axios.get(this.$httpUrl+'/openTime/2',{
        headers:{
          'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
        },
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res=>{
        // 假设 res.data 是您从后端获得的数据
        const data = res.data.data;
        console.log(res)
        console.log(data)
        // 检查 data 是否为数组
        if (res.data.code===2010) {
          // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
          this.yanjiusheng.yanjiu_opentime=data.openTime
          this.yanjiusheng.yanjiu_endtime=data.closeTime
          // 将转换后的数据添加到 Room 数组
          console.log(this.yanjiusheng)
          this.yanjiu= 2
          console.log(this.yanjiu)
          // 打印转换后的数据

        } else {
          this.yanjiu= 1
          this.yanjiusheng.yanjiu_opentime=null
          this.yanjiusheng.yanjiu_endtime=null
          console.log(this.yanjiu)
          console.error("The response data is not an array.");
        }
      });
      this.$message.success('Cancel  operation');

      // 清空表单

    },
    cancel3(){
      this.boshi_edit = false;
      this.$axios.get(this.$httpUrl+'/openTime/3',{
        headers:{
          'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
        },
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res=>{
        // 假设 res.data 是您从后端获得的数据
        const data = res.data.data;
        console.log(res)
        console.log(data)
        // 检查 data 是否为数组
        if (res.data.code===2010) {
          // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
          this.boshisheng.boshi_opentime=data.openTime
          this.boshisheng.boshi_endtime=data.closeTime
          // 将转换后的数据添加到 Room 数组
          console.log(this.bo)
          this.bo= 2
          console.log(this.bo)
          // 打印转换后的数据

        } else {
          this.bo= 1
          this.boshisheng.boshi_opentime=null
          this.boshisheng.boshi_endtime=null
          console.log(this.bo)
          console.error("The response data is not an array.");
        }
      });
      this.$message.success('Cancel  operation');

      // 清空表单

    },

    cancel4(){
      this.houbu_edit = false;
      this.$axios.get(this.$httpUrl+'/openTime/4',{
        headers:{
          'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('admin')).token
        },
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res=>{
        // 假设 res.data 是您从后端获得的数据
        const data = res.data.data;
        console.log(res)
        console.log(data)
        // 检查 data 是否为数组
        if (res.data.code===2010) {
          // 使用 Array.map 将每个符合条件的房间的数据转换为 RoomForm 格式
          this.houbusheng.houbu_opentime=data.openTime
          this.houbusheng.houbu_endtime=data.closeTime
          // 将转换后的数据添加到 Room 数组
          console.log(this.houbu)
          this.houbu= 2
          console.log(this.houbu)
          // 打印转换后的数据

        } else {
          this.houbu= 1
          this.houbusheng.houbu_opentime=null
          this.houbusheng.houbu_endtime=null
          console.log(this.houbu)
          console.error("The response data is not an array.");
        }
      });
      this.$message.success('Cancel  operation');

      // 清空表单

    }
  }


}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">

      <el-button type="primary" style="margin-left: 5px" @click="loadPost">设置研究男生时间</el-button>
      <el-button type="success" @click="resetParam">设置研究女生时间</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">设置博士男时间</el-button>
      <el-button type="success" style="margin-left: 5px" @click="add2">设置博士女生时间</el-button>
    </div>
    <el-descriptions  :column="3" border>
      <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >研究男生</el-descriptions-item>
      <el-descriptions-item label="开始时间">{{ benkesheng.benke_opentime | formatDate }}</el-descriptions-item>
      <el-descriptions-item label="结束时间">{{ benkesheng.benke_endtime | formatDate }}</el-descriptions-item>

      <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >研究女生</el-descriptions-item>
      <el-descriptions-item label="开始时间">{{ yanjiusheng.yanjiu_opentime | formatDate }} </el-descriptions-item>
      <el-descriptions-item label="结束时间">{{ yanjiusheng.yanjiu_endtime | formatDate }} </el-descriptions-item>

      <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >博士男生</el-descriptions-item>
      <el-descriptions-item label="开始时间">{{ boshisheng.boshi_opentime | formatDate }} </el-descriptions-item>
      <el-descriptions-item label="结束时间">{{ boshisheng.boshi_endtime | formatDate }} </el-descriptions-item>


      <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >博士女生</el-descriptions-item>
      <el-descriptions-item label="开始时间">{{ houbusheng.houbu_opentime | formatDate }} </el-descriptions-item>
      <el-descriptions-item label="结束时间">{{ houbusheng.houbu_endtime | formatDate }} </el-descriptions-item>
    </el-descriptions>

    <el-dialog
        :visible.sync="benke_edit"
        title="Undergraduate Time"
        width="600px"
        center
    >
      <el-form
          ref="Room-name"
          :model="benkesheng"
          :rules="rules"
          label-width="auto"
          label-position="right"
          size="default"
          style="font-size: 20px;"
      >
        <el-form-item label="OpenTime" prop="benke_opentime">
          <el-date-picker
              type="datetime"
              v-model="benkesheng.benke_opentime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="EndTime" prop="benke_endtime">
          <el-date-picker
              type="datetime"
              v-model="benkesheng.benke_endtime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item>
          <div class="button-group2">
            <el-button type="primary" @click="AddRoom('Room-name')" class="submit-button">Submit</el-button>
            <el-button @click="cancel('Room-name')" class="cancel-button">Cancel</el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>

    <el-dialog
        :visible.sync="yanjiu_edit"
        title="Undergraduate Time"
        width="600px"
        center
    >
      <el-form
          ref="Room-name"
          :model="yanjiusheng"
          :rules="rules"
          label-width="auto"
          label-position="right"
          size="default"
          style="font-size: 20px;"
      >
        <el-form-item label="OpenTime" prop="yanjiu_opentime">
          <el-date-picker
              type="datetime"
              v-model="yanjiusheng.yanjiu_opentime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="EndTime" prop="yanjiu_endtime">
          <el-date-picker
              type="datetime"
              v-model="yanjiusheng.yanjiu_endtime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item>
          <div class="button-group2">
            <el-button type="primary" @click="AddRoom2('Room-name')" class="submit-button">Submit</el-button>
            <el-button @click="cancel2('Room-name')" class="cancel-button">Cancel</el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>
    <el-dialog
        :visible.sync="boshi_edit"
        title="Undergraduate Time"
        width="600px"
        center
    >
      <el-form
          ref="Room-name"
          :model="boshisheng"
          :rules="rules"
          label-width="auto"
          label-position="right"
          size="default"
          style="font-size: 20px;"
      >
        <el-form-item label="OpenTime" prop="boshi_opentime">
          <el-date-picker
              type="datetime"
              v-model="boshisheng.boshi_opentime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="EndTime" prop="boshi_endtime">
          <el-date-picker
              type="datetime"
              v-model="boshisheng.boshi_endtime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item>
          <div class="button-group2">
            <el-button type="primary" @click="AddRoom3('Room-name')" class="submit-button">Submit</el-button>
            <el-button @click="cancel3('Room-name')" class="cancel-button">Cancel</el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>

    <el-dialog
        :visible.sync="houbu_edit"
        title="Undergraduate Time"
        width="600px"
        center
    >
      <el-form
          ref="Room-name"
          :model="houbusheng"
          :rules="rules"
          label-width="auto"
          label-position="right"
          size="default"
          style="font-size: 20px;"
      >
        <el-form-item label="OpenTime" prop="houbu_opentime">
          <el-date-picker
              type="datetime"
              v-model="houbusheng.houbu_opentime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="EndTime" prop="houbu_endtime">
          <el-date-picker
              type="datetime"
              v-model="houbusheng.houbu_endtime"
              label="Pick a time"
              placeholder="Pick a time"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item>
          <div class="button-group2">
            <el-button type="primary" @click="AddRoom4('Room-name')" class="submit-button">Submit</el-button>
            <el-button @click="cancel4('Room-name')" class="cancel-button">Cancel</el-button>
          </div>
        </el-form-item>


      </el-form>
    </el-dialog>
  </div>
</template>


<style scoped>

.my-label {
  background: #dcf3d8;
}

.my-content {
  background: #FDE2E2;
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

</style>