const router=            {
        path: "901",
        name: "901",
        meta: {
            title: 'web3项目',
        },
        redirect: "/901/Web3Home",
        children: [
            {
                path: "Web3Home",
                name: "Web3Home",
                component: () => import( "../Web3Home"),
                meta: {
                    title: '首页',
                },
            },
            {
                path: "BlockInfo",
                name: "BlockInfo",
                component: () => import( "../BlockInfo"),
                meta: {
                    title: '区块信息',
                },
            },
            {
                path: "ContractManage",
                name: "ContractManage",
                component: () => import( "../ContractManage"),
                meta: {
                    title: '合约管理',
                },
            },
        ],
    }

    export default router;