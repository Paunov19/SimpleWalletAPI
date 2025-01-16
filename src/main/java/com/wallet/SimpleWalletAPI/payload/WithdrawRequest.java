package com.wallet.SimpleWalletAPI.payload;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record WithdrawRequest(
        @Schema(description = "Amount to withdraw from the primary wallet")
        BigDecimal amount,
        @Schema(description = "Currency of the deposit. Supported currencies: BGN, EURO, USD. Currency is optional, default currency: BGN")
        String currency) {
}
