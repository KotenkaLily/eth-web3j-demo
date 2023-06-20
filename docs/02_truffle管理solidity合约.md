## truffle+Java合约管理记录

### truffle

#### 安装(全局)

```
npm install -g truffle
#这样一来，cmd里面就可以有truffle命令了
```

#### 创建

```
truffle unbox <box_name>
#下载box_name这个box
```

```
truffle init
#创建一个空项目
```

#### 结构

```
contracts/MetaCoin.sol： 这是一个用 Solidity 编写的 MetaCoin 代币 智能合约。注意他还引用了目录下的另外一个合约文件 contracts/ConvertLib.sol 。
contracts/Migrations.sol： 这是一个单独的 Solidity 文件，用来管理和升级智能合约. 每一个工程都有这样的一个文件，并且通常不需要编辑它。
migrations/1_initial_migration.js： 这是一个部署脚本，用来部署 Migrations 合约，对应 Migrations.sol 文件。
migrations/2_deploy_contracts.js： 这是一个部署脚本，用来部署 MetaCoin 合约. (部署脚本的运行是有顺序的，以2开头的脚本通常在以1开头的脚本之后运行)
test/TestMetacoin.sol： 这是一个用Solidity编写的测试用例文件，用来检查合约是否像预期一样工作。
test/metacoin.js ： 这是一个用JavaScript编写的测试用例脚本，用途和上面一样。
truffle-config.js （之前是 truffle.js）： Truffle 配置文件, 用来设置网络信息，和其他项目相关的设置。当我们使用内建的默认的Truffle命令时，这个文件留空也是可以的。
```

#### 测试

```
truffle test ./test/TestMetacoin.sol
#solidity测试案例
```

```
truffle test ./test/metacoin.js
#js测试案例
```

#### 编译

```
truffle compile
```

#### 部署-deveolop

```
truffle develop，创建一个包含10个钱包的区块，并进入develop环境
```

```
(truffle) migrate
#括号是可以省略不写，因为在develop下。
```

##### 信息

migrate命令会根据migrations下的js部署脚本来决定deploy什么东西

案例：

```
const ConvertLib = artifacts.require("ConvertLib");
const MetaCoin = artifacts.require("MetaCoin");
const ERC20 = artifacts.require("ERC20");
module.exports = function(deployer) {
  deployer.deploy(ConvertLib);
  deployer.link(ConvertLib, MetaCoin);
  deployer.deploy(MetaCoin);
  deployer.deploy(ERC20);
};
```



```
truffle(develop)> migrate
#开始
Compiling your contracts...

===========================
#编译所有solidity文件
> Compiling .\contracts\ConvertLib.sol

> Compiling .\contracts\MetaCoin.sol
> Compiling .\contracts\MyToken.sol

#编译后文件Artifacts(工件，可理解为Java的Class文件)
> Artifacts written to G:\Project\metacoin\build\contracts

#汇报编译结果
> Compiled successfully using:
   - solc: 0.8.13+commit.abaa5c0e.Emscripten.clang



#开始部署
Starting migrations...
======================
#网络名称，也就是开发环境
> Network name:    'develop'
> Network id:      5777
# 当前区块链的交易手续费最大限制
> Block gas limit: 6721975 (0x6691b7)

#按照前缀编号决定部署脚本执行的大顺序
1_deploy_contracts.js
=====================

#部署合约ConvertLib
   Replacing 'ConvertLib'
   ----------------------

   > transaction hash:    0x85c181ad2e165b530bb3d85e70d6291339f79987ff5e78b84e55df5216458ed4

   > Blocks: 0            Seconds: 0
   > contract address:    0x02AFb0374BcbD25b3c98015eb3350D215d179Eb2
   > block number:        7
   > block timestamp:     1681696570
   > account:             0x6333BD68A96b61AaaA40cBE7996b92285918637d
   > balance:             99.994131957674531132
   > gas used:            157568 (0x26780)
   > gas price:           2.922300506 gwei
   > value sent:          0 ETH
   > total cost:          0.000460461046129408 ETH


#链接合约，因为MetaCoin文件import了依赖ConvertLib
   Linking
   -------
   * Contract: MetaCoin <--> Library: ConvertLib (at address: 0x02AFb0374BcbD25b3c98015eb3350D215d179Eb2)

#部署MetaCoin
   Replacing 'MetaCoin'
   --------------------

   > transaction hash:    0x6e3d20ac617a7d00cd0f64007ad8e99c2cbb44060f275868c446fff271f15c06

   > Blocks: 0            Seconds: 0
   > contract address:    0x6aF1d1Dc55957D7CFaD87F174c9353e699cB8DD0
   > block number:        8
   > block timestamp:     1681696571
   > account:             0x6333BD68A96b61AaaA40cBE7996b92285918637d
   > balance:             99.992935504829804144
   > gas used:            416594 (0x65b52)
   > gas price:           2.871987702 gwei
   > value sent:          0 ETH
   > total cost:          0.001196452844726988 ETH


   > Saving artifacts
   -------------------------------------
   > Total cost:     0.001656913890856396 ETH

Summary
=======
> Total deployments:   2
> Final cost:          0.001656913890856396 ETH
```

