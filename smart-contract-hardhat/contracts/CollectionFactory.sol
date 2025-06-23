// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "./NFTCollectionERC1155.sol";

contract NFTCollectionFactory {
    event CollectionCreated(address indexed owner, address indexed collection);

    function createCollection(string memory name, string memory symbol) external returns (address) {
        NFTCollectionERC1155 collection = new NFTCollectionERC1155(name, symbol);
        collection.transferOwnership(msg.sender);

        emit CollectionCreated(msg.sender, address(collection));
        return address(collection);
    }
}

