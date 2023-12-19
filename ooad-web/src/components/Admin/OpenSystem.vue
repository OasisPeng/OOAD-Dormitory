<script>
export default {
  created() {

    this.setStudentTime(1, this.benkesheng);
    this.setStudentTime(2, this.yanjiusheng);
    this.setStudentTime(3, this.boshisheng);
    this.setStudentTime(4, this.houbusheng);
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
        benke_opentime: [
          { required: true, message: '请选择开始时间', trigger: 'change' },
          { validator: this.validateOpenTime, trigger: 'change' },
        ],
        benke_endtime: [
          { required: true, message: '请选择结束时间', trigger: 'change' },
          { validator: this.validateEndTime, trigger: 'change' },
        ],
       yanjiu_opentime: [
          { required: true, message: '请选择开始时间', trigger: 'change' },
          { validator: this.validateOpenTime, trigger: 'change' },
        ],
        yanjiu_endtime: [
          { required: true, message: '请选择结束时间', trigger: 'change' },
          { validator: this.validateEndTime2, trigger: 'change' },
        ],
        boshi_opentime: [
          { required: true, message: '请选择开始时间', trigger: 'change' },
          { validator: this.validateOpenTime, trigger: 'change' },
        ],
        boshi_endtime: [
          { required: true, message: '请选择结束时间', trigger: 'change' },
          { validator: this.validateEndTime3, trigger: 'change' },
        ],
        houbu_opentime: [
          { required: true, message: '请选择开始时间', trigger: 'change' },
          { validator: this.validateOpenTime, trigger: 'change' },
        ],
        houbu_endtime: [
          { required: true, message: '请选择结束时间', trigger: 'change' },
          { validator: this.validateEndTime4, trigger: 'change' },
        ],
      },
    }
  },

  methods:{
    setStudentTime(id, target) {
      this.$axios.get(this.$httpUrl + `/openTime/${id}`).then(res => {
        const data = res.data.data;
        console.log(res);
        console.log(data);

        if (res.data.code === 2010) {
          target.opentime = data.openTime;
          target.endtime = data.closeTime;
          console.log(target);
          target.edit = false;
          console.log(target.status);
        } else {
          target.status = 1;
          target.opentime = null;
          target.endtime = null;
          console.log(target.status);
          console.error("The response data is not an array.");
        }
      });
      this.$message.success('Cancel operation');
    },

    updateOrAddTime(id, data, status) {
      this.$refs['Room-name'].validate(valid => {
        if (valid) {
          this.setStudentTime(id, data);
          this.$nextTick(() => this.updateOrAddTimeHelper(id, data, status));
        } else {
          this.$message.error('Form validation failed. Please check input.');
        }
      });
    },

    updateOrAddTimeHelper(id, data, status) {
      if (status === 2) {
        this.$axios.put(this.$httpUrl + '/openTime', { id, openTime: data.opentime, closeTime: data.endtime }, { withCredentials: true })
            .then(res => console.log(res.data));
      } else if (status === 1) {
        this.$axios.post(this.$httpUrl + '/openTime', { id, openTime: data.opentime, closeTime: data.endtime }, { withCredentials: true })
            .then(res => {
              console.log(res.data);
              this.$message.success('Time set successfully');
            });
      } else {
        console.log("wrong");
      }
    },
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
          const id = 1;
          const data = this.benkesheng;
          this.updateOrAddTime(id, data,this.benke);
        } else {
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom2(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.benke_edit = false;
          const id = 1;
          const data = this.benkesheng;
          this.updateOrAddTime(id, data,this.yanjiu);
        } else {
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom3(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.benke_edit = false;
          const id = 1;
          const data = this.benkesheng;
          this.updateOrAddTime(id, data,this.bo);
        } else {
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    AddRoom4(FormName) {
      this.$refs[FormName].validate((valid) => {
        if (valid) {
          this.benke_edit = false;
          const id = 1;
          const data = this.benkesheng;
          this.updateOrAddTime(id, data,this.houbu);
        } else {
          this.$message.error('表单验证不通过，请检查输入');
        }
      });
    },
    cancelRoom(type, id, data, form, statusKey) {
      this[type + '_edit'] = false;
      this.$axios.get(`${this.$httpUrl}/openTime/${id}`).then(res => {
        const responseData = res.data.data;
        if (res.data.code === 2010) {
          this[data][`${type}_opentime`] = responseData.openTime;
          this[data][`${type}_endtime`] = responseData.closeTime;
          this[statusKey] = 2;
        } else {
          this[statusKey] = 1;
          this[data][`${type}_opentime`] = null;
          this[data][`${type}_endtime`] = null;
          console.error("The response data is not an array.");
        }
      });
      this.$message.success(`Cancel ${type} operation`);
      // 清空表单
      this.$refs[form].resetFields();
    },

    cancel1() {
      this.cancelRoom('benke', 1, 'benkesheng', 'Room-name', 'benke');
    },

    cancel2() {
      this.cancelRoom('yanjiu', 2, 'yanjiusheng', 'Room-name-2', 'yanjiu');
    },

    cancel3() {
      this.cancelRoom('boshi', 3, 'boshisheng', 'Room-name-3', 'bo');
    },

    cancel4() {
      this.cancelRoom('houbu', 4, 'houbusheng', 'Room-name-4', 'houbu');
    },
  }


}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">

      <el-button type="primary" style="margin-left: 5px" @click="loadPost">设置本科生时间</el-button>
      <el-button type="success" @click="resetParam">设置研究生时间</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">设置博士生时间</el-button>
      <el-button type="success" style="margin-left: 5px" @click="add2">设置候补时间</el-button>
    </div>
  <el-descriptions  :column="3" border>
    <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >本科生</el-descriptions-item>
    <el-descriptions-item label="开始时间">{{ benkesheng.benke_opentime | formatDate }}</el-descriptions-item>
    <el-descriptions-item label="结束时间">{{ benkesheng.benke_endtime | formatDate }}</el-descriptions-item>

    <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >研究生</el-descriptions-item>
    <el-descriptions-item label="开始时间">{{ yanjiusheng.yanjiu_opentime | formatDate }} </el-descriptions-item>
    <el-descriptions-item label="结束时间">{{ yanjiusheng.yanjiu_endtime | formatDate }} </el-descriptions-item>

    <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >博士生</el-descriptions-item>
    <el-descriptions-item label="开始时间">{{ boshisheng.boshi_opentime | formatDate }} </el-descriptions-item>
    <el-descriptions-item label="结束时间">{{ boshisheng.boshi_endtime | formatDate }} </el-descriptions-item>


    <el-descriptions-item label="学生类别" style=" background: #dcf3d8" >候补学生</el-descriptions-item>
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