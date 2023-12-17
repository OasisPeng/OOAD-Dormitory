

<template>
  <div>
    <el-card class="box-card">
      <div class="btn">
        <el-button type="primary" plain @click="dialogVisible = true">申请</el-button>
        <el-button type="primary">搜索</el-button>
      </div>
    </el-card>

    <el-card class="tab">

      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="title" label="标题">
        </el-table-column>
        <el-table-column label="图片" width="180">
          <template slot-scope="scope">
            <el-image style="width: 100px; height: 100px"
                      src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                      fit="fill"></el-image>

          </template>
        </el-table-column>
        <el-table-column prop="area" label="区域">
        </el-table-column>
        <el-table-column prop="zan" label="点赞">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="text" @click="topage(scope.row)">详情</el-button>
            <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
              删除
            </el-button>

          </template>
        </el-table-column>
      </el-table>
    </el-card>


    <el-dialog title="提示" :visible.sync="dialogVisible" width="40%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="活动名称">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="帖子类型">
          <el-select v-model="form.region" placeholder="请选择帖子类型">
            <el-option label="个人帖子" value="1"></el-option>
            <el-option label=" 帖子" value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="团队信息">
          <el-select v-model="form.userId" filterable placeholder="请选择">
            <el-option v-for="item in timelsit" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="内容">
          <div class="content">
            <quill-editor class="ql-editor" v-model="form.content" ref="myQuillEditor" :options="editorOption"
                          @blur="onEditorBlur($event)" @focus="onEditorFocus($event)" @change="onEditorChange($event)">
            </quill-editor>
          </div>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="sub()">确 定</el-button>
            </span>
    </el-dialog>

  </div>
</template>
<script>
import router from '@/router';
import { quillEditor } from 'vue-quill-editor'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import axios from 'axios';

export default {
  components: {
    quillEditor
  },
  data() {
    return {
      // 富文本编辑器配置
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
            ['blockquote', 'code-block'], // 引用  代码块
            [{ header: 1 }, { header: 2 }], // 1、2 级标题
            [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
            [{ script: 'sub' }, { script: 'super' }], // 上标/下标
            [{ indent: '-1' }, { indent: '+1' }], // 缩进
            [{ direction: 'rtl' }], // 文本方向
            [{ size: ['12px', false, '16px', '18px', '20px', '30px'] }], // 字体大小
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
            [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
            [{ font: [false, 'SimSun', 'SimHei', 'Microsoft-YaHei', 'KaiTi', 'FangSong', 'Arial'] }], // 字体种类
            [{ align: [] }], // 对齐方式
            ['clean'], // 清除文本格式
            ['link', 'image', 'video'] // 链接、图片、视频
          ]
        },
        placeholder: '请输入正文'
      },
      options: [],
      list: [],
      loading: false,
      states: ["Alabama", "Alaska", "Arizona",
        "Arkansas", "California", "Colorado",
        "Connecticut", "Delaware", "Florida",
        "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky",
        "Louisiana", "Maine", "Maryland",
        "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio",
        "Oklahoma", "Oregon", "Pennsylvania",
        "Rhode Island", "South Carolina",
        "South Dakota", "Tennessee", "Texas",
        "Utah", "Vermont", "Virginia",
        "Washington", "West Virginia", "Wisconsin",
        "Wyoming"],

      dialogVisible: false,
      form: {
        title: '',
        content: '',
        region: '1',//帖子类型
        user: '',
        userId: '',//用户id 或 团队id
      },
      tableData: [],
      timelsit: []
    }
  },
  mounted() {
    this.list = this.states.map(item => {
      return { value: `value:${item}`, label: `label:${item}` };
    });
  },
  created() {
    this.gettimes()
    this.getPosts()
  },
  methods: {
    topage(data) {
      console.log(data);
      router.push({ path: '/detail',query: { id: data.id
        }})
    },
    // 获取所有队伍
    gettimes() {
      this.$axios.get(this.$httpUrl + '/teams', {}, {
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res => {

        this.timelsit = res.data.data
        console.log(res.data, this.timelsit)
      })
    },
    // 添加
    sub() {
      let data = {
        title: this.form.title,
        content: this.form.content,
        time: Date.now(),
        // teamId: "1",
        id:6
      }

      if (this.form.region == '1') {
        // 个人
        this.$axios.post(this.$httpUrl + '/personPost', data, {
          withCredentials: true // 允许跨域请求中的Cookie
        }).then(res => {
          // console.log(res)

        })


      } else {
        // 团队
        this.$axios.post(this.$httpUrl + '/teamPost', data, {
          withCredentials: true // 允许跨域请求中的Cookie
        }).then(res => {
          // console.log(res)

        })
      }


    },
    // 获取帖子 personPosts
    getPosts() {
      this.$axios.get(this.$httpUrl + '/teamPosts', {}, {
        withCredentials: true // 允许跨域请求中的Cookie
      }).then(res => {
        console.log(res.data, "帖子");
        this.tableData = res.data.data
      })
    },

    // 内容改变事件
    onEditorChange({ quill, html, text }) {
      console.log('editor change!', quill, html, text)
      this.form.content = html
    },
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options = this.list.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options = [];
      }
    }

  }
}
</script>
<style scoped>
.tab {
  margin-top: 20px;
}

.btn {
  width: 100%;
  display: flex;
  /* flex-direction: row-reverse; */
  flex-direction: row;
  justify-content: right;
}

.content {
  width: 100%;
  min-height: 300px;
}

.quill-editor /deep/ .ql-container {
  min-height: 220px;
}

.ql-container {
  min-height: 230px;
}
</style>