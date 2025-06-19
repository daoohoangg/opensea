package com.example.nftservice.service;

import com.example.nftservice.entity.NFT;
import com.example.nftservice.entity.NFTCollectionERC1155;
import com.example.nftservice.repository.NFTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;


import java.math.BigInteger;
import java.util.List;

@Service
public class NFTService {
    @Autowired
    private NFTRepository nftRepository;

    public List<NFT> getAllNFTs() {
        return nftRepository.findAll();
    }

    public NFT[] getNFTByCollectionName(String collectionName) {
        return nftRepository.findNFTByCollectionName(collectionName);
    }

    public NFT getNftByName(String name) {
        return nftRepository.findNftByName(name);
    }

    public void createNFT(NFT n) {
        NFT nft = new NFT(n.getName(), n.getPrice(), n.getCollectionName(), n.getImageName(), n.getToken());
        nftRepository.insert(nft);
    }

    public NFT updateNFT(String id, NFT nft) {
        return nftRepository.findById(id).map(x -> {
            x.setName(nft.getName());
            x.setPrice(nft.getPrice());
            x.setCollectionName(nft.getCollectionName());
            x.setImageName(nft.getImageName());
            x.setToken(nft.getToken());
            return nftRepository.save(x);
        }).orElseGet(() -> {
            nft.setId(id);
            return nftRepository.save(nft);
        });
    }

    public void deleteNFT(String id) {
        nftRepository.deleteById(id);
    }

    @Value("${SEPOLIA_RPC_URL}")
    private String rpcUrl;

    @Value("${PRIVATE_KEY}")
    private String privateKey;

    public Web3j getWeb3() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    public Credentials getCredentials() {
        return Credentials.create(privateKey);
    }

    public NFTCollectionERC1155 deployContract(String metadataUri) throws Exception {
        Web3j web3j = getWeb3();
        Credentials creds = getCredentials();
        BigInteger gasPrice = Convert.toWei("2", Convert.Unit.GWEI).toBigInteger();
        BigInteger gasLimit = BigInteger.valueOf(5_000_000);

        return NFTCollectionERC1155.deploy(
                web3j,
                creds,
                new StaticGasProvider(gasPrice, gasLimit),
                metadataUri
        ).send();
    }

    public TransactionReceipt mint(String contractAddress, String to, BigInteger tokenId, BigInteger amount) throws Exception {
        Web3j web3j = getWeb3();
        Credentials creds = getCredentials();

        NFTCollectionERC1155 contract = NFTCollectionERC1155.load(
                contractAddress,
                web3j,
                creds,
                new DefaultGasProvider()
        );

        return contract.mint(
                to,
                tokenId,
                amount,
                new byte[0]
        ).send();
    }
}
