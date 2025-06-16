// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/token/ERC1155/extensions/ERC1155URIStorage.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract NFTCollectionERC1155 is ERC1155URIStorage, Ownable {
    uint256 public currentTokenId = 0;

    constructor() ERC1155("") Ownable(msg.sender) {}

    function mint(address to, uint256 amount, string memory tokenURI) public onlyOwner {
        currentTokenId++;
        uint256 tokenId = currentTokenId;
        _mint(to, tokenId, amount, "");
        _setURI(tokenId, tokenURI);
    }

    function uri(uint256 tokenId)
        public
        view
        override
        returns (string memory)
    {
        return super.uri(tokenId);
    }
}
