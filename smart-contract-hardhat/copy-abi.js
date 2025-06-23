const fs = require("fs");        
const path = require("path");
const contractsDir = path.resolve(__dirname, '../smart-contract-hardhat/artifacts/contracts');
const outDir = path.resolve(__dirname, '../../frontend-vue-tailwind/src/contracts');

fs.readdirSync(contractsDir).forEach(file => {
  if (!file.endsWith('.sol')) return;

  const contractName = file.replace('.sol', '');
  const srcPath = path.join(contractsDir, file, `${contractName}.json`);
  const destPath = path.join(outDir, `${contractName}.json`);

  if (!fs.existsSync(srcPath)) {
    console.warn(`⚠️ Skipping missing ABI for ${contractName}`);
    return;
  }

  const artifact = require(srcPath);
  const minimal = {
    abi: artifact.abi,
    bytecode: artifact.bytecode,
  };

  fs.mkdirSync(path.dirname(destPath), { recursive: true });
  fs.writeFileSync(destPath, JSON.stringify(minimal, null, 2));
  console.log(`✅ Copied ${contractName}.json`);
});
