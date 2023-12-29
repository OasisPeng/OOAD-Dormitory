<template>
  <div>
    <div class="fixed" @click="previous()">
      <svg t="1703511619340" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2146" width="200" height="200"><path d="M512.105912 80.83812c-237.365082 0-429.779394 192.56883-429.779394 430.134481 0 237.564627 192.414311 430.132434 429.779394 430.132434 237.387595 0 429.801906-192.567807 429.801906-430.132434C941.907818 273.40695 749.494531 80.83812 512.105912 80.83812zM605.570607 696.710862c10.212596 10.166547 11.227716 25.591865 2.321882 34.477232-8.92937 8.885368-24.465205 7.844665-34.675754-2.297322l-199.88547-197.651592c-5.79089-5.7694-8.53028-13.19451-8.266267-20.180622-0.309038-7.048533 2.432399-14.541181 8.245801-20.35356l199.905936-197.651592c10.210549-10.166547 25.724895-11.183713 34.675754-2.298346 8.905834 8.884344 7.891737 24.311709-2.321882 34.477232L417.64554 510.972601 605.570607 696.710862z" fill="#5E5E5E" p-id="2147"></path></svg>
    </div>
    <div class="content">

      <div style="display: flex;align-content: center;justify-content: center;align-items: center">
        <h2 class="title" style="margin-right: 10px">{{ title }}</h2>
        <div>发帖人：{{user.name}}</div>
      </div>



      <div class="box" v-html="content"></div>
      <div style="display: flex;align-items: center;justify-content: flex-end;">

        <div @click="getFans()">
          <svg v-if="isFans"  style="width: 20px;height: 20px" t="1703596795086" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2393" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z" fill="#000000" p-id="2394"></path></svg>
          <svg v-if="!isFans" style="width: 20px;height: 20px" t="1703596336496" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2102" width="200" height="200"><path d="M857.28 344.992h-264.832c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-71.808-153.792-140.544-143.808-60.608 8.8-89.536 59.904-89.536 125.536v59.296c0 76.064-58.208 140.928-132.224 148.064l-117.728-0.192A67.36 67.36 0 0 0 64 483.04V872c0 37.216 30.144 67.36 67.36 67.36h652.192a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824zM128 872V483.04c0-1.856 1.504-3.36 3.36-3.36H208v395.68H131.36A3.36 3.36 0 0 1 128 872z m767.328-417.088l-73.728 388.96a38.72 38.72 0 0 1-38.048 31.488H272V476.864a213.312 213.312 0 0 0 173.312-209.088V208.512c0-37.568 12.064-58.912 34.72-62.176 27.04-3.936 67.36 38.336 67.36 80.48 0 37.312-9.504 84-28.864 139.712a32 32 0 0 0 30.24 42.496h308.512a38.72 38.72 0 0 1 38.048 45.888z" p-id="2103"></path></svg>
        </div>
      </div>
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
            <div class="info">
              <div>{{item.personId}}</div>
              <div class="s">
                回复：{{ item.content }}
              </div>
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

  </div>
</template>
<script>
import router from "@/router";

export default {
  data() {
    return {
      content: '',
      context: '',
      title: '',
      contentLength: 100,
      type: 1,
      list:[],
      user:{},
      isFans:false
    }
  },
  created(data) {
    console.log(data);
    // this.type = data.type ? data.type : 1;
    this.getpost()
    this.getreply()
  },
  methods: {
    // 获取用户信息
    getUser(id){
      this.$axios.get(this.$httpUrl + `/user/${id}`, {}, {
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res => {
        console.log(res.data)
        this.user = res.data.data
      })


    },
    // 点赞
    getFans(){
      let data = {
        postid:this.$router.history.current.query.id,
        personid:JSON.parse(localStorage.getItem("CurUser")).id

      }
      if (this.$router.history.current.query.type == 1) {
        this.$axios.get(this.$httpUrl + `/favouritePersonPost`, data,{
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          this.isFans = !this.isFans
        })
      } else {
        this.$axios.get(this.$httpUrl + `/favouriteTeamPost`, data, {
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          this.isFans = !this.isFans
        })
      }
    },

    // /获取帖子评论列表
    getreply() {
      if (this.$router.history.current.query.type == 1) {
        this.$axios.get(this.$httpUrl + `/commentPersonPost/post/${this.$router.history.current.query.id}`, {}, {
          withCredentials: true // 允许跨域请求中的Cookie
        }).then(res => {
          console.log(res.data.data,"评论")
          if(res.data.data == 'Err') return
          this.list = res.data.data
        })

      } else {
        this.$axios.get(this.$httpUrl + `/commentTeamPost/post/${this.$router.history.current.query.id}`, {}, {
          withCredentials: true // 允许跨域请求中的Cookie
        }).then(res => {
          console.log(res.data.data,"团队")
          if(res.data.data == 'Err') return
          this.list = res.data.data
        })
      }
    },
    // 添加帖子评论
    addReply() {
      console.log("cesi");
      if (this.$router.history.current.query.type == 1) {
        this.$axios.post(this.$httpUrl + `/commentPersonPost`, { personId:JSON.parse(localStorage.getItem("CurUser")).id, postId: this.$router.history.current.query.id, content: this.context }, {
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          console.log(res)
          this.context = ''
          this.getreply()
        })
      }else{
        this.$axios.post(this.$httpUrl + `/commentTeamPost`, { postId: this.$router.history.current.query.id,personId:JSON.parse(localStorage.getItem("CurUser")).id, content: this.context }, {
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          console.log(res)
          this.context = ''
          this.getreply()
        })
      }

    },

    getpost(data) {
      //
      let that = this
      console.log(this.$router.history.current.query);
      if (this.$router.history.current.query.type == 1) {
        this.$axios.get(this.$httpUrl + `/personPost/${this.$router.history.current.query.id}`, {},{
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          console.log(res.data.data[0], "帖子2");
          if (res.data.data.length > 0 && res.data.code == 2011) {

            this.$message.error(res.data.msg);
            return
          }
          // console.log(this.data.data[0]);
          this.title = res.data.data.title;
          this.content = res.data.data.content;
          this.time = res.data.data.time;

        if(res.data.data.writerId){
          that.getUser(res.data.data.writerId)
        }


        })
      } else {
        this.$axios.get(this.$httpUrl + `/teamPost/${this.$router.history.current.query.id}`, {}, {
          withCredentials: true, // 允许跨域请求中的Cookie
          "token":"Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token
        }).then(res => {
          console.log(res.data, "帖子1");
          this.title = res.data.data.title;
          this.content = res.data.data.content;
          this.time = res.data.data.time;
          if(res.data.data.writerId){
            that.getUser(res.data.data.writerId)
          }

        })
      }


    },


    // 入团
    addteam(){

    },
    previous(){

      this.$router.push('JoinPost')
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
  min-height: 650px;
  /* background-color: black; */
  border: 1px solid #eee;
  border-radius: 20px;
  padding: 20px;
  box-sizing: border-box;
  margin-bottom: 10px;
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
  padding-bottom: 10px;
  padding-top: 10px;
  border-bottom: 1px solid #eee;
}

.avatar {
  margin-right: 12px;
}

.replyicon {
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
}
.fixed {

//background: red;
  position: absolute;
  top: 15%;
  left: 20%;
  border-radius: 50%;
  text-align: center;
  line-height: 50px;
  font-size: 18px;
  color: #fff;

}
.s {
  color: #1e1c1c;
}
.icon {
  width: 50px;
  height: 50px;
}
.info {
  display: flex;
  flex-direction: column;
  font-size: 16px;
  color: #909090;
}
</style>