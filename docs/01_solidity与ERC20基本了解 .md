https://goerli-faucet.pk910.de/

solidity

https://learnblockchain.cn/docs/solidity/grammar.html

基本类型：primitives

```
uint unsigned int 自然数
int 整数
int=int256
-2^255<int256<2^255-1
0<uint8<2*8-1

int8,int(256),uint8,uint(256),
bool,default=false
address default=0x0000000000000000000000000000000000000000

    bytes1 public a = 0xb5; //  [10110101]
    bytes1 public b = 0x56; //  [01010110]
    bytes2 public aa=0xaab5;
    bytes后面几就是固定几个16进制，每个byte由2位组成，例如0xaa

uint不能为负数，会溢出报错
0.8新增了unchecked标注
```

vars

```
变量分为：全局，状态
local
declared inside a function
not stored on the blockchain
state
declared outside a function
stored on the blockchain
global (provides information about the blockchain)
```

constant

```

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

contract Constants {
    // coding convention to uppercase constant variables
    address public constant MY_ADDRESS = 0x777788889999AaAAbBbbCcccddDdeeeEfFFfCcCc;
    uint public constant MY_UINT = 123;
}

变量声明为constant可以减少gas消耗
Constants are variables that cannot be modified.
Their value is hard coded and using constants can save gas cost.
```

immutable

```
一经构造就不再可变
Immutable variables are like constants. Values of immutable variables can be set inside the constructor but cannot be modified afterwards.
```

state variable

```
// 状态变量，合约变量
// To write or update a state variable you need to send a transaction.

// On the other hand, you can read state variables, for free, without any transaction fee.

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

contract SimpleStorage {
    // State variable to store a number
    uint public num;

    // You need to send a transaction to write to a state variable.
    function set(uint _num) public {
        num = _num;
    }

    // You can read from a state variable without sending a transaction.
    function get() public view returns (uint) {
        return num;
    }
}
```

Ether & wei

```
Transactions are paid with `ether`.

Similar to how one dollar is equal to 100 cent, one `ether` is equal to 10^18 `wei`.
```



```solidity
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

contract EtherUnits {
    uint public oneWei = 1 wei;
    // 1 wei is equal to 1
    bool public isOneWei = 1 wei == 1;

    uint public oneEther = 1 ether;
    // 1 ether is equal to 10^18 wei
    bool public isOneEther = 1 ether == 1e18;
}
```

erc20详细：

```
erc20是一种token的通用模板协议，并且只有遵照了该模板的token才有资格在链上交互
基于该协议的合约才有资格在对应的链上运作
erc20的token运作经过下列过程
一个智能合约是：
chatgpt:【
	智能合约定义了代币的创建、转移和管理的规则和条件。合约代码存储在区块链上，并可以被任何与合约交互的人执行
	】
0.公约
	以太坊公约：一切活动都得付中介费gas，作为矿工耗费算力的补贴。
1. totalSupply() 这个合约总共的token数量,例如狗狗币就是一个合约，狗狗币总supply为1,000,000,000,000,000
2. balanceOf(一个钱包地址)，某个钱包地址的余额
3. transfer(接收账户，数量n) 将调用者钱包里的n个token转给接受账户。
4. allowance(owner钱包地址，spender钱包地址)：
	返回spender被允许花费owner的多少币
	问chatgpt：是否大部分个人交易中，owner就是spender，而多人参与的合作交易中，owner可能是公共的公司账户，spender则是某个员工？
	chatgpt回答：yes。
5.approve(spender,amount)
	设置spender允许花多少钱
	问chatgpt：approve就相当于setAmount方法，allowance就相当于getAmount方法，是这样吗？
	回答：yes
6.transferFrom()

translate your last reply to Chinese please, thank you

当第三方（支出者）想要从另一个地址的余额（发件人地址）中转移代币到不同的地址（收件人地址）时，transferFrom(from, to, amount)函数是ERC-20代币标准中定义的标准函数之一。

以下是一个transferFrom()函数被调用的示例：
	Alice拥有100个SHIB代币并批准Bob代表她转移50个代币。Alice在SHIB智能合约上调用approve(Bob's address, 50)函数。
	Bob想要将30个SHIB代币转移给Carol，因此他在SHIB智能合约上调用transferFrom(Alice's address, Carol's address, 30)函数。
	SHIB智能合约检查Bob是否已被授权代表Alice转移代币，并且他想要转移的代币数量（30）是否小于或等于Alice已经授权他转移的数量（50）。
	SHIB智能合约从Alice的余额中减去30个代币，并将30个代币添加到Carol的余额中。
	SHIB智能合约更新Bob的授权限制，以反映他仍然被允许代表Alice转移的剩余数量（20个代币）。
	在这个例子中，transferFrom()函数被Bob用来代表Alice将代币从Alice的余额转移到Carol的余额。只要他获得了代币所有者（Alice）的批准，该函数允许Bob在没有拥有或控制代币的情况下转移代币，通过approve()函数获得授权。
```