### truffle中合约的交互与使用

truffle内置引入了web3.js包，web3.js有很多交互方法

```
基于以太坊开发DApp（去中心化应用程序），可以使用 web3.js库提供的 web3 对象， 在底层实现上， web3通过RPC调用 与本地节点通信， web3.js 可以与任何暴露了RPC接口的以太坊节点连接。

web3 包含下面几个对象：
web3.eth 用来与以太坊区块链及合约的交互
web3.shh 用来与Whisper协议相关交互
web3.net 用来获取网络相关信息
web3 主对象 包含一些工具
```

#### 示例

##### 获取账户

```
truffle(develop)> web3.eth.getAccounts()
[
  '0x6333BD68A96b61AaaA40cBE7996b92285918637d',
  '0x55fB67C8C1F0a62EF1b9cE4c10136E94DA317420',
  '0xBF495D3e6b65D8A0c13cD5c115c02826c2d88090',
  '0x22e7ca0780a3f7A97587D09Ad1e83e7A11937b4a',
  '0x31EA81491b2Db4856847864A0a4737f830aCeEBA',
  '0xeD373e1C95fE5eed3a8F275c1Cb38c3406d0EB9C',
  '0xA6D739AA7488cd743DE458A45583960B0f4470AC',
  '0x2e59a19CdE4E01644Ae0C9B4A010358cAA0B0BA5',
  '0xa44Bc4b3F3edFD04C04aAe5ff111bb3442059Cee',
  '0x421E19A4081c01bB2891034005729594Aba7B5E0'
]
```

在 Truffle v5, 控制台支持 async/await 方法（同步方式）, 这样让跟合约交互更简单了，方法如下：

- 从获取部署合约实例 及获取账号列表 开始：

  ```
  truffle(development)> let instance = await MetaCoin.deployed()
  truffle(development)> let accounts = await web3.eth.getAccounts()
  ```

- 检查账号余额：

  ```
  truffle(development)> let balance = await instance.getBalance(accounts[0])
  truffle(development)> balance.toNumber()
  ```

- 查看以太价值（其实就是调用了一个合约方法：合约方法里定义了一个metacoin 价值 2 ether)：

  ```
  truffle(development)> let ether = await instance.getBalanceInEth(accounts[0])
  truffle(development)> ether.toNumber()
  ```

- 发送一些 metacoin 到其他的账号 :

  ```
  truffle(development)> instance.sendCoin(accounts[1], 500)
  ```

- 检查刚刚收款人的余额：

  ```
  truffle(development)> let received = await instance.getBalance(accounts[1])
  truffle(development)> received.toNumber()
  ```

- 检查刚刚发送方的余额：

  ```
  truffle(development)> let newBalance = await instance.getBalance(accounts[0])
  truffle(development)> newBalance.toNumber()
  ```

##### 合约交互

```
#合约交互的第一步是声明一个合约实例
truffle(develop)> let myContract = new web3.eth.Contract(ERC20.abi,'0xbEdEC9c52DE13D68A8A0165Cf181ef90533D2F4B');
#ERC20是我自定义的一个合约（contract），后面是合约的地址
```

###### 示例：

```
//migrate之后获得contractAddress，部署的合约的地址。
//获取当前所有的账户数组
const addressList = await web3.eth.getAccounts();
//声明合约实例
let contractInstance = new web3.eth.Contract(ERC20.abi, contractAddress);
//为钱包0和钱包1增发tokens
contractInstance.methods.mint(addressList[0],20000);
contractInstance.methods.mint(addressList[1],10000);
//默认账户0转给账户1和账户2，20个token
contractInstance.methods.transfer(addressList[1],20);
contractInstance.methods.transfer(addressList[2],20);
//从账户1转给账户2，1个token
contractInstance.methods.transferFrom(addressList[1],addressList[2],1);

await contractInstance.methods.transferFrom(addressList[1],addressList[2],1);
```

### java

#### java连接truffle环境

```
//        Connect to your Ethereum node
//如果是alchemy，需要用他们的api-key；如果是infura，需要用project-id
//        Web3j web3j = Web3j.build(
//                new HttpService("https://eth-mainnet.alchemyapi.io/v2/" +
////                        "YOUR-API-KEY"
//                        "lyi7Ts83UzDgPJQTnGxdGmMU-KVhY0Pg"
//                ));
//        Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/YOUR-PROJECT-ID"));
//truffle develop 默认9545（以前8545）
        Web3j client = Web3j.build(new HttpService("http://localhost:9545"));
```

