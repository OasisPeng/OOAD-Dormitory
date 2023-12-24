<script>
import Papa from 'papaparse'
import axios from "axios";
export default {
  name: "StudentManage",
  props: {},
  methods: {

    async upLoadStudent(data){
      try {
        const resopnse = await axios.post(this.$httpUrl + '/user/upload',{List: data});
        if (resopnse.code == 2020){
        console.log('学生上传成功')
        }
        else
          console.error('学生上传失败')

      }
      catch (error){console.error('上传错误', error);
      }
    },
    loadPost() {
            this.$axios.get(this.$httpUrl + '/users/listPage', {
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    name: this.name
                },

            withCredentials: true, headers:{
            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
            },
            }).then(res => res.data).then(res => {
                console.log(res);
                if (res.code === 2020) {
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                } else {
                    alert('获取数据失败');
                }
            }).catch(error => {
                console.error('Error:', error);
            });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.pageNum = 1;
      this.loadPost();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadPost()
    },
    resetParam() {
      this.name = '';
      this.loadPost()
    },
    mod(row) {//参数是拿到的一整行的数据
      this.centerDialogVisible = true;
      this.$nextTick(() => {//异步处理
        //赋值到表单,form是表单内容
        this.form.id = row.id;
        this.form.name = row.name;
                this.form.sex = row.sex;
                this.form.college = row.college;
                this.form.studentType = row.studentType;
                this.form.grade = row.grade;
      })
    },
    del(id) {
            this.$axios.delete(this.$httpUrl+'/user/'+id).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==2030){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
    },
    add() {
      this.centerDialogVisible = true;
      this.$nextTick(() => {//异步处理
        this.resetForm();
      })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {//表示修改
            this.doMod();
          } else {
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$refs.form.resetFields();//表单内容回到前一个状态
    },
    doSave() {
            this.$axios.post(this.$httpUrl+'/user', this.form,
                {withCredentials: true, headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                },}).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==2040){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.centerDialogVisible=false
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
    },
    doMod() {
            this.$axios.put(this.$httpUrl+'/user', this.form,{
                withCredentials: true, headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                },
            }).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==2020){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.centerDialogVisible=false
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
              }
            })
            }
},
  beforeMount() {
    this.loadPost();
  },
  data() {

    return {
      targetTeam:null,
      currentTeam: null,


      dormSelction:[],
      tableData: [],
      textarea: '',
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      centerDialogVisible: false,
      form: {
        id: '',
        name: '',
                sex:'',
                college:'',
                studentType:'',
                grade:''
      },
      rules: {
        name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                college: [
                    { required: true, message: '请输入书院', trigger: 'blur' }
                ],
                studentType: [
                    { required: true, message: '请输入学生类型', trigger: 'blur' }
                ],
                grade: [
                    { required: true, message: '请输入年级', trigger: 'blur' }
        ]
      },


      csvVisible: false,
      id: '',
      file: {},
      formMaxSize: 10, // 上传文件大小
      formFileList: [], // 显示上传文件
      uploadFormFileList: [], // 确定上传文件
    }
  },
  mounted() {
    console.log(this.id)
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
            <el-input v-model="name" placeholder="请输入学生姓名" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>

      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{ background:'#f2f5fc', color: '#555555'}"
              border
    >
            <el-table-column prop="id" label="学号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
            <el-table-column prop="sex" label="性别" width="120">
      </el-table-column>
            <el-table-column prop="college" label="书院" width="180">
      </el-table-column>
      <el-table-column prop="studentType" label="学生类型" width="180">
      </el-table-column>
      <el-table-column prop="grade" label="年级" width="180">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除这条数据吗？"
              @confirm="del(scope.row.id)"
              style="margin-left: 5px"
          >
            <el-button slot="reference" size="small" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
        >
      </el-table-column>



    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
            <el-form ref="form" :model="form" :rules="rules" label-width="100px">
                <el-form-item label="姓名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-col :span="20">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="男"></el-radio>
                            <el-radio label="女"></el-radio>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
                <el-form-item label="学院" prop="college">
                    <el-col :span="20">
                        <el-select v-model="form.college">
                            <el-option label="致诚" value="致诚"></el-option>
                            <el-option label="致仁" value="致仁"></el-option>
                            <el-option label="致新" value="致新"></el-option>
                            <el-option label="树德" value="树德"></el-option>
                            <el-option label="树礼" value="树礼"></el-option>
                            <el-option label="树仁" value="树仁"></el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item label="学生类型" prop="studentType">
                    <el-col :span="20">
                        <el-radio-group v-model="form.studentType">
                            <el-radio label="本科生"></el-radio>
                            <el-radio label="研究生"></el-radio>
                            <el-radio label="博士生"></el-radio>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
                <el-form-item label="年级" prop="grade">
          <el-col :span="20">
                        <el-input v-model="form.grade" placeholder="例：21"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
            </span>
    </el-dialog>




    <el-dialog :title="'导入文件'" :visible.sync="csvVisible" width="50%">
      <div>
        <el-form ref="file" label-width="120px">
          <el-form-item label="CSV文件导入：">
            <el-upload
                class="upload-demo"
                action
                :limit="1"
                :file-list="formFileList"
                :http-request="handleUploadForm"
                :on-exceed="formHandleExceed"
                :on-remove="formHandleRemove"
                :before-upload="beforeUploadForm"
                accept=".csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"
            >
              <el-button type="primary">上传文件</el-button>
              <span slot="tip" class="el-upload__tip" style="margin: 0 10px;"
              >只能上传xlsx/xls/csv文件，且不超过{{ formMaxSize }}M</span
              >
            </el-upload>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="csvVisible = false">取消</el-button>
      </span>
    </el-dialog>

    <input  type="file" id="files" ref="refFile" v-on:change="importCsv">
    <el-button type="primary" @click ="exportStudentCsv">导出学生</el-button>
    <el-button type="primary" @click ="exportDormCsv">导出宿舍选择情况</el-button>
    <el-button type="primary" @click ="exchangeDorm">交换宿舍</el-button>

  </div>
</template>

<style scoped>

</style>