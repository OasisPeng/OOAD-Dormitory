<template>
    <div>
        <el-container>
            <!-- 头部 -->
            <el-header style="background-color: #67C23A; color: #fff; padding: 20px;margin-left: -10px; margin-top: -10px;">
                <h2 style="text-align: center">组队邀请处理</h2>
            </el-header>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 邀请列表 -->
                <el-card v-for="(invitation, index) in invitations" :key="index" style="margin-bottom: 20px;">
                    <!-- 邀请人信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="invitation.avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{ invitation.username }}</span>
                    </div>

                    <!-- 邀请内容 -->
                    <div style="font-size: 16px">
                        <strong>邀请您加入他们的组队：</strong>
                        {{ invitation.teamName }}
                    </div>

                    <!-- 操作按钮 -->
                    <div style="margin-top: 10px;">
                        <el-button type="success" @click="handleAccept(invitation)">同意</el-button>
                        <el-button type="danger" @click="handleReject(invitation)">拒绝</el-button>
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
            invitations: [
                {
                    username: 'John Doe',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    teamName: 'Web 开发组',
                },
                {
                    username: 'Alice Smith',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    teamName: '移动应用组',
                },
                // 添加更多邀请...
            ],
        };
    },
    methods: {
        handleAccept(invitation) {
            // 处理同意操作，可以在这里触发相关逻辑
            console.log(`同意 ${invitation.username} 的组队邀请`);
        },
        handleReject(invitation) {
            // 处理拒绝操作，可以在这里触发相关逻辑
            console.log(`拒绝 ${invitation.username} 的组队邀请`);
        },
    },
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
