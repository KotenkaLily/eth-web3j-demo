const {defineConfig} = require("@vue/cli-service");
module.exports = defineConfig({
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    javascriptEnabled: true,
                    modifyVars: {
                        // 全局主色
                        "primary-color": "rgb(231,77,110)",
                        // 链接色
                        "link-color": "#1890ff",
                        // 成功色
                        "success-color": "#52c41a",
                        // 警告色
                        "warning-color": "#faad14",
                        // 错误色
                        "error-color": "#f5222d",
                        // 主字号
                        "font-size-base": "14px",
                        // 标题色
                        "heading-color": "rgba(0, 0, 0, 0.85)",
                        // 主文本色
                        "text-color": "rgba(0, 0, 0, 0.65)",
                        // 次文本色
                        "text-color-secondary": "rgba(0, 0, 0, 0.45)",
                        // 失效色
                        "disabled-color": "rgba(0, 0, 0, 0.25)",
                        // 组件/浮层圆角
                        "border-radius-base": "2px",
                        // 边框色
                        "border-color-base": "#d9d9d9",
                        // 浮层阴影
                        "box-shadow-base": "0 2px 8px rgba(0, 0, 0, 0.15)",
                    },
                },
            },
        },
    },
    transpileDependencies: true,

    devServer: {
        port: 18866,
        // If you want to turn on the proxy, please remove the mockjs /src/main.jsL11
        proxy: {
            '/K000-Java8': {
                target: 'http://127.0.0.1:33849',
                //如果要代理 websockets，配置这个参数
                ws: false,
                //是否跨域
                changeOrigin: true,
                pathRewrite: {'^/K000-Java8': ''}
            },
            '/K000-J17': {
                target: 'http://127.0.0.1:33848', ws: false, changeOrigin: true,
                pathRewrite: {'^/K000-J17': ''}
            },
        },
    },

});

