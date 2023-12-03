<script>
import emojis from "@/assets/emoji";
import '@fortawesome/fontawesome-free/css/all.css';

let client
export default {
    name:"MyMessage",
    components:{
      emojis
    },
    methods:{
        load(){
            this.$axios.get(this.$httpUrl+'/chat/listSingle?fromUser='+this.fromuser+'&toUser='+this.touser).then(res=>{
                if (res.data.code===2010) {
                    this.messages = res.data.data
                } else {
                    // 登录失败，可以显示错误消息
                }
                this.loadUnReadNums()
                this.scrollToBottom()
            })
            this.$axios.get(this.$httpUrl+'/chat/listGroup?toUser='+this.touser).then(res=>{
                if (res.data.code===2010) {
                    this.groupMessages = res.data.data

                } else {
                    // 登录失败，可以显示错误消息
                }
                this.loadUnReadNums()
                this.scrollToBottom()
            })
        },
        loadUserList(){  //开启过聊天的人
            this.$axios.get(this.$httpUrl+'/chat/getUserList?fromUser='+this.fromuser).then(res=>{
                if (res.data.code===2010) {
                    this.users = res.data.data

                } else {
                    // 登录失败，可以显示错误消息
                }
            })
        },
        loadGroupList() {//加入的群
            this.$axios.get(this.$httpUrl+'/chat/getGroupList?fromUser='+this.fromuser).then(res=>{
                if (res.data.code===2010) {
                    this.groups = res.data.data

                } else {
                    // 登录失败，可以显示错误消息
                }
            })
        },
        selectToUser(item){
            this.touser = item.id
            this.touserName = item.name
            this.toAvatar = item.avatar
            this.load()//查询聊天记录
            this.setSingleReaded()
        },
        selectToGroup(item){
            this.touser = item
            this.touserName = ''
            this.load()//查询聊天记录
        },
        clickEmoji(emoji){
            document.getElementById('im-content').innerHTML += emoji
        },
        send() {
            if(!this.touser) {
                this.$notify.error('请选择用户')
                return
            }
            if(client) {
                let message = this.getMessage('text')
                client.send(JSON.stringify(message))

            }
            document.getElementById('im-content').innerHTML=''//清空输入框
        },
        scrollToBottom() {
            this.$nextTick(() => {
                //设置聊天滚动条到底部
                let imMessageBox = document.getElementsByClassName("im-message-box")[0]
                imMessageBox.scrollTop = imMessageBox.scrollHeight
                console.log('触发滚动')
            })
        },
        setSingleReaded() {
            this.$axios.get(this.$httpUrl+'/chat/setSingleReaded?fromUser='+this.fromuser+'&toUser='+this.touser).then(res=>{
                this.loadUnReadNums()
            })
        },
        loadUnReadNums() {
           // 未读消息数量
            this.$axios.get(this.$httpUrl+'/chat/unReadNums?toUser='+this.fromuser).then(res=>{
                if (res.data.code===2010) {
                    this.unRead = res.data.data
                    console.log(res.data.data);
                } else {
                    // 登录失败，可以显示错误消息
                    console.log(res.data.msg);
                }
            })
        },
        getMessage(type){  //聊天框中的内容一旦发送就封装成一个Message
            let inputBox = document.getElementById('im-content')
            const content = inputBox.innerHTML
            if(!content && type === 'text') {
                this.$notify.error('请输入聊天内容')
                return
            }
            return {
                content: content,
                fromuser: this.fromuser,
                touser: this.touser,
                fromuserName: this.fromuserName,
                touserName: this.touserName,
                fromavatar: this.user.avatar,
                toavatar: this.toAvatar,
                time: null,
                type: 'text',
                readed: 0,
                isGroup: this.touserName === ''? 1:0
            }
        },
        extractContent(inputString) {
            // 使用正则表达式匹配最后一个括号以前的内容
            const match = inputString.match(/^(.*)\([^)]*\)$/);

            // 如果匹配成功，返回匹配的内容；否则返回原始字符串
            return match ? match[1].trim() : inputString.trim();
        },

    },
    data(){
        return {
            groups:[],
            users:[],
            user:JSON.parse(sessionStorage.getItem('CurUser')),
            fromuser:'',

            touser:'', //群组名或者聊天对象
            touserName: '',//私聊对象名称
            fromuserName:'',
            toAvatar:'',
            emojis:[],
            unRead:{},
            messages:[],
            groupMessages:[],
            groupAvatar:' https://via.placeholder.com/150/2ecc71/ffffff?text=Group',
        }
    },
    mounted() {
        this.user = JSON.parse(localStorage.getItem('CurUser'))
        this.fromuser = this.user.id
        this.fromuserName = this.user.name
        this.emojis = emojis.split(' ')
        this.loadUserList()
        this.loadGroupList()
        this.load()

        client = new WebSocket('ws://localhost:8090/chatSever')
        client.onopen = () => {
            console.log('websocket open')
        }
        client.onclose = () => { //页面刷新和后台服务器关闭的时候
            console.log('websocket close')
        }
        client.onmessage = (msg) => {
            if(msg.data) {
                let json = JSON.parse(msg.data)
                if(json.content && (json.fromuser === this.fromuser && json.touser === this.touser)
                    || json.touser === this.fromuser && json.fromuser === this.touser) {
                    // this.messages.push(json)
                    // this.scrollToBottom()
                }
                //加载消息数字
                if((this.touser === json.fromuser&&json.isGroup === 0)||(this.touser === json.touser&&json.isGroup === 1)) {
                    this.setSingleReaded()
                } else {
                    this.loadUnReadNums()
                }
                this.load()
                this.scrollToBottom()
            }
        }
    },
    beforeDestroy() {
        if(client){
            client.close()
        }
    }
}
</script>

