<script>

export default {
    name: "AsideAdmin",
    data(){
        return{
            // isCollapse:false,
            menu:[
                {
                    menuClick:'DormitoryManage',
                    menuName:'寝室管理',
                    menuIcon:'el-icon-s-order'
                },
                {
                    menuClick:'StudentManage',
                    menuName:'学生管理',
                    menuIcon:'el-icon-user-solid'
                },
                {
                    menuClick:'Broadcast',
                    menuName:'发送广播',
                    menuIcon:'el-icon-s-promotion'
                },
                {
                    menuClick:'OpenSystem',
                    menuName:'开放系统',
                    menuIcon:'el-icon-s-opportunity'
                },
            ]
        }
    },
    props:{
        isCollapse:Boolean
    }
}
</script>

<template>
    <el-menu
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            style="height: 100%"
            default-active=/DormitoryManage
            :collapse="isCollapse"
            :collapse-transition="false"
            router
    >


        <el-menu-item :index="'/'+item.menuClick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuIcon"></i>
            <span slot="title">{{item.menuName}}</span>
        </el-menu-item>

    </el-menu>
</template>

<style scoped>

</style>