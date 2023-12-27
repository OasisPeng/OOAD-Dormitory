<script>
export default {
    name:"MyPrefer",
    methods: {
        handleSelect(index) {
            this.currentRoute = index; // 更新选中的路由
            this.$router.push(this.currentRoute)
        },
        //在这里实现的是显示个人的收藏而不是团队的收藏
        getAllFavoriteDorms(){
            this.$axios.get(this.$httpUrl+'/favouriteDorm/user?id='+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.favoriteDorms = res.data.data
                    console.log(this.favoriteDorms)
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        //取消收藏
        cancelFav(dorm) {
            const requestData = {
                personId: this.user.id,
                dormId: dorm.id,
                teamId: this.user.teamId
            };

            // 发起DELETE请求
            this.$axios
                .delete(this.$httpUrl + '/favouriteDorm', {
                    data: requestData,  // 使用data字段传递请求体
                    withCredentials: true,
                    headers: {
                        'Authorization': 'Bearer ' + JSON.parse(sessionStorage.getItem('CurUser')).token
                    }
                })
                .then(res => {
                    if (res.data.code === 2030) {
                        // 取消收藏成功的处理逻辑
                        this.$message({
                            type: "success",
                            message: "取消成功",
                        });
                        this.getAllFavoriteDorms()
                    } else {
                        console.log(requestData)
                        console.log(res.data.msg);
                    }
                })
                .catch(error => {
                    // 错误处理逻辑
                    console.error('取消收藏失败', error);
                });
        },

        getImageUrl(dorm){
            if (dorm.distribution === '湖畔'){
                return require('@/assets/hupan.jpg');
            }
            if (dorm.distribution === '二期'){
                return require('@/assets/erqi.png');
            }
            if (dorm.distribution === '欣园'){
                return require('@/assets/xinyuan.png');
            }
            if (dorm.distribution === '荔园'){
                return require('@/assets/liyuan.png');
            }
        }
    },
    data() {
        return {
            currentRoute: '/MyPrefer', // 设置默认选中的路由
            favoriteDorms: [] ,
            user: JSON.parse(sessionStorage.getItem('UserData'))

        }
    },
    created() {
        this.getAllFavoriteDorms()
    }
}
</script>

<template>
    <div>
        <el-menu :default-active="this.currentRoute" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="/Home" style="font-family: arial, sans-serif">{{$t('个人信息')}}</el-menu-item>
            <el-menu-item index="/MyPrefer" style="font-family: arial, sans-serif">{{$t('我的收藏')}}</el-menu-item>
        </el-menu>
        <div style="margin-top: 20px"></div>
        <el-row>
            <el-col :span="4" v-for="(dorm, index) in favoriteDorms" :key="index" style="margin-left: 6px">
                <el-card :body-style="{ padding: '0px' }">
                    <!-- 假设每个元素有一个distribution、building和room属性 -->
                    <img :src="getImageUrl(dorm)" class="image">
                    <div style="padding: 14px;">
                        <!-- 使用插值语法显示动态数据 -->
                        <span>{{ dorm.distribution + '-' + dorm.building + '-' + dorm.room }}</span>
                        <div class="bottom clearfix">
                            <el-row>
                                <el-popover
                                    placement="bottom"
                                    title=""
                                    width="200"
                                    trigger="click"
                                    content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
                                    <el-button slot="reference" type="text" class="button" style="margin-left: 7px;">{{$t('查看详情')}}</el-button>
                                    <!-- 自定义内容 -->
                                    <div>
                                        <p>寝室状态:
                                            <el-tag
                                            :type="dorm.available === '是'?'success':'danger'"
                                            disable-transitions>
                                                {{ dorm.available === '是'? '空闲':'已被占用'}}
                                            </el-tag>
                                        </p>
                                        <p>该寝室已被收藏 {{ dorm.favourite }} 次</p>
                                    </div>
                                </el-popover>
                                <el-button type="text" class="button" @click="cancelFav(dorm)">{{$t('取消收藏')}}</el-button>
                            </el-row>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<style scoped>

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.image {
    width: 100%;
    height: 170px;
    display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>