require("@nomicfoundation/hardhat-toolbox");
require("dotenv").config();

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.28",
  paths: {
    sources: "./contracts",
    artifacts: "./artifacts",
  },
  networks: {
    hardhat: {},
    mumbai: {
      url: process.env.POLYGON_RPC_URL, // hoặc dùng Alchemy/Infura
      accounts: [process.env.PRIVATE_KEY] // dùng dotenv để bảo mật
    }
  }
};
