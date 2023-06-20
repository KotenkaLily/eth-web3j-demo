import router901 from '@/views/901-web3-demo/router/router'

const routes = [
    {
        path: "/",
        name: "root",
        meta: {
            title: 'Demo'
        },
        redirect: "/901/Web3Home",
        children: [
            router901,
        ]
    },
    {
        path: '/:catchAll(.*)',
        name: 'NotFound',
        component: () => import('@/exception/Exception404')
    },
];

export default routes;
