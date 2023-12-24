<script>

 export default {
     name: "Aside",
     data(){
         return{
            // isCollapse:false,
             user:JSON.parse(sessionStorage.getItem('CurUser')),
             menu:[
                 {
                     menuClick:'DormitoryView',
                     menuName:'寝室查看',
                     menuIcon:'el-icon-menu'
                 },
                 {
                     menuClick:'MyRoommates',
                     menuName:'我的组队',
                     menuIcon:'el-icon-s-custom'
                 },
                 {
                   menuClick:'OtherRoommates',
                   menuName:'查看组队',
                   menuIcon:'el-icon-s-custom'
                 },
                 {
                     menuClick:'DormitoryPropose',
                     menuName:'寝室申请',
                     menuIcon:'el-icon-s-release'
                 },
                 {
                     menuClick:'Post',
                     menuName:'帖子广场',
                     menuIcon:'el-icon-s-promotion'
                 }
             ]
         }
     },
     computed:{
       // "menu":{
       //     get(){//将菜单状态从store中取出来
       //         console.log(this.$store.state.menu)
       //         return this.$store.state.menu
       //     }
       // }

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
            default-active=/Home
            :collapse="isCollapse"
            :collapse-transition="false"
            router
        >
            <el-menu-item index="/Home">
                <i class="el-icon-s-home"></i>
                <span slot="title">{{ $t('个人中心') }}</span>
            </el-menu-item>

            <el-menu-item :index="'/'+item.menuClick" v-for="(item,i) in menu" :key="i">
                <i :class="item.menuIcon"></i>
                <span slot="title">{{ $t(item.menuName) }}</span>
            </el-menu-item>

        </el-menu>
</template>

<style scoped>

</style>