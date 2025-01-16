package com.wallet.SimpleWalletAPI.payload;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record TransferBetweenOwnRequest(
        @Schema(description = "The wallet code from which the funds are transferred")
        String fromWalletCode,
        @Schema(description = "The wallet code to which the funds are transferred")
        String toWalletCode,
        @Schema(description = "Amount to transfer between wallets")
        BigDecimal amount,
        @Schema(description = "Currency of the deposit. Supported currencies: BGN, EURO, USD. Currency is optional, default currency: BGN")
        String currency) {
}
