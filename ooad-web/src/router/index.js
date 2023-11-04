import VueRouter  from 'vue-router';

const routes =[
    {
        path:'/',
        name:'login',
        component:()=>import('../components/Login')
    },
    {
        path: '/',
        name: 'index',
        component:()=>import('../components/Index'),
        children:[
            {
                path: '/JoinPost',
                name: 'JoinPost',
                meta: {
                    title: '申请帖子广场'
                },
                component: () => import('../components/post/JoinPost.vue')
            },
            {
                path: '/InvitePost',
                name: 'InvitePost',
                meta: {
                    title: '邀请帖子广场'
                },
                component: () => import('../components/post/InvitePost.vue')
            },
            {
                path: '/Post',
                name: 'AreaPost',
                meta: {
                    title: '区域帖子广场'
                },
                component: () => import('../components/post/AreaPost.vue')
            },
            {
                path: '/DormitoryPropose',
                name: 'DormitoryPropose',
                meta: {
                    title: '寝室申请'
                },
                component: () => import('../components/dormitory/DormitoryPropose.vue')
            },
            {
                path: '/OtherRoommates',
                name: 'OtherRoommates',
                meta: {
                    title: '查看他人组队'
                },
                component: () => import('../components/roommates/OtherRoommates.vue')
            },
            {
                path: '/Roommates',
                name: 'MyRoommates',
                meta: {
                    title: '我的组队'
                },
                component: () => import('../components/roommates/MyRoommates.vue')
            },
            {
                path: '/DormitoryView',
                name: 'dormitoryView',
                meta: {
                    title: '寝室查看'
                },
                component: () => import('../components/dormitory/DormitoryView.vue')
            },
            {
                path: '/DormitoryManage',
                name: 'dormitoryManage',
                meta: {
                    title: '寝室管理'
                },
                component: () => import('../components/Admin/DormitoryManage.vue')
            },
            {
                path: '/Home',
                name: 'home',
                meta: {
                    title: '首页'
                },
                component: () => import('../components/home/Home.vue')
            },
            {
                path: '/MyPrefer',
                name: 'myPrefer',
                meta: {
                    title: '收藏'
                },
                component: () => import('../components/home/MyPrefer.vue')
            },
            {
                path: '/HuPanDormitoryView.vue',
                name: 'huPanDormitoryView.vue',
                meta: {
                    title: '湖畔寝室查看' // 自定义页面标题
                },
                component: () => import('../components/dormitory/HuPanDormitoryView.vue')
            },
            // {
            //     path: '/Login.vue',
            //     name: 'login.vue',
            //     meta: {
            //         title: '湖畔寝室查看' // 自定义页面标题
            //     },
            //     component: () => import('../components/roommates/Login.vue')
            // }

        ]
    },
    {
        path: '/indexAdmin',
        name: 'indexAdmin',
        component:()=>import('../components/IndexAdmin'),
        children:[

            {
                path: '/DormitoryManage',
                name: 'dormitoryManage',
                meta: {
                    title: '寝室管理'
                },
                component: () => import('../components/Admin/DormitoryManage.vue')
            },
            {
                path: '/OpenSystem',
                name: 'openSystem',
                meta: {
                    title: '开放系统'
                },
                component: () => import('../components/Admin/OpenSystem.vue')
            },
            {
                path: '/SendBroadcast',
                name: 'sendBroadcast',
                meta: {
                    title: '发送广播'
                },
                component: () => import('../components/Admin/SendBroadcast.vue')
            },
            {
                path: '/StudentManage',
                name: 'studentManage',
                meta: {
                    title: '学生管理'
                },
                component: () => import('../components/Admin/StudentManage.vue')
            },

        ]
    },


]

const router = new VueRouter({
    mode:'history',
    routes
})

export function resetRouter(){
    router.matcher = new VueRouter({
        mode:'history',
        routes:[]
    }).matcher
}

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push=function push(to){
    return VueRouterPush.call(this,to).catch(err=>err)
}
export default router