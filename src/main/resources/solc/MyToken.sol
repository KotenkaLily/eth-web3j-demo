// SPDX-License-Identifier: MIT
// OpenZeppelin Contracts (last updated v4.6.0) (token/ERC20/IERC20.sol)
import "./ERC20.sol";

pragma solidity ^0.8.13;

contract MyToken is ERC20 {
    constructor (string memory name_, string memory symbol_, uint totalSupply_) ERC20(name_, symbol_){
        _mint(msg.sender, totalSupply_);
    }
}