<template>
    <div>
        <el-container>
            <!-- 头部 -->
            <el-header style="background-color: #3384d5; color: #fff; padding: 20px;margin-left: -10px; margin-top: -10px;">
                <h2 style="text-align: center">点赞通知</h2>
            </el-header>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(like, index) in likes" :key="index" style="margin-bottom: 20px;">
                    <!-- 点赞用户信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="like.avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{ like.username }}</span>
                    </div>

                    <!-- 点赞内容 -->
                    <div style="font-size: 16px">
                        <i class="el-icon-thumb"></i> <!-- Element UI 的点赞图标 -->
                        {{ like.content }}
                    </div>

                    <!-- 点赞时间 -->
                    <div style="margin-top: 10px; color: #888;">{{ like.time }}</div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            likes: [
                {
                    username: 'John Doe',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    content: '赞了你的帖子：“今天天气真好！”',
                    time: '2023-01-05 12:15',
                },
                {
                    username: 'Alice Smith',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    content: '赞了你的帖子：“今天天气真不好！”',
                    time: '2023-01-06 09:30',
                },
                // 添加更多点赞通知...
            ],
        };
    },
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
