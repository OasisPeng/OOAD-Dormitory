<script>

export default {
    name:"Index",
    methods:{
    },
    created() {
        this.$message({
            type: 'success',
            message: '欢迎来到消息中心'
        })
        this.$router.push("/MyMessage")
    },
    data(){
        return{
            isCollapse:false,
            aside_width:'200px',
            icon:'el-icon-s-fold',
            user:JSON.parse(sessionStorage.getItem('CurUser')),
            menu:[
                {
                    menuClick:'MyMessage',
                    menuName:'我的消息',
                },
                {
                    menuClick:'Like',
                    menuName:'点赞',
                },
                {
                    menuClick:'Comment',
                    menuName:'评论',
                },
                {
                    menuClick:'BroadcastMessages',
                    menuName:'广播通知',
                },
                {
                    menuClick:'TeamInvitation',
                    menuName:'组队邀请',
                },
                {
                    menuClick:'TeamOffer',
                    menuName:'组队申请',
                }
            ]
        }
    }
};
</script>

<template>
    <el-container style="height: 100%; border: 1px solid #eee">
        <!--        一-->
        <el-aside :width="aside_width" style="width:130px;height: 100vh;background-color: rgb(255,255,255);margin-left: -1px;">
            <el-menu
                    background-color="#606266"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="height: 100%"
                    default-active=/MyMessage
                    :collapse-transition="false"
                    router
            >
                <el-menu-item :index="'/'+item.menuClick" v-for="(item,i) in menu" :key="i">
                    <span slot="title">{{item.menuName}}</span>
                </el-menu-item>

            </el-menu>
        </el-aside>
        <!--        二-->
        <el-container style="height: 100%;">
            <!--         三-->
            <el-main style="height: 100%;">
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<style scoped>
.el-header {
##background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
}
.el-main {
    padding: 5px;
}
.el-aside {
    color: #333;
}
</style>