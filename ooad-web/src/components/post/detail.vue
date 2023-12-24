<template>
    <div class="content">
        <h2 class="title">{{ title }}</h2>
        <div style="display: flex;align-items: center;justify-content: flex-end;">
            <!--            <img class="icon" style="margin-right: 20px;" src="../../assets/guanzhu.png" alt="">-->
            <!-- <img class="icon" src="../../assets/guanzhu1.png" alt=""> -->
            <!--            <img class="icon" src="../../assets/jiaru.png" alt="">-->
        </div>
        <div class="box" v-html="content"></div>

        <div>
            <div class="comment">评论</div>
            <div class="message">
                <div>
                    <el-avatar :src="'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'"
                               :size="40"></el-avatar>
                </div>
                <div class="messageipt">
                    <el-input placeholder="请输入" v-model="context" class="input" type="textarea" resize="none" size="mini"
                              :maxlength="contentLength"></el-input>
                </div>
                <el-button type="info" @click="addReply()">留言</el-button>
            </div>
        </div>



        <div class="reply">
            <div class="comment" @click="addReply()">回复</div>

            <div>
                <div class="replybox" v-for="(item,index) in list" :key="index">
                    <div class="avatar">
                        <el-avatar :src="'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'"
                                   :size="40"></el-avatar>
                    </div>
                    <div class="s">
                        回复：{{ item.content }}
                    </div>
                </div>
                <!-- <div class="replyicon">
                    <svg class="icon" aria-hidden="true" style="color: blueviolet; margin-right: 24px;">
                        <use xlink:href="#icon-31dianzan"></use>
                    </svg>

                    <svg class="icon" aria-hidden="true" style="color: blueviolet;">
                        <use xlink:href="#icon-31pinglun"></use>
                    </svg>
                </div> -->

            </div>

        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            content: '',
            context: '',
            title: '',
            contentLength: 100,
            type: 1,
            list:[]
        }
    },
    created(data) {
        console.log(data);
        // this.type = data.type ? data.type : 1;
        this.getpost()
        this.getreply()
    },
    methods: {
        // /获取帖子评论列表
        getreply() {
            if (this.type == 1) {
                this.$axios.get(this.$httpUrl + `/commentPersonPost/post/${this.$router.history.current.query.id}`, {}, {
                    withCredentials: true // 允许跨域请求中的Cookie
                }).then(res => {
                    console.log(res.data)
                    this.list = res.data.data
                })

            } else {
                this.$axios.get(this.$httpUrl + `/commentTeamPost/post/${this.$router.history.current.query.id}`, {}, {
                    withCredentials: true // 允许跨域请求中的Cookie
                }).then(res => {
                    console.log(res.data,"团队")
                })
            }
        },
        // 添加帖子评论
        addReply() {
            console.log("cesi");
            this.$axios.post(this.$httpUrl + `/commentPersonPost`, { personID: 1, postId: 1, content: this.content }, {
                withCredentials: true, // 允许跨域请求中的Cookie
                "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
            }).then(res => {
                console.log(res)
                this.getreply()
            })
        },

        getpost(data) {
            //
            console.log(this.$router.history.current.query);
            if (this.type == 1) {
                this.$axios.get(this.$httpUrl + `/personPost/user/${this.$router.history.current.query.id}`, {},{
                    withCredentials: true, // 允许跨域请求中的Cookie
                    "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
                }).then(res => {
                    console.log(res.data.data[0], "帖子2");
                    if (res.data.data.length > 0 && res.data.code == 2011) {

                        this.$message.error(res.data.msg);
                        return
                    }
                    // console.log(this.data.data[0]);
                    this.title = res.data.data[0].title;
                    this.content = res.data.data[0].content;
                    this.time = res.data.data[0].time;


                })
            } else {
                this.$axios.get(this.$httpUrl + `/teamPost/user/${this.$router.history.current.query.id}`, {}, {
                    withCredentials: true, // 允许跨域请求中的Cookie
                    "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
                }).then(res => {
                    console.log(res.data, "帖子1");
                    this.title = res.data.data[0].title;
                    this.content = res.data.data.content;
                    this.time = res.data.data.time;


                })
            }


        },

        // 入团
        addteam(){
            // if(this.type == 1){
            //
            // }
        }
    }
}
</script>

<style scoped>
.icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
}

.content {
    background-color: #ffffff;
    width: 680px;
    margin: auto;
}

.title {
    margin: 20px 0;
    text-align: center;
}

.box {
    min-height: 700px;
    /* background-color: black; */
}

.comment {
    margin: 12px;
    position: relative;
    margin-left: 20px;
}

.comment::before {
    display: inline-block;
    content: '';
    width: 2px;
    height: 20px;
    background: #a0c90d;
    position: absolute;
    left: -20px;

}

.message {
    display: flex;
}

.messageipt {
    flex: 1;
    margin: 0 12px;
}

.reply {
    margin-top: 30px;
    padding-bottom: 30px;
}

.replybox {
    display: flex;
    align-items: center;
}

.avatar {
    margin-right: 12px;
}

.replyicon {
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
}
</style>