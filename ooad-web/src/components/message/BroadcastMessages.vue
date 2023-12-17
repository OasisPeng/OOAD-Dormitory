<template>
    <div>
        <el-container>
            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(notification, index) in notifications" :key="index" style="margin-bottom: 20px;">
                    <!-- 通知标题 -->
                    <div style="font-size: 15px; font-weight: bold; margin-bottom: 10px;">{{ notification.title }}</div>
                    <!-- 通知内容 -->
                    <div style="font-size: 16px">{{ notification.content }}</div>
                    <!-- 通知时间 -->
                    <div style="margin-top: 10px; color: #888;">{{ notification.time }}</div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            notifications: [
                {
                    title: '日期公布',
                    content: '宿舍申请将在2023/07/01~2023/08/30之间进行，请同学们做好准备。',
                    time: '2023-01-03 15:45',
                },
                {
                    title: '系统更新',
                    content: '系统将于明天进行更新，可能会影响部分功能，请提前做好准备。',
                    time: '2023-01-02 15:45',
                },
                {
                    title: '重要通知',
                    content: '这是一个非常重要的系统通知，务必仔细阅读。',
                    time: '2023-01-01 10:30',
                },
                // 添加更多通知...
            ],
        };
    },
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
