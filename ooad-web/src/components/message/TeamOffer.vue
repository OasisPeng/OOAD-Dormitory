<template>
    <div>
        <el-container>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 申请列表 -->
                <el-card v-for="(application, index) in applications" v-if="application.teamHeadId === user.id" :key="index" style="margin-bottom: 20px;">
                    <!-- 申请人信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{application.userId}}{{ application.userName }}</span>
                    </div>

                    <!-- 申请内容 -->
                    <div style="font-size: 16px">
                        <span>申请加入您的组队：</span>
                        {{ application.teamName }}
                    </div>

                    <!-- 操作按钮 -->
                    <div style="margin-top: 10px;">
                        <el-button type="success" @click="handleAccept(application)">同意</el-button>
<!--                        <el-button type="danger" @click="handleReject(application)">拒绝</el-button>-->
                    </div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            user: JSON.parse(sessionStorage.getItem('UserData')),
            avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            applications: [
                // {
                //     username: '张三',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     teamName: '我们都早睡小队',
                // },
                // {
                //     username: '李二',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     teamName: '111',
                // },
                // 添加更多申请...
            ],
        };
    },
    methods: {
        getAllOffers() { //“我是队长，别人申请加入我的组队”
            if (this.user.teamId == null) {
                return;
            }
            this.$axios.get(this.$httpUrl+'/application/offer/'+this.user.teamId,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.applications = res.data.data
                    console.log(this.applications)
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        handleAccept(application) {
            // 处理同意操作，可以在这里触发相关逻辑
            this.$axios.post(this.$httpUrl+'/team/'+application.teamId+'/'+application.userId,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2040) {
                    console.log(res.data.msg)
                    this.$message({
                        type: 'error',
                        message: '已加入'
                    });

                    // 成功同意后删除该通知
                    const app = {
                        teamId: this.user.teamId,
                        userId: application.userId,
                        type: 1
                    }
                    this.$axios.delete(this.$httpUrl+'/application',
                        {
                            data: app,
                            withCredentials: true,
                            headers:{
                                'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                            }}
                    ).then(res=>{
                        if (res.data.code===2030) {
                            console.log(res.data.msg)
                            this.getAllInvitations()

                        } else {
                            this.$message({
                                type: 'error',
                                message: res.data.msg
                            });
                        }
                    })
                    //更新用户的完整信息，存入sessionStorage
                    this.$axios.get(this.$httpUrl+'/user/'+JSON.parse(sessionStorage.getItem('CurUser')).id, {
                        withCredentials: true,
                        headers:{
                            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                        }}
                    ).then(res=>{
                        if (res.data.code===2010) {
                            sessionStorage.setItem("UserData", JSON.stringify(res.data.data));
                            console.log(JSON.parse(sessionStorage.getItem('UserData')));
                            this.$router.push({ name:'index' });
                        } else {
                            console.log(res.data.msg)
                            // 登录失败，可以显示错误消息
                        }
                    })
                } else {
                    this.$message({
                        type: 'error',
                        message: res.data.msg
                    });
                }
            })

        },
        handleReject(application) {
            // 处理拒绝操作，可以在这里触发相关逻辑
            console.log(`拒绝 ${application.userName} 的组队申请`);
        },
    },
    mounted() {
        this.getAllOffers()
    }
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
