package com.wallet.SimpleWalletAPI.payload;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record TransferToUserRequest(
        @Schema(description = "The wallet code of the recipient user")
        String toWalletCode,
        @Schema(description = "Amount to transfer")
        BigDecimal amount,
        @Schema(description = "Currency of the deposit. Supported currencies: BGN, EURO, USD. Currency is optional, default currency: BGN")
        String currency) {
}