<template>
    <div>
        <el-container>
            <!-- 头部 -->
            <el-header style="background-color: #3384d5; color: #fff; padding: 20px;margin-left: -10px; margin-top: -10px;">
                <h2 style="text-align: center">我的消息</h2>
            </el-header>

            <!-- 内容区域 -->
            <el-main style="padding: 20px;">
                <el-card shadow="hover" style="width:900px; height: 680px; margin-top: 5px;margin-left: 160px;">
                    <div class="main-body-content">
                        <div style="display: flex; align-items: flex-start; font-size: 13px">
                            <div style="width: 200px; border: 1px solid #b4b4b4; border-radius: 5px; height: 600px;display: flex; flex-direction: column; ">
                                <div style="padding: 10px 10px; border-bottom: 1px solid #b4b4b4; background-color: #eee; font-weight: bold;">列表</div>
                                <div class="user-list-box" style="overflow-y: auto; flex: 1;">
                                    <div class="user-list-item" v-for="item in users" @click="selectToUser(item)">
                                        <img :src="item.avatar" style="width: 25px; height: 25px; border-radius: 50%; padding: 5px 5px;" alt="">
                                        <span style="margin-top: 10px;margin-left: 3px" :style="{color: item.id === touser ? '#3a8ee6' : ''}">{{item.name}}</span>
                                        <div class="user-list-item-badge" style="text-align: center; margin-top: 10px; margin-left: 15px" v-if="unRead?.[item.id]">{{unRead?.[item.id]}}</div>
                                    </div>
                                    <div class="user-list-item" v-for="item in groups" @click="selectToGroup(item)">
                                        <img :src="groupAvatar" style="width: 25px; height: 25px; border-radius: 50%; padding: 5px 5px;" alt="">
                                        <span style="margin-top: 10px;margin-left: 3px" :style="{color: item === touser ? '#3a8ee6' : ''}">{{extractContent(item)}}</span>
                                        <div class="user-list-item-badge" style="text-align: center; margin-top: 10px; margin-left: 15px" v-if="unRead?.[item]">{{unRead?.[item]}}</div>
                                    </div>
                                </div>
                            </div>

                            <!--            聊天-->
                            <div style="width: 80%; height: 600px; border: 1px solid #b4b4b4; border-radius: 5px; background-color: #f1f1f1; margin-left: 10px; display: flex; flex-direction: column;">
                                <div style="padding: 20px 0; text-align: center; border-bottom: 1px solid #b4b4b4; background-color: #eee; height: 10px;">
                                    {{ this.touser+' '+this.touserName+'' }}
                                </div>
                                <div class="im-message-box" style="flex: 1; display: flex; flex-direction: column; overflow-y: auto; padding: 0">
                                    <div style="padding: 15px 15px 15px 15px">
                                        <div v-for="item in messages" :key="item.id">
                                            <!-- 右边的聊天气泡 -->
                                            <div v-if="item.fromuser == fromuser" style="display: flex; justify-content: flex-end; align-self: flex-end">
                                                <div class="im-message" style="margin-right: 7px;background-color: #219328; color: #1e1c1c; margin-top: 4px" v-html="item.content" v-if="item.type === 'text'"></div>
                                                <img :src="item.fromavatar" alt="" style="width: 40px; height: 40px; border-radius: 50%; ">
                                            </div>

                                            <!--左边的聊天气泡-->
                                            <div style="display: flex; justify-content: flex-start;" v-else>
                                                <img :src="item.fromavatar" alt="" style="width: 40px; height: 40px; border-radius: 50%;">
                                                <div>
                                                    <div style="margin-left: 10px; color: #817f7f">{{  item.fromuserName }}</div>
                                                    <div class="im-message" style="margin-left: 7px;background-color: #f8f6f6; color: #1e1c1c; max-width: 450px" v-html="item.content" v-if="item.type === 'text'"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div v-for="item in groupMessages" v-if="item.touser === touser" :key="item.id">
                                            <!-- 右边的聊天气泡 -->
                                            <div v-if="item.fromuser == fromuser" style="display: flex; justify-content: flex-end; align-self: flex-end">
                                                <div class="im-message" style="margin-right: 7px;background-color: #219328; color: #1e1c1c; margin-top: 4px" v-html="item.content" v-if="item.type === 'text'"></div>
                                                <img :src="item.fromavatar" alt="" style="width: 40px; height: 40px; border-radius: 50%; ">
                                            </div>

                                            <!--左边的聊天气泡-->
                                            <div style="display: flex; justify-content: flex-start;" v-else>
                                                <img :src="item.fromavatar" alt="" style="width: 40px; height: 40px; border-radius: 50%;">
                                                <div>
                                                    <div style="margin-left: 10px; color: #817f7f">{{item.fromuser}} {{item.fromuserName}}</div>
                                                    <div class="im-message" style="margin-left: 7px;background-color: #f8f6f6; color: #1e1c1c; max-width: 450px" v-html="item.content" v-if="item.type === 'text'"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--            输入区域-->
                                <!-- 输入区域 -->
                                <div style="display: flex; align-items: center; border-top: 1px solid #b4b4b4; margin-top: auto; padding: 10px; background-color: #f1f1f1;">
                                    <!-- 笑脸表情 -->
                                    <el-popover placement="top" width="300" trigger="click">
                                        <div class="emoji-box" style="display: flex; align-items: center; flex-wrap: wrap;">
                                            <span v-for="(item, index) in emojis" :key="index" style="margin-right: 5px" v-html="item" @click="clickEmoji(item)"></span>
                                        </div>
                                        <i slot="reference" class="fa fa-smile" style="margin-right: 10px; color: #9f9f9f; font-size: 16px;"></i>
                                    </el-popover>
                                    <!-- 输入框 -->
                                    <div id="im-content" contenteditable style="flex: 1; border: 1px solid #ddd; border-radius: 5px; padding: 5px; margin-right: 10px; background-color: white"></div>
                                    <!-- 发送按钮 -->
                                    <el-button type="primary" @click="send">发送</el-button>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<style scoped>
.im-message-box {
    padding: 10px;
    overflow-y: auto;
    background-color: white;
}
.main-body-content {
    margin-top: 20px;
}
.user-list-item {
    border-bottom: 1px solid #e8e5e5;
    height: 35px;
    display: flex;
}
.im-message {
    border-radius: 5px;
    padding: 8px;
    margin-bottom: 5px;
    max-width: 60%; /* 控制气泡最大宽度 */
}
.user-list-item-badge {
    background-color: #ff0000; /* 设置背景颜色 */
    color: #ffffff; /* 设置文本颜色 */
    height: 15px;
    width: 15px;
    border-radius: 50%; /* 设置圆角 */
    font-size: 12px; /* 设置字体大小 */
}
</style>