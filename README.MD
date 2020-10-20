[![tx-api-gw Actions Status](https://github.com/abdelhamidbakhta/tx-api-gw/workflows/tx-api-gw-ci/badge.svg)](https://github.com/abdelhamidbakhta/tx-api-gw/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=abdelhamidbakhta_tx-api-gw&metric=alert_status)](https://sonarcloud.io/dashboard?id=abdelhamidbakhta_tx-api-gw)
<img src="https://sonarcloud.io/images/project_badges/sonarcloud-white.svg" alt="SonarCloud" width="100" >
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
java -jar java -jar build/libs/tx-api-gw.jar --rpc-url=http://127.0.0.1:8545
```

OR

```shell script
./gradlew bootRun --args="--rpc-url=http://127.0.0.1:8545"
```

## API

### Open API v3

Go to http://localhost:8080 to see the API definition in a web browser.

API docs specification are available at http://localhost:8080/v3/api-docs

## Docker

### Build image

#### Using plain docker file
```shell script
docker build -t $IMAGE_NAME .
```

#### Using gradle task
```shell script
./gradlew bootBuildImage --imageName=$IMAGE_NAME
```

### Run container

```shell script
docker run -p 8080:8080 -t $IMAGE_NAME
```

### Use existing image

```shell script
docker run -p 8080:8080 abdelhamidbakhtaconsensys/tx-api-gw:latest
```