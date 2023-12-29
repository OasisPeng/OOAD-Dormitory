<!--<script setup>-->
<!--import axios from 'axios';-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      allStudents:[],-->

<!--    };-->
<!--  },-->
<!--  created() {-->
<!--    this.getAllStudents();-->

<!--  },-->
<!--  methods:{-->
<!--    async getAllStudents() {-->
<!--      try {-->
<!--        const response = await axios.get(this.$httpUrl + '/user');-->
<!--        if (response.code == 2010) {-->
<!--          console.log('查询成功');-->
<!--          this.allStudents = response.data.data;-->
<!--          console.log(this.allStudents)-->
<!--        } else {-->
<!--          console.error('查询失败：', response.data.msg);-->
<!--        }-->
<!--      } catch (error) {-->
<!--        console.error('请求失败：', error);-->
<!--        this.$message.error(response.data.msg);-->
<!--      }-->
<!--    },-->

<!--  }-->

<!--}-->

<!--</script>-->

<!--<template>-->
<!--    <el-table>-->


<!--    </el-table>-->
<!--</template>-->

<!--<style scoped>-->

<!--</style>-->