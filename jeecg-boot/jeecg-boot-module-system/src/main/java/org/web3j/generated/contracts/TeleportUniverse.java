package org.web3j.generated.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
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
public class TeleportUniverse extends Contract {
    public static final String BINARY = "6000600955600a805460ff1916600117905560e06040526036608081815290620020e060a03980516200003b91600b9160209091019062000186565b503480156200004957600080fd5b506040518060400160405280601181526020017f54656c65706f727420556e6976657273650000000000000000000000000000008152506040518060400160405280600581526020017f54454c4559000000000000000000000000000000000000000000000000000000815250620000e2620000d36200012d640100000000026401000000009004565b64010000000062000131810204565b8151620000f790600390602085019062000186565b5080516200010d90600490602084019062000186565b506200012164010000000062000181810204565b60015550620002829050565b3390565b60008054600160a060020a03838116600160a060020a0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b600190565b82805462000194906200022c565b90600052602060002090601f016020900481019282620001b8576000855562000203565b82601f10620001d357805160ff191683800117855562000203565b8280016001018555821562000203579182015b8281111562000203578251825591602001919060010190620001e6565b506200021192915062000215565b5090565b5b8082111562000211576000815560010162000216565b6002810460018216806200024157607f821691505b602082108114156200027c577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b611e4e80620002926000396000f3fe608060405234801561001057600080fd5b50600436106101ad5760003560e060020a9004806370a08231116100fd578063b88d4fde1161009b578063e985e9c511610075578063e985e9c51461035f578063f2fde38b1461039b578063f4a0a528146103ae578063f71143ca146103c157600080fd5b8063b88d4fde14610330578063c87b56dd14610343578063d5abeb011461035657600080fd5b8063853828b6116100d7578063853828b6146102fc5780638da5cb5b1461030457806395d89b4114610315578063a22cb4651461031d57600080fd5b806370a08231146102c1578063715018a6146102d4578063772dc32f146102dc57600080fd5b806323b872dd1161016a57806355f804b31161014457806355f804b31461028a5780636352211e1461029d5780636817c76c146102b05780636c0360eb146102b957600080fd5b806323b872dd1461025157806342842e0e14610264578063484b973c1461027757600080fd5b806301ffc9a7146101b2578063049c5c49146101da57806306fdde03146101e4578063081812fc146101f9578063095ea7b31461022457806318160ddd14610237575b600080fd5b6101c56101c0366004611974565b6103ce565b60405190151581526020015b60405180910390f35b6101e261046b565b005b6101ec6104b5565b6040516101d191906119f0565b61020c610207366004611a03565b610547565b604051600160a060020a0390911681526020016101d1565b6101e2610232366004611a33565b6105a4565b60025460015403600019015b6040519081526020016101d1565b6101e261025f366004611a5d565b610691565b6101e2610272366004611a5d565b6106c9565b6101e2610285366004611a33565b610711565b6101e2610298366004611a99565b61080b565b61020c6102ab366004611a03565b610844565b61024360095481565b6101ec610856565b6102436102cf366004611b0b565b6108e4565b6101e261094c565b6102436102ea366004611b0b565b600c6020526000908152604090205481565b6101e2610985565b600054600160a060020a031661020c565b6101ec6109f9565b6101e261032b366004611b26565b610a08565b6101e261033e366004611b91565b610ab7565b6101ec610351366004611a03565b610b4e565b61024361344e81565b6101c561036d366004611c6d565b600160a060020a03918216600090815260086020908152604080832093909416825291909152205460ff1690565b6101e26103a9366004611b0b565b610bf2565b6101e26103bc366004611a03565b610ca7565b600a546101c59060ff1681565b6000600160e060020a031982167f80ac58cd0000000000000000000000000000000000000000000000000000000014806104315750600160e060020a031982167f5b5e139f00000000000000000000000000000000000000000000000000000000145b8061046557507f01ffc9a700000000000000000000000000000000000000000000000000000000600160e060020a03198316145b92915050565b600054600160a060020a031633146104a15760405160e560020a62461bcd02815260040161049890611ca0565b60405180910390fd5b600a805460ff19811660ff90911615179055565b6060600380546104c490611cd5565b80601f01602080910402602001604051908101604052809291908181526020018280546104f090611cd5565b801561053d5780601f106105125761010080835404028352916020019161053d565b820191906000526020600020905b81548152906001019060200180831161052057829003601f168201915b5050505050905090565b600061055282610cd9565b610588576040517fcf4700e400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50600090815260076020526040902054600160a060020a031690565b600054600160a060020a031633146105d15760405160e560020a62461bcd02815260040161049890611ca0565b60006105dc82610844565b905080600160a060020a031683600160a060020a0316141561062a576040517f943f7b8c00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b33600160a060020a0382161480159061064a5750610648813361036d565b155b15610681576040517fcfb3b94200000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b61068c838383610d12565b505050565b600054600160a060020a031633146106be5760405160e560020a62461bcd02815260040161049890611ca0565b61068c838383610d7b565b600054600160a060020a031633146106f65760405160e560020a62461bcd02815260040161049890611ca0565b61068c83838360405180602001604052806000815250610ab7565b600054600160a060020a0316331461073e5760405160e560020a62461bcd02815260040161049890611ca0565b61344e8161074f6001546000190190565b6107599190611d29565b11156107aa5760405160e560020a62461bcd02815260206004820152601a60248201527f4d696e74696e672065786365656473206d617820737570706c790000000000006044820152606401610498565b600081116107fd5760405160e560020a62461bcd02815260206004820152601460248201527f5175616e74697479206c657373207468616e20310000000000000000000000006044820152606401610498565b6108078282610fdd565b5050565b600054600160a060020a031633146108385760405160e560020a62461bcd02815260040161049890611ca0565b61068c600b83836118c5565b600061084f82610ff7565b5192915050565b600b805461086390611cd5565b80601f016020809104026020016040519081016040528092919081815260200182805461088f90611cd5565b80156108dc5780601f106108b1576101008083540402835291602001916108dc565b820191906000526020600020905b8154815290600101906020018083116108bf57829003601f168201915b505050505081565b6000600160a060020a038216610926576040517f8f4eb60400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50600160a060020a031660009081526006602052604090205467ffffffffffffffff1690565b600054600160a060020a031633146109795760405160e560020a62461bcd02815260040161049890611ca0565b6109836000611139565b565b600054600160a060020a031633146109b25760405160e560020a62461bcd02815260040161049890611ca0565b600054600160a060020a0316604051600160a060020a039190911690303180156108fc02916000818181858888f193505050501580156109f6573d6000803e3d6000fd5b50565b6060600480546104c490611cd5565b600160a060020a038216331415610a4b576040517fb06307db00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b336000818152600860209081526040808320600160a060020a03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b600054600160a060020a03163314610ae45760405160e560020a62461bcd02815260040161049890611ca0565b610aef848484610d7b565b600160a060020a0383163b15158015610b115750610b0f84848484611196565b155b15610b48576040517fd1a57ed600000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b50505050565b6060610b5982610cd9565b610b8f576040517fa14c4b5000000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b6000610b996112d8565b90506000610ba6846112e7565b9050815160001415610bc75760405180602001604052806000815250610bea565b8181604051602001610bda929190611d68565b6040516020818303038152906040525b949350505050565b600054600160a060020a03163314610c1f5760405160e560020a62461bcd02815260040161049890611ca0565b600160a060020a038116610c9e5760405160e560020a62461bcd02815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201527f64647265737300000000000000000000000000000000000000000000000000006064820152608401610498565b6109f681611139565b600054600160a060020a03163314610cd45760405160e560020a62461bcd02815260040161049890611ca0565b600955565b600081600111158015610ced575060015482105b801561046557505060009081526005602052604090205460e060020a900460ff161590565b600082815260076020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0387811691821790925591518593918516917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92591a4505050565b6000610d8682610ff7565b8051909150600090600160a060020a031633600160a060020a03161480610db457508151610db4903361036d565b80610dcf575033610dc484610547565b600160a060020a0316145b905080610e08576040517f59c896be00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b84600160a060020a03168260000151600160a060020a031614610e57576040517fa114810000000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b600160a060020a038416610e97576040517fea553b3400000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b610ea76000848460000151610d12565b600160a060020a038581166000908152600660209081526040808320805467ffffffffffffffff1980821667ffffffffffffffff9283166000190183161790925589861680865283862080549384169383166001908101841694909417905589865260059094528285208054600160e060020a03191690941760a060020a429092169190910217909255908601808352912054909116610f9357600154811015610f93578251600082815260056020908152604090912080549186015167ffffffffffffffff1660a060020a02600160e060020a0319909216600160a060020a03909316929092171790555b508284600160a060020a031686600160a060020a03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a45b5050505050565b6108078282604051806020016040528060008152506116a1565b60408051606081018252600080825260208201819052918101919091528180600111158015611027575060015481105b156111075760008181526005602090815260409182902082516060810184529054600160a060020a038116825260a060020a810467ffffffffffffffff169282019290925260e060020a90910460ff16151591810182905290611105578051600160a060020a03161561109b579392505050565b506000190160008181526005602090815260409182902082516060810184529054600160a060020a03811680835260a060020a820467ffffffffffffffff169383019390935260e060020a900460ff1615159281019290925215611100579392505050565b61109b565b505b6040517fdf2d9b4200000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b60008054600160a060020a0383811673ffffffffffffffffffffffffffffffffffffffff19831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6040517f150b7a02000000000000000000000000000000000000000000000000000000008152600090600160a060020a0385169063150b7a02906111e4903390899088908890600401611dbf565b602060405180830381600087803b1580156111fe57600080fd5b505af192505050801561122e575060408051601f3d908101601f1916820190925261122b91810190611dfb565b60015b6112a2573d80801561125c576040519150601f19603f3d011682016040523d82523d6000602084013e611261565b606091505b50805161129a576040517fd1a57ed600000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b805181602001fd5b600160e060020a0319167f150b7a0200000000000000000000000000000000000000000000000000000000149050949350505050565b6060600b80546104c490611cd5565b6060816001111580156112fb5750611f4182105b1561133957505060408051808201909152600181527f3100000000000000000000000000000000000000000000000000000000000000602082015290565b81611f411115801561134c5750612af982105b1561138a57505060408051808201909152600181527f3200000000000000000000000000000000000000000000000000000000000000602082015290565b81612af91115801561139d5750612ee182105b156113db57505060408051808201909152600181527f3300000000000000000000000000000000000000000000000000000000000000602082015290565b81612ee1111580156113ee575061307182105b1561142c57505060408051808201909152600181527f3400000000000000000000000000000000000000000000000000000000000000602082015290565b816130711115801561143f575061313982105b1561147d57505060408051808201909152600181527f3500000000000000000000000000000000000000000000000000000000000000602082015290565b8161313911158015611490575061319d82105b156114ce57505060408051808201909152600181527f3600000000000000000000000000000000000000000000000000000000000000602082015290565b8161319d111580156114e157506131d982105b1561151f57505060408051808201909152600181527f3700000000000000000000000000000000000000000000000000000000000000602082015290565b816131d911158015611532575061330582105b1561157057505060408051808201909152600181527f3800000000000000000000000000000000000000000000000000000000000000602082015290565b8161330511158015611583575061339b82105b156115c157505060408051808201909152600181527f3900000000000000000000000000000000000000000000000000000000000000602082015290565b8161339b111580156115d457506133ff82105b1561161257505060408051808201909152600281527f3130000000000000000000000000000000000000000000000000000000000000602082015290565b816133ff11158015611625575061343082105b1561166357505060408051808201909152600281527f3131000000000000000000000000000000000000000000000000000000000000602082015290565b505060408051808201909152600281527f3132000000000000000000000000000000000000000000000000000000000000602082015290565b919050565b61068c83838360018054600160a060020a0385166116eb576040517f2e07630000000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b83611722576040517fb562e8dd00000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b600160a060020a038516600081815260066020908152604080832080546fffffffffffffffffffffffffffffffff19811667ffffffffffffffff8083168c0181169182176801000000000000000067ffffffffffffffff1990941690921783900481168c0181169092021790915585845260059092529091208054600160e060020a03191690921760a060020a4290921691909102179055808085018380156117d45750600160a060020a0387163b15155b15611876575b6040518290600160a060020a038916906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a46118256000888480600101955088611196565b61185b576040517fd1a57ed600000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b808214156117da57826001541461187157600080fd5b6118bc565b5b604051600183019290600160a060020a038916906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a480821415611877575b50600155610fd6565b8280546118d190611cd5565b90600052602060002090601f0160209004810192826118f35760008555611939565b82601f1061190c5782800160ff19823516178555611939565b82800160010185558215611939579182015b8281111561193957823582559160200191906001019061191e565b50611945929150611949565b5090565b5b80821115611945576000815560010161194a565b600160e060020a0319811681146109f657600080fd5b60006020828403121561198657600080fd5b81356119918161195e565b9392505050565b60005b838110156119b357818101518382015260200161199b565b83811115610b485750506000910152565b600081518084526119dc816020860160208601611998565b601f01601f19169290920160200192915050565b60208152600061199160208301846119c4565b600060208284031215611a1557600080fd5b5035919050565b8035600160a060020a038116811461169c57600080fd5b60008060408385031215611a4657600080fd5b611a4f83611a1c565b946020939093013593505050565b600080600060608486031215611a7257600080fd5b611a7b84611a1c565b9250611a8960208501611a1c565b9150604084013590509250925092565b60008060208385031215611aac57600080fd5b823567ffffffffffffffff80821115611ac457600080fd5b818501915085601f830112611ad857600080fd5b813581811115611ae757600080fd5b866020828501011115611af957600080fd5b60209290920196919550909350505050565b600060208284031215611b1d57600080fd5b61199182611a1c565b60008060408385031215611b3957600080fd5b611b4283611a1c565b915060208301358015158114611b5757600080fd5b809150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60008060008060808587031215611ba757600080fd5b611bb085611a1c565b9350611bbe60208601611a1c565b925060408501359150606085013567ffffffffffffffff80821115611be257600080fd5b818701915087601f830112611bf657600080fd5b813581811115611c0857611c08611b62565b604051601f8201601f19908116603f01168101908382118183101715611c3057611c30611b62565b816040528281528a6020848701011115611c4957600080fd5b82602086016020830137600060208483010152809550505050505092959194509250565b60008060408385031215611c8057600080fd5b611c8983611a1c565b9150611c9760208401611a1c565b90509250929050565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b600281046001821680611ce957607f821691505b60208210811415611d23577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b60008219821115611d63577f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b500190565b60008351611d7a818460208801611998565b835190830190611d8e818360208801611998565b7f2e6a736f6e0000000000000000000000000000000000000000000000000000009101908152600501949350505050565b6000600160a060020a03808716835280861660208401525083604083015260806060830152611df160808301846119c4565b9695505050505050565b600060208284031215611e0d57600080fd5b81516119918161195e56fea26469706673582212204e4bbf6a901f8d1494288a74cd6dac1201c2781c78e0949f7ec7ab30ea41cab464736f6c63430008090033697066733a2f2f516d6573793473626a394e5048704a7a7052706f45747959364c4c594c51454757504e51625336367536313763362f";

