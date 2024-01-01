<template>
    <div>
        <el-container>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 邀请列表 -->
                <el-card v-for="(invitation, index) in invitations" :key="index" style="margin-bottom: 20px;">
                    <!-- 邀请人信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{invitation.userId}}{{ invitation.userName }}</span>
                    </div>

                    <!-- 邀请内容 -->
                    <div style="font-size: 16px">
                        <span>申请与您更换宿舍</span><br>
                        <span>更换至：{{invitation.Dorm}}</span>
                    </div>

                    <!-- 操作按钮 -->
                    <div style="margin-top: 10px;">
                        <el-button type="success" @click="handleAccept(invitation)">同意</el-button>
                        <!--                        <el-button type="danger" @click="handleReject(invitation)">拒绝</el-button>-->
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
            invitations: [
                // {
                //     username: '张三',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     teamName: '230宿舍',
                // },
                // {
                //     username: '赵五',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     teamName: '333小队',
                // },
                // 添加更多邀请...
            ],
        };
    },
    methods: {
        getAllInvitations(){
            this.$axios.get(this.$httpUrl+'/application/change/'+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.invitations = res.data.data
                    console.log(this.invitations)
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        handleAccept(invitation) {
            // 触发换宿舍的方法
            this.$axios.post(this.$httpUrl+'/team/change/'+invitation.userId+'/'+this.user.id,null,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2040) {
                    this.$message({
                        type: 'success',
                        message: res.data.msg
                    });
                    // 成功同意后删除该通知
                    const application = {
                        teamId: invitation.userId,
                        userId: this.user.id,
                        type: 2
                    }
                    this.$axios.delete(this.$httpUrl+'/application',
                        {
                            data: application,
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
        handleReject(invitation) {
            // 处理拒绝操作，可以在这里触发相关逻辑
            console.log(`拒绝 ${invitation.username} 的组队邀请`);
        },
    },
    mounted() {
        this.getAllInvitations()
    }
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
