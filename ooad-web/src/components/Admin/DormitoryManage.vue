<script>
export default {
    name:"DomritoryManage",
    methods:{
        loadPost(){
            this.$axios.get(this.$httpUrl+'/dorms?pageSize='+this.pageSize+'&pageNum='+this.pageNum
            ).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==2010){
                    this.tableData=res.data.records
                    this.total=res.data.total
                }else {
                    alert('获取数据失败')
                }
            })
        },
        loadByDis(){
            this.$axios.get(this.$httpUrl+'/dorm2/'+this.distribution+'?pageSize='+this.pageSize+'&pageNum='+this.pageNum
            ).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==2010){
                    this.tableData=res.data.records
                    console.log(this.tableData)
                    this.total=res.data.total
                }else {
                    alert('获取数据失败')
                }
            })
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize=val;
            this.pageNum=1;
            this.loadPost();
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageNum=val;
            this.loadPost()
        },
        resetParam(){
            this.distribution='';
            this.loadPost()
        },
        mod(row){//参数是拿到的一整行的数据
            this.centerDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                //赋值到表单,form是表单内容
                this.form.id=row.id;
                this.form.distribution = row.distribution;
                this.form.building = row.building;
                this.form.floor = row.floor;
                this.form.floorSex = row.floorSex;
                this.form.room = row.room;
                this.form.available = row.available;
                this.form.size = row.size;
                this.form.detail = row.detail;
            })
        },
        del(id){
            this.$axios.delete(this.$httpUrl+'/dorm/'+id).then(res=>res.data).then(res=>{
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
        add(){
            this.centerDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                this.resetForm();
            })
        },
        save(){
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if(this.form.id){//表示修改
                        this.doMod();
                    }else {
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
        doSave(){
            this.$axios.post(this.$httpUrl+'/dorm',this.form).then(res=>res.data).then(res=>{
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
        doMod(){
            this.$axios.put(this.$httpUrl+'/dorm', this.form).then(res=>res.data).then(res=>{
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
            tableData: [],
            pageSize:10,
            pageNum:1,
            total:0,
            distribution:'',
            centerDialogVisible:false,
            form:{
                id:'',
                distribution:'',
                building:'',
                floor:'',
                floorSex:'',
                room:'',
                available:'',
                size:'',
                detail:''
            },
            rules: {
                distribution: [
                    { required: true, message: '请输入区域', trigger: 'blur' }
                ],
                building: [
                    { required: true, message: '请输入楼栋', trigger: 'blur' }
                ],
                floor: [
                    { required: true, message: '请输入楼层', trigger: 'blur' }
                ],
                floorSex: [
                    { required: true, message: '请选择寝室类别', trigger: 'change' }
                ],
                room: [
                    { required: true, message: '请输入房间号', trigger: 'blur' }
                ],
                available: [
                    { required: true, message: '请选择是否空闲', trigger: 'change' }
                ],
                size: [
                    { required: true, message: '请选择大小', trigger: 'change' }
                ],
                detail: [
                    { required: true, message: '请输入备注', trigger: 'blur' }
                ]
            }
        }
    }
}
</script>

<template>
    <div>
        <div style="margin-bottom: 5px">
            <el-input v-model="distribution" placeholder="请输入区域信息，例：湖畔" suffix-icon="el-icon-search" style="width: 250px"
                      @keyup.enter.native="loadByDis"></el-input>

            <el-button type="primary" style="margin-left: 5px" @click="loadByDis">查询</el-button>
            <el-button type="success" @click="resetParam">重置</el-button>
            <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button>
        </div>
        <el-table :data="tableData"
                  :header-cell-style="{ background:'#f2f5fc', color: '#555555'}"
                  border
        >
            <el-table-column prop="id" label="ID" width="60">
            </el-table-column>
            <el-table-column prop="distribution" label="区域" width="80">
            </el-table-column>
            <el-table-column prop="building" label="几栋" width="80">
            </el-table-column>
            <el-table-column prop="floor" label="楼层" width="80">
            </el-table-column>
            <el-table-column prop="floorSex" label="男寝还是女寝" width="80">
            </el-table-column>
            <el-table-column prop="room" label="房间号" width="80">
            </el-table-column>
            <el-table-column prop="available" label="是否空闲" width="80">
            </el-table-column>
            <el-table-column prop="size" label="几人间" width="80">
            </el-table-column>
            <el-table-column prop="detail" label="备注" width="250">
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
                </template>>
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
            <el-form ref="form" :model="form" :rules="rules" label-width="110px">
                <el-form-item label="区域" prop="distribution">
                    <el-col :span="20">
                        <el-select v-model="form.distribution">
                            <el-option label="湖畔" value="湖畔"></el-option>
                            <el-option label="二期" value="二期"></el-option>
                            <el-option label="荔园" value="荔园"></el-option>
                            <el-option label="欣园" value="欣园"></el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item label="几栋" prop="building">
                    <el-col :span="20">
                        <el-input v-model="form.building"  placeholder="例：11栋"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="楼层" prop="floor">
                    <el-col :span="20">
                        <el-input v-model="form.floor" placeholder="请输入数字"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="男寝还是女寝" prop="floorSex">
                    <el-col :span="20">
                        <el-radio-group v-model="form.floorSex">
                            <el-radio label="男"></el-radio>
                            <el-radio label="女"></el-radio>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
                <el-form-item label="房间号" prop="room">
                    <el-col :span="20">
                        <el-input v-model="form.room" placeholder="请输入房间号"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="是否空闲" prop="available">
                    <el-col :span="20">
                        <el-radio-group v-model="form.available">
                            <el-radio label="是"></el-radio>
                            <el-radio label="否"></el-radio>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
                <el-form-item label="几人间" prop="size">
                    <el-col :span="20">
                        <el-radio-group v-model="form.size">
                            <el-radio label="2"></el-radio>
                            <el-radio label="3"></el-radio>
                            <el-radio label="4"></el-radio>
                        </el-radio-group>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="detail">
                    <el-col :span="20">
                        <el-input type="textarea" v-model="form.detail"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<style scoped>

</style>