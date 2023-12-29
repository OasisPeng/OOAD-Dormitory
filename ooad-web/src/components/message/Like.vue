<template>
    <div>
        <el-container>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <!-- 通知列表 -->
                <el-card v-for="(like, index) in likes1.concat(likes2)" :key="index" style="margin-bottom: 20px;">
                    <!-- 点赞用户信息 -->
                    <div style="display: flex; align-items: center; margin-bottom: 10px;">
                        <img :src="defaultAvatar" alt="User Avatar" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                        <span>{{like.likeById}}{{ like.likeByName }}</span>
                    </div>

                    <!-- 点赞内容 -->
                    <div style="font-size: 16px">
                        <i class="el-icon-thumb"></i> <!-- Element UI 的点赞图标 -->
                        赞了你 标题为 '{{ like.postTitle }}' 的帖子
                    </div>

<!--                    &lt;!&ndash; 点赞时间 &ndash;&gt;-->
<!--                    <div style="margin-top: 10px; color: #888;">{{ like.time }}</div>-->
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
            defaultAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            likes1: [],
            likes2:[]
        };
    },
    methods: {
        getAllLike(){
            this.$axios.get(this.$httpUrl+'/favouritePersonPost/like/'+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.likes1 = res.data.data
                    console.log(this.likes1)
                } else {
                    console.log(res.data.msg)
                }

            })
            this.$axios.get(this.$httpUrl+'/favouriteTeamPost/like/'+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.likes2 = res.data.data
                    console.log(this.likes2)
                } else {
                    console.log(res.data.msg)
                }

            })
        }
    },
    mounted() {
        this.getAllLike()
    }
};
</script>

<style>
/* 可以添加自定义的样式 */
</style>
