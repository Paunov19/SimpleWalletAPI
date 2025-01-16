package com.wallet.SimpleWalletAPI.payload;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateWalletRequest(
        @Schema(description = "Name of the wallet to be created")
        String walletName) {
}
