package tech.pegasys.net.txapigw;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            title =
                "This module is a standalone HTTP service enabling to submit Ethereum transactions. ",
            description =
                "This component aims to help with the development of EIP-1559 since there are no wallet providers nor web3 libraries supporting new format transactions.\n"
                    + "\n"
                    + "Don't use it in production and use only debug private keys.\n"
                    + "\n",
            contact =
                @Contact(
                    name = "Abdelhamid Bakhta",
                    url = "https://consensys.net/",
                    email = "abdelhamid.bakhta@consensys.net"),
            license =
                @License(name = "Apache-2.0", url = "http://www.apache.org/licenses/LICENSE-2.0")),
    servers = @Server(url = "http://localhost:8080"))
public class OpenAPIConfiguration {}
