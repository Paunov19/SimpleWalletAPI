package com.wallet.SimpleWalletAPI.payload;

import io.swagger.v3.oas.annotations.media.Schema;

public record ConvertCurrencyRequest(
        @Schema(description = "The wallet code of the wallet to convert", required = true)
        String walletCode,
        @Schema(description = "The target currency to convert the wallet's balance to. Supported currencies: BGN, EURO, USD. Currency is optional, default currency: BGN", required = false)
        String targetCurrency
) {
}