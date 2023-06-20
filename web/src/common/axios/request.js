import axios from 'axios'
import {message, notification} from 'ant-design-vue'

// 添加接口错误提醒类型，两种(message全局提示)(notification通知提醒框)
const errorHintType = 'notification'

axios.defaults.headers = {
    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    //prevent from cross error
    'Access-Control-Allow-Origin': "*",
}
const service = axios.create({
    baseURL: "/K000-Java8",
    timeout: 45000 // 请求超时时间
})

const err = (error) => {
    const resData = error.response
    if (resData) {
        const res = resData.data
        if (res.code === 500) {
            errorHintType === 'message' ? message.error('服务器在偷懒...') : notification.error({
                message: '请求错误',
                description: '服务器在偷懒...'
            })
        } else {
            errorHintType === 'message' ? message.error(res.errorCtx.errorMsg) : notification.error({
                message: '请求错误',
                description: res.message
            })
        }
    }
    return Promise.reject((error.response && error.response.data) ? error.response.data : error)
}

function formatter(obj) {
    for (const key in obj) {
        const item = obj[key],
            regDay = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/,
            regHour = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d)$/,
            regMin = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d$/;
        if (regDay.test(item)) {
            obj[key] = item + " 00:00:00";
        } else if (regHour.test(item)) {
            obj[key] = item + ":00:00";
        } else if (regMin.test(item)) {
            obj[key] = item + ":00";
        }
    }
}

// request interceptor
service.interceptors.request.use(config => {
    if (config.params) {
        formatter(config.params)
    }
    if (config.data) {
        formatter(config.data)
    }
    return config
}, err)

// response interceptor
service.interceptors.response.use((response) => {
    const res = response.data
    if (res.code === 200 || (res instanceof Blob && response.status === 200) || (typeof res === 'string' && response.status === 200)) {
        return Promise.resolve(res)
    } else {
        if (res.code === 500) {
            errorHintType === 'message' ? message.error('服务器在偷懒...') : notification.error({
                message: '请求错误',
                description: '服务器在偷懒...'
            })
        } else {
            errorHintType === 'message' ? message.error(res.errorCtx.errorMsg) : notification.error({
                message: '请求错误',
                description: res.message
            })
        }
        return Promise.reject(res)
    }
}, err)


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        service.post(url, data).then(
            response => {
                resolve(response);
            },
            err => {
                reject(err);
            }
        );
    });
}

/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url, data = {}) {
    return new Promise((resolve, reject) => {
        service.put(url, data).then(
            response => {
                resolve(response);
            },
            err => {
                reject(err);
            }
        );
    });
}


/**
 * 封装delete请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function axiosDelete(url, data = {}) {
    return new Promise((resolve, reject) => {
        service({
            method: "DELETE",
            url: url,
            data: data
        })
            .then(response => {
                resolve(response);
            })
            .catch(err => {
                reject(err);
            });
    });
}

/**
 * 封装get请求
 * @param url
 * @param params
 * @returns {Promise}
 */
export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        service
            .get(url, {
                params: params
            })
            .then(response => {
                resolve(response);
            })
            .catch(err => {
                reject(err);
            });
    });
}

export default service