<template>
    <div>
        <el-container>
            <!-- 头部 -->
            <el-header style="background-color: #3384d5; color: #fff; padding: 20px;margin-left: -10px; margin-top: -10px;">
                <h2 style="text-align: center">评论通知</h2>
            </el-header>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(comment, index) in comments" :key="index" style="margin-bottom: 20px;">
                    <!-- 评论用户信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="comment.avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{ comment.username }}</span>
                    </div>

                    <!-- 评论内容 -->
                    <div style="font-size: 16px">
                        <i class="el-icon-chat-dot-round"></i> <!-- Element UI 的评论图标 -->
                        {{ comment.content }}
                    </div>

                    <!-- 评论时间 -->
                    <div style="margin-top: 10px; color: #888;">{{ comment.time }}</div>

                    <!-- 如果是回复评论，显示被回复用户信息 -->
                    <div v-if="comment.replyTo">
                        <div style="margin-top: 10px; color: #666;">回复了 你 的评论：</div>
                        <div>{{ comment.replyToContent }}</div>
                    </div>

                    <div v-if="comment.comment">
                        <div style="margin-top: 10px; color: #666;">评论了 你 的帖子：</div>
                        <div>{{ comment.commentToContent }}</div>
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
            comments: [
                {
                    username: 'John Doe',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    content: '好文章，写得非常好。',
                    time: '2023-01-05 12:15',
                    comment: 1,
                    commentToContent: '我认为...',
                },
                {
                    username: 'Alice Smith',
                    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                    content: '这是一个很棒的观点。',
                    time: '2023-01-06 09:30',
                    replyTo: 1,
                    replyToContent: '这间宿舍很不错。'
                },
                // 添加更多评论通知...
            ],
        };
    },
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
