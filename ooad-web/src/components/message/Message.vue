<template>
    <div :style="{ background: dynamicBackground }"  class="app-container">
        <div class="menu-container">
            <div
                class="menu-item"
                v-for="(item, index) in menu"
                :key="index"
                :class="{ 'active-menu': $route.path === '/' + item.menuClick }"
                @click="navigate(item.menuClick)"
            >
                <i :class="item.icon"></i> {{ $t(item.menuName) }}
            </div>
            <div class="menu-item" @click="navigateHome">
                <i class="el-icon-house"></i> {{ $t("返回首页") }}
            </div>
<!--            <div class="menu-item" @click="switchLanguage">-->
<!--                {{ $t("切换语言") }}-->
<!--            </div>-->
        </div>
        <div class="content-container">
            <router-view />
        </div>
    </div>
</template>

<script>
export default {
    name: "Index",
    data() {
        return {
            menu: [
                { menuClick: "MyMessage", menuName: "我的消息", icon: "el-icon-message" },
                { menuClick: "Like", menuName: "点赞", icon: "el-icon-thumb" },
                { menuClick: "Comment", menuName: "评论", icon: "el-icon-chat-dot-round" },
                { menuClick: "BroadcastMessages", menuName: "广播通知", icon: "el-icon-service" },
                { menuClick: "TeamInvitation", menuName: "组队邀请", icon: "el-icon-user" },
                { menuClick: "TeamOffer", menuName: "组队申请", icon: "el-icon-suitcase" },
            ],
            dynamicBackground: "", // 添加动态背景样式
        };
    },
    created() {
        this.$message({
            type: 'success',
            message: this.$t('欢迎来到消息中心')
        })
        // 设置默认路由和动态背景
        this.setDefaultRoute();
        this.setDynamicBackground();
    },
    methods: {
        navigate(route) {
            this.$router.push("/" + route);
        },
        navigateHome() {
            this.$router.push("/Home");
        },
        setDefaultRoute() {
            this.$router.push("/MyMessage");
        },
        setDynamicBackground() {
            // 你可以根据需要设置不同的背景样式
            this.dynamicBackground = "linear-gradient(to right, #FAFBFC 0%, #98A594 100%)";
        },
        // switchLanguage() {
        //     // const newLocale = this.$i18n.locale === 'en' ? 'zh' : 'en';
        //     // this.$i18n.locale = newLocale;
        // },
    },
};
</script>

<style scoped>
.app-container {
    display: flex;
    height: 100vh;
//background-image: linear-gradient(to right, #5FC3E4 0%, #E55D87 100%);
}

.menu-container {
    width: 130px;
    height: 100%;
//background: rgba(255, 255, 255, 0.5);
    background: #545c64;
    display: flex;
    flex-direction: column;
}

.menu-item {
    padding: 15px;
    cursor: pointer;
    transition: background-color 0.3s;
    font-size: 14px;
    color: white;
    display: flex;
    align-items: center;
}

.menu-item:hover {
    background-color: rgba(255, 255, 255, 0.8);
}

.active-menu {
    color: #ffd04b; /* 设置激活菜单的文字颜色 */
}

.menu-item i {
    margin-right: 8px;
}
.content-container {
    flex: 1;
    height: 100%;
    overflow: auto;
    padding: 20px;
}
</style>
