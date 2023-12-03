<script>
export default {
    name:"MyPrefer",
    methods: {
        handleSelect(index) {
            this.currentRoute = index; // 更新选中的路由
            this.$router.push(this.currentRoute)
        },
    },
    data() {
        return {
            currentRoute: '/MyPrefer', // 设置默认选中的路由
        }
    }
}
</script>

<template>
    <div>
        <el-menu :default-active="this.currentRoute" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="/Home" style="font-family: arial, sans-serif">个人信息</el-menu-item>
            <el-menu-item index="/MyPrefer" style="font-family: arial, sans-serif">我的收藏</el-menu-item>
        </el-menu>
        <div style="margin-top: 20px"></div>
        <el-row>
            <el-col :span="4" style="margin-left: 6px">
                <el-card :body-style="{ padding: '0px' }">
                    <img src="../../assets/img1.jpg" class="image">
                    <div style="padding: 14px;">
                        <span>湖畔3栋-2楼-222</span>
                        <div class="bottom clearfix">
                            <el-row>
                                <el-button type="text" class="button" style="margin-left: 7px;">查看详情</el-button>
                                <el-button type="text" class="button">取消收藏</el-button>
                            </el-row>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="4" style="margin-left: 6px">
                <el-card :body-style="{ padding: '0px' }">
                    <img src="../../assets/img2.jpg" class="image">
                    <div style="padding: 14px;">
                        <span>湖畔1栋-3楼-333</span>
                        <div class="bottom clearfix">
                            <el-button type="text" class="button" style="margin-left: 7px;">查看详情</el-button>
                            <el-button type="text" class="button">取消收藏</el-button>
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