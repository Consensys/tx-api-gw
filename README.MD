# Transaction API Gateway

## Description

This module is a standalone HTTP service enabling to submit Ethereum transactions.
Supported transactions are the following:
- legacy Ethereum transactions
- [EIP-1559](https://eips.ethereum.org/EIPS/eip-1559) Ethereum transactions

This component aims to help with the development of [EIP-1559](https://eips.ethereum.org/EIPS/eip-1559) since there are no wallet providers nor `web3` libraries supporting new format transactions.

`Don't use it in production and use only debug private keys.`

The HTTP service accepts non signed Ethereum transactions and submit them to a configured Ethereum client.
The process is the following:
- receive Ethereum transactions parameter through the REST API
- encode transactions according to RLP definition
- sign transactions
- submit transactions to the Ethereum client using `eth_sendRawTransaction` RPC endpoint

## Installation

```shell script
./gradlew assemble
```

## Usage

```shell script
./gradlew bootRun
```

## API

### Open API v3

Go to http://localhost:8080 to see the API definition in a web browser.

API docs specification are available at http://localhost:8080/v3/api-docs
