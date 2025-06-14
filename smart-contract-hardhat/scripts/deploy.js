const hre = require("hardhat");

async function main() {
  // const Contract = await hre.ethers.getContractFactory("MyContract");
  // const contract = await Contract.deploy();

  const Contract = await hre.ethers.getContractFactory("MyNFTCollection");
  const contract = await Contract.deploy();

  await contract.deployed();

  console.log(`Contract deployed to: ${contract.target}`);
  console.log(`Contract deployed to: ${contract.address}`);

}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});