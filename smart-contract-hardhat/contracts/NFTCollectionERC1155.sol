// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/token/ERC1155/extensions/ERC1155URIStorage.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract NFTCollectionERC1155 is ERC1155URIStorage, Ownable {
    uint256 public currentTokenId = 0;

    // constructor() ERC1155("") Ownable(msg.sender) {}

    event Minted(address indexed to, uint256 indexed tokenId, uint256 amount, string uri);
    
    function mint(address to, uint256 amount, string memory tokenURI) public onlyOwner {
        currentTokenId++;
        uint256 tokenId = currentTokenId;
        _mint(to, tokenId, amount, "");
        _setURI(tokenId, tokenURI);

        emit Minted(to, tokenId, amount, tokenURI);
    }
    string public name;
    string public symbol;

    constructor(string memory _name, string memory _symbol) ERC1155("") Ownable(msg.sender) {
        name = _name;
        symbol = _symbol;
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
