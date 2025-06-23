const fs = require("fs");
const path = require("path");

const contracts = ["NFTCollectionERC1155", "NFTCollectionFactory"]; // <-- Tên contract copy

contracts.forEach((contractName) => {
  const artifactPath = path.resolve(
    __dirname,
    "artifacts/contracts",
    `${contractName}.sol`,
    `${contractName}.json`
  );

  const destDir = path.resolve(__dirname, "frontend/src/contracts");
  const destPath = path.resolve(destDir, `${contractName}.json`);

  if (!fs.existsSync(artifactPath)) {
    console.error(`ABI not found for ${contractName}`);
    return;
  }

  if (!fs.existsSync(destDir)) {
    fs.mkdirSync(destDir, { recursive: true });
  }

  const artifact = require(artifactPath);

  const minimalABI = {
    abi: artifact.abi,
    bytecode: artifact.bytecode,
  };

  fs.writeFileSync(destPath, JSON.stringify(minimalABI, null, 2));
  console.log(`✅ Copied ABI for ${contractName} to frontend.`);
});
