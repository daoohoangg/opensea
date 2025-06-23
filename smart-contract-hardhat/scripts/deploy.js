const { ethers } = require("hardhat");

async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contract with address:", deployer.address);
  const balance = await deployer.provider.getBalance(deployer.address);
  console.log("Deployer balance:", ethers.formatEther(balance), "MATIC");

  const ContractFactory = await ethers.getContractFactory("NFTCollectionERC1155");
  const contract = await ContractFactory.deploy("My Collection", "MYC"); // truyen tham số constructor tu frontend

  await contract.waitForDeployment(); // Ethers v6 - chờ contract được deploy

  const deployedAddress = await contract.getAddress();
  console.log("✅ Contract deployed to:", deployedAddress);
}

main().catch((error) => {
  console.error("Error deploying contract:", error);
  process.exitCode = 1;
});
