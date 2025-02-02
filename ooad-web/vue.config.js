const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer: {
    host: '8.134.23.156',
    // host: 'localhost',
    open: true,
    // 例如将'localhost:8080/api/xxx'代理到'www.example.com/api/xxx'
    proxy: {
      '/api': { // 匹配所有以 '/api'开头的请求路径
        target: 'http://8.134.23.156:8090', // 代理目标的基础路径
        // secure: false,  // 如果是https接口，需要配置这个参数
        changeOrigin: true, // 支持跨域
        pathRewrite: { // 重写路径: 去掉路径中开头的'/api'
          '^/api': ''
        }
      }
    }
  }
})
publicPath = "/"
