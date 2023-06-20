let addressList = await web3.eth.getAccounts();
let contractInstance = new web3.eth.Contract(ERC20.abi, contractAddress);
//mint for first 2 accounts
contractInstance.methods.mint(addressList[0],20000);
contractInstance.methods.mint(addressList[1],10000);
//translate from second to first by 20 tokens
contractInstance.methods.transfer(addressList[1],20);