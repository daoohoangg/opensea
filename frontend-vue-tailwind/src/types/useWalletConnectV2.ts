// useWalletConnectV2.ts
import { Web3Wallet } from '@walletconnect/web3wallet'
import { buildApprovedNamespaces } from '@walletconnect/utils'
import { createSiweMessage } from 'viem'
import { getAddress } from 'viem/utils'

const projectId = 'YOUR_PROJECT_ID'
let web3wallet

export async function initWalletConnect() {
  if (web3wallet) return web3wallet

  web3wallet = await Web3Wallet.init({
    projectId,
    metadata: {
      name: "My Vue App",
      description: "Login with WalletConnect",
      url: "https://yourdomain.com",
      icons: ["https://yourdomain.com/icon.png"]
    }
  })

  return web3wallet
}

export async function connectWallet() {
  await initWalletConnect()

  return new Promise((resolve, reject) => {
    web3wallet.once('session_proposal', async (proposal) => {
      try {
        const { id, params } = proposal
        const { requiredNamespaces, relays } = params

        // 👇 Lưu URI lại để show QR
        const pairingUri = proposal?.pairingTopic
          ? web3wallet.engine.signClient.core.pairing.getPairing(proposal.pairingTopic).uri
          : undefined

        if (!pairingUri) return reject("No pairing URI found")

        // Chờ người dùng scan QR → sau đó sẽ có 'session_settle'
        web3wallet.once('session_settle', async (settled) => {
          const session = settled
          const account = session.namespaces.eip155.accounts[0]
          const address = account.split(':')[2]

          // Tạo message để sign (SIWE hoặc đơn giản)
          const message = `Sign to login to My Vue App at ${new Date().toISOString()}`

          const signature = await web3wallet.engine.signClient.request({
            topic: session.topic,
            chainId: 'eip155:1',
            request: {
              method: 'personal_sign',
              params: [message, address],
            }
          })

          resolve({
            pairingUri,
            session,
            address,
            message,
            signature,
          })
        })

        // Phê duyệt session proposal
        const accounts = ['eip155:1:0x0000000000000000000000000000000000000000'] // placeholder, phải cập nhật sau khi get account
        const namespaces = buildApprovedNamespaces({
          proposal: params,
          supportedNamespaces: {
            eip155: {
              methods: ['personal_sign'],
              chains: ['eip155:1'],
              events: [],
              accounts,
            }
          }
        })

        await web3wallet.approveSession({
          id,
          relayProtocol: relays[0].protocol,
          namespaces,
        })

      } catch (err) {
        console.error(err)
        reject(err)
      }
    })
  })
}
