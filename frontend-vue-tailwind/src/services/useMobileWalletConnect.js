import WalletConnect from "@walletconnect/client";
import QRCodeModal from "@walletconnect/qrcode-modal";

export async function connectWallet() {
  const connector = new WalletConnect({
    bridge: "https://bridge.walletconnect.org", // required
  });

  // 👉 Luôn xoá session cũ để hiện QR mới
  if (connector.connected) {
    await connector.killSession();
  }

  // 👉 Tạo session và mở QR
  await connector.createSession();

  QRCodeModal.open(connector.uri, () => {
    console.log("QR Code Modal closed");
  });

  return new Promise((resolve, reject) => {
    connector.on("connect", async (error, payload) => {
      if (error) return reject(error);
      QRCodeModal.close();

      const { accounts } = payload.params[0];
      const address = accounts[0];

      const message = `Login at ${new Date().toISOString()}`;
      const signature = await connector.signPersonalMessage([
        message,
        address,
      ]);

      resolve({ address, signature, message });
    });

    connector.on("disconnect", () => {
      console.log("Disconnected");
    });
  });
}
