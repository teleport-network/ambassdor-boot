package org.web3j.generated.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class ERC721A extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001a8e38038062001a8e83398101604081905262000034916200028a565b6200005a6200004b640100000000620000a5810204565b640100000000620000a9810204565b81516200006f906003906020850190620000fe565b50805162000085906004906020840190620000fe565b5062000099640100000000620000f9810204565b600155506200034a9050565b3390565b60008054600160a060020a03838116600160a060020a0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b600190565b8280546200010c90620002f4565b90600052602060002090601f0160209004810192826200013057600085556200017b565b82601f106200014b57805160ff19168380011785556200017b565b828001600101855582156200017b579182015b828111156200017b5782518255916020019190600101906200015e565b50620001899291506200018d565b5090565b5b808211156200018957600081556001016200018e565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600082601f830112620001e557600080fd5b81516001604060020a0380821115620002025762000202620001a4565b604051601f8301601f19908116603f011681019082821181831017156200022d576200022d620001a4565b816040528381526020925086838588010111156200024a57600080fd5b600091505b838210156200026e57858201830151818301840152908201906200024f565b83821115620002805760008385830101525b9695505050505050565b600080604083850312156200029e57600080fd5b82516001604060020a0380821115620002b657600080fd5b620002c486838701620001d3565b93506020850151915080821115620002db57600080fd5b50620002ea85828601620001d3565b9150509250929050565b6002810460018216806200030957607f821691505b6020821081141562000344577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b611734806200035a6000396000f3fe608060405234801561001057600080fd5b506004361061010f5760003560e060020a9004806370a08231116100a6578063a22cb46511610075578063a22cb46514610218578063b88d4fde1461022b578063c87b56dd1461023e578063e985e9c514610251578063f2fde38b1461028d57600080fd5b806370a08231146101e4578063715018a6146101f75780638da5cb5b146101ff57806395d89b411461021057600080fd5b806318160ddd116100e257806318160ddd1461019157806323b872dd146101ab57806342842e0e146101be5780636352211e146101d157600080fd5b806301ffc9a71461011457806306fdde031461013c578063081812fc14610151578063095ea7b31461017c575b600080fd5b61012761012236600461130b565b6102a0565b60405190151581526020015b60405180910390f35b61014461033d565b6040516101339190611387565b61016461015f36600461139a565b6103cf565b604051600160a060020a039091168152602001610133565b61018f61018a3660046113ca565b61042c565b005b60025460015403600019015b604051908152602001610133565b61018f6101b93660046113f4565b610522565b61018f6101cc3660046113f4565b61055a565b6101646101df36600461139a565b6105a2565b61019d6101f2366004611430565b6105b4565b61018f61061c565b600054600160a060020a0316610164565b610144610655565b61018f61022636600461144b565b610664565b61018f6102393660046114b6565b610713565b61014461024c36600461139a565b6107aa565b61012761025f366004611592565b600160a060020a03918216600090815260086020908152604080832093909416825291909152205460ff1690565b61018f61029b366004611430565b61085b565b6000600160e060020a031982167f80ac58cd0000000000000000000000000000000000000000000000000000000014806103035750600160e060020a031982167f5b5e139f00000000000000000000000000000000000000000000000000000000145b8061033757507f01ffc9a700000000000000000000000000000000000000000000000000000000600160e060020a03198316145b92915050565b60606003805461034c906115c5565b80601f0160208091040260200160405190810160405280929190818152602001828054610378906115c5565b80156103c55780601f1061039a576101008083540402835291602001916103c5565b820191906000526020600020905b8154815290600101906020018083116103a857829003601f168201915b5050505050905090565b60006103da82610913565b610410576040517fcf4700e400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50600090815260076020526040902054600160a060020a031690565b600054600160a060020a031633146104625760405160e560020a62461bcd02815260040161045990611619565b60405180910390fd5b600061046d826105a2565b905080600160a060020a031683600160a060020a031614156104bb576040517f943f7b8c00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b33600160a060020a038216148015906104db57506104d9813361025f565b155b15610512576040517fcfb3b94200000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b61051d83838361094c565b505050565b600054600160a060020a0316331461054f5760405160e560020a62461bcd02815260040161045990611619565b61051d8383836109b5565b600054600160a060020a031633146105875760405160e560020a62461bcd02815260040161045990611619565b61051d83838360405180602001604052806000815250610713565b60006105ad82610c38565b5192915050565b6000600160a060020a0382166105f6576040517f8f4eb60400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50600160a060020a031660009081526006602052604090205467ffffffffffffffff1690565b600054600160a060020a031633146106495760405160e560020a62461bcd02815260040161045990611619565b6106536000610d9c565b565b60606004805461034c906115c5565b600160a060020a0382163314156106a7576040517fb06307db00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b336000818152600860209081526040808320600160a060020a03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b600054600160a060020a031633146107405760405160e560020a62461bcd02815260040161045990611619565b61074b8484846109b5565b600160a060020a0383163b1515801561076d575061076b84848484610df9565b155b156107a4576040517fd1a57ed600000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50505050565b60606107b582610913565b6107eb576040517fa14c4b5000000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b600061080260408051602081019091526000815290565b9050600061080f84610f3b565b90508151600014156108305760405180602001604052806000815250610853565b818160405160200161084392919061164e565b6040516020818303038152906040525b949350505050565b600054600160a060020a031633146108885760405160e560020a62461bcd02815260040161045990611619565b600160a060020a0381166109075760405160e560020a62461bcd02815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201527f64647265737300000000000000000000000000000000000000000000000000006064820152608401610459565b61091081610d9c565b50565b600081600111158015610927575060015482105b801561033757505060009081526005602052604090205460e060020a900460ff161590565b600082815260076020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0387811691821790925591518593918516917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92591a4505050565b60006109c082610c38565b8051909150600090600160a060020a031633600160a060020a031614806109ee575081516109ee903361025f565b80610a095750336109fe846103cf565b600160a060020a0316145b905080610a42576040517f59c896be00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b84600160a060020a03168260000151600160a060020a031614610a91576040517fa114810000000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b600160a060020a038416610ad1576040517fea553b3400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b610ae1600084846000015161094c565b600160a060020a038581166000908152600660209081526040808320805467ffffffffffffffff1980821667ffffffffffffffff9283166000190183161790925589861680865283862080549384169383166001908101841694909417905589865260059094528285208054600160e060020a03191690941774010000000000000000000000000000000000000000429092169190910217909255908601808352912054909116610bef57600154811015610bef578251600082815260056020908152604090912080549186015167ffffffffffffffff167401000000000000000000000000000000000000000002600160e060020a0319909216600160a060020a03909316929092171790555b508284600160a060020a031686600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a45050505050565b60408051606081018252600080825260208201819052918101919091528180600111158015610c68575060015481105b15610d6a5760008181526005602090815260409182902082516060810184529054600160a060020a038116825274010000000000000000000000000000000000000000810467ffffffffffffffff169282019290925260e060020a90910460ff16151591810182905290610d68578051600160a060020a031615610ced579392505050565b506000190160008181526005602090815260409182902082516060810184529054600160a060020a03811680835274010000000000000000000000000000000000000000820467ffffffffffffffff169383019390935260e060020a900460ff1615159281019290925215610d63579392505050565b610ced565b505b6040517fdf2d9b4200000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b60008054600160a060020a0383811673ffffffffffffffffffffffffffffffffffffffff19831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6040517f150b7a02000000000000000000000000000000000000000000000000000000008152600090600160a060020a0385169063150b7a0290610e479033908990889088906004016116a5565b602060405180830381600087803b158015610e6157600080fd5b505af1925050508015610e91575060408051601f3d908101601f19168201909252610e8e918101906116e1565b60015b610f05573d808015610ebf576040519150601f19603f3d011682016040523d82523d6000602084013e610ec4565b606091505b508051610efd576040517fd1a57ed600000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b805181602001fd5b600160e060020a0319167f150b7a0200000000000000000000000000000000000000000000000000000000149050949350505050565b606081600111158015610f4f5750611f4182105b15610f8d57505060408051808201909152600181527f3100000000000000000000000000000000000000000000000000000000000000602082015290565b81611f4111158015610fa05750612af982105b15610fde57505060408051808201909152600181527f3200000000000000000000000000000000000000000000000000000000000000602082015290565b81612af911158015610ff15750612ee182105b1561102f57505060408051808201909152600181527f3300000000000000000000000000000000000000000000000000000000000000602082015290565b81612ee111158015611042575061307182105b1561108057505060408051808201909152600181527f3400000000000000000000000000000000000000000000000000000000000000602082015290565b8161307111158015611093575061313982105b156110d157505060408051808201909152600181527f3500000000000000000000000000000000000000000000000000000000000000602082015290565b81613139111580156110e4575061319d82105b1561112257505060408051808201909152600181527f3600000000000000000000000000000000000000000000000000000000000000602082015290565b8161319d1115801561113557506131d982105b1561117357505060408051808201909152600181527f3700000000000000000000000000000000000000000000000000000000000000602082015290565b816131d911158015611186575061330582105b156111c457505060408051808201909152600181527f3800000000000000000000000000000000000000000000000000000000000000602082015290565b81613305111580156111d7575061339b82105b1561121557505060408051808201909152600181527f3900000000000000000000000000000000000000000000000000000000000000602082015290565b8161339b1115801561122857506133ff82105b1561126657505060408051808201909152600281527f3130000000000000000000000000000000000000000000000000000000000000602082015290565b816133ff11158015611279575061343082105b156112b757505060408051808201909152600281527f3131000000000000000000000000000000000000000000000000000000000000602082015290565b505060408051808201909152600281527f3132000000000000000000000000000000000000000000000000000000000000602082015290565b919050565b600160e060020a03198116811461091057600080fd5b60006020828403121561131d57600080fd5b8135611328816112f5565b9392505050565b60005b8381101561134a578181015183820152602001611332565b838111156107a45750506000910152565b6000815180845261137381602086016020860161132f565b601f01601f19169290920160200192915050565b602081526000611328602083018461135b565b6000602082840312156113ac57600080fd5b5035919050565b8035600160a060020a03811681146112f057600080fd5b600080604083850312156113dd57600080fd5b6113e6836113b3565b946020939093013593505050565b60008060006060848603121561140957600080fd5b611412846113b3565b9250611420602085016113b3565b9150604084013590509250925092565b60006020828403121561144257600080fd5b611328826113b3565b6000806040838503121561145e57600080fd5b611467836113b3565b91506020830135801515811461147c57600080fd5b809150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080600080608085870312156114cc57600080fd5b6114d5856113b3565b93506114e3602086016113b3565b925060408501359150606085013567ffffffffffffffff8082111561150757600080fd5b818701915087601f83011261151b57600080fd5b81358181111561152d5761152d611487565b604051601f8201601f19908116603f0116810190838211818310171561155557611555611487565b816040528281528a602084870101111561156e57600080fd5b82602086016020830137600060208483010152809550505050505092959194509250565b600080604083850312156115a557600080fd5b6115ae836113b3565b91506115bc602084016113b3565b90509250929050565b6002810460018216806115d957607f821691505b60208210811415611613577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b6000835161166081846020880161132f565b83519083019061167481836020880161132f565b7f2e6a736f6e0000000000000000000000000000000000000000000000000000009101908152600501949350505050565b6000600160a060020a038087168352808616602084015250836040830152608060608301526116d7608083018461135b565b9695505050505050565b6000602082840312156116f357600080fd5b8151611328816112f556fea264697066735822122001813f412e3323734ce3bd9b34e538d15dc20c819db727e733c1d0333d4fcc6464736f6c63430008090033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected ERC721A(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC721A(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC721A(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC721A(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC721A load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721A(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC721A load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721A(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC721A load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC721A(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC721A load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC721A(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC721A> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name_, String symbol_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_)));
        return deployRemoteCall(ERC721A.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ERC721A> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name_, String symbol_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_)));
        return deployRemoteCall(ERC721A.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC721A> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_)));
        return deployRemoteCall(ERC721A.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC721A> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_)));
        return deployRemoteCall(ERC721A.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
