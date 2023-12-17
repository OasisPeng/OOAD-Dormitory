<template>
    <div>
        <el-container>
            <!-- 头部 -->
            <el-header style="background-color:  #3384d5; color: #fff; padding: 20px;margin-left: -10px; margin-top: -10px;">
                <h2 style="text-align: center">组队申请处理</h2>
            </el-header>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 申请列表 -->
                <el-card v-for="(application, index) in applications" :key="index" style="margin-bottom: 20px;">
                    <!-- 申请人信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="application.avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{ application.username }}</span>
                    </div>

                    <!-- 申请内容 -->
                    <div style="font-size: 16px">
                        <strong>申请加入您的组队：</strong>
                        {{ application.teamName }}
                    </div>

                    <!-- 操作按钮 -->
                    <div style="margin-top: 10px;">
                        <el-button type="success" @click="handleAccept(application)">同意</el-button>
                        <el-button type="danger" @click="handleReject(application)">拒绝</el-button>
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
            applications: [
                {
                    username: '张三',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    teamName: '我们都早睡小队',
                },
                {
                    username: '李二',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    teamName: '111',
                },
                // 添加更多申请...
            ],
        };
    },
    methods: {
        handleAccept(application) {
            // 处理同意操作，可以在这里触发相关逻辑
            console.log(`同意 ${application.username} 的组队申请`);
        },
        handleReject(application) {
            // 处理拒绝操作，可以在这里触发相关逻辑
            console.log(`拒绝 ${application.username} 的组队申请`);
        },
    },
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
