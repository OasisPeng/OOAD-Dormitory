<template>
    <div>
        <el-container>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(comment, index) in comments1.concat(comments2)" :key="index" style="margin-bottom: 20px;">
                    <!-- 评论用户信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="avatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{comment.userId}}{{ comment.userName }}</span>
                    </div>

                    <!-- 评论内容 -->
                    <div style="font-size: 16px">
                        <i class="el-icon-chat-dot-round"></i> <!-- Element UI 的评论图标 -->
                        {{ comment.content }}
                    </div>

<!--                    &lt;!&ndash; 评论时间 &ndash;&gt;-->
<!--                    <div style="margin-top: 10px; color: #888;">{{ comment.time }}</div>-->

<!--                    &lt;!&ndash; 如果是回复评论，显示被回复用户信息 &ndash;&gt;-->
<!--                    <div v-if="comment.replyTo">-->
<!--                        <div style="margin-top: 10px; color: #666;">回复了 你 的评论：</div>-->
<!--                        <div>{{ comment.replyToContent }}</div>-->
<!--                    </div>-->

                    <div>
                        <div style="margin-top: 10px; color: #666;">评论了 你 的帖子：</div>
                        <div>{{ comment.postTitle }}</div>
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
            avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            user: JSON.parse(sessionStorage.getItem('UserData')),
            comments1: [
                // {
                //     username: 'John Doe',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     content: '好文章，写得非常好。',
                //     time: '2023-01-05 12:15',
                //     comment: 1,
                //     commentToContent: '我认为...',
                // },
                // {
                //     username: 'Alice Smith',
                //     avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', // 请替换为实际的用户头像路径
                //     content: '这是一个很棒的观点。',
                //     time: '2023-01-06 09:30',
                //     replyTo: 1,
                //     replyToContent: '这间宿舍很不错。'
                // },
                // 添加更多评论通知...
            ],
            comments2: []
        };
    },
    methods:{
        getAllComments(){
            this.$axios.get(this.$httpUrl+'/commentPersonPost/comment/'+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.comments1 = res.data.data
                } else {
                    console.log(res.data.msg)
                }

            })
            this.$axios.get(this.$httpUrl+'/commentTeamPost/comment/'+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.comments2 = res.data.data
                } else {
                    console.log(res.data.msg)
                }

            })
        }
    },
    mounted() {
        this.getAllComments()
    }
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
