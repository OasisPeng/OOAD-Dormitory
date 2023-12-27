<template>
    <div>
        <el-container>
            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(notification, index) in notifications" :key="index" style="margin-bottom: 20px;">
                    <!-- 通知标题 -->
                    <div style="font-size: 15px; font-weight: bold; margin-bottom: 10px;">重要通知</div>
                    <!-- 通知内容 -->
                    <div style="font-size: 16px">{{ notification.content }}</div>

                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            notifications: [],
        };
    },
    methods :{
        getAllBroadcasts(){
            this.$axios.get(this.$httpUrl+'/broadCasts',
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.notifications = res.data.data
                } else {
                    console.log(res.data.msg)
                }

            })
        }
    },
    created() {
        this.getAllBroadcasts()
    }
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
