<script>
export default {
    name:"HeaderAdmin",
    data(){
        return{
        }
    },
    methods:{
        logout(){
            //退出确认
            this.$confirm('您确定要退出登录吗？','提示',{
                confirmButtonText:'确定',
                type:'warning',
                center:true
            }).then(()=>{
                this.$message({
                    type:'success',
                    message:'退出登录成功'
                })
                this.$router.push('/');//路由
                sessionStorage.clear()
            })
                .catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消退出登录'
                    })
                })
        },
        collapse(){
            this.$emit('doCollapse')
        },
    },
    props:{
        icon:String
    },
    created() {
        this.$router.push("/DormitoryManage")
    }
};
</script>

<template>
    <div style="display: flex;line-height: 60px;">
        <div>
            <i :class="icon" style="font-size: 20px;line-height: 60px;cursor: pointer;" @click="collapse"></i>
        </div>
        <div style="flex: 1; font-size: 30px; text-align: center; margin-left:110px;font-weight: bold; color: #282626; font-family: 'Arial', sans-serif;">
            <span>Dormitory Selection System</span>
        </div>

          <el-dropdown>
            <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
            <el-dropdown-menu slot="dropdown">
                <!--                <el-upload-->
                <!--                    action="https://jsonplaceholder.typicode.com/posts/"-->
                <!--                    :show-file-list="false"-->
                <!--                    :on-success="handleUploadSuccess"-->
                <!--                >-->
                <!--                    <el-dropdown-item slot="trigger">上传头像</el-dropdown-item>-->
                <!--                </el-upload>-->
                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<style scoped>

</style>