package com.example.nftservice.entity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;

public class NFTCollectionERC1155 extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b506040516104e23803806104e283398101604081905261002f9161005c565b6001600160a01b03811661005f57600080fd5b50506100f3565b600080546001600160a01b0319163317905561009a806100846000396000f3fe60806040526004361061003f5760003560e01c8063095ea7b31461004457806340c10f191461007c578063f2fde38b146100a9575b600080fd5b61006a600480360381019061006591906102e8565b6100bc565b604051610077919061037e565b60405180910390f35b6100a760048036038101906100a29190610305565b6101c2565b005b6100b36101ef565b6040516100c0919061039f565b60405180910390f35b600080546001600160a01b03191633179055565b60008054905090565b60008060009054906101000a90046001600160a01b0316905090565b6000546001600160a01b0316331461011e57600080fd5b565b6001600160a01b038116811461013557600080fd5b50565b60006020828403121561014a57600080fd5b813561015581610120565b9392505050565b6000806040838503121561016f57600080fd5b823561017a81610120565b9150602083013561018a81610120565b809150509250929050565b6000819050919050565b6101a881610195565b82525050565b60006020820190506101c3600083018461019f565b92915050565b60006101d482610195565b91506101df83610195565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561021457610213576101fd565b5b828201905092915050565b600061022a82610195565b915061023583610195565b925082820190508082111561024d5761024c6101fd565b5b92915050565b60006020820190508181036000830152610270816101a2565b9050919050565b6000819050919050565b61028c81610279565b811461029757600080fd5b50565b6000813590506102a981610283565b92915050565b600080604083850312156102c457600080fd5b6102cd83610283565b91506102db60208401610283565b90509250929050565b6102ee81610279565b82525050565b600060208201905061030960008301846102e5565b92915050565b600080fd5b61031d81610279565b811461032857600080fd5b5056fea2646970667358221220be4caaac9dc4ec7cfc3d764142a2efb0a05f2f408dcad5d7402a573b3f1cdfe564736f6c634300080e0033";

    public static final String ABI = "[{\"inputs\":[{\"internalType\":\"string\",\"name\":\"uri\",\"type\":\"string\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"mint\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"uri\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]";

    protected NFTCollectionERC1155(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super(ABI, contractAddress, web3j, credentials, gasProvider);
    }

    public static NFTCollectionERC1155 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new NFTCollectionERC1155(contractAddress, web3j, credentials, gasProvider);
    }

    public static RemoteCall<NFTCollectionERC1155> deploy(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider, String uri) {
        return deployRemoteCall(NFTCollectionERC1155.class, web3j, credentials, gasProvider, BINARY, uri);
    }

    public RemoteCall<TransactionReceipt> mint(String account, BigInteger id, BigInteger amount, byte[] data) {
        final Function function = new Function(
                "mint",
                Arrays.asList(new Address(account), new Uint256(id), new Uint256(amount), new org.web3j.abi.datatypes.DynamicBytes(data)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> uri(BigInteger id) {
        final Function function = new Function(
                "uri",
                Arrays.asList(new Uint256(id)),
                Arrays.asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
