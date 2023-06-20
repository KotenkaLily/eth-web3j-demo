import router from '@/router'
import {getToken} from "@/common/config/auth";

const loginPath = '/web-service/login';

router.beforeEach((to, from, next) => {
    const hasToken = getToken()
    if (from.path !== loginPath // not yet login or from another page
        && to.path !== loginPath // if going to login page, never stop it
        && !hasToken) { // if from another page, judge if owning a token
        if (!hasToken) {
            //own no token
            next(loginPath)
        } else {
            //own a token
            next({...to, replace: true})
        }
    } else {//如果有上一次的token
        next()
    }
})