    public static final String FUNC_ADDRESSCLAIMED = "addressClaimed";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BASEURI = "baseURI";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_ISSALELIVE = "isSaleLive";

    public static final String FUNC_MAXSUPPLY = "maxSupply";

    public static final String FUNC_MINTPRICE = "mintPrice";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNERMINT = "ownerMint";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SETBASEURI = "setBaseURI";

    public static final String FUNC_SETMINTPRICE = "setMintPrice";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOGGLESALESTATUS = "toggleSaleStatus";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_WITHDRAWALL = "withdrawAll";

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
    protected TeleportUniverse(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TeleportUniverse(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TeleportUniverse(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TeleportUniverse(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<BigInteger> addressClaimed(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADDRESSCLAIMED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<String> baseURI() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BASEURI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<Boolean> isSaleLive() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISSALELIVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> maxSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MAXSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> mintPrice() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MINTPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> ownerMint(String to, BigInteger quantity) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OWNERMINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(quantity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> setBaseURI(String newURI) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBASEURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(newURI)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMintPrice(BigInteger _MintPrice) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMINTPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_MintPrice)), 
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

    public RemoteFunctionCall<TransactionReceipt> toggleSaleStatus() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TOGGLESALESTATUS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> withdrawAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TeleportUniverse load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TeleportUniverse(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TeleportUniverse load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TeleportUniverse(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TeleportUniverse load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TeleportUniverse(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TeleportUniverse load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TeleportUniverse(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TeleportUniverse> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TeleportUniverse.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<TeleportUniverse> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TeleportUniverse.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TeleportUniverse> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TeleportUniverse.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TeleportUniverse> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TeleportUniverse.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